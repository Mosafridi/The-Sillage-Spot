/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Javas;

import java.util.ArrayList;
import java.util.List;

/**
 * The Cart class represents a shopping cart which contains a list of items.
 * It provides methods to add items, retrieve the list of items, and calculate
 * the total price of all items in the cart.
 *
 * Author: arets
 * Page: CartPage.java
 * Connected with: CartItem.java
 */
public class cart {
    // List to store cart items
    private List<CartItem> items = new ArrayList<>();

    /**
     * Adds an item to the cart.
     * Step 1: Create an instance of CartItem and pass it to this method.
     * Connected with: CartItem.java - ensures the item adheres to the CartItem structure.
     */
    public void addItem(CartItem item) {
        items.add(item);
    }

    /**
     * Retrieves the list of items in the cart.
     * Step 2: Call this method to get the current items in the cart.
     * Connected with: DisplayCartPage.java - used to display the items in the cart.
     */
    public List<CartItem> getItems() {
        return items;
    }

    /**
     * Calculates the total price of all items in the cart.
     * Step 3: Call this method to get the total price of all items.
     * Connected with: CheckoutPage.java - used to display the total price at checkout.
     */
    public double getTotalPrice() {
        double total = 0;
        // Loop through each item and calculate the total price
        for (CartItem item : items) {
            total += item.getPrice() * item.getQuantity();
        }
        return total;
    }
}