/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Javas;

/**
 * The CartItem class represents an item in the shopping cart.
 * 
 * Author: arets
 * Page: CartItem.java
 * Connected with: CartServlet.java, OrderServlet.java.
 */
public class CartItem {
    private String productName;
    private String productType;
    private double price;
    private int quantity;
    private String image;

    /**
     * Constructor to initialize a CartItem object.
     * Step 1: Initialize the CartItem attributes.
     * Connected with: CartServlet.java - when adding items to the cart.
     */
    public CartItem(String productName, String productType, double price, int quantity, String image) {
        this.productName = productName;
        this.productType = productType;
        this.price = price;
        this.quantity = quantity;
        this.image = image;
    }

    /**
     * Getter for productName.
     * Step 2: Return the product name.
     * Connected with: OrderServlet.java, CartServlet.java - for retrieving product name.
     */
    public String getProductName() {
        return productName;
    }

    /**
     * Getter for productType.
     * Step 3: Return the product type.
     * Connected with: OrderServlet.java, CartServlet.java - for retrieving product type.
     */
    public String getProductType() {
        return productType;
    }

    /**
     * Getter for price.
     * Step 4: Return the product price.
     * Connected with: OrderServlet.java, CartServlet.java - for retrieving product price.
     */
    public double getPrice() {
        return price;
    }

    /**
     * Getter for quantity.
     * Step 5: Return the product quantity.
     * Connected with: OrderServlet.java, CartServlet.java - for retrieving product quantity.
     */
    public int getQuantity() {
        return quantity;
    }

    /**
     * Getter for image.
     * Step 6: Return the product image URL.
     * Connected with: OrderServlet.java, CartServlet.java - for retrieving product image URL.
     */
    public String getImage() {
        return image;
    }
}
