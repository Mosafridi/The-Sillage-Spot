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
 * Servlet implementation class QueryServlet
 * 
 * This servlet handles product queries based on the product type and displays the results.
 * 
 * Author: Andrei Retsja
 * Page: QueryServlet.java
 * Connected with: ShopDisplay.html, CartServlet.java
 */
@WebServlet("/QueryServlet")
public class QueryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet result = null;

    /**
     * Default constructor.
     * Step 1: Initialize the servlet.
     */
    public QueryServlet() {
        super();
    }

    /**
     * Handles the HTTP GET request.
     * Step 2: Load the MariaDB driver and establish a connection.
     * Step 3: Create a statement.
     * Step 4: Query the database for products.
     * Step 5: Display the products.
     * Step 6: Add a message if no products are selected.
     * Step 7: Add JavaScript to check if at least one product is selected.
     * Step 8: Close all resources.
     * Connected with: ShopDisplay.html - includes its content.
     *                CartServlet.java - used for ordering products.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        try {
            // Step 2: Load the MariaDB driver
            Class.forName("org.mariadb.jdbc.Driver");
            // Step 2: Establish a connection to the database
            connect = DriverManager.getConnection("jdbc:mariadb://localhost:3306/silage_spot", "root", "");
            // Step 3: Create a statement
            statement = connect.createStatement();
            String product = request.getParameter("producttype");

            out.println("<html><head><link rel='stylesheet' href='css/bootstrap.min.css' /><link rel='stylesheet' href='css/style1.css' /></head><body>");

            // Step 4: Query the database for products
            String sql = "SELECT * FROM products WHERE qty > 0 AND (producttype = '" + product + "') ORDER BY id";
            result = statement.executeQuery(sql);

            result.next();

            // Include the ShopDisplay.html page
            RequestDispatcher rd = request.getRequestDispatcher("ShopDisplay.html");
            rd.include(request, response);
            out.println("<div class='container pb-5'>"); // Adjust margin-top to prevent navbar overlap
            out.println("<h1 class='montblack' style='margin-top: 15vh !important; text-transform: uppercase; text-align: center;'>" + product + "</h1>");
            out.println("<form method='post' action='CartServlet' id='productForm'>");
            out.println("<table class=\"table table-borderless m-auto\">\r\n"
                    + "  <thead>\r\n"
                    + "    <tr>\r\n"
                    + "      <th scope=\"col\">&nbsp;</th>\r\n"
                    + "      <th scope=\"col\">Product Name</th>\r\n"
                    + "      <th scope=\"col\">Description</th>\r\n"
                    + "      <th scope=\"col\">Price</th>\r\n"
                    + "      <th scope=\"col\">Quantity</th>\r\n"
                    + "      <th scope=\"col\">Product-Image</th>\r\n"
                    + "      <th scope=\"col\">&nbsp;</th>\r\n"
                    + "    </tr>");

            // Step 5: Display the products
            do {
                String id = result.getString("id");
                String productName = result.getString("productname");
                String description = result.getString("description"); 
                String price = result.getString("price");
                String img = result.getString("img");
                String productType = result.getString("producttype");

                out.println("<tr>");
                out.println("<td class='align-middle'><input style='height: 25px; width: 25px;' type='checkbox' name='id' value='" + id + "' /></td>");
                out.println("<td class='align-middle'>" + productName + "</td>");
                out.println("<td class='align-middle'>" + description + "</td>"); // Display description
                out.println("<td class='align-middle'>Euro- " + price + "</td>");
                out.println("<td class='align-middle'>"
                        + "<div class=\"input-group\">"
                        + "<div class=\"input-group-prepend\">"
                        + "<input type=\"button\" class=\"incdec" + id + " decrement btn btn-outline-secondary\" value=\"-\" />"
                        + "</div>"
                        + "<input type='text' size='3' value='1' class='form-control col-lg-3 text-center' name='quantity" + id + "' id='qty" + id + "'/>"
                        + "<div class=\"input-group-append\"><input type=\"button\" class=\"incdec" + id + " increment btn btn-outline-secondary\" value=\"+\" /></td>"
                        + "</div>"
                        + "<script>"
                        + "var $input" + id + " = $(\"#qty" + id + "\");"
                        + "$input" + id + ".val(1);"
                        + "$(\".incdec" + id + "\").click(function(){"
                        + "if ($(this).hasClass('increment'))"
                        + "$input" + id + ".val(parseInt($input" + id + ".val())+1);"
                        + "else if ($input" + id + ".val()>=1)"
                        + "$input" + id + ".val(parseInt($input" + id + ".val())-1);"
                        + "});"
                        + "</script>");
                out.println("<td class='align-middle text-center'><img class='product' style='height: auto; width: 150px;' src='" + img + "'></td>");
                out.println("<input type='hidden' name='name" + id + "' value='" + productName + "' />");
                out.println("<input type='hidden' name='price" + id + "' value='" + price + "' />");
                out.println("<input type='hidden' name='img" + id + "' value='" + img + "' />");
                out.println("<input type='hidden' name='type" + id + "' value='" + productType + "' />");
                out.println("</tr>");
            } while (result.next());
            out.println("</table><br />");

            out.println("<input type='submit' value='ORDER' class='ProductSans btn btn-success' />");
            out.println("<input type='reset' value='CLEAR' class='ProductSans btn btn-secondary'/></form>");

            // Step 6: Add a message if no products are selected
            out.println("<p id='errorMessage' style='color:red; display:none;'>Please select at least one product to proceed.</p>");

            // Step 7: Add JavaScript to check if at least one product is selected
            out.println("<script>"
                    + "document.getElementById('productForm').onsubmit = function() {"
                    + "var checkboxes = document.querySelectorAll('input[type=\"checkbox\"]:checked');"
                    + "if (checkboxes.length === 0) {"
                    + "document.getElementById('errorMessage').style.display = 'block';"
                    + "return false;"
                    + "}"
                    + "};"
                    + "</script>");

            out.println("</div>");
            out.println("</body></html>");

        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            // Step 8: Close all resources
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
     * Handles the HTTP POST request by forwarding it to doGet method.
     * Step 9: Handle POST request (Delegates to doGet).
     * Connected with: N/A if required in the future 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}