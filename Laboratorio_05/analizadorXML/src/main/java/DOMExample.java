import javax.xml.parsers.*;
import org.w3c.dom.*;
import java.io.*;

public class DOMExample {
    public static void main(String[] args) {
        try {
            // Crear una instancia de DocumentBuilderFactory
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            // Crear una instancia de DocumentBuilder
            DocumentBuilder builder = factory.newDocumentBuilder();
            // Parsear el archivo XML
            Document document = builder.parse(new File("Ej01.xml"));

            // Obtener el elemento raíz
            Element root = document.getDocumentElement();
            System.out.println("ejecutando DOM - Elemento raíz: " + root.getNodeName());

            // Obtener una lista de nodos de ORGANISMO
            NodeList nodeList = root.getElementsByTagName("ORGANISMO");

            System.out.println("Número de elementos 'ORGANISMO': " + nodeList.getLength());

            // Iterar sobre la lista de nodos de ORGANISMO
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                System.out.println("Tipo de nodo: " + node.getNodeType());
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    // Obtener y mostrar los datos de ORGANISMO
                    System.out.println("Organismo: " + element.getTextContent());
                    // Puedes agregar más líneas para mostrar otros datos aquí
                    System.out.println("-----------------------");
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



