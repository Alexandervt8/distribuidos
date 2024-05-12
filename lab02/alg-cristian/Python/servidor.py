import zmq
import datetime
import random
import time

context = zmq.Context()
socket = context.socket(zmq.REP)
socket.bind("tcp://*:4595")

while True:
    print("Esperando solicitud de tiempo...")
    solicitudP = socket.recv()
    print(solicitudP.decode('utf-8'))
    print("Solicitud de tiempo recibida satisfactoriamente")

    tiempoCs = time.time()
    print(
        "Enviando respuesta a P\n\t tiempo del servidor:",
        datetime.datetime.fromtimestamp(tiempoCs).strftime(
            '%Y-%m-%d %H:%M:%S'))

    print("Simulando latencia de envío")
    for _ in range(5):
        print(".")
        time.sleep(random.uniform(0, 1))  

    socket.send(str(tiempoCs).encode('utf-8'))
    print("Tiempo enviado satisfactoriamente")
    print("----Esperando siguiente solicitud----\n\n")
