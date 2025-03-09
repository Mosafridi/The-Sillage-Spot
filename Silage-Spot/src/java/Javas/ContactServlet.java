/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/Servlet.java to edit this template
 */
package Javas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * The ContactServlet class handles the contact form submission and stores the data in the database.
 * 
 * Author: MOhammad Shoaib/ Andre Retsja
 * Page: ContactServlet.java
 * Connected with: contact.html, contacts table in mountains_shop database
 */
@WebServlet("/ContactServlet")
public class ContactServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP GET request to forward to the contact page.
     * Step 1: Forward the request to the contact page.
     * Connected with: contact.html - the form page for entering contact details.
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Step 1: Forward the request to the contact page
        request.getRequestDispatcher("/contact.html").forward(request, response);
    }

    /**
     * Handles the HTTP POST request to process the contact form submission.
     * Step 2: Retrieve and validate form inputs.
     * Step 3: Establish a database connection.
     * Step 4: Create the contacts table if it doesn't exist.
     * Step 5: Insert contact information into the database.
     * Step 6: Send a response to the user.
     * Step 7: Handle exceptions and close resources.
     * Connected with: contacts table - stores contact form submissions.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Step 2: Retrieve and validate form inputs
        String name = request.getParameter("name");
        String email = request.getParameter("email");
        String address = request.getParameter("Address");
        String county = request.getParameter("County");
        String phone = request.getParameter("Phone");
        String subject = request.getParameter("Subject");
        String message = request.getParameter("message");

        if (name == null || name.isEmpty() ||
            email == null || email.isEmpty() ||
            address == null || address.isEmpty() ||
            county == null || county.isEmpty() ||
            phone == null || phone.isEmpty() ||
            subject == null || subject.isEmpty() ||
            message == null || message.isEmpty()) {
            // Handle invalid input
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Error</title></head><body>");
            out.println("<h1>Invalid form input</h1>");
            out.println("<p>Please fill out all fields</p>");
            out.println("</body></html>");
            return;
        }

        // Step 3: Establish a database connection
        Connection conn = null;
        PreparedStatement pstmt = null;

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            conn = DriverManager.getConnection("jdbc:mariadb://localhost:3306/silage_spot", "root", "");

            // Step 4: Create the contacts table if it doesn't exist
            String createTableSQL = "CREATE TABLE IF NOT EXISTS contacts ("
                    + "id INT(11) NOT NULL AUTO_INCREMENT,"
                    + "created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP,"
                    + "name VARCHAR(255),"
                    + "email VARCHAR(255),"
                    + "address VARCHAR(255),"
                    + "county VARCHAR(255),"
                    + "phone VARCHAR(255),"
                    + "subject VARCHAR(255),"
                    + "message TEXT,"
                    + "PRIMARY KEY (id)"
                    + ")";
            pstmt = conn.prepareStatement(createTableSQL);
            pstmt.executeUpdate();

            // Step 5: Insert contact information into the database
            String insertContactSQL = "INSERT INTO contacts (name, email, address, county, phone, subject, message) VALUES (?, ?, ?, ?, ?, ?, ?)";
            pstmt = conn.prepareStatement(insertContactSQL);
            pstmt.setString(1, name);
            pstmt.setString(2, email);
            pstmt.setString(3, address);
            pstmt.setString(4, county);
            pstmt.setString(5, phone);
            pstmt.setString(6, subject);
            pstmt.setString(7, message);
            pstmt.executeUpdate();

            // Step 6: Send a response to the user
            response.setContentType("text/html");
            PrintWriter out = response.getWriter();
            out.println("<html><head><title>Thank You!</title>");
            out.println("<link rel=\"icon\" href=\"img\\Silage-Logo.png\" type=\"image/png\">");
            out.println("<style>");
            out.println("body {");
            out.println("    color:white;");
            out.println("    .content { color: :white; }");
            out.println("    background: url('img/Background-02.png');");
            out.println("    display: flex;");
            out.println("    justify-content: center;");
            out.println("    align-items: center;");
            out.println("    height: 100vh;");
            out.println("}");
            out.println(".content {");
            out.println("    text-align: center;");
            out.println("}");
            out.println(".btn-send {");
            out.println("    display: inline-block;");
            out.println("    padding: 10px 20px;");
            out.println("    font-size: 16px;");
            out.println("    color: #fff;");
            out.println("    background-color: #007bff;");
            out.println("    border: none;");
            out.println("    border-radius: 5px;");
            out.println("    cursor: pointer;");
            out.println("    text-decoration: none;");
            out.println("}");
            out.println(".btn-send:hover {");
            out.println("    background-color: #0056b3;");
            out.println("}");
            out.println("</style>");
            out.println("</head>");
            out.println("<body class=\"bg-background text-white\">");
            out.println("<div class=\"content\">");
            out.println("<h1 class=\"text-white\">Thank you for using our Contact Page.</h1>");
            out.println("<h1 class=\"text-white\">We will reply to your Email as soon as possible!!!</h1>");
            out.println("<a href=\"index.html\" class=\"btn btn-send\">Go back to the main page</a>");
            out.println("</div>");
            out.println("</body></html>");

        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            // Handle database-related exceptions
        } finally {
            // Step 7: Close resources
            try {
                if (pstmt != null) {
                    pstmt.close();
                }
                if (conn != null) {
                    conn.close();
                }
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }
}
