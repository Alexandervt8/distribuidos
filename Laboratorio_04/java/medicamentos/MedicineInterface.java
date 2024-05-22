//package Medicinas;
import java.rmi.Remote;

public interface MedicineInterface extends Remote {
    Medicine getMedicine(int amount) throws Exception;
    int getStock() throws Exception;
    String print() throws Exception;
}
