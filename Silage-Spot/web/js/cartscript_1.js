/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

var cart = {};

function addToCart(productId, price, productName) {
    if (cart[productId]) {
        cart[productId].quantity += 1; // Increment quantity if product already in cart
    } else {
        // Add new item to the cart if not already present
        cart[productId] = {
            quantity: 1,
            price: price,
            name: productName // Store product name
        };
    }
    renderCart(); // Update cart display after adding item
}

function removeFromCart(productId) {
    if (cart[productId]) {
        if (cart[productId].quantity > 1) {
            cart[productId].quantity -= 1;
        } else {
            delete cart[productId];
        }
    }
    renderCart();
}

function renderCart() {
    const cartTable = document.getElementById('cartItems');
    let total = 0;
    let innerHTML = `<tr>
        <th>Item</th>
        <th>Quantity</th>
        <th>Price</th>
        <th>Action</th>
    </tr>`;

    for (const id in cart) {
        const item = cart[id];
        total += item.quantity * item.price; // Calculate total price
        innerHTML += `<tr>
            <td>${item.name}</td> <!-- Display product name -->
            <td>${item.quantity}</td>
            <td>$${(item.price * item.quantity).toFixed(2)}</td> <!-- Display total price for each item -->
            <td><button onclick="removeFromCart(${id})">Remove</button></td>
        </tr>`;
    }

    cartTable.innerHTML = innerHTML;
    document.getElementById('totalPrice').textContent = total.toFixed(2); // Update total price in the cart
}

function checkout() {
    alert('Checkout - Total: $' + document.getElementById('totalPrice').textContent);
}
