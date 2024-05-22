package Lab_04;

import java.rmi.Remote;
import java.util.ArrayList;

public interface CreditCardWalletInterface extends Remote {
    public ArrayList<CreditCard> getAllCreditCards() throws Exception;
    public CreditCard getCreditCard(String cardNumber) throws Exception;
}