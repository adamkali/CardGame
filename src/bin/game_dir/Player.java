package CardGame.src.bin.game_dir;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import CardGame.src.bin.card_dir.*;

public class Player {

    // This is determined by the Cards they get.
    private List<Card> cardCollection = new ArrayList<Card>();
    private String account;
    private String password;
    // To be implemented with SQL
    private int mana;

    public Player( String aString ) {
        // To Do

    }

    public String getAccount() {
        return account;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAccount(String account) {
        this.account = account;
    }

    public void createPlayerAccount( String username, String password, String confirmPassword ) {
        boolean uFlag = false;
        boolean pFlag = false; 

        // isUsed will not be gratuitous when SQL is implemented.
        if ( isUsed(username) && isUNameValid(username) ) { uFlag = true; }

        if ( password.equals(confirmPassword) && isPasswordValid(password) ) { pFlag = true; }

        if ( uFlag && pFlag ) {
            setPassword(password);
            setAccount(username);
        }
    }

    private static boolean isPasswordValid( String password ) { 
        
        // Regex to check valid password. 
        String regex = "^(?=.*[0-9])"
                       + "(?=.*[a-z])(?=.*[A-Z])"
                       + "(?=.*[@#$%^&+=])"
                       + "(?=\\S+$).{8,20}$"; 
  
        // Compile the ReGex 
        Pattern regexPattern = Pattern.compile(regex); 
  
        // If the password is empty 
        // return false 
        if (password == null) { 
            return false; 
        }
        
        Matcher doesPassMatch = regexPattern.matcher(password);

        return doesPassMatch.matches();
    }

    private static boolean isUNameValid( String uName ) {
        Pattern regexUNamePattern = Pattern.compile("[^A-Za-z0-9]");

        if ( uName == null ) {
            return false;
        }

        Matcher doesUNameMatch = regexUNamePattern.matcher(uName);

        return doesUNameMatch.matches();
    }

    private static boolean isUsed( String uName ) {
        // To Do
        return true;
    }

}
