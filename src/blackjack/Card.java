/*        © 12.2021 Salma Ayman     */
package blackjack;

public class Card {

    private int suit;
    private int rank;
    private int value;

    // Parameterized constructor that sets all the attributes
    public Card(int suit, int rank, int value) {
        this.suit = suit;
        this.rank = rank;
        this.value = value;
    }

    // Copy constructor
    public Card(Card copiedCard) {
        this.suit = copiedCard.suit;
        this.rank = copiedCard.rank;
        this.value = copiedCard.value;
    }

    // Getters for the attributes
    public int getSuit() {
        return suit;
    }

    public int getRank() {
        return rank;
    }

    public int getValue() {
        return value;
    }

}
