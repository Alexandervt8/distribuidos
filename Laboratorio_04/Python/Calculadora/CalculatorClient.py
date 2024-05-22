import socket
import sys

PORT = 8080
BUFFER_SIZE = 1024

def main():
    if len(sys.argv) != 3:
        print(f"Uso: {sys.argv[0]} <num1> <num2>")
        return 1

    try:
        num1 = int(sys.argv[1])
        num2 = int(sys.argv[2])
    except ValueError:
        print("Los argumentos deben ser números enteros.")
        return 1

    server_address = ('127.0.0.1', PORT)
    buffer = f"{num1} {num2}"

    try:
        # Crear un socket TCP/IP
        sock = socket.socket(socket.AF_INET, socket.SOCK_STREAM)

        # Conectar el socket al puerto donde el servidor está escuchando
        sock.connect(server_address)

        # Enviar los datos
        sock.sendall(buffer.encode())

        # Esperar y recibir la respuesta
        data = sock.recv(BUFFER_SIZE)
        print(data.decode())

    except socket.error as e:
        print(f"Error de socket: {e}")
        return -1

    finally:
        sock.close()

    return 0

if __name__ == "__main__":
    main()
