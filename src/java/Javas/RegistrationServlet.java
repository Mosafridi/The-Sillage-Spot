package Javas;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.SQLIntegrityConstraintViolationException;
import java.sql.Statement;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class RegistrationServlet
 * 
 * This servlet handles user registration. It inserts new user data into the database,
 * creates a trigger to auto-increment profileId, and manages error handling for various exceptions.
 * 
 * Author: Mohammad Shoaib
 * Page: RegistrationServlet.java
 * Connected with: registration.jsp, login.html
 */
@WebServlet("/RegistrationServlet")
public class RegistrationServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    /**
     * Handles the HTTP POST request for user registration.
     * Step 1: Load the MariaDB driver and establish a connection.
     * Step 2: Create a trigger for setting profileId automatically.
     * Step 3: Prepare the SQL insert statement.
     * Step 4: Set parameters from the request and execute the statement.
     * Step 5: Handle success and error cases.
     * Step 6: Close resources.
     * Connected with: registration.jsp - for displaying error messages.
     *                login.html - for redirecting after successful registration.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        Connection connect = null;
        PreparedStatement pstatement = null;
        Statement statement = null;

        try {
            // Step 1: Load the MariaDB driver and establish a connection
            Class.forName("org.mariadb.jdbc.Driver");
            connect = DriverManager.getConnection("jdbc:mariadb://localhost:3306/silage_spot", "root", "");

            // Step 2: Create the trigger for setting profileId automatically
            statement = connect.createStatement();
            String createTrigger = "CREATE TRIGGER IF NOT EXISTS before_insert_userTable "
                                 + "BEFORE INSERT ON userTable "
                                 + "FOR EACH ROW "
                                 + "SET NEW.profileId = (SELECT COALESCE(MAX(profileId), 0) + 1 FROM userTable)";
            statement.executeUpdate(createTrigger);
            System.out.println("Trigger for userTable Created");

            // Step 3: Prepare the SQL insert statement
            String sql = "INSERT INTO userTable (userName, userPass, userEmail, userPostalRegion) VALUES (?, ?, ?, ?)";
            pstatement = connect.prepareStatement(sql);

            // Step 4: Set parameters from the request and execute the statement
            pstatement.setString(1, request.getParameter("name"));
            pstatement.setString(2, request.getParameter("pass"));
            pstatement.setString(3, request.getParameter("email"));
            pstatement.setString(4, request.getParameter("PostalRegion"));

            int rowsAffected = pstatement.executeUpdate();

            // Step 5: Handle success and error cases
            if (rowsAffected > 0) {
                response.sendRedirect("login.html");
            } else {
                request.setAttribute("errorMessage", "Failed to register user!");
                RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
                dispatcher.forward(request, response);
            }
        } catch (ClassNotFoundException e) {
            handleError(request, response, e, "Error: " + e.getMessage());
        } catch (SQLIntegrityConstraintViolationException e) {
            handleIntegrityError(request, response, e);
        } catch (SQLException e) {
            handleError(request, response, e, "SQL Error: " + e.getMessage());
        } catch (NumberFormatException e) {
            handleError(request, response, e, "Error parsing number: " + e.getMessage());
        } finally {
            // Step 6: Close resources
            close(statement);
            close(pstatement);
            close(connect);
        }
    }

    /**
     * Handles errors by setting the error message in the request and forwarding to registration.jsp.
     */
    private void handleError(HttpServletRequest request, HttpServletResponse response, Exception e, String errorMessage) throws ServletException, IOException {
        e.printStackTrace();
        request.setAttribute("errorMessage", errorMessage);
        RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Handles SQL integrity constraint violation errors.
     */
    private void handleIntegrityError(HttpServletRequest request, HttpServletResponse response, SQLIntegrityConstraintViolationException e) throws ServletException, IOException {
        e.printStackTrace();
        if (e.getMessage().contains("userName")) {
            request.setAttribute("errorMessage", "Error: Username already exists!");
        } else if (e.getMessage().contains("userEmail")) {
            request.setAttribute("errorMessage", "Error: Email already exists!");
        } else {
            request.setAttribute("errorMessage", "SQL Error: " + e.getMessage());
        }
        RequestDispatcher dispatcher = request.getRequestDispatcher("registration.jsp");
        dispatcher.forward(request, response);
    }

    /**
     * Closes the given AutoCloseable resource.
     */
    private void close(AutoCloseable ac) {
        if (ac != null) {
            try {
                ac.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}