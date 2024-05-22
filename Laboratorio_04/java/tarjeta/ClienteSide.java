package Lab_04;

import java.rmi.Naming;
import java.util.ArrayList;

public class ClienteSide {
    public static void main(String[] args) {
        try {
            CreditCardWalletInterface wallet = (CreditCardWalletInterface) Naming.lookup("WALLET");
            ArrayList<CreditCard> creditCards = wallet.getAllCreditCards();
            for (CreditCard card : creditCards) {
                System.out.println("Número de Tarjeta: " + card.getCardNumber());
                System.out.println("Titular: " + card.getCardHolderName());
                System.out.println("Fecha de Expiración: " + card.getExpirationDate());
                System.out.println("----------------------------------------");
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}