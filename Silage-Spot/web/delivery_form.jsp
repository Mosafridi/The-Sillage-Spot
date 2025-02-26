<<%-- 
    Document   : delivery_form
    Created on : 20 Jul 2024, 08:55:43
    Author     : arets
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Javas.Product" %>
<!DOCTYPE html>
<html>
<head>
    <title>The Silage Spot - delivery Form</title>
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/style1.css" />
    <link rel="stylesheet" href="css/styleCart.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fork-awesome@1.1.7/css/fork-awesome.min.css" integrity="sha256-gsmEoJAws/Kd3CjuOQzLie5Q3yshhvmo7YNtBG7aaEY=" crossorigin="anonymous">
    <link rel="icon" href="img/Silage-Logo.png" type="image/png" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body class="bg-background">
    <!-- Navigation Bar -->
    <nav class="navbar navbar-expand-lg navbar-dark navbar-black bg-black fixed-top pt-4 pb-4 text-center">
        <div class="container">
            <!-- Brand Logo -->
            <a class="navbar-brand montblack" href="index.html"><img src="img/Silage-Logo.png" width="40" height="40" /> All For Mountains </a>
            <!-- Toggler Button for Collapsed Navigation -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <!-- Navbar Links -->
            <div class="collapse navbar-collapse" id="navbarText">
                <ul class="navbar-nav mr-auto montlight" style="padding-right: 40px;">
                    <!-- Home Link -->
                    <li class="nav-item">
                        <a class="nav-link" href="WelcomeServlet">Home</a>
                    </li>
                    <!-- Shop Dropdown -->
                    <li class="nav-item active dropdown montlight" id="shop">
                        <a class="nav-link dropdown-toggle" href="#" role="button" id="dropshop" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            SHOP <span class="sr-only">(current)</span>
                        </a>
                        <div class="dropdown-menu bg-dark bg-black" aria-labelledby="dropshop">
                            <a class="dropdown-item text-white" href="QueryServlet?producttype=Mountain Bikes">Mountain Bikes</a>
                            <a class="dropdown-item text-white" href="QueryServlet?producttype=Hiking Clothes">Hiking Clothes</a>
                            <a class="dropdown-item text-white" href="QueryServlet?producttype=Hiking Boots">Hiking Boots</a>
                            <a class="dropdown-item text-white" href="QueryServlet?producttype=Hiking Tours">Hiking Tours</a>
                            <div class="dropdown-divider"></div>
                            <a class="dropdown-item text-white" href="cart.jsp"><i class="fa fa-shopping-cart" aria-hidden="true"></i> Cart</a>
                        </div>
                    </li>
                    <!-- Support Dropdown -->
                    <li class="nav-item dropdown montlight">
                        <a class="nav-link dropdown-toggle" href="#" role="button" id="dropsupport" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Support
                        </a>
                        <div class="dropdown-menu bg-dark bg-black" aria-labelledby="dropsupport">
                            <a class="dropdown-item text-white" href="contact.html">Contact Us</a>
                        </div>
                    </li>
                    <!-- Account Dropdown -->
                    <li class="nav-item dropdown montlight">
                        <a class="nav-link dropdown-toggle" href="#" role="button" id="dropaccount" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                            Account
                        </a>
                        <div class="dropdown-menu bg-dark bg-black" aria-labelledby="dropaccount">
                            <a class="dropdown-item text-white" href="index.html"><i class="fa fa-sign-out" aria-hidden="true"></i> Logout</a>
                        </div>
                    </li>
                </ul>
                <!-- Search Form -->
                <span class="navbar-text montlight">
                    <form class="form-inline" action="SearchServlet" method="get">
                        <div class="input-group">
                            <input class="form-control" type="text" name="search" placeholder="Search" aria-label="Search" aria-describedby="Search Button">
                            <div class="input-group-append">
                                <button class="btn btn-outline-warning" type="submit"><i class="fa fa-search" aria-hidden="true"></i></button>
                            </div>
                        </div>
                    </form>
                </span>
            </div>
        </div>
    </nav>
    <!-- Container for Delivery Form and Product Details -->
    <div class="container" style="margin-top: 30vh;">
        <div class="container mt-5">
            <!-- Table to display selected products -->
            <table class="table">
                <thead>
                    <tr>
                        <th>Product ID</th>
                        <th>Product Type</th>
                        <th>Product Name</th>
                        <th>Price</th>
                        <th>Quantity</th>
                        <th>Image</th>
                    </tr>
                </thead>
                <tbody>
                    <% 
                        // Retrieve products from session
                        List<Product> products = (List<Product>) session.getAttribute("products");
                        if (products != null) {
                            for (Product product : products) {
                    %>
                    <tr>
                        <td><%= product.getId() %></td>
                        <td><%= product.getProductType() %></td>
                        <td><%= product.getProductName() %></td>
                        <td>&euro;<%= String.format("%.2f", product.getPrice()) %></td>
                        <td><%= product.getQuantity() %></td>
                        <td><img src="<%= product.getImg() %>" alt="<%= product.getProductName() %>" class="product-img"></td>
                    </tr>
                    <%
                            }
                        }
                    %>
                    <tr>
                        <!-- Display the discount -->
                        <th colspan="5" class="text-right">Discount:</th>
                        <td>&euro;<%= String.format("%.2f", request.getAttribute("discount")) %></td>
                    </tr>
                </tbody>
                <!-- Display Total Price -->
                <tfoot>
                    <tr>
                        <td colspan="5" class="text-right">Total Price:</td>
                        <th>&euro;<%= String.format("%.2f", request.getAttribute("totalPrice")) %></th>
                    </tr>
                </tfoot>
            </table>
            <!-- Delivery Information Form -->
            <h2 class="text-center">Delivery Information</h2>
            <form action="DeliveryServlet" method="post">
                <div class="form-group">
                    <label for="name">Full Name:</label>
                    <input type="text" class="form-control" id="name" name="name" required>
                </div>
                <div class="form-group">
                    <label for="email">Email:</label>
                    <input type="email" class="form-control" id="email" name="email" required>
                </div>
                <div class="form-group">
                    <label for="address">Address:</label>
                    <input type="text" class="form-control" id="address" name="address" required>
                </div>
                <div class="form-group">
                    <label for="address1">Address1:</label>
                    <input type="text" class="form-control" id="address1" name="address1" required>
                </div>  
                <div class="form-group">
                    <label for="city">City:</label>
                    <input type="text" class="form-control" id="city" name="city" required>
                </div>
                <div class="form-group">
                    <label for="phone">Phone Number:</label>
                    <input type="text" class="form-control" id="phone" name="phone" required>
                </div>
                <a href="javascript:history.back()" class="btn btn-primary">Back</a>
                <button type="submit" class="btn btn-primary">Submit</button>
            </form>
        </div>
    </div>
    <br><br><br><br>
    <!-- JQuery -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
    <!-- Bootstrap Bundle -->
    <script src="js/bootstrap.bundle.js"></script>
</body>
</html>