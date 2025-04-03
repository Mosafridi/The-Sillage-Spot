package Javas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class SearchServlet
 * 
 * This servlet handles product search requests and displays the results.
 * 
 * Author: arets
 * Page: SearchServlet.java
 * Connected with: ShopDisplay.html, OrderServlet.java, QueryServlet.java
 */
@WebServlet("/SearchServlet")
public class SearchServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static PreparedStatement st = null;
    private static Connection con = null;

    /**
     * Default constructor.
     * Step 1: Initialize the servlet.
     * Connected with: N/A
     */
    public SearchServlet() {
        super();
    }

    /**
     * Handles the HTTP GET request.
     * Step 2: Get search parameter and validate it.
     * Step 3: Determine SQL query based on search term.
     * Step 4: Execute query and display results.
     * Step 5: Handle exceptions and close resources.
     * Connected with: ShopDisplay.html - includes its content.
     *                OrderServlet.java - used for ordering products.
     *                QueryServlet.java - used for querying product details.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        String search = request.getParameter("search");

        // Step 2: Validate search parameter
        if (search == null || search.trim().isEmpty()) {
            response.sendRedirect("index.html");
            return;
        }

        // Step 3: Determine SQL query based on search term
        String sql;
        switch (search.toLowerCase()) {
            case "men perfume":
                sql = "SELECT * FROM products WHERE producttype = 'Men Perfume'";
                break;
            case "women perfume":
                sql = "SELECT * FROM products WHERE producttype = 'Women Perfume'";
                break;
            case "unisex perfume":
                sql = "SELECT * FROM products WHERE producttype = 'Unisex Perfume'";
                break;
            case "perfume":
                sql = "SELECT * FROM products WHERE producttype = 'Perfume'";
                break;
            default:
                sql = "SELECT * FROM products WHERE productname LIKE ?";
                break;
        }

        try {
            // Step 4: Load the MariaDB driver and establish a connection
            Class.forName("org.mariadb.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mariadb://localhost:3306/silage_spot", "root", "");

            // Include content from ShopDisplay.html
            RequestDispatcher rd = request.getRequestDispatcher("ShopDisplay.html");
            rd.include(request, response);

            st = con.prepareStatement(sql);

            // Set the search parameter if it's a general search
            if (!search.equalsIgnoreCase("Men Perfume") && !search.equalsIgnoreCase("Women Perfume") &&
                !search.equalsIgnoreCase("Unisex Perfume") && !search.equalsIgnoreCase("Perfume")) {
                st.setString(1, "%" + search + "%");
            }

            // Execute the query
            ResultSet result = st.executeQuery();

            // Step 4: Display the search results
            out.println("<!DOCTYPE html><html><head><link rel=\"icon\" href=\"img/Silage-Logo.png\" type=\"image/png\" /><link rel='stylesheet' href='css/style1.css'/></head><body>");
            out.println("<div class='container pb-5' style='margin-top: 15vh !important;'>");
            out.println("<p class='montlight text-center'>Searching For... </p><h1 class='montblack' style='text-transform: uppercase; text-align: center;'>" + search + "</h1>");

            if (!result.isBeforeFirst()) { // Check if ResultSet is empty
                out.println("<h3 class='montlight text-center'>No matched items, please try again</h3>");
            } else {
                out.println("<form method='get' action='OrderServlet'>");
                out.println("<table class='table table-borderless m-auto'>");
                out.println("<thead><tr><th scope='col'>&nbsp;</th><th scope='col'>Product Name</th><th scope='col'>Price</th><th scope='col'>&nbsp;</th></tr></thead>");
                out.println("<tbody>");

                while (result.next()) {
                    out.println("<tr>");
                    out.println("<td class='align-middle'><a class='btn btn-warning montlight' href='QueryServlet?producttype=" + result.getString("producttype") + "#qty" + result.getString("id") + "'>VIEW</a></td>");
                    out.println("<td class='align-middle' id='" + result.getString("id") + "'>" + result.getString("productname") + "</td>");
                    out.println("<td class='align-middle'> Euro- " + result.getString("price") + "</td>");
                    out.println("<td class='align-middle text-center'><img class='product' style='height: auto; width: 150px;' src='" + result.getString("img") + "'></td>");
                    out.println("</tr>");
                }

                out.println("</tbody></table><br />");
                out.println("</form>");
            }

            out.println("</div></body></html>");

        } catch (Exception e) {
            // Step 5: Handle any exceptions
            e.printStackTrace(out);
        } finally {
            // Step 5: Close resources
            try {
                if (st != null) st.close();
                if (con != null) con.close();
            } catch (Exception e) {
                e.printStackTrace(out);
            }
        }
    }

    /**
     * Handles the HTTP POST request by forwarding it to doGet method.
     * Step 6: Handle POST request (Delegates to doGet).
     * Connected with: N/A if required in the future 
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
