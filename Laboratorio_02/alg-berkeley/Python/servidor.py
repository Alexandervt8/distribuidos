from dateutil import parser
import threading
import datetime
import socket
import time

diferencias_tiempo = {}

def recibirTiempoEsclavoYCalcularAjuste(connector, address):
    while True:
        tiempo_recibido = connector.recv(1024).decode()
        tiempo_esclavo = parser.parse(tiempo_recibido)
        diferencia_tiempo = tiempo_maestro - tiempo_esclavo
        diferencias_tiempo[address] = diferencia_tiempo
        print("Tiempo del esclavo " + address + ": " + tiempo_recibido)
        print("Diferencia de tiempo del esclavo " + address + ": " +
              str(diferencia_tiempo))
        ajuste = datetime.datetime.now() + diferencia_tiempo
        print("Ajuste para el esclavo " + address + ": " + str(ajuste))
        connector.send(str(ajuste).encode())
        time.sleep(5)

def sincronizarRelojes():
    while True:
        if len(diferencias_tiempo) > 0:
            ajuste_promedio = sum(
                diferencias_tiempo.values(),
                datetime.timedelta()) / len(diferencias_tiempo)
            print("Ajuste promedio: " + str(ajuste_promedio))
        else:
            print("No hay datos de esclavos. Sincronización no aplicable.")
        print("\n\n")
        time.sleep(5)

def iniciarServidorReloj(port=8080):
    servidor = socket.socket()
    servidor.setsockopt(socket.SOL_SOCKET, socket.SO_REUSEADDR, 1)
    servidor.bind(('', port))
    servidor.listen(10)
    print("Servidor de reloj iniciado...\n")

    while len(diferencias_tiempo) < 3:
        cliente_socket, addr = servidor.accept()
        direccion_cliente = str(addr[0]) + ":" + str(addr[1])
        print("Cliente " + direccion_cliente + " conectado exitosamente")
        hilo_cliente = threading.Thread(
            target=recibirTiempoEsclavoYCalcularAjuste,
            args=(cliente_socket, direccion_cliente))
        hilo_cliente.start()

    hilo_sincronizacion = threading.Thread(target=sincronizarRelojes)
    hilo_sincronizacion.start()

if __name__ == '__main__':
    tiempo_maestro = datetime.datetime.now()
    print("Tiempo maestro: " + str(tiempo_maestro))

    iniciarServidorReloj(port=8080)
