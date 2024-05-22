import Pyro4
from tarjeta import TarjetaDeCredito

Pyro4.config.SERIALIZER = "serpent"

uri = "PYRO:obj_e3d9ec092ba545f395d9588448982b0f@localhost:40049"

with Pyro4.Proxy(uri) as servidor:
   tarjeta = servidor.obtener_tarjeta()
   print("Información de la tarjeta:")
   print(tarjeta.obtener_informacion())
