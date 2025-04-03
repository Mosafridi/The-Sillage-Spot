/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Javas;

import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * Servlet implementation class PaymentServlet
 * 
 * This servlet handles the payment process. It processes payment details,
 * clears the cart, and redirects to a success page.
 * 
 * Author: arets
 * Page: PaymentServlet.java
 * Connected with: payment_success.jsp, CartServlet.java
 */
@WebServlet("/PaymentServlet")
public class PaymentServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP POST request for processing payments.
     * Step 1: Set content type and get PrintWriter.
     * Step 2: Retrieve session and payment details.
     * Step 3: Parse and validate total price.
     * Step 4: Clear the cart.
     * Step 5: Redirect to success page.
     * Connected with: payment_success.jsp - for displaying success message.
     *                CartServlet.java - for handling cart operations.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Step 1: Set content type and get PrintWriter
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Step 2: Retrieve session and payment details
        HttpSession session = request.getSession();
        String cardNumber = request.getParameter("cardNumber");
        String cardName = request.getParameter("cardName");
        String expiryDate = request.getParameter("expiryDate");
        String cvv = request.getParameter("cvv");

        // Step 3: Parse and validate total price
        float totalPrice = 0;
        try {
            totalPrice = Float.parseFloat(request.getParameter("totalPrice"));
        } catch (NumberFormatException e) {
            e.printStackTrace();
            out.print("Error processing total price.");
            return;
        }

        // Step 4: Clear the cart
        session.removeAttribute("cart");

        // Step 5: Redirect to success page
        response.sendRedirect("payment_success.jsp");
    }
}
