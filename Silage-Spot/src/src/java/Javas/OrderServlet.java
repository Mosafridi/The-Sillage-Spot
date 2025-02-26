package Javas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class OrderServlet
 * 
 * This servlet handles the processing of orders. It updates product quantities,
 * calculates total price and discounts, and forwards to the delivery form page.
 * 
 * Author: arets
 * Page: OrderServlet.java
 * Connected with: cart.jsp, WelcomeServlet.java, DeliveryServlet.java, delivery_form.jsp
 */
@WebServlet("/OrderServlet")
public class OrderServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;
    private ResultSet result = null;

    /**
     * Default constructor.
     * Step 1: Initialize the servlet.
     * Connected with: N/A
     */
    public OrderServlet() {
        super();
    }

    /**
     * Handles the HTTP GET request.
     * Step 2: Redirect GET requests to the cart page.
     * Connected with: cart.jsp
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Step 2: Redirect GET requests to the cart page.
        response.sendRedirect("cart.jsp");
    }

    /**
     * Handles the HTTP POST request for processing orders.
     * Step 3: Retrieve session and cart items.
     * Step 4: Load the MariaDB driver and establish a connection.
     * Step 5: Update product quantities and display the checkout page.
     * Step 6: Calculate total price and discounts.
     * Step 7: Forward to the delivery form page.
     * Step 8: Close all resources.
     * Connected with: delivery_form.jsp, WelcomeServlet.java, DeliveryServlet.java
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        ArrayList<Product> cart = (ArrayList<Product>) session.getAttribute("cart");

        // Step 3: Redirect to cart page if cart is empty or null
        if (cart == null || cart.isEmpty()) {
            response.sendRedirect("cart.jsp");
            return;
        }

        List<Product> products = new ArrayList<>();
        PrintWriter out = response.getWriter();

        try {
            // Step 4: Load the MariaDB driver and establish a connection
            Class.forName("org.mariadb.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mariadb://localhost:3306/silage_spot", "root", "");
            statement = connect.createStatement();

            out.println("<!DOCTYPE html><html><head><link rel=\"icon\" href=\"img/Silage-Logo.png\" type=\"image/png\" /><link rel='stylesheet' href='css/style1.css'/></head><body>");
            out.println("<div class='container'>");

            out.println("<h1 class='montblack'>Checkout</h1>");
            out.println("<table class=\"table table-borderless m-auto\"><thead><tr>"
                    + "<th>&nbsp;</th><th>Product Name</th><th>Price</th><th>Quantity</th><th>&nbsp;</th></tr></thead><tbody>");

            // Step 5: Update product quantities and display the checkout page
            for (Product product : cart) {
                String id = String.valueOf(product.getId());
                String productName = product.getProductName();
                float price = product.getPrice();
                int qtyOrdered = product.getQuantity();
                String img = product.getImg();

                String sql = "UPDATE products SET qty = qty - " + qtyOrdered + " WHERE id = " + id;
                statement.executeUpdate(sql);

                out.println("<tr>");
                out.println("<td class='align-middle'>" + product.getProductType() + "</td>");
                out.println("<td class='align-middle'>" + productName + "</td>");
                out.println("<td class='align-middle'>€ " + price + "</td>");
                out.println("<td class='align-middle'>" + qtyOrdered + "</td>");
                out.println("<td class='align-middle text-center'><img class='product' style='height: auto; width: 150px;' src='" + img + "'></td>");
                out.println("</tr>");

                products.add(product);
            }

            // Step 6: Calculate total price and discounts
            float totalPrice = calculateTotalPrice(cart);
            float discount = calculateDiscount(totalPrice);
            totalPrice -= discount;

            request.setAttribute("totalPrice", totalPrice);
            request.setAttribute("discount", discount);

            out.println("<tr><td colspan='4'>Discount: <h3 class='montblack'>€ " + discount + "</h3></td></tr>");
            out.println("<tr><td colspan='4'>Total Price: <h3 class='montblack'>€ " + totalPrice + "</h3></td></tr>");
            out.println("</tbody></table>");

            out.println("<h2 class='text-center montblack'>Thank You </h2>");
            out.println("<p class='text-center montlight'>Your order will be shipped in 2 days.<br/><br/><br/><br>");
            out.println("<a class='btn btn-outline-success m-auto text-center' href='WelcomeServlet'>Return To Home Page</a>");
            out.println("<a class='btn btn-outline-success m-auto text-center' href='DeliveryServlet'>Proceed to Delivery Details</a>");
            out.println("</div></body></html>");

            // Set session attributes for total price and products
            session.setAttribute("totalPrice", totalPrice);
            session.setAttribute("discount", discount);
            session.setAttribute("products", cart);

            // Step 7: Forward to the delivery form page
            RequestDispatcher dispatcher = request.getRequestDispatcher("delivery_form.jsp");
            dispatcher.forward(request, response);

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
     * Calculates the total price of the products in the cart.
     * @return The total price of the products.
     * Step 9: Calculate total price.
     * Connected with: Used for calculating total price during order processing.
     */
    public static float calculateTotalPrice(ArrayList<Product> products) {
        float totalPrice = 0;
        for (Product product : products) {
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }

    /**
     * Calculates the discount based on the total price.
     * @return The discount amount.
     * Step 10: Calculate discount based on total price.
     * Connected with: Used for calculating discounts during order processing.
     */
    public static float calculateDiscount(float totalPrice) {
        float discount = 0f;
        if (totalPrice >= 100) { // Apply a 10% discount for orders over 100 euros
            discount = totalPrice * 0.1f;
        } else if (totalPrice >= 50) { // Apply a 5% discount for orders over 50 euros
            discount = totalPrice * 0.05f;
        }
        return discount;
    }
}
