import org.w3c.dom.*;
import javax.xml.parsers.*;
import java.io.*;
import org.xml.sax.SAXException; // Agrega esta línea


public class DOMParserExample {
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
            System.out.println("Root element: " + root.getNodeName());

            // Obtener y mostrar los elementos del XML
            NodeList nodeList = document.getElementsByTagName("TITULO");
            for (int i = 0; i < nodeList.getLength(); i++) {
                Node node = nodeList.item(i);
                if (node.getNodeType() == Node.ELEMENT_NODE) {
                    Element element = (Element) node;
                    System.out.println("Title: " + element.getTextContent());
                }
            }

            // Similarmente, puedes acceder a otros elementos de la misma forma

        } catch (ParserConfigurationException | SAXException | IOException e) {
            e.printStackTrace();
        }
    }
}
