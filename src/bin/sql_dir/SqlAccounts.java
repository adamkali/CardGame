package CardGame.src.bin.sql_dir;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class SqlAccounts {
    private String username;
    private String password;
    private boolean isAccountCreated;
    private static int numberOfAccounts;

    private Connection accountConnection; 

    public SqlAccounts( String Name, String Password ) {

        setUsername(Name);
        setPassword(Password);

        try {
            startConnection();
            checkAccount(getUsername(), getPassword());
            setAccountNumber();
        } catch ( SQLException sqlException ) {
            sqlException.printStackTrace();
        }
    }



    private void setIsAccountCreated(boolean isIt) {
        this.isAccountCreated = isIt;
    }

    private void setUsername( String uname ) { this.username = uname;}
   
    private void setPassword( String pword ) { this.password = pword;}

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public boolean getIsAccountCreated() { return isAccountCreated; }

    public int getNumberOfAccounts() { return numberOfAccounts; }


    public Connection getAccountConnection() { return accountConnection; }

    private void startConnection() throws SQLException {
        this.accountConnection = DriverManager.getConnection("jbdc:mysql:localhost:3306/accountdatabase", "root", "Sierra&Adam4ever");
    }

    public void checkAccount( String queryUName, String queryPassword ) throws SQLException {
        getAccountConnection();
        
        PreparedStatement statement = getAccountConnection().prepareStatement("Select name, password from account where name=? and password=?");

        statement.setString(1, queryUName);
        statement.setString(2, queryPassword);

        ResultSet result = statement.executeQuery();

        setIsAccountCreated(result.next());

    }
    
    private void setAccountNumber() throws SQLException {
    
        Statement statement = getAccountConnection().createStatement();

        String query = "select count(*) from Accounts";

        ResultSet resultSet = statement.executeQuery(query);

        this.numberOfAccounts = resultSet.getInt(1);

    }

    public void createPlayerAccount( ) throws SQLException {

       Statement statement = getAccountConnection().createStatement();

       int nextNumber = numberOfAccounts + 1;

       statement.executeUpdate("INSERT INTO Accounts VALUES ( " +  " )");
    }

}
