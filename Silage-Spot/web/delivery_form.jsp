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
    <!-- Navigation Bar -->
    </nav>
        <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
        <div class="container">
            <!-- Brand Logo -->
            <a class="navbar-brand montblack" href="index.html">
                <img src="img/Silage-Logo.png" width="40" height="40" alt="Logo"> Silage Spot
            </a>
            <!-- Toggler Button for Collapsed Navigation -->
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <!-- Navbar Links -->
            <div class="collapse navbar-collapse" id="navbarText">
                <form class="form-inline my-2 my-lg-0">
                    <div class="input-group">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                        <div class="input-group-append">
                            <button class="btn btn-outline-warning" type="button"><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item dropdown">
                            <a class="nav-link dropdown-toggle" href="#" id="dropshop" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                                SHOP
                            </a>
                            <div class="dropdown-menu" aria-labelledby="dropshop">
                                <a class="dropdown-item" href="QueryServlet?producttype=Women Perfume">Women Perfume</a>
                                <a class="dropdown-item" href="QueryServlet?producttype=Men Perfume">Men Perfume</a>
                                <a class="dropdown-item" href="QueryServlet?producttype=Unisex Perfume">Unisex Perfume</a>
                                <a class="dropdown-item" href="QueryServlet?producttype=Perfume">Perfume</a>
                                <div class="dropdown-divider"></div>
                                <a class="dropdown-item" href="cart.jsp"><i class="fa fa-shopping-cart"></i> Cart</a>
                            </div>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="contact.html">Contact Us</a>
                        </li>
                    </ul>
                </form>    
                <div class="nav-right-links">
                    <!-- Logout button will only be visible if the user is logged in -->
                    <c:if test="${not empty sessionScope.user}">
                        <a class="dropdown-item text-white" href="index.html"><i class="fa fa-sign-out" aria-hidden="true"></i> Logout</a>
                    </c:if>
                </div>
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
    <br><br>
    <br><br>
    <section>
        <!-- Footer -->
        <footer class="footer">
            <div class="footer-left">
                <p>&copy; 2025 The Silage Spot. All rights reserved.</p>
            </div>
            <div class="footer-links">
                <a href="index.html">Home</a>
                <a href="Perfume.html">Perfumes</a>
                <a href="About.html">About</a>
                <a href="contact.html">Contact Us</a>
                <a href="https://www.facebook.com/YourPageName" target="_blank">
                    <img src="img/facebook39.png" alt="Facebook">
                </a>
                <a href="https://www.twitter.com/YourPageName" target="_blank">
                    <img src="img/twitter39.png" alt="Twitter">
                </a>
                <a href="https://www.instagram.com/YourPageName" target="_blank">
                    <img src="img/Instagram39.png" alt="Instagram">
                </a>
            </div>
        </footer>
    </section>   
    <!-- JQuery -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
    <!-- Bootstrap Bundle -->
    <script src="js/bootstrap.bundle.js"></script>
</body>
</html>