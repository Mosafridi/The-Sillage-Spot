package Javas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class EshopDisplayServlet
 * 
 * This servlet handles the display of product categories in the e-shop.
 * It retrieves distinct product types from the database where quantity is greater than zero.
 * 
 * Author: arets
 * Page: EshopDisplayServlet.java
 * Connected with: home.html, products table in Silage_spot database
 */
@WebServlet("/EshopDisplayServlet")
public class EshopDisplayServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet result = null;

    /**
     * Default constructor.
     * Step 1: Initialize the servlet.
     */
    public EshopDisplayServlet() {
        super();
    }

    /**
     * Handles the HTTP GET request to display product categories.
     * Step 2: Register JDBC driver.
     * Step 3: Open a connection to the database.
     * Step 4: Create a statement.
     * Step 5: Declare and execute an SQL query to retrieve product categories.
     * Step 6: Include home.html page and display login success message if applicable.
     * Step 7: Close all resources.
     * Connected with: home.html - for displaying the homepage.
     *                products table in mountains_shop database - retrieves product categories.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            // Step 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");
            // Step 3: Open a connection to the database
            connect = DriverManager.getConnection("jdbc:mariadb://localhost:3306/silage_spot", "root", "");
            // Step 4: Create a statement
            statement = connect.createStatement();
            // Step 5: Declare and execute an SQL query to retrieve product categories
            String sql = "SELECT DISTINCT producttype FROM products WHERE qty > 0";
            result = statement.executeQuery(sql);

            // Step 6: Include home.html page and display login success message if applicable
            if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
                out.println("\r\n"
                        + "<!-- Modal -->\r\n"
                        + "<div class=\"modal fade\" id=\"loginSuccess\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">\r\n"
                        + "  <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">\r\n"
                        + "    <div class=\"modal-content\">\r\n"
                        + "      <div class=\"modal-body\">\r\n"
                        + "        <button type=\"button\" class=\"close\" data-dismiss=\"modal\" aria-label=\"Close\">\r\n"
                        + "          <span aria-hidden=\"true\">&times;</span>\r\n"
                        + "          </button>\r\n"
                        + "          <br><br>\r\n"
                        + "        <p class=\"montlight\" style=\"text-align: center;\">Login Successful</p><br>\r\n"
                        + "      </div>\r\n"
                        + "    </div>\r\n"
                        + "  </div>\r\n"
                        + "</div>"
                        + "\r\n"
                        + "<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>"
                        + "<script>"
                        + "$(document).ready(function(){"
                        + "$('#loginSuccess').modal('show')"
                        + "});"
                        + "</script>");
            }
            RequestDispatcher rd = request.getRequestDispatcher("home.html");
            rd.include(request, response);

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Step 7: Close all resources
            try {
                if (result != null) result.close();
                if (statement != null) statement.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * Handles the HTTP POST request by forwarding it to the doGet method.
     * Step 8: Handle POST request (Delegates to doGet).
     * Connected with: doGet method.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}

	