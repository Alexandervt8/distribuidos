import Pyro4
from tarjeta import TarjetaDeCredito

# Configuración de Pyro
Pyro4.config.SERIALIZER = "serpent"


# Clase del servidor
@Pyro4.expose
class ServidorTarjetas:

    def obtener_tarjeta(self):
        return TarjetaDeCredito()

daemon = Pyro4.Daemon()
uri = daemon.register(ServidorTarjetas)
print("URI del servidor:", uri)
daemon.requestLoop()
