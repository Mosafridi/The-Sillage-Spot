package Javas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/submitCommentServlet")
public class SubmitCommentServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/silage_spot";
    private static final String USER = "root";
    private static final String PASS = "";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        HttpSession session = request.getSession(false);
        if (session == null || session.getAttribute("profileId") == null) {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            out.println("{\"error\": \"User not logged in.\"}");
            return;
        }

        int userId = (int) session.getAttribute("profileId");
        String commentText = request.getParameter("commentText");
        String postIdParam = request.getParameter("postId");

        if (commentText == null || commentText.trim().isEmpty() || postIdParam == null) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\": \"Missing comment or postId.\"}");
            return;
        }

        try {
            int postId = Integer.parseInt(postIdParam);
            try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
                 PreparedStatement stmt = conn.prepareStatement(
                         "INSERT INTO comments (postId, userId, commentText) VALUES (?, ?, ?)")) {

                stmt.setInt(1, postId);
                stmt.setInt(2, userId);
                stmt.setString(3, commentText);
                stmt.executeUpdate();

                out.println("{\"status\": \"success\"}");
            }
        } catch (NumberFormatException | SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\": \"Error saving comment.\"}");
        }
    }
}
