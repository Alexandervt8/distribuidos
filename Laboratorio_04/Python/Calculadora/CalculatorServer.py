import socket
import sys
from Calculator import add, sub, mul, divide

PORT = 8080
BUFFER_SIZE = 1024

def handle_client(conn):
    try:
        buffer = conn.recv(BUFFER_SIZE).decode()
        num1, num2 = map(int, buffer.split())

        sum_result = add(num1, num2)
        diff_result = sub(num1, num2)
        product_result = mul(num1, num2)
        division_result = divide(num1, num2)

        response = (f"Suma: {sum_result}\n"
                    f"Resta: {diff_result}\n"
                    f"Multiplicación: {product_result}\n"
                    f"División: {division_result}\n")
        conn.sendall(response.encode())
    except Exception as e:
        print(f"Error al manejar el cliente: {e}")
    finally:
        conn.close()

def main():
    server_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    server_socket.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)

    server_socket.bind(('0.0.0.0', PORT))
    server_socket.listen(3)

    print("El servidor de la calculadora está listo.")

    while True:
        conn, addr = server_socket.accept()
        handle_client(conn)

if __name__ == "__main__":
    main()
