public class Card {
    private int value; //Numeric Value of the card 
    private String suit; // Suit of the card
    private String rank; // Rank of Card
    
    public Card(int value, String suit, String rank){
        this.value = value;
        this.suit = suit;
        this.rank = rank;

    }
    //Accessor (getter) for value
    public int getValue(){

        return value;
    }

    //Mutator for value
    public void setValue(int value){
        this.value = value;
    }
     // Accessor (getter) for suit
     public String getSuit() {
        return suit;
    }
    // Mutator (setter) for suit
    public void setSuit(String suit) {
        this.suit = suit;
    }
    // Accessor (getter) for rank
    public String getRank() {
        return rank;
    }
    public void setRank(String rank) {
        this.rank = rank;
    }

    // toString method to represent the card in a readable format @Override
    public String toString() {
        return rank + " of " + suit;
    }
    public boolean isFaceCard() {
        return rank.equals("Jack") || rank.equals("Queen") || rank.equals("King");
    }

    // Additional method (if needed): Checks if the card is an Ace
    public boolean isAce() {
        return rank.equals("Ace");
    }
}
