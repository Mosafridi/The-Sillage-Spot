package Javas;

import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

@WebServlet("/api/create-post")
@MultipartConfig(fileSizeThreshold = 1024 * 1024 * 2, maxFileSize = 1024 * 1024 * 10, maxRequestSize = 1024 * 1024 * 50)
public class CreatePostServlet extends HttpServlet {

    // Updated upload directory path
    private static final String UPLOAD_DIR = "C:\\Users\\shoai\\Downloads\\Silage-Spot 7\\Silage-Spot\\web\\img_product";

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json");
        PrintWriter out = response.getWriter();

        // Get form data
        String content = request.getParameter("content");
        Part filePart = request.getPart("image");

        if (content == null || filePart == null || filePart.getSize() == 0) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            out.println("{\"error\": \"Missing required fields.\"}");
            return;
        }

        // Save the uploaded file
        String fileName = extractFileName(filePart);
        String filePath = UPLOAD_DIR + File.separator + fileName; // Construct full file path
        File uploadDir = new File(UPLOAD_DIR);

        if (!uploadDir.exists()) {
            uploadDir.mkdirs(); // Create directory if it doesn't exist
        }

        try {
            filePart.write(filePath); // Write the file to the specified directory
        } catch (Exception e) {
            response.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
            out.println("{\"error\": \"Failed to upload image.\"}");
            return;
        }

        // Generate the image URL (relative to the web application)
        String imageUrl = "/img_product/" + fileName;

        // Simulate saving to the database (replace with actual DB logic)
        String title = "New Post"; // Replace with actual logic
        int userId = 1; // Replace with actual user ID from session

        // Example SQL query to save post data
        String sql = "INSERT INTO communityPosts (userId, postTitle, postContent, postImage, postCategory) VALUES (?, ?, ?, ?, ?)";

        // Execute the SQL query (pseudo-code, replace with actual implementation)
        // executeUpdate(sql, userId, title, content, imageUrl, "Clones");

        // Return the new post data as JSON
        String jsonResponse = String.format(
            "{\"title\": \"%s\", \"content\": \"%s\", \"imageUrl\": \"%s\"}",
            title, content, imageUrl
        );

        out.println(jsonResponse);
    }

    private String extractFileName(Part part) {
        String contentDisp = part.getHeader("content-disposition");
        String[] items = contentDisp.split(";");
        for (String item : items) {
            if (item.trim().startsWith("filename")) {
                return item.substring(item.indexOf("=") + 2, item.length() - 1);
            }
        }
        return "";
    }
}
