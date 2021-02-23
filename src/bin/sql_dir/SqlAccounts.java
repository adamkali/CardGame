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
    private int numberOfAccounts;

    private Connection accountConnection;

    public boolean loginCase;

    public SqlAccounts(String Name, String Password) {

        setUsername(Name);
        setPassword(Password);

        try {
            startConnection();
        } catch (SQLException sqlException) {
            loginCase = false;
        } finally {
            loginCase = true;
        }
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
        setAccountNumber();
    }

    public boolean checkAccount( String queryUName, String queryPassword ) throws SQLException {
        getAccountConnection();
        
        PreparedStatement statement = getAccountConnection().prepareStatement("Select name, password from account where name=? and password=?");

        statement.setString(1, queryUName);
        statement.setString(2, queryPassword);

        ResultSet result = statement.executeQuery();

        return result.next();
    }
    
    private void setAccountNumber() throws SQLException {
    
        Statement statement = getAccountConnection().createStatement();

        String query = "select count(*) from Accounts";

        ResultSet resultSet = statement.executeQuery(query);

        this.numberOfAccounts = resultSet.getInt(1);

    }

    public void createPlayerAccount() throws SQLException {

       Statement statement = getAccountConnection().createStatement();

       int nextNumber = getNumberOfAccounts() + 1;

       statement.executeUpdate("INSERT INTO Accounts VALUES ( " + nextNumber + ", '" + getUsername() + "', '" + getPassword() + "' )");
    }


    public boolean loginToAccount() {
        
        try {
            return checkAccount(getUsername(), getPassword());
        } catch (SQLException e) {
            loginCase = false;
        }
        return false; 
    }
}
