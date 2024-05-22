import java.rmi.Naming;
import java.rmi.registry.LocateRegistry;
public class CalculatorServer {
    public CalculatorServer() {
        try {
// Iniciar el registro RMI en el puerto 1099
            LocateRegistry.createRegistry(1099);
// Crear una instancia del objeto que se va a registrar
            Calculator c = new CalculatorImpl();
 // Registro RMI
Naming.rebind("rmi://localhost:1099/CalculatorService", c);
            System.out.println("CalculatorServer está listo.");
        } catch (Exception e) {
            System.out.println("Trouble: " + e);
            e.printStackTrace();
        }
    }
    public static void main(String[] args) {
        new CalculatorServer();
    }
}