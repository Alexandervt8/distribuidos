package Lab_04;

import java.rmi.Naming;

public class ServerSide {
    public static void main(String[] args) {
        try {
            CreditCardWallet wallet = new CreditCardWallet();
            Naming.rebind("WALLET", wallet);
            System.out.println("Server ready");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}