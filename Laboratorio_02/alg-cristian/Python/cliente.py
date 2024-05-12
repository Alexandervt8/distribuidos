import zmq
import datetime
import time
import random

context = zmq.Context()

print("Conectándose al proceso S…")
socket = context.socket(zmq.REQ)
socket.connect("tcp://localhost:4595")

tiempo_peticion_cliente = time.time()
print("Enviando solicitud de tiempo al servidor...")
socket.send(b"Solicitud de tiempo")

tiempo_respuesta_servidor = float(socket.recv().decode('utf-8'))
tiempo_recepcion_cliente = time.time()

T0 = tiempo_peticion_cliente
T1 = tiempo_recepcion_cliente
Cs = tiempo_respuesta_servidor
Tcliente = Cs + (T1 - T0) / 2

print(
    "Tiempo de la solicitud del cliente: ",
    datetime.datetime.fromtimestamp(tiempo_peticion_cliente).strftime(
        '%Y-%m-%d %H:%M:%S'))
print(
    "Tiempo de recepción del mensaje del servidor: ",
    datetime.datetime.fromtimestamp(tiempo_recepcion_cliente).strftime(
        '%Y-%m-%d %H:%M:%S'))
print("Tiempo estimado del cliente: ",
      datetime.datetime.fromtimestamp(Tcliente).strftime('%Y-%m-%d %H:%M:%S'))

if Tcliente > T1:
    print("El reloj del cliente se actualizará.")
    tiempo_cliente_actualizado = Tcliente
else:
    print("El reloj del cliente se detendrá a esperar al servidor.")
    tiempo_detenido_cliente = T1 + (T1 - Tcliente)
    tiempo_cliente_actualizado = tiempo_detenido_cliente

print(
    "Tiempo del cliente después de la sincronización: ",
    datetime.datetime.fromtimestamp(tiempo_cliente_actualizado).strftime(
        '%Y-%m-%d %H:%M:%S'))
