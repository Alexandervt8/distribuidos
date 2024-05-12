from dateutil import parser
import socket
import time
import datetime


def enviarTiempoYRecibirAjuste(server_host, server_port):
    cliente_socket = socket.socket(socket.AF_INET, socket.SOCK_STREAM)
    cliente_socket.connect((server_host, server_port))

    while True:
        tiempo_esclavo = datetime.datetime.now()
        cliente_socket.send(str(tiempo_esclavo).encode())
        ajuste = cliente_socket.recv(1024).decode()
        print("Ajuste recibido del servidor: " + ajuste)
        ajuste_delta = parser.parse(ajuste) - datetime.datetime.now()
        nuevo_tiempo = tiempo_esclavo + ajuste_delta
        print("Nuevo tiempo del esclavo: " + str(nuevo_tiempo))
        time.sleep(5)


if __name__ == "__main__":
    SERVER_HOST = "localhost"
    SERVER_PORT = 8080
    enviarTiempoYRecibirAjuste(SERVER_HOST, SERVER_PORT)
