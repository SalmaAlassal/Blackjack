/*        © 2021 Salma Ayman     */
package blackjack;

import java.util.Scanner;

public class BlackJack {

    static GUI gui = new GUI();

// Set the playerInfo & 2 intial cards
    public void setPlayersInfo(int i, Game obj, String name) {

        obj.playerInfo(i, name);

    }


    // The first 3 players option (hit or stand)
    public void playersOption(Game obj) {

        Scanner input = new Scanner(System.in);

        for (int p = 0; p < 3; p++) {

            int option;
            do {
                System.out.println(obj.player[p].getName() + " : 1-HIT OR 2-STAND");
                option = input.nextInt();

                if (option == 1) {

                    Card randomCard = obj.randomCard();

                    obj.player[p].setPlayerCards(obj.player[p].cardNumber, randomCard);

                    gui.updatePlayerHand(randomCard, p);

                    // To print the rest of cards
                    /*
                    if (obj.player[p].PlayerCards[obj.player[p].cardNumber].getSuit() == 0) {
                        System.out.print(obj.player[p].getName() + " Card num : " + obj.player[p].cardNumber + " - Club");
                    } else if (obj.player[p].PlayerCards[obj.player[p].cardNumber].getSuit() == 1) {
                        System.out.print(obj.player[p].getName() + " Card num : " + obj.player[p].cardNumber + " - Diamond");
                    } else if (obj.player[p].PlayerCards[obj.player[p].cardNumber].getSuit() == 2) {
                        System.out.print(obj.player[p].getName() + " Card num : " + obj.player[p].cardNumber + " - Heart");
                    } else if (obj.player[p].PlayerCards[obj.player[p].cardNumber].getSuit() == 3) {
                        System.out.print(obj.player[p].getName() + " Card num : " + obj.player[p].cardNumber + " - Spade");
                    }

                    System.out.println(" value: " + obj.player[p].PlayerCards[obj.player[p].cardNumber].getValue()
                            + "  rank: " + obj.player[p].PlayerCards[obj.player[p].cardNumber].getRank());
                  */
                    obj.updateMaximumScore(p, obj.player[p].cardNumber);
                    obj.player[p].cardNumber++;

                } else if (option != 2) {
                    option = 1;
                    System.out.println(obj.player[p].getName() + " YOU should chosose 1-HIT OR 2-STAND");
                }

            } while (option == 1 && obj.player[p].cardNumber < 11);
            System.out.println();

        }
    }


    // Dealer Option
    public void dealerOption(Game obj) {

        while (obj.player[3].getScore() < obj.valid_HighScore && obj.player[3].cardNumber < 11) {

            Card randomCard = obj.randomCard();
            obj.player[3].setPlayerCards(obj.player[3].cardNumber, randomCard);

            obj.updateMaximumScore(3, obj.player[3].cardNumber);

            gui.updateDealerHand(randomCard, obj.deckOfCards);

            // To print cards
            /*
            if (obj.player[3].PlayerCards[obj.player[3].cardNumber].getSuit() == 0) {
                System.out.print(obj.player[3].getName() + " -Card num : " + obj.player[3].cardNumber + " - Club");
            } else if (obj.player[3].PlayerCards[obj.player[3].cardNumber].getSuit() == 1) {
                System.out.print(obj.player[3].getName() + " -Card num : " + obj.player[3].cardNumber + " - Diamond");
            } else if (obj.player[3].PlayerCards[obj.player[3].cardNumber].getSuit() == 2) {
                System.out.print(obj.player[3].getName() + " -Card num : " + obj.player[3].cardNumber + " - Heart");
            } else if (obj.player[3].PlayerCards[obj.player[3].cardNumber].getSuit() == 3) {
                System.out.print(obj.player[3].getName() + " -Card num : " + obj.player[3].cardNumber + " - Spade");
            }

            System.out.println(" value: " + obj.player[3].PlayerCards[obj.player[3].cardNumber].getValue()
                    + "  rank: " + obj.player[3].PlayerCards[obj.player[3].cardNumber].getRank());
             */

            obj.player[3].cardNumber++;

        }
    }


    public static void main(String[] args) {

        Scanner input = new Scanner(System.in);
        BlackJack obj2 = new BlackJack();
        Game obj = new Game();
       
        // Generates the card deck    
        obj.generateCards();

        // Enter players names
        for (int i = 0; i < 4; i++) {
            if (i == 3) {
                System.out.println("Enter Dealer name");
            } else {
                System.out.println("Enter Player " + (i + 1) + " name");
            }

            String name = input.next();
            obj2.setPlayersInfo(i, obj, name);

        }

        obj2.gui.runGUI(obj.deckOfCards, obj.player[0].PlayerCards,
                        obj.player[1].PlayerCards, obj.player[2].PlayerCards,
                        obj.player[3].PlayerCards);

        obj2.playersOption(obj);
        obj2.dealerOption(obj);
        System.out.println();
        
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");
        
        for (int p = 0; p < 4; p++) {
            System.out.println("player " + (p + 1) + "  Score : " + obj.player[p].getScore());
        }

        System.out.print("Game Result : ");
        obj.gameResult();
        System.out.println("* * * * * * * * * * * * * * * * * * * * * * *");

    }

}