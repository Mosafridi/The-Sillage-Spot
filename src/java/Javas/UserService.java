/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Javas;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

/**
 * The UserService class provides methods for user authentication.
 * It connects to the database to verify user credentials.
 *
 * Author: arets
 * Page: UserService.java
 * Connected with: Database (userTable), LoginServlet.java
 */
public class UserService {

    /**
     * Authenticates a user by checking the provided username and password against the database.
     * Step 1: Establish a database connection.
     * Step 2: Prepare and execute the SQL query to check credentials.
     * Step 3: Retrieve the profileId if credentials match.
     * Step 4: Handle exceptions and close resources.
     * Connected with: Database (userTable) - verifies user credentials.
     *                LoginServlet.java - used to handle login requests.
     */
    public static int authenticateUser(String username, String password) {
        int profileId = -1;
        Connection conn = null;
        PreparedStatement pst = null;
        ResultSet rs = null;

        try {
            // Step 1: Load the MariaDB driver and establish a connection
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/mountains_shop", "root", "");

            // Step 2: Prepare the SQL query
            pst = conn.prepareStatement("SELECT profileId FROM userTable WHERE userName = ? AND userPass = ?");
            pst.setString(1, username);
            pst.setString(2, password);

            // Step 3: Execute the query and retrieve the result
            rs = pst.executeQuery();
            if (rs.next()) {
                profileId = rs.getInt("profileId");
            }
        } catch (Exception e) {
            // Step 4: Handle any exceptions
            e.printStackTrace();
        } finally {
            // Step 4: Close the resources
            try {
                if (rs != null) rs.close();
                if (pst != null) pst.close();
                if (conn != null) conn.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return profileId;
    }
}
