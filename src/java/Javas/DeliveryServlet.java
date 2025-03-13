package Javas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;
import java.util.List;
import java.util.Calendar;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The DeliveryServlet class handles the delivery details and order processing.
 * 
 * Author: arets
 * Page: DeliveryServlet.java
 * Connected with: delivery_form.jsp, cart.jsp, order_review_page.jsp, deliveryTable in database
 */
@WebServlet("/DeliveryServlet")
public class DeliveryServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP GET request to redirect to the delivery form.
     * 
     * @param request  The HttpServletRequest object that contains the request the client made to the servlet.
     * @param response The HttpServletResponse object that contains the response the servlet returns to the client.
     * @throws ServletException if a servlet-specific error occurs.
     * @throws IOException if an I/O error occurs.
     * Step 1: Redirect to the delivery form JSP.
     * Connected with: delivery_form.jsp - the form page for entering delivery details.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Step 1: Redirect to the delivery form JSP
        response.sendRedirect("delivery_form.jsp");
    }

    /**
     * Handles the HTTP POST request to process the delivery details and order.
     * Step 2: Set content type and get PrintWriter.
     * Step 3: Retrieve session and product details.
     * Step 4: Calculate total price and discount.
     * Step 5: Retrieve delivery information from request parameters.
     * Step 6: Validate the address1 parameter.
     * Step 7: Get the current date and calculate delivery date.
     * Step 8: Connect to the database and prepare SQL statement.
     * Step 9: Generate a unique order ID.
     * Step 10: Insert delivery details into the database.
     * Step 11: Store user details and forward to order review page.
     * Step 12: Handle exceptions and close resources.
     * Connected with: deliveryTable in database - for storing delivery details.
     *                order_review_page.jsp - for displaying order review.
     *                cart.jsp - for redirecting if the cart is empty.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Step 2: Set content type and get PrintWriter
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Step 3: Retrieve session and product details
        HttpSession session = request.getSession();
        List<Product> products = (List<Product>) session.getAttribute("products");

        if (products == null || products.isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        float totalPrice = 0;
        float discount = 0;

        // Step 4: Calculate total price and discount
        try {
            totalPrice = (float) session.getAttribute("totalPrice");
            discount = (float) session.getAttribute("discount");
            // Add logging to check the values
            System.out.println("Total Price from session: " + totalPrice);
            System.out.println("Discount from session: " + discount);
        } catch (Exception e) {
            e.printStackTrace();
            out.print("Error retrieving total price or discount from session");
            return;
        }

        // Step 5: Retrieve delivery information from request parameters
        String name = request.getParameter("name");
        String address = request.getParameter("address");
        String address1 = request.getParameter("address1");
        String city = request.getParameter("city");
        String phone = request.getParameter("phone");
        String email = request.getParameter("email");

        // Step 6: Validate the address1 parameter
        if (address1 == null || address1.isEmpty()) {
            out.println("Address1 cannot be null or empty");
            return; // Stop further processing
        }

        // Step 7: Get the current date and calculate delivery date
        java.util.Date currentDate = new java.util.Date();
        java.sql.Date orderDate = new java.sql.Date(currentDate.getTime());
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(currentDate);
        calendar.add(Calendar.DATE, 2);
        java.sql.Date deliveryDate = new java.sql.Date(calendar.getTimeInMillis());

        Connection conn = null;
        PreparedStatement ps = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            // Step 8: Connect to the database and prepare SQL statement
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/silage_spot", "root", "");
            stmt = conn.createStatement();

            // Step 9: Generate a unique order ID
            int orderId = 0;
            rs = stmt.executeQuery("SELECT MAX(order_id) FROM deliveryTable");
            if (rs.next()) {
                orderId = rs.getInt(1) + 1;
            } else {
                orderId = 1;
            }

            // Step 10: Insert delivery details into the database
            String insertDelivery = "INSERT INTO deliveryTable (order_id, name, address, address1, city, phone, email, product_id, product_name, quantity, price, total_price, created_at, delivery_date) "
                                  + "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
            ps = conn.prepareStatement(insertDelivery);

            for (Product product : products) {
                // Set parameters
                ps.setInt(1, orderId);
                ps.setString(2, name);
                ps.setString(3, address);
                ps.setString(4, address1);
                ps.setString(5, city);
                ps.setString(6, phone);
                ps.setString(7, email);
                ps.setInt(8, product.getId());
                ps.setString(9, product.getProductName());
                ps.setInt(10, product.getQuantity());
                ps.setFloat(11, product.getPrice());
                ps.setFloat(12, totalPrice);
                ps.setDate(13, orderDate); // Set the order date
                ps.setDate(14, deliveryDate); // Set the delivery date

                // Execute the update
                ps.addBatch();
            }
            ps.executeBatch();

            // Step 11: Store user details and forward to order review page
            request.setAttribute("name", name);
            request.setAttribute("address", address);
            request.setAttribute("address1", address1);
            request.setAttribute("city", city);
            request.setAttribute("phone", phone);
            request.setAttribute("email", email);
            request.setAttribute("totalPrice", totalPrice); 
            request.setAttribute("discount", discount);

            // Forward the request to the order review page
            session.setAttribute("totalPrice", totalPrice);
            session.setAttribute("discount", discount);
            request.getRequestDispatcher("order_review_page.jsp").forward(request, response);

        } catch (SQLException e) {
            // Step 12: Handle database errors
            e.printStackTrace();
            out.print("Database error: " + e.getMessage());
        } finally {
            // Step 12: Close resources
            try {
                if (rs != null) rs.close();
                if (ps != null) ps.close();
                if (stmt != null) stmt.close();
                if (conn != null) conn.close();
            } catch (SQLException e) {
                e.printStackTrace();
                out.print("Error closing resources: " + e.getMessage());
            }
            out.close();
        }
    }
}
