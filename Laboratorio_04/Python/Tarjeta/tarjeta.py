import Pyro4


@Pyro4.expose
class TarjetaDeCredito:

    def obtener_informacion(self, numero, nombre):
        # Aquí podrías realizar la lógica para verificar el acceso
        if numero == "1234567890123456" and nombre == "John Doe":
            return "Acceso concedido"
        else:
            return "Acceso denegado"
