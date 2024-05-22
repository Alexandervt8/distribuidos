package Laboratorio_04.java.tarjeta;



import java.rmi.server.UnicastRemoteObject;
import java.util.Date;
import java.rmi.RemoteException;

public class CreditCardImpl extends UnicastRemoteObject implements CreditCard {
    private String cardNumber;
    private String cardHolderName;
    private Date expirationDate;

    public CreditCardImpl(String cardNumber, String cardHolderName, Date expirationDate) throws RemoteException {
        super();
        this.cardNumber = cardNumber;
        this.cardHolderName = cardHolderName;
        this.expirationDate = expirationDate;
    }

    @Override
    public String getCardNumber() throws RemoteException {
        return this.cardNumber;
    }

    @Override
    public String getCardHolderName() throws RemoteException {
        return this.cardHolderName;
    }

    @Override
    public Date getExpirationDate() throws RemoteException {
        return this.expirationDate;
    }
}
