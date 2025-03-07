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
 * Author: MOhammad Soiab /Andrei Retsja
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
                         "(101, 'Women Perfume', 'Versace-Crystal Noir', 460, 1000, 'Men_img_product/GT1.jpg', '')," +
                         "(102, 'Women Perfume', 'Versace-Bright Crystal Absolu', 36.80, 1000, 'Men_img_product/1-1.png', 'Comfortable and waterproof hiking jacket')," +
                         "(103, 'Women Perfume', 'Hiking-Boots-GTX-Vakuum', 49.20, 1000, 'Men_img_product/1-1.png', 'Durable hiking boots with GTX Vakuum technology')," +
                         "(104, 'Unisex Perfume', 'The Kerry-Way', 25, 1000, 'Men_img_product/1-1.png', 'Experience the scenic Kerry Way hiking tour')," +
                         "(105, 'Unisex Perfume', 'Hoodie-Jacket-Sky-Jacket', 30, 1000, 'Men_img_product/1-1.png', 'Stylish and warm hoodie jacket')," +
                         "(106, 'Perfume', 'With Love from Ireland', 38, 1000, 'Men_img_product/1-1.png', 'Discover Ireland with love on this hiking tour')," +
                         "(107, 'Perfume', 'Irelands-West-Coast', 195, 1000, 'Men_img_product/1-1.png', 'Explore Ireland’s beautiful west coast')," +
                         "(108, 'Perfume', 'With Love from Ireland', 38, 1000, 'Men_img_product/1-1.png', 'Discover Ireland with love on this hiking tour')," +
                         "(109, 'Perfume', 'With Love from Ireland', 38, 1000, 'Men_img_product/1-1.png', 'Discover Ireland with love on this hiking tour');";

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
