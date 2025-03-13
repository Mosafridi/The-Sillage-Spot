package Javas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * The LoginDatabaseConnector class handles the validation of user credentials
 * by connecting to the database and executing the appropriate SQL query.
 *
 * Author: Mohammad Shoaib
 * Page: LoginDatabaseConnector.java
 * Connected with: LoginServlet.java, userTable in database
 */
public class LoginDatabaseConnector {

    /**
     * Validates the user credentials by checking the username and password
     * against the records in the database. Returns the profileId if valid, -1 otherwise.
     * Step 1: Register JDBC driver.
     * Step 2: Open a connection to the database.
     * Step 3: Prepare a statement to execute the SQL query.
     * Step 4: Set parameters and execute the query.
     * Step 5: Return the profileId if credentials are valid, -1 otherwise.
     * Connected with: userTable in the database - validates user credentials.
     *                LoginServlet.java - used for handling login requests.
     */
    public static int validate(String name, String pass) {  
        int profileId = -1;
        Connection connect = null;
        PreparedStatement pstatement = null;
        
        try {
            // Step 1: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");
            // Step 2: Open a connection
            connect = DriverManager.getConnection("jdbc:mariadb://localhost:3306/silage_spot", "root", "");
            // Step 3: Prepare a statement
            pstatement = connect.prepareStatement("SELECT profileId FROM userTable WHERE UserName=? AND UserPass=?");
            // Step 4: Set parameters and execute the query
            pstatement.setString(1, name);
            pstatement.setString(2, pass);
            
            ResultSet rs = pstatement.executeQuery();
            if (rs.next()) {
                profileId = rs.getInt("profileId"); // Get the profileId if credentials are valid
            }
            
            // Close resources
            rs.close();
            pstatement.close();
            connect.close();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return profileId;
    }
}
