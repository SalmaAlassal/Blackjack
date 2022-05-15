/*        © 2021 Salma Ayman     */

package blackjack;

import java.util.Random;

public class Game {

    // Generate 52-Card Deck

    // suit = 0 for Clubs, suit = 1 for Diamonds , suit = 2 for Hearts, suit = 3 for Spades

    Card[] deckOfCards = new Card[52];

    public void generateCards() {
        int suit = 0, value, rank = 0;
        for (int i = 0; i < 52; i++) {

            if (rank > 9) {
                value = 10;
            } else {
                value = rank + 1;
            }

            deckOfCards[i] = new Card(suit, rank, value);

            // To Print Cards on console
            /*
             * if (suit == 0) {
             * System.out.print("Club");
             * } else if (suit == 1) {
             * System.out.print(" Diamonds");
             * } else if (suit == 2) {
             * System.out.print("Hearts");
             * } else if(suit==3) {
             * System.out.print(" Spades");
             * }
             * System.out.println("  value: " + value + "  rank: " + rank);
             */

            rank++;

            if (rank > 12) {
                rank = 0;
                suit++;
            }

        }
    }

    // Draw randomCard from the card deck array
    Card copiedCard;

    public Card randomCard() {

        Random rand = new Random();
        int randomChoise;

        while (true) {
            randomChoise = rand.nextInt(52); // [0,51]

            // Make that object equals null in the card deck array
            if (deckOfCards[randomChoise] != null) {

                copiedCard = new Card(deckOfCards[randomChoise]);
                deckOfCards[randomChoise] = null;
                break;
            }

        }

        return copiedCard;
    
    }

    // Set the playerInfo & 2 intial cards

    // Array of 4 players (the dealer is at index [3])
    Player player[] = new Player[4];

    /*
     * A function that takes names from the user and draw 2
     * random cards for each player at the beginning of the game.
     */
    public void playerInfo(int i, String name) {

        player[i] = new Player(name);
        for (player[i].cardNumber = 0; player[i].cardNumber < 2; player[i].cardNumber++) {

            Card randomCard = randomCard();
            player[i].setPlayerCards(player[i].cardNumber, randomCard);

            updateMaximumScore(i, player[i].cardNumber);

            BlackJack.gui.updatePlayerHand(randomCard, i);

            // To print the first 2 cards on the console
            /*
            if (player[i].PlayerCards[player[i].cardNumber].getSuit() == 0) {

                System.out.print(player[i].getName() + " Card num : " + player[i].cardNumber + " - Club");

            } else if (player[i].PlayerCards[player[i].cardNumber].getSuit() == 1) {
                System.out.print(player[i].getName() + " Card num : " + player[i].cardNumber + " - Diamond");
            } else if (player[i].PlayerCards[player[i].cardNumber].getSuit() == 2) {
                System.out.print(player[i].getName() + " Card num : " + player[i].cardNumber + " - Heart");
            } else if (player[i].PlayerCards[player[i].cardNumber].getSuit() == 3) {
                System.out.print(player[i].getName() + " Card num : " + player[i].cardNumber + " - Spade");
            }
            System.out.println(" value: " + player[i].PlayerCards[player[i].cardNumber].getValue()
                    + "  rank: " + player[i].PlayerCards[player[i].cardNumber].getRank());
            */
        }
        

    }

    // update Maximum Score of all players after each card
    int valid_HighScore = 0; // keeps track of the existing VALID high score of all players (<= 21)
    int whoGotHighscore;

    public void updateMaximumScore(int i, int cardNumber) {
        player[i].setScore(player[i].PlayerCards[cardNumber].getValue());
        if (player[i].getScore() <= 21 && valid_HighScore < player[i].getScore()) {
            {
                valid_HighScore = player[i].getScore();
                whoGotHighscore = i;
            }
        }

    }

    // Calculate game result
    boolean isTie;

    public void gameResult() {

        valid_HighScore = 0;
        for (int i = 0; i < 4; i++) {

            if (player[i].getScore() <= 21 && valid_HighScore < player[i].getScore()) {

                valid_HighScore = player[i].getScore();
                whoGotHighscore = i;
                isTie = false;
            } else if (valid_HighScore == player[i].getScore() && whoGotHighscore != i) {
                isTie = true;
            }

        }

        if (isTie == true) {
            System.out.println("PUSH");
        } else if (valid_HighScore == 0) {
            System.out.println("Bust");
        } else {
            if (valid_HighScore == 21) {
                System.out.println("player " + (whoGotHighscore + 1) + " wins." + " He got BlackJack");
            } else {
                System.out.println("player " + (whoGotHighscore + 1) + " wins." + " His Score is: " + valid_HighScore);
            }
        }
    }
}