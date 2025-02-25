// CardCollection.java
import java.util.*;

public class CardCollection {
    private HashMap<String, List<String>> cardMap;

    // Constructor to initialize the HashMap
    public CardCollection() {
        cardMap = new HashMap<>();
        initializeCards();
    }

    // Method to initialize the cards
    private void initializeCards() {
        String[] symbols = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] values = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};
        
        for (String symbol : symbols) {
            List<String> cards = new ArrayList<>();
            for (String value : values) {
                cards.add(value + " of " + symbol);
            }
            cardMap.put(symbol, cards);
        }
    }

    // Method to find all cards of a given symbol
    public void findCardsBySymbol(String symbol) {
        if (cardMap.containsKey(symbol)) {
            List<String> cards = cardMap.get(symbol);
            System.out.println("Cards of symbol \"" + symbol + "\":");
            for (String card : cards) {
                System.out.println(card);
            }
        } else {
            System.out.println("Invalid symbol! Available symbols are: " + cardMap.keySet());
        }
    }

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        CardCollection cardCollection = new CardCollection();
        
        System.out.println("Welcome to the Card Collection System!");
        System.out.println("Enter a symbol to find all the cards of that symbol (e.g., Hearts, Diamonds, Clubs, Spades):");
        String symbol = scanner.nextLine();
        
        cardCollection.findCardsBySymbol(symbol);

        scanner.close();
    }
}
