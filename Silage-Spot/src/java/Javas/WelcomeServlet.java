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
            out.print("<div class='container' style='margin-top: 10vh;'>"
                    + "<div class='col-md-4 m-auto'>"
                    + "<div class=\"alert alert-success\" role=\"alert\"><br>"
                    + "<p class=\"montlight m-auto\" style=\"text-align: center;\">Welcome " + request.getParameter("name") + "</p><br>\r\n"
                    + "</div></div></div>");
        }

        // Include the content from EshopDisplayServlet
        RequestDispatcher rd = request.getRequestDispatcher("EshopDisplayServlet");
        rd.include(request, response);

    // Footer Section
//    out.println("<section>");
//    out.println("<footer class=\"footer\" style=\"padding-top: 50px; padding-bottom: 60px; background-color: #333; color: white; margin-top: 100px;\">");
//
//    out.println("<div class=\"socials\" style=\"text-align: center; margin-bottom: 20px;\">");
//    out.println("<a href=\"https://www.facebook.com/YourPageName\" target=\"_blank\">");
//    out.println("<img src=\"/img/facebook.png\" alt=\"Facebook\" style=\"width: 30px; height: 30px; margin: 0 10px;\">");
//    out.println("</a>");
//    out.println("<a href=\"https://www.twitter.com/YourPageName\" target=\"_blank\">");
//    out.println("<img src=\"/img/twitter.png\" alt=\"Twitter\" style=\"width: 30px; height: 30px; margin: 0 10px;\">");
//    out.println("</a>");
//    out.println("<a href=\"https://www.instagram.com/YourPageName\" target=\"_blank\">");
//    out.println("<img src=\"/img/Instagram.png\" alt=\"Instagram\" style=\"width: 30px; height: 30px; margin: 0 10px;\">");
//    out.println("</a>");
//    out.println("</div>");
//
//
//    out.println("<p style=\"text-align: center; margin-top: 10px;\">&copy; 2025 The Silage Spot. All rights reserved.</p>");
//
//    out.println("</footer>");
//    out.println("</section>");

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Not implemented currently
    }
}
