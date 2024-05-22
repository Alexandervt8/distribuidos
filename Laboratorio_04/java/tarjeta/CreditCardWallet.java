
import java.util.Date;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class CreditCardWallet extends UnicastRemoteObject implements CreditCardWalletInterface {
    private ArrayList<CreditCard> creditCards;

    public CreditCardWallet() throws Exception {
        super();
        this.creditCards = new ArrayList<>();
        this.creditCards.add(new CreditCardImpl("1234-5678-9101-1121", "John Doe", new Date()));
        this.creditCards.add(new CreditCardImpl("1111-2222-3333-4444", "Jane Smith", new Date()));
    }

    @Override
    public ArrayList<CreditCard> getAllCreditCards() throws Exception {
        return this.creditCards;
    }

    @Override
    public CreditCard getCreditCard(String cardNumber) throws Exception {
        // Buscar y devolver la tarjeta de crédito según el número de tarjeta
        for (CreditCard card : this.creditCards) {
            if (card.getCardNumber().equals(cardNumber)) {
                return card;
            }
        }
        return null;
    }
}
