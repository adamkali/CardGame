package src.bin.sql_dir;

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

    public boolean loginCase = true;

    public SqlAccounts(String Name, String Password) {

        setUsername(Name);
        setPassword(Password);

        try {
            DriverManager.getConnection("jdbc:mysql://localhost:3306/accountdatabase", "root", "Sierra&Adam4ever");
        } catch (SQLException sqlException) {
            loginCase = false;
            System.out.println("Cannot connect to database.");
        }
    }

    private void setUsername( String uname ) { this.username = uname;}
   
    private void setPassword( String pword ) { this.password = pword;}

    public String getUsername() { return username; }

    public String getPassword() { return password; }

    public boolean getIsAccountCreated() { return isAccountCreated; }

    public int getNumberOfAccounts() { return numberOfAccounts; }

    public boolean checkAccount( String queryUName, String queryPassword ) throws SQLException {
       
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountdatabase", "root", "Sierra&Adam4ever");
        
        PreparedStatement statement = connection.prepareStatement("Select username, passphrase from accounts where username=? && passphrase=?");

        statement.setString(1, queryUName);
        statement.setString(2, queryPassword);

        ResultSet result = statement.executeQuery();

        return result.next();
    }
    
    public void createPlayerAccount() throws SQLException {
        
        Connection connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/accountdatabase", "root", "Sierra&Adam4ever");
        Statement statement = connection.createStatement();

        System.out.println(getUsername());

        statement.executeUpdate("INSERT INTO Accounts VALUES ( '" + getUsername() + "', '" + getPassword() + "' )");
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
