package src.bin.game_dir;

import src.bin.card_dir.*;
import src.bin.game_dir.ui.MainUIController;

public class Game {
    
    private final CardXmlParse allCards = new CardXmlParse("CardGame\\data\\cards.xml");
    private Player player;
    private boolean isLoggedIn = false;

    public String gameUName;
    public String gamePWord;
    public String gameCPWrd;

    private void setIsLoggedIn( boolean bool ) { this.isLoggedIn = bool; }

    private Player getPlayer() { return player; }

    private void setPlayer( String uString, String pString ) { this.player = new Player(uString, pString); }

    private void createPlayer( String uString, String wString, String cString ) { this.player = new Player(uString, wString, cString); }

    public boolean getIsLoggedIn() { return isLoggedIn; }

    public String getGameUName() {
        return gameUName;
    }

    public String getGamePWord() {
        return gamePWord;
    }

    public String getGameCPWrd() {
        return gameCPWrd;
    }

    public void setGameUName(String gameUName) {
        this.gameUName = gameUName;
    }

    public void setGamePWord(String gamePWord) {
        this.gamePWord = gamePWord;
    }

    public void setGameCPWrd(String gameCPWrd) {
        this.gameCPWrd = gameCPWrd;
    }

    public void gameLogIn() {
        setPlayer(getGameUName(), getGamePWord());
        setIsLoggedIn(getPlayer().getIsLoggedIn());
    }

    public void gameCreateAccount() {
        String uName = System.console().readLine("Username: ");
        String wantPass = System.console().readLine("Password: ");
        String confPass = System.console().readLine("Confirm Password: ");

        createPlayer(uName, wantPass, confPass);

        setIsLoggedIn(getPlayer().getIsLoggedIn());
    }

    public static void main(String[] args) {
        MainUIController.initialize(args);
    }
}
