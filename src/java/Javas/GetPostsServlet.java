package Javas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/getPostsServlet")
public class GetPostsServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/silage_spot";
    private static final String USER = "root";
    private static final String PASS = "";

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             PreparedStatement stmt = conn.prepareStatement(
                     "SELECT p.postContent, p.postImage, u.userName " +
                     "FROM communityPosts p JOIN userTable u ON p.userId = u.id " +
                     "ORDER BY p.postDate DESC")) {

            ResultSet rs = stmt.executeQuery();

            StringBuilder json = new StringBuilder("[");
            boolean first = true;

            while (rs.next()) {
                if (!first) json.append(",");
                first = false;
                json.append("{")
                    .append("\"username\":\"").append(escape(rs.getString("userName"))).append("\",")
                    .append("\"content\":\"").append(escape(rs.getString("postContent"))).append("\",")
                    .append("\"imageUrl\":\"").append(escape(rs.getString("postImage"))).append("\"")
                    .append("}");
            }

            json.append("]");
            out.print(json.toString());

        } catch (SQLException e) {
            e.printStackTrace();
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.print("{\"error\": \"Failed to load posts.\"}");
        }
    }

    private String escape(String str) {
        return str == null ? "" : str.replace("\"", "\\\"").replace("\n", "").replace("\r", "");
    }
}
