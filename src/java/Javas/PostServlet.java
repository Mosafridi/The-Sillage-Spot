package Javas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

@WebServlet("/PostServlet")
public class PostServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    public PostServlet() {
        super();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getParameter("action");
        PrintWriter out = response.getWriter();

        if ("createPost".equals(action)) {
            createPost(request, out);
        } else if ("createComment".equals(action)) {
            createComment(request, out);
        }
    }

    private void createPost(HttpServletRequest request, PrintWriter out) {
        String title = request.getParameter("title");
        String content = request.getParameter("content");
        String imageUrl = request.getParameter("imageUrl");

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/silage_spot", "root", "");
            String sql = "INSERT INTO communityPosts (userId, postTitle, postContent, postImage) VALUES (?, ?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, 1); // Assuming userId is 1 for simplicity
            statement.setString(2, title);
            statement.setString(3, content);
            statement.setString(4, imageUrl);
            statement.executeUpdate();
            out.println("Post created successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
    }

    private void createComment(HttpServletRequest request, PrintWriter out) {
        int postId = Integer.parseInt(request.getParameter("postId"));
        String commentText = request.getParameter("commentText");

        try {
            Class.forName("org.mariadb.jdbc.Driver");
            Connection connection = DriverManager.getConnection("jdbc:mariadb://localhost:3306/silage_spot", "root", "");
            String sql = "INSERT INTO comments (postId, userId, commentText) VALUES (?, ?, ?)";
            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setInt(1, postId);
            statement.setInt(2, 1); // Assuming userId is 1 for simplicity
            statement.setString(3, commentText);
            statement.executeUpdate();
            out.println("Comment posted successfully!");
        } catch (ClassNotFoundException | SQLException e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        }
    }
}
