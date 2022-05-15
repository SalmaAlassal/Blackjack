/*        © 2021 Salma Ayman     */

package blackjack;

public class Player {

    private String Name;
    private int Score = 0;

    // Array of 11 Card objects
    Card[] PlayerCards = new Card[11];
    int cardNumber = 0;

    public Player(String Name) {
        this.Name = Name;

    }

    public String getName() {
        return Name;
    }

    public void setPlayerCards(int i, Card Card) {

        this.PlayerCards[i] = new Card(Card);
    }

   // The values of all the cards in the player’s hand at the beginning and after each hit
    public void setScore(int Score) {
        this.Score += Score;
    }

    public int getScore() {
        return Score;
    }

}