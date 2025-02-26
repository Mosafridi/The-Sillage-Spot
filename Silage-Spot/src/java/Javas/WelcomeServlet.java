package Javas;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 
 * This servlet handles the welcome message for the user. It checks if the 
 * "name" parameter is present in the request and displays a welcome message.
 *
 * Author: arets
 * Page: WelcomeServlet.java
 * Connected with: EshopDisplayServlet.java
 */
@WebServlet("/WelcomeServlet")
public class WelcomeServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    /**
     * Step 1: Initialize the servlet.
     * Connected with: N/A
     */
    public WelcomeServlet() {
        super();
    }

    /**
     * Handles the HTTP GET request.
     * Step 2: Check for the "name" parameter and display the welcome message.
     * Step 3: Include content from EshopDisplayServlet.
     * Connected with: EshopDisplayServlet.java - includes its content.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Get the writer to write the response
        PrintWriter out = response.getWriter();

        // Check if the "name" parameter is present and not empty
        if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
            // Print the welcome message
            out.print("<div class='container' style='margin-top: 25vh;'>"
                    + "<div class='col-md-4 m-auto'>"
                    + "<div class=\"alert alert-success\" role=\"alert\"><br>"
                    + "<p class=\"montlight m-auto\" style=\"text-align: center;\">Welcome " + request.getParameter("name") + "</p><br>\r\n"
                    + "</div></div></div>");
        }

        // Include the content from EshopDisplayServlet
        RequestDispatcher rd = request.getRequestDispatcher("EshopDisplayServlet");
        rd.include(request, response);
    }

    /**
     * Handles the HTTP POST request.
     * 
     * Step 4: Handle POST request (Currently not implemented).
     * Connected with: N/A / if required in the future 
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Currently not implemented
    }
}