package CardGame.src.bin.game_dir;

import CardGame.src.bin.card_dir.*;

public class Game {
    
    private CardXmlParse allCards = new CardXmlParse("CardGame\\data\\cards.xml");
    private Player player;
    private boolean isGameOn;
    private boolean isLoggedIn;

    private Player getPlayer() { return player; }

    private void setPlayer( String uString, String pString ) { this.player = new Player(uString, pString); }

    private void createPlayer( String uString, String wString, String cString ) { this.player = new Player(uString, wString, cString); }

    private CardXmlParse getAllCards() { return allCards; } 

    private boolean getIsGameOn() { return isGameOn; }

    private void startGame() { this.isGameOn = true; }

    private void quitGame() { this.isGameOn = false; }

    private void gameLogIn() {
        String uName = System.console().readLine("Username: ");
        String pWord = System.console().readLine("Password: ");

        setPlayer(uName, pWord);
    }

    private void gameCreateAccount() {
        String uName = System.console().readLine("Username: ");
        String wantPass = System.console().readLine("Password: ");
        String confPass = System.console().readLine("Confirm Password: ");

        createPlayer(uName, wantPass, confPass);        
    }

    public void runGame() {
        startGame();

        while ( getIsGameOn() ) {
            

        }

    }

}
