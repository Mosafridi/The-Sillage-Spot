/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Javas;

/**
 * The Product class represents a product with various attributes such as id,
 * product type, product name, price, quantity, image, and description.
 *
 * Author: Mohammad Shoaib/ Andrei Resja
 * Page: Product.java
 * Connected with: QueryServlet.java, CartServlet.java
 */
public class Product {
    // Product attributes
    private int id;
    private String productType;
    private String productName;
    private float price;
    private int qty;
    private String img;
    private String description;

    /**
     * Parameterized constructor to initialize the product with all attributes.
     * Step 1: Initialize all product attributes.
     * Connected with: Used to create product instances with all attributes.
     */
    public Product(int id, String productType, String productName, float price, int qty, String img, String description) {
        this.id = id;
        this.productType = productType;
        this.productName = productName;
        this.price = price;
        this.qty = qty;
        this.img = img;
        this.description = description;
    }

    /**
     * Default constructor.
     * Step 2: Create an empty product instance.
     * Connected with: Used when creating product instances without initial values.
     */
    public Product() {
        // Default constructor
    }

    /**
     * Overloaded constructor to initialize the product with all attributes.
     * Step 3: Initialize all product attributes, casting price to float.
     * Connected with: Used to create product instances with all attributes.
     */
    public Product(int id, String productType, String productName, double price, int quantity, String img, String description) {
        this.id = id;
        this.productType = productType;
        this.productName = productName;
        this.price = (float) price;
        this.qty = quantity;
        this.img = img;
        this.description = description;
    }

    // Getters and setters for all attributes
    public int getId() { return id; }
    public void setId(int id) { this.id = id; }

    public String getProductType() { return productType; }
    public void setProductType(String productType) { this.productType = productType; }

    public String getProductName() { return productName; }
    public void setProductName(String productName) { this.productName = productName; }

    public float getPrice() { return price; }
    public void setPrice(float price) { this.price = price; }

    public int getQuantity() { return qty; }
    public void setQuantity(int quantity) { this.qty = quantity; }

    public String getImg() { return img; }
    public void setImg(String img) { this.img = img; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    /**
     * Returns a string representation of the product.
     * Step 4: Convert product attributes to a string.
     * Connected with: Used for debugging and logging product details.
     */
    @Override
    public String toString() {
        return "Product [id=" + id + ", productType=" + productType + ", productName=" + productName + ", price="
                + price + ", quantity=" + qty + ", img=" + img + ", description=" + description + "]";
    }
}
