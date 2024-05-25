import org.xml.sax.*;
import org.xml.sax.helpers.*;
import java.io.*;
import javax.xml.parsers.SAXParser;
import javax.xml.parsers.SAXParserFactory;
import org.xml.sax.Attributes;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;


public class SAXExample {
    public static void main(String[] args) {
        try {
            System.out.println("ejecutando SAX ");
            // Crear una instancia de SAXParserFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // Crear una instancia de SAXParser
            SAXParser saxParser = factory.newSAXParser();

            // Definir el manejador de eventos
            DefaultHandler handler = new DefaultHandler() {
                boolean inOrganismo = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("organismo")) {
                        inOrganismo = true;
                        System.out.println("Organismo:");
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (inOrganismo) {
                        System.out.println(new String(ch, start, length));
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("organismo")) {
                        inOrganismo = false;
                        System.out.println("-----------------------");
                    }
                }
            };

            // Parsear el archivo XML
            saxParser.parse(new File("Ej01.xml"), handler);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
