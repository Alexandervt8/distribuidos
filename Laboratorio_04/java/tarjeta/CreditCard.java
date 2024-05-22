package Laboratorio_04.java.tarjeta;


import java.rmi.Remote;
import java.util.Date;

public interface CreditCard extends Remote {
    public String getCardNumber() throws Exception;

    public String getCardHolderName() throws Exception;

    public Date getExpirationDate() throws Exception;
}
