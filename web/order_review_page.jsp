<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.List" %>
<%@ page import="Javas.Product" %>
<%
    // Retrieve order details from session and request attributes
    float totalPrice = (float) session.getAttribute("totalPrice");
    float discount = (float) session.getAttribute("discount");
    List<Product> products = (List<Product>) session.getAttribute("products");
    String name = (String) request.getAttribute("name");
    String address = (String) request.getAttribute("address");
    String address1 = (String) request.getAttribute("address1");
    String city = (String) request.getAttribute("city");
    String phone = (String) request.getAttribute("phone");
    String email = (String) request.getAttribute("email");
%>
<!DOCTYPE html>
<html>
<head>
    <!-- Page Metadata -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Page Title -->
    <title>Silage Spot - Order Review</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/style1.css" />
    <!-- Fork Awesome for Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fork-awesome@1.1.7/css/fork-awesome.min.css" integrity="sha256-gsmEoJAws/Kd3CjuOQzLie5Q3yshhvmo7YNtBG7aaEY=" crossorigin="anonymous">
    <!-- Favicon -->
    <link rel="icon" href="img/Silage-Logo.png" type="image/png" />
    <!-- Responsive Meta Tag -->
</head>
<body>
    <!-- Navigation Bar -->
    <nav class="navbar navbar-expand-lg navbar-light bg-light fixed-top">
        <div class="container">
            <a class="navbar-brand montblack" href="index.html">
                <img src="img/Silage-Logo.png" width="40" height="40" alt="Logo"> Sillage Spot
            </a>
            <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarText" aria-controls="navbarText" aria-expanded="false" aria-label="Toggle navigation">
                <span class="navbar-toggler-icon"></span>
            </button>
            <div class="collapse navbar-collapse" id="navbarText">
                <form class="form-inline my-2 my-lg-0">
                    <div class="input-group">
                        <input class="form-control mr-sm-2" type="search" placeholder="Search" aria-label="Search">
                        <div class="input-group-append">
                            <button class="btn btn-outline-warning" type="button"><i class="fa fa-search"></i></button>
                        </div>
                    </div>
                    <ul class="navbar-nav mr-auto">
                        <li class="nav-item active">
                            <a class="nav-link" href="index.html">Home</a>
                        </li>
                        <li class="nav-item">
                            <a class="nav-link" href="contact.html">Contact Us</a>
                        </li>
                    </ul>
                </form>    
                <div class="nav-right-links">
                    <c:if test="${not empty sessionScope.user}">
                        <a class="dropdown-item text-white" href="index.html"><i class="fa fa-sign-out" aria-hidden="true"></i> Logout</a>
                    </c:if>
                </div>
            </div>
        </div>
    </nav>

    <!-- Order Review Section -->
    <div class="container mt-5 pt-5">
        <h3 class="text-center mb-4">Thank you for your order! Please review the details below:</h3>
        
        <!-- Products Table -->
        <table class="table table-bordered">
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
                    if (products != null) {
                        for (Product product : products) {
                %>
                            <tr>
                                <td><%= product.getId() %></td>
                                <td><%= product.getProductType() %></td>
                                <td><%= product.getProductName() %></td>
                                <td>€<%= product.getPrice() %></td>
                                <td><%= product.getQuantity() %></td>
                                <td><img src="<%= product.getImg() %>" alt="<%= product.getProductName() %>" style="height: auto; width: 150px;"></td>
                            </tr>
                <% 
                        }
                    }
                %>
                <tr>
                    <th colspan="5" class="text-right">Discount:</th>
                    <td>€<%= String.format("%.2f", discount) %></td>
                </tr>
            </tbody>
            <tfoot>
                <tr>
                    <th colspan="5" class="text-right">Total Price:</th>
                    <td>€<%= String.format("%.2f", totalPrice) %></td>
                </tr>
            </tfoot>
        </table>

        <!-- User Details Section -->
        <div class="card mb-4">
            <div class="card-header">
                <h5>User Details:</h5>
            </div>
            <div class="card-body">
                <p><strong>Name:</strong> <%= name %></p>
                <p><strong>Address:</strong> <%= address %></p>
                <p><strong>Address1:</strong> <%= address1 %></p>
                <p><strong>City:</strong> <%= city %></p>
                <p><strong>Phone:</strong> <%= phone %></p>
                <p><strong>Email:</strong> <%= email %></p>
            </div>
        </div>

        <!-- Navigation Buttons -->
        <div class="text-center">
            <a href="javascript:history.back()" class="btn btn-primary">Back</a>
            <form action="payment_details_form.jsp" method="post" style="display:inline;">
                <input type="hidden" name="totalPrice" value="<%= totalPrice %>">
                <button type="submit" class="btn btn-primary">Proceed to Payment</button>
            </form>
        </div>
    </div>
    <section>
        <!-- Footer -->
        <footer class="footer">
            <div class="footer-left">
                <p>&copy; 2025 The Silage Spot. All rights reserved.</p>
            </div>
            <div class="footer-links">
                <a href="index.html">Home</a>
                <a href="About.html">About Us</a>
                <a href="contact.html">Contact Us</a>
                <a href="termsOfServices.html">Terms of Services</a>
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
