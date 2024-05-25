import org.xml.sax.*;
import org.xml.sax.helpers.*;
import javax.xml.parsers.SAXParserFactory; // Agrega esta línea
import javax.xml.parsers.SAXParser; // Agrega esta línea
import org.xml.sax.SAXException; // Agrega esta línea

import java.io.*;

public class SAXParserExample {
    public static void main(String[] args) {
        try {
            // Crear una instancia de SAXParserFactory
            SAXParserFactory factory = SAXParserFactory.newInstance();
            // Crear una instancia de SAXParser
            SAXParser saxParser = factory.newSAXParser();

            // Definir el manejador de eventos
            DefaultHandler handler = new DefaultHandler() {
                boolean isTitle = false;

                @Override
                public void startElement(String uri, String localName, String qName, Attributes attributes) throws SAXException {
                    if (qName.equalsIgnoreCase("TITULO")) {
                        isTitle = true;
                    }
                }

                @Override
                public void endElement(String uri, String localName, String qName) throws SAXException {
                    if (qName.equalsIgnoreCase("TITULO")) {
                        isTitle = false;
                    }
                }

                @Override
                public void characters(char[] ch, int start, int length) throws SAXException {
                    if (isTitle) {
                        System.out.println("Title: " + new String(ch, start, length));
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
