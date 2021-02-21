package CardGame.src.bin.game_dir;

import CardGame.src.bin.card_dir.*;

public class Game {

    
    
    private CardXmlParse allCards = new CardXmlParse("CardGame\\data\\cards.xml");

    private boolean isGameOn;

    private CardXmlParse getAllCards() { return allCards; } 

    private boolean getIsGameOn() { return isGameOn; }

    private void startGame() { this.isGameOn = true; }

    private void quitGame() { this.isGameOn = false; }

    private void openPack() {
        CardPack cardPack = new CardPack(getAllCards());
        cardPack.printPackOfCards();
    }

    public void runGame() {
        startGame();

        while ( getIsGameOn() ) {
            System.out.println("Choose either to [Open] a pack or to [Quit]\n\n");            
            String choice = System.console().readLine();

            if ( choice.equalsIgnoreCase("open") || choice.equalsIgnoreCase("o") ) {
                openPack();
                System.out.println("\n");
            } else if ( choice.equalsIgnoreCase("quit") || choice.equalsIgnoreCase("Q") ) {
                quitGame();
            } else {
                System.out.println("Invalid choice. Please choose again.\n");
            }

        }

    }

}
