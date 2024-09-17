import java.util.ArrayList;
import java.util.Random;
import java.util.Scanner;

public class BlackJack {
    // Using ArrayList for deck management
    private static ArrayList<Card> deck = new ArrayList<>();
    private static Random random = new Random(); // Random object for shuffling

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        boolean turn = true;

        while (turn) {
            initializeDeck(); // Create and shuffle the deck
            shuffleDeck();

            int playerTotal = dealInitialPlayerCards();
            int dealerTotal = dealInitialDealerCards();

            // Player's turn
            playerTotal = playerTurn(scanner, playerTotal);
            if (playerTotal > 21) {
                System.out.println("You busted! Dealer wins.");
            } else {
                // Dealer's turn
                dealerTotal = dealerTurn(dealerTotal);
                // Determine winner
                determineWinner(playerTotal, dealerTotal);
            }

            // Ask player if they want to play again
            System.out.println("Would you like to play another hand? (yes or no)");
            String playerDecision = scanner.nextLine().toLowerCase();
            if (playerDecision.equals("no")) {
                turn = false;
            }
        }
        System.out.println("Thanks for playing!");
    }

    // Initialize the deck using an ArrayList
    private static void initializeDeck() {
        deck.clear(); // Clear the deck if there are cards from previous games
        String[] suits = {"Hearts", "Diamonds", "Clubs", "Spades"};
        String[] ranks = {"2", "3", "4", "5", "6", "7", "8", "9", "10", "Jack", "Queen", "King", "Ace"};

        // Create a full deck of cards
        for (String suit : suits) {
            for (int i = 0; i < ranks.length; i++) {
                int value = (i < 9) ? i + 2 : 10; // Set value: 2-10 for number cards, 10 for face cards
                deck.add(new Card(value, suit, ranks[i]));
            }
        }
    }

    // Shuffle the deck
    private static void shuffleDeck() {
        for (int i = 0; i < deck.size(); i++) {
            int randomIndex = random.nextInt(deck.size());
            Card temp = deck.get(i);
            deck.set(i, deck.get(randomIndex));
            deck.set(randomIndex, temp);
        }
    }

    // Deal a card from the deck
    private static Card dealCard() {
        if (deck.isEmpty()) { // Check if the deck is empty
            System.out.println("Deck is empty, reshuffling...");
            initializeDeck();
            shuffleDeck();
        }
        return deck.remove(0); // Remove and return the top card
    }

    // Deal initial player cards and return their total value
    private static int dealInitialPlayerCards() {
        Card card1 = dealCard();
        Card card2 = dealCard();
        System.out.println("Your cards: " + card1 + " and " + card2);
        return card1.getValue() + card2.getValue();
    }

    // Deal initial dealer card and return its value
    private static int dealInitialDealerCards() {
        Card card1 = dealCard();
        System.out.println("Dealer's card: " + card1);
        return card1.getValue();
    }

    // Player's turn logic
    private static int playerTurn(Scanner scanner, int playerTotal) {
        while (true) {
            System.out.println("Your total is " + playerTotal + ". Do you want to hit or stand?");
            String action = scanner.nextLine().toLowerCase();

            if (action.equals("hit")) {
                Card newCard = dealCard();
                playerTotal += newCard.getValue();
                System.out.println("You drew a " + newCard);
                if (playerTotal > 21) {
                    break; // Player busts, exit loop
                }
            } else if (action.equals("stand")) {
                break; // Player stands, exit loop
            } else {
                System.out.println("Invalid action. Please type 'hit' or 'stand'.");
            }
        }
        return playerTotal;
    }

    // Dealer's turn logic. 
    private static int dealerTurn(int dealerTotal) {
        while (dealerTotal < 17) { //If is less than 17, hits
            Card newCard = dealCard();
            dealerTotal += newCard.getValue();
            System.out.println("Dealer draws " + newCard);
        }
        System.out.println("Dealer's total is " + dealerTotal);
        return dealerTotal;
    }

    // Determine the winner of the game
    private static void determineWinner(int playerTotal, int dealerTotal) {
        if (dealerTotal > 21 || playerTotal > dealerTotal) {
            System.out.println("You win!");
        } else if (dealerTotal == playerTotal) {
            System.out.println("It's a tie!");
        } else {
            System.out.println("Dealer wins!");
        }
    }
}
