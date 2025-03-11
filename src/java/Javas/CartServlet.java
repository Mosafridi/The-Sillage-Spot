package Javas;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

/**
 * The CartServlet class handles adding and removing products in the shopping cart.
 * 
 * Author: arets
 * Page: CartServlet.java
 * Connected with: cart.jsp, product pages that call CartServlet for cart operations
 */
@WebServlet("/CartServlet")
public class CartServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private static final Logger LOGGER = Logger.getLogger(CartServlet.class.getName());

    /**
     * Handles the HTTP POST request to manage the cart.
     * Step 1: Initialize or retrieve the cart from session.
     * Step 2: Log all request parameters for debugging.
     * Step 3: Handle remove action.
     * Step 4: Handle add/update action.
     * Step 5: Set the updated cart in session and redirect to cart.jsp.
     * Connected with: cart.jsp - the shopping cart page.
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // Step 1: Initialize or retrieve the cart from session
        HttpSession session = request.getSession();
        ArrayList<Product> cart;

        if (session.getAttribute("cart") == null) {
            cart = new ArrayList<>();
        } else {
            cart = (ArrayList<Product>) session.getAttribute("cart");
        }

        String action = request.getParameter("action");
        String[] selectedIds = request.getParameterValues("id");

        // Step 2: Log all request parameters for debugging
        LOGGER.info("Request Parameters:");
        Enumeration<String> parameterNames = request.getParameterNames();
        while (parameterNames.hasMoreElements()) {
            String paramName = parameterNames.nextElement();
            LOGGER.info(paramName + " = " + request.getParameter(paramName));
        }

        // Step 3: Handle remove action
        if ("remove".equals(action)) {
            if (selectedIds != null) {
                for (String id : selectedIds) {
                    cart.removeIf(product -> product.getId() == Integer.parseInt(id));
                }
            } else {
                LOGGER.severe("No products selected for removal");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No products selected for removal");
                return;
            }
        } else {
            // Step 4: Handle add/update action
            if (selectedIds != null) {
                for (String id : selectedIds) {
                    String productName = request.getParameter("name" + id);
                    String productPriceStr = request.getParameter("price" + id);
                    String productQuantityStr = request.getParameter("quantity" + id);
                    String productImg = request.getParameter("img" + id);
                    String productType = request.getParameter("type" + id);

                    LOGGER.info("Processing product ID: " + id);
                    LOGGER.info("Product Name: " + productName);
                    LOGGER.info("Product Price: " + productPriceStr);
                    LOGGER.info("Product Quantity: " + productQuantityStr);
                    LOGGER.info("Product Image: " + productImg);
                    LOGGER.info("Product Type: " + productType);

                    if (productName == null || productPriceStr == null || productQuantityStr == null || productImg == null || productType == null) {
                        LOGGER.severe("Missing product information for product ID: " + id);
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Missing product information for product ID: " + id);
                        return;
                    }

                    try {
                        float productPrice = Float.parseFloat(productPriceStr.trim());
                        int productQuantity = Integer.parseInt(productQuantityStr.trim());

                        Product product = new Product();
                        product.setId(Integer.parseInt(id));
                        product.setProductName(productName);
                        product.setPrice(productPrice);
                        product.setQuantity(productQuantity);
                        product.setImg(productImg);
                        product.setProductType(productType);
                        cart.add(product);
                    } catch (NumberFormatException e) {
                        LOGGER.severe("Invalid product price or quantity for product ID: " + id);
                        e.printStackTrace();
                        response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Invalid product price or quantity for product ID: " + id);
                        return;
                    }
                }
            } else {
                LOGGER.severe("No products selected");
                response.sendError(HttpServletResponse.SC_BAD_REQUEST, "No products selected");
                return;
            }
        }

        // Step 5: Set the updated cart in session and redirect to cart.jsp
        session.setAttribute("cart", cart);
        response.sendRedirect("cart.jsp");
    }
}