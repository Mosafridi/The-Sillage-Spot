package Javas;

import java.util.List;
/**
 * The CartUtils class provides utility methods for calculating the total price and discount for a list of products.
 * 
 * Author: arets
 * Page: CartUtils.java
 * Connected with: OrderServlet.java, DeliveryServlet.java.
 */
public class CartUtils {

    /**
     * Calculates the total price for a list of products.
     * Step 1: Initialize totalPrice to 0.
     * Step 2: Iterate through the list of products.
     * Step 3: Calculate the total price by summing up the price * quantity of each product.
     * Connected with: OrderServlet.java, DeliveryServlet.java - used for calculating total price during order processing.
     */
    public static float calculateTotalPrice(List<Product> products) {
        float totalPrice = 0; // Step 1: Initialize totalPrice to 0.
        // Step 2: Iterate through the list of products
        for (Product product : products) {
            // Step 3: Calculate the total price by summing up the price * quantity of each product
            totalPrice += product.getPrice() * product.getQuantity();
        }
        return totalPrice;
    }

    /**
     * Calculates the discount based on the total price..
     * Step 4: Initialize discount to 0.
     * Step 5: Apply discount rules based on total price.
     * Connected with: OrderServlet.java, DeliveryServlet.java - used for calculating discounts during order processing.
     */
    public static float calculateDiscount(float totalPrice) {
        float discount = 0f; // Step 4: Initialize discount to 0.
        // Step 5: Apply discount rules based on total price
        if (totalPrice >= 100) {
            discount = totalPrice * 0.1f; // 10% discount for orders over 100 euros
        } else if (totalPrice >= 50) {
            discount = totalPrice * 0.05f; // 5% discount for orders over 50 euros
        }
        return discount;
    }
}