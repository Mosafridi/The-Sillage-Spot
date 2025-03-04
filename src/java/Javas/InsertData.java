package Javas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertData
 * 
 * This servlet inserts sample data into the products table in the database.
 * 
 * Author: MOhammad Shoaib /Andrei Retsja
 * Page: InsertData.java
 * Connected with: products table in silage_shop database
 */
@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;

    /**
     * Default constructor.
     * Step 1: Initialize the servlet.
     */
    public InsertData() {
        super();
    }

    /**
     * Handles the HTTP GET request to insert data into the database.
     * Step 2: Register JDBC driver.
     * Step 3: Open a connection to the database.
     * Step 4: Create a statement.
     * Step 5: Declare and execute an SQL query to insert data.
     * Step 6: Close all resources.
     * Connected with: products table in silage_shop database - inserts sample product data.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            out.println("Registering JDBC driver");
            // Step 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");
            out.println("Setting up the connection with the DB");
            // Step 3: Open a connection to the database
            connect = DriverManager.getConnection("jdbc:mariadb://localhost:3306/silage_spot", "root", "");
            // Step 4: Create a statement
            statement = connect.createStatement();
            // Step 5: Declare and execute an SQL query to insert data
            String sql = "INSERT INTO products (id, producttype, productname, price, qty, img, description) VALUES" +
                         "(101, '', '', , , '', '')," +
                         "(102, '', '', ,, '', '');";
            statement.executeUpdate(sql);
            out.println("Data inserted.....");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        } finally {
            // Step 6: Close all resources
            try {
                if (statement != null) statement.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles the HTTP POST request by forwarding it to the doGet method.
     * Step 7: Handle POST request (Delegates to doGet).
     * Connected with: doGet method.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
