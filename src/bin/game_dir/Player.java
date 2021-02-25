package CardGame.src.bin.game_dir;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import CardGame.src.bin.card_dir.*;
import CardGame.src.bin.errs.LogInException;
import CardGame.src.bin.sql_dir.*;

public class Player {

    private SqlAccounts sqlAccount;

    // This is determined by the Cards they get.
    private List<Card> cardCollection = new ArrayList<Card>();
    private String account;
    private String password;
    // To be implemented with SQL
    private int mana;

    private boolean isConnected = true;
    private boolean isLoggedIn;

    public Player( String uName, String pWord) {

        sqlAccount = new SqlAccounts(uName, pWord);

        while ( sqlAccount.loginCase ) {
            if ( sqlAccount.loginToAccount() ) {
                setAccount(uName);
                setPassword(pWord);
                setIsLoggedIn(sqlAccount.loginToAccount());
            }
        }
    }

    public Player( String uName, String wantedPWord, String confirmPWord ) {
        
        try {
            createPlayerAccount(uName, wantedPWord, confirmPWord);
            sqlAccount = new SqlAccounts(getAccount(), getPassword());
            sqlAccount.createPlayerAccount();
            setIsLoggedIn(true);
        } catch (LogInException | SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean getIsLoggedIn() { return isLoggedIn; }

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

    public void setIsLoggedIn( boolean bool ) { this.isLoggedIn = bool; }

    // Authentication

    public void createPlayerAccount( String username, String wantedPassword, String confirmPassword ) throws LogInException {
        boolean uFlag = false;
        boolean pFlag = false; 

        // isUsed will not be gratuitous when SQL is implemented.
        if ( isUNameValid(username) ) { 
            uFlag = true; 
            System.out.println("username true");
        }

        if ( wantedPassword.equals(confirmPassword) && isPasswordValid( wantedPassword ) ) { pFlag = true; }

        if ( uFlag && pFlag ) {
            setPassword(wantedPassword);
            setAccount(username);
        }  else { 
            throw new LogInException("Credentials are not valid.");
        }
    }

    private static boolean isPasswordValid( String password ) { 
        
        // Regex to check valid password. 
        String regex = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}";
  
        // Compile the ReGex 
        Pattern regexPattern = Pattern.compile(regex); 
  
        // If the password is empty 
        // return false 
        if (password == null) { 
            return false; 
        }
        
        Matcher doesPassMatch = regexPattern.matcher(password);

        if ( doesPassMatch.matches() ) {
            System.out.println("password true");
        }

        return doesPassMatch.matches();
    }

    private static boolean isUNameValid( String uName ) {
        Pattern regexUNamePattern = Pattern.compile("^[A-Za-z0-9]\\w{5,29}");

        if ( uName == null ) {
            System.out.println("uName is Null");
            return false;
        }

        Matcher doesUNameMatch = regexUNamePattern.matcher(uName);

        if ( !doesUNameMatch.matches() ) { System.out.println("this is false"); } 

        return doesUNameMatch.matches();
    }

    public void openPack( CardXmlParse xml ) {
        CardPack cardPack = new CardPack(xml);
        
        cardPack.printPackOfCards();

        for ( Card elementCard : cardPack.getPack() ) {
            cardCollection.add(elementCard);
        }
    }

    public void showCollection() {
        for ( Card elementCard : cardCollection ) {
            System.out.println(elementCard);
            System.out.println("\n");
        }
    }
}
