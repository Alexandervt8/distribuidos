
package serv;

import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import serv.SOAPl;

public class ClienteWebService {
    public static void main(String[] args) {
        // Crear una instancia de Service para acceder al servicio web
        QName qname = new QName("http://soap.rosamarfil.es/", "SOAPlmplService");
        Service service = Service.create(qname);

        // Obtener una instancia del servicio web
        SOAPl cliente = service.getPort(SOAPl.class);

        // Invocar métodos del servicio web
        System.out.println("Productos disponibles:");
        cliente.getProducts().forEach(System.out::println);
    }
}

