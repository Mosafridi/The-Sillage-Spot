package Javas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The CreateDatabase class handles the creation of the database and tables.
 * 
 * Author: Mohammad Shoab
 * Page: CreateDatabase.java
 * Connected with: products table, userTable, deliveryTable in silage_spot database
 */
@WebServlet("/CreateDatabase")
public class CreateDatabase extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     * Step 1: Initialize the servlet.
     * Connected with: N/A
     */
    public CreateDatabase() {
        super();
    }

    /**
     * Handles the HTTP GET request to create the database and tables.
     * Step 2: Register JDBC driver.
     * Step 3: Connect to MySQL without specifying a database.
     * Step 4: Create the database if it doesn't exist.
     * Step 5: Close the initial connection and reconnect to the new database.
     * Step 6: Create tables in the newly created database.
     * Step 7: Close resources.
     * Connected with: products table - stores product details.
     *                userTable - stores user details.
     *                deliveryTable - stores delivery details.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        Connection connection = null;
        Statement statement = null;

        try {
            // Step 2: Register JDBC driver
            out.println("Registering JDBC driver");
            Class.forName("org.mariadb.jdbc.Driver");

            // Step 3: Connect to MySQL without specifying a database
            out.println("Setting up the connection with the DB");
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306", "root", "");
            statement = connection.createStatement();

            // Step 4: Create the database if it doesn't exist
            out.println("Creating the database...");
            String createDatabaseSQL = "CREATE DATABASE IF NOT EXISTS silage_spot";
            statement.executeUpdate(createDatabaseSQL);
            out.println("Database created.....");

            // Step 5: Close the initial connection and reconnect to the new database
            connection.close();
            connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/silage_spot", "root", "");
            statement = connection.createStatement();

          
            // Step 6: Create tables in the newly created database
            out.println("Creating tables in the database...");

            // Create userTable
            String createUserTable = "CREATE TABLE IF NOT EXISTS userTable ("
                                    + "id INT AUTO_INCREMENT PRIMARY KEY,"
                                    + "userName VARCHAR(255) UNIQUE,"
                                    + "userPass VARCHAR(255),"
                                    + "userEmail VARCHAR(255) UNIQUE,"
                                    + "userPostalRegion VARCHAR(255),"
                                    + "profileId INT"
                                    + ")";
            statement.executeUpdate(createUserTable);
            out.println("Table 'userTable' created.....");

            out.println("Creating tables in the database...");

            String createProductsTable = "CREATE TABLE IF NOT EXISTS products ("
                                        + "id INT NOT NULL AUTO_INCREMENT, "
                                        + "producttype VARCHAR(255), "
                                        + "productname VARCHAR(255), "
                                        + "price DECIMAL(10, 2) NOT NULL, "
                                        + "qty INTEGER, "
                                        + "img VARCHAR(255), "
                                        + "description VARCHAR(255), "  
                                        + "PRIMARY KEY (id)"
                                        + ")";
            statement.executeUpdate(createProductsTable);
            out.println("Table 'products' created.....");
            
            
            } catch (ClassNotFoundException e) {
                        e.printStackTrace();
                        out.println("Error: " + e.getMessage());
                    } catch (SQLException e) {
                        e.printStackTrace();
                        out.println("SQL Error: " + e.getMessage());
                    } finally {
                        // Step 7: Close resources
                        close(statement);
                        close(connection);
                    }
                }

                /**
                 * Handles the HTTP POST request by forwarding it to the doGet method.
                 * 
                 * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
                 * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
                 * @throws ServletException if a servlet-specific error occurs.
                 * @throws IOException if an I/O error occurs.
                 * Step 8: Handle POST request (Delegates to doGet).
                 * Connected with: doGet method.
                 */
                @Override
                protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
                    doGet(request, response);
                }

                /**
                 * Closes the given AutoCloseable resource.
                 * 
                 * @param ac The AutoCloseable resource to close.
                 * Step 9: Close the given resource.
                 */
                private void close(AutoCloseable ac) {
                    if (ac != null) {
                        try {
                            ac.close();
                        } catch (Exception e) {
                            e.printStackTrace();
            }
        }
    }
}