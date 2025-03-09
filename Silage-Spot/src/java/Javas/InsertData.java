package Javas;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.Statement;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class InsertData
 * 
 * This servlet inserts sample data into the products table in the database.
 * 
 * Author: MOhammad Soiab /Andrei Retsja
 * Page: InsertData.java
 * Connected with: products table in silage_shop database
 */
@WebServlet("/InsertData")
public class InsertData extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private Connection connect = null;
    private Statement statement = null;

    /**
     * Default constructor.
     * Step 1: Initialize the servlet.
     */
    public InsertData() {
        super();
    }

    /**
     * Handles the HTTP GET request to insert data into the database.
     * Step 2: Register JDBC driver.
     * Step 3: Open a connection to the database.
     * Step 4: Create a statement.
     * Step 5: Declare and execute an SQL query to insert data.
     * Step 6: Close all resources.
     * Connected with: products table in silage_shop database - inserts sample product data.
     */
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();

        try {
            out.println("Registering JDBC driver");
            // Step 2: Register JDBC driver
            Class.forName("org.mariadb.jdbc.Driver");
            out.println("Setting up the connection with the DB");
            // Step 3: Open a connection to the database
            connect = DriverManager.getConnection("jdbc:mariadb://localhost:3306/silage_spot", "root", "");
            // Step 4: Create a statement
            statement = connect.createStatement();
            // Step 5: Declare and execute an SQL query to insert data
            String sql = "INSERT INTO products (id, producttype, productname, price, qty, img, description) VALUES" +
            "(101, 'Women Perfume', 'Versace-Crystal Noir', 460.0, 1000, 'Women_img_product/1-2.png', 'Luxury women’s fragrance with a rich scent')," +
            "(102, 'Women Perfume', 'Versace-Bright Crystal Absolu', 36.80, 1000, 'Women_img_product/2-2.png', 'Elegant, floral fragrance perfect for special occasions')," +
            "(103, 'Women Perfume', 'Versace Pour Femme', 49.20, 1000, 'Women_img_product/3-2.png', 'A bold and floral fragrance for the modern woman')," +
            "(104, 'Unisex Perfume', 'Nautica Classic', 25.00, 1000, 'Unisex_img_product/1-2.png', 'Fresh and aquatic fragrance suitable for both men and women')," +
            "(105, 'Unisex Perfume', 'Olympea by Paco Rabanne', 30.00, 1000, 'Unisex_img_product/2-2.png', 'A sensual fragrance for women with a sweet floral base')," +
            "(106, 'Perfume', 'Acqua di Gio', 38.00, 1000, 'Men_img_product/1-2.png', 'Fresh, aquatic scent ideal for daytime wear')," +
            "(107, 'Perfume', 'Bad Boy by Carolina Herrera', 195.00, 1000, 'Men_img_product/2-2.png', 'A powerful fragrance with spicy, floral notes')," +
            "(108, 'Perfume', 'Versace Dylan Blue', 38.00, 1000, 'Men_img_product/3-2.png', 'A bold fragrance with hints of citrus and black pepper')," +
            "(109, 'Perfume', 'Dior Sauvage', 38.00, 1000, 'Men_img_product/4-2.png', 'A masculine fragrance with a fresh, woody scent')," +
            "(110, 'Women Perfume', 'Gucci Flora', 45.00, 1000, 'Women_img_product/4-2.png', 'A flowery and vibrant fragrance with notes of citrus')," +
            "(111, 'Women Perfume', 'Chanel No.5', 120.00, 1000, 'Women_img_product/5-2.png', 'Timeless floral fragrance with a blend of roses and jasmine')," +
            "(112, 'Unisex Perfume', 'Tom Ford Black Orchid', 65.00, 1000, 'Unisex_img_product/3-2.png', 'Sensual and exotic fragrance with rich, dark notes')," +
            "(113, 'Unisex Perfume', 'Dolce & Gabbana Light Blue', 50.00, 1000, 'Unisex_img_product/4-2.png', 'Fresh and citrusy fragrance perfect for summer')," +
            "(114, 'Men Perfume', 'Hugo Boss Bottled', 55.00, 1000, 'Men_img_product/5-2.png', 'Classic men’s fragrance with woody and citrus notes')," +
            "(115, 'Men Perfume', 'Paco Rabanne Invictus', 60.00, 1000, 'Men_img_product/6-2.png', 'Energetic fragrance with fresh and woody notes')," +
            "(116, 'Men Perfume', 'John Varvatos Vintage', 80.00, 1000, 'Men_img_product/7-2.png', 'A rich fragrance with aromatic and spicy notes')," +
            "(117, 'Women Perfume', 'Lancome La Vie Est Belle', 85.00, 1000, 'Women_img_product/6-2.png', 'Elegant and sweet fragrance with iris and vanilla notes')," +
            "(118, 'Women Perfume', 'Dior Jadore', 70.00, 1000, 'Women_img_product/7-2.png', 'Floral and fruity fragrance with a touch of rose')," +
            "(119, 'Unisex Perfume', 'Viktor & Rolf Flowerbomb', 95.00, 1000, 'Unisex_img_product/5-2.png', 'Floral fragrance with notes of jasmine, orange flower, and patchouli')," +
            "(120, 'Perfume', 'Creed Aventus', 300.00, 1000, 'Men_img_product/8-2.png', 'Fruity and smoky fragrance perfect for the modern man')," +
            "(121, 'Perfume', 'Hermes Terre d Hermes', 120.00, 1000, 'Men_img_product/9-2.png', 'Woody and earthy fragrance with a touch of citrus')," +
            "(122, 'Perfume', 'Yves Saint Laurent La Nuit de l Homme', 95.00, 1000, 'Men_img_product/10-2.png', 'Spicy and woody fragrance for an intense, seductive experience')," +
            "(123, 'Women Perfume', 'Jo Malone London', 110.00, 1000, 'Women_img_product/8-2.png', 'Delicate floral fragrance perfect for daytime wear')," +
            "(124, 'Women Perfume', 'Jo Malone Peony & Blush Suede', 120.00, 1000, 'Women_img_product/9-2.png', 'Fruity and floral fragrance with peony and suede')," +
            "(125, 'Unisex Perfume', 'Le Labo Santal 33', 150.00, 1000, 'Unisex_img_product/6-2.png', 'Rich and woody fragrance with a deep, smoky scent')," +
            "(126, 'Unisex Perfume', 'Tom Ford Tobacco Vanille', 200.00, 1000, 'Unisex_img_product/7-2.png', 'Warm and spicy fragrance with notes of tobacco and vanilla')," +
            "(127, 'Men Perfume', 'Armani Code', 85.00, 1000, 'Men_img_product/11-2.png', 'Sophisticated fragrance with citrus and leather notes')," +
            "(128, 'Men Perfume', 'Paco Rabanne 1 Million', 70.00, 1000, 'Men_img_product/12-2.png', 'Spicy and citrusy fragrance with leather and amber notes')," +
            "(129, 'Perfume', 'Versace Pour Homme', 55.00, 1000, 'Men_img_product/13-2.png', 'A fresh and clean fragrance with a touch of citrus and wood')," +
            "(130, 'Perfume', 'Chanel Bleu de Chanel', 110.00, 1000, 'Men_img_product/14-2.png', 'Elegant and refreshing fragrance with notes of citrus and sandalwood');";
            statement.executeUpdate(sql);
            out.println("Data inserted.....");

        } catch (Exception e) {
            e.printStackTrace();
            out.println("Error: " + e.getMessage());
        } finally {
            // Step 6: Close all resources
            try {
                if (statement != null) statement.close();
                if (connect != null) connect.close();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
    
    /**
     * Handles the HTTP POST request by forwarding it to the doGet method.
     * Step 7: Handle POST request (Delegates to doGet).
     * Connected with: doGet method.
     */
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
