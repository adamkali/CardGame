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

    public boolean loginCase;

    public SqlAccounts(String Name, String Password) {


        setUsername(Name);
        setPassword(Password);

        try {
            setAccountNumber();
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

    public boolean checkAccount( String queryUName, String queryPassword ) throws SQLException {
       
        Connection connection = DriverManager.getConnection("jbdc:mysql://localhost:3306\\accountdatabase", "root", "Sierra&Adam4ever");
        
        PreparedStatement statement = connection.prepareStatement("Select name, password from account where name=? && password=?");

        statement.setString(1, queryUName);
        statement.setString(2, queryPassword);

        ResultSet result = statement.executeQuery();

        if ( result.next() ) {
            System.out.println("Tell me why!");
        }

        return result.next();
    }
    
    private void setAccountNumber() throws SQLException {
    
        Connection connection = DriverManager.getConnection("jbdc:mysql://localhost:3306\\accountdatabase", "root", "Sierra&Adam4ever");
        Statement statement = connection.createStatement();

        String query = "select count(*) from Accounts";

        ResultSet resultSet = statement.executeQuery(query);

        this.numberOfAccounts = resultSet.getInt(1);

    }

    public void createPlayerAccount() throws SQLException {
        
        Connection connection = DriverManager.getConnection("jbdc:mysql://localhost:3306/accountdatabase", "root", "Sierra&Adam4ever");
        Statement statement = connection.createStatement();

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
