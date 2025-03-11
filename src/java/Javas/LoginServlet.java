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
 * Servlet implementation class LoginServlet
 * 
 * This servlet handles user login by authenticating the user's credentials
 * and directing them to the appropriate page based on the result.
 * 
 * Author: Mohammad Shoaib
 * Page: LoginServlet.java
 * Connected with: LoginDatabaseConnector.java, WelcomeServlet.java, login.html
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Default constructor.
     * Step 1: Initialize the servlet.
     */
    public LoginServlet() {
        super();
    }

    /**
     * Handles the HTTP GET request by forwarding it to processRequest method.
     * @throws IOException if an I/O error occurs.
     * Step 2: Forward GET request to processRequest.
     * Connected with: processRequest method.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Handles the HTTP POST request by forwarding it to processRequest method.
     * Step 3: Forward POST request to processRequest.
     * Connected with: processRequest method.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        processRequest(request, response);
    }

    /**
     * Processes the login request by authenticating the user's credentials.
     * Step 4: Get user credentials from request.
     * Step 5: Authenticate user using LoginDatabaseConnector.
     * Step 6: If authentication is successful, forward to WelcomeServlet.
     * Step 7: If authentication fails, display error message and include login.html.
     * Connected with: LoginDatabaseConnector.java - for user authentication.
     *                WelcomeServlet.java - for successful login redirection.
     *                login.html - for displaying login page and error messages.
     */
    private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Step 4: Set content type and get PrintWriter
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Step 4: Get user credentials from request
        String username = request.getParameter("name");
        String password = request.getParameter("pass");

        // Step 5: Authenticate user using LoginDatabaseConnector
        int profileId = LoginDatabaseConnector.validate(username, password);
        if (profileId != -1) {
            // Step 6: If authentication is successful, forward to WelcomeServlet
            request.getSession().setAttribute("profileId", profileId);
            RequestDispatcher rd = request.getRequestDispatcher("WelcomeServlet");
            rd.forward(request, response);
        } else {
            // Step 7: If authentication fails, display error message and include login.html
            RequestDispatcher rd = request.getRequestDispatcher("login.html");
            rd.include(request, response);
            out.println("<div class=\"modal fade\" id=\"loginFail\" tabindex=\"-1\" role=\"dialog\" aria-labelledby=\"exampleModalLabel\" aria-hidden=\"true\">"
                    + "  <div class=\"modal-dialog modal-dialog-centered\" role=\"document\">"
                    + "    <div class=\"modal-content\">"
                    + "      <div class=\"modal-body\">"
                    + "          <br>"
                    + "        <p class=\"montlight\" style=\"text-align: center;\">Login Failed, Please Try Again!</p><br>"
                    + "<p style=\"text-align: center;\"><a href=\"login.html\"><button class=\"btn btn-secondary ProductSans\">BACK</button></a></p>"
                    + "      </div>"
                    + "    </div>"
                    + "  </div>"
                    + "</div>"
                    + "<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js\"></script>"
                    + "<script type=\"text/javascript\" src=\"https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js\"></script>"
                    + "<script src=\"js/bootstrap.bundle.js\"></script>"
                    + "<script>"
                    + "$(document).ready(function(){"
                    + "$('#loginFail').modal('show')"
                    + "});"
                    + "</script>");
        }
        out.close();
    }
}
