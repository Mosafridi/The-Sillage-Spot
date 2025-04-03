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

    public WelcomeServlet() {
        super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        // Check if the "name" parameter is present and not empty
        if (request.getParameter("name") != null && !request.getParameter("name").isEmpty()) {
            out.print("<div class='welcome-message'>"
                    + "<div class='alert alert-success' role='alert'>"
                    + "<p>Welcome " + request.getParameter("name") + "</p>"
                    + "</div></div>");
        }

        // Include the content from EshopDisplayServlet
        RequestDispatcher rd = request.getRequestDispatcher("EshopDisplayServlet");
        rd.include(request, response);

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Not implemented currently
    }
}
