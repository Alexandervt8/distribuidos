
package serv;

import javax.xml.ws.Endpoint;
import javax.xml.*;
import serv.SOAPlmpl;
public class PublishServices {
 public static void main(String[] args) {
	 Endpoint.publish("http://localhost:8080/Soap_ejercicio_docente/WS/Products", new SOAPlmpl());
 }
}
