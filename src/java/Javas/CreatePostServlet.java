package Javas;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.file.Paths;
import java.sql.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.*;

@WebServlet("/createPostServlet")
@MultipartConfig(
    fileSizeThreshold = 1024 * 1024 * 2,
    maxFileSize = 1024 * 1024 * 10,
    maxRequestSize = 1024 * 1024 * 50
)
public class CreatePostServlet extends HttpServlet {

    private static final String DB_URL = "jdbc:mariadb://localhost:3306/silage_spot";
    private static final String USER = "root";
    private static final String PASS = "";
    private static final String ENV_UPLOAD_DIR = System.getenv("UPLOAD_DIR");
    private static final String DEFAULT_UPLOAD_DIR = "C:/Users/AndreiRetsja(Transfe/OneDrive - Transferendum/Documents/NetBeansProjects/Silage-Spot/web/post_images/";
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        String uploadDirPath = (ENV_UPLOAD_DIR != null) ? ENV_UPLOAD_DIR : DEFAULT_UPLOAD_DIR;
        File uploadDir = new File(uploadDirPath);
        if (!uploadDir.exists()) uploadDir.mkdirs();

        String content = request.getParameter("content");
        Part filePart = request.getPart("image");

        if (content == null || filePart == null || filePart.getSize() == 0) {
            respondWithError(response, out, HttpServletResponse.SC_BAD_REQUEST, "Missing post text or image.");
            return;
        }

        String fileName = Paths.get(filePart.getSubmittedFileName()).getFileName().toString();
        String sanitizedFileName = System.currentTimeMillis() + "_" + sanitizeFileName(fileName);
        String filePath = uploadDirPath + File.separator + sanitizedFileName;

        String lowerName = fileName.toLowerCase();
        if (!(lowerName.endsWith(".jpg") || lowerName.endsWith(".jpeg") || lowerName.endsWith(".png") || lowerName.endsWith(".webp"))) {
            respondWithError(response, out, HttpServletResponse.SC_BAD_REQUEST, "Only JPG, PNG or WEBP images allowed.");
            return;
        }

        filePart.write(filePath);
        String imageUrl = "post_images/" + sanitizedFileName;

        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            HttpSession session = request.getSession(false);
            if (session == null || session.getAttribute("profileId") == null) {
                respondWithError(response, out, HttpServletResponse.SC_UNAUTHORIZED, "User not logged in.");
                return;
            }

            int userId = (int) session.getAttribute("profileId");

            // Insert the post
            String insertSql = "INSERT INTO communityPosts (userId, postTitle, postContent, postImage, postCategory, postDate) VALUES (?, ?, ?, ?, ?, ?)";
            try (PreparedStatement stmt = conn.prepareStatement(insertSql)) {
                stmt.setInt(1, userId);
                stmt.setString(2, "New Post");
                stmt.setString(3, content);
                stmt.setString(4, imageUrl);
                stmt.setString(5, "Clones");
                stmt.setTimestamp(6, new Timestamp(System.currentTimeMillis()));
                stmt.executeUpdate();
            }

            // Get user info for post
            String userSql = "SELECT userName FROM userTable WHERE profileId = ?";
            String userName = "Anonymous";

            try (PreparedStatement userStmt = conn.prepareStatement(userSql)) {
                userStmt.setInt(1, userId);
                ResultSet rs = userStmt.executeQuery();
                if (rs.next()) {
                    userName = rs.getString("userName");
                }
            }

            String postDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(new Date());

            out.println("{"
                + "\"status\": \"success\"," 
                + "\"content\": \"" + escapeJson(content) + "\"," 
                + "\"imageUrl\": \"" + imageUrl + "\"," 
                + "\"username\": \"" + escapeJson(userName) + "\"," 
                + "\"postDate\": \"" + postDate + "\""
                + "}");

        } catch (SQLException e) {
            e.printStackTrace();
            respondWithError(response, out, HttpServletResponse.SC_INTERNAL_SERVER_ERROR, "Database error: " + e.getMessage());
        }
    }

    private String sanitizeFileName(String fileName) {
        return fileName.replaceAll("[^a-zA-Z0-9\\.\\-_]", "_");
    }

    private void respondWithError(HttpServletResponse response, PrintWriter out, int statusCode, String message) {
        response.setStatus(statusCode);
        out.println("{\"error\": \"" + escapeJson(message) + "\"}");
        out.flush();
    }

    private String escapeJson(String text) {
        return text.replace("\\", "\\\\")
                   .replace("\"", "\\\"")
                   .replace("\n", "\\n")
                   .replace("\r", "");
    }
}