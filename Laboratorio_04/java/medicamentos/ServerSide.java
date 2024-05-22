//package Medicinas;
import java.rmi.*;

public class ServerSide {
    public static void main(String[] args) throws Exception {
        // Crear una instancia del objeto Stock
        Stock pharmacy = new Stock();

        // Agregar medicamentos al inventario
        pharmacy.addMedicine("Paracetamol", 3.2f, 10);
        pharmacy.addMedicine("Mejoral", 2.0f, 20);
        pharmacy.addMedicine("Amoxilina", 1.0f, 30);
        pharmacy.addMedicine("Aspirina", 5.0f, 40);

        // Enlazar el objeto Stock al registro RMI en el puerto 33331
        Naming.rebind("PHARMACY", pharmacy);

        // Imprimir mensaje de que el servidor está listo
        System.out.println("Server ready");
    }
}
