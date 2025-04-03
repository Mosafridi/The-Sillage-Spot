<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Page Metadata -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Page Title -->
    <title>Silage Spot - Payment Details</title>
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
            <!-- Brand Logo -->
            <a class="navbar-brand montblack" href="index.html">
                <img src="img/Silage-Logo.png" width="40" height="40" alt="Logo"> Sillage Spot
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

    <!-- Payment Details Form Section -->
    <div class="container mt-5 pt-5">
        <h3 class="text-center mb-4">Payment Details</h3>

        <% 
            // Retrieve total price from request parameter
            float totalPrice = 0;
            String totalPriceParam = request.getParameter("totalPrice");
            if (totalPriceParam != null) {
                totalPrice = Float.parseFloat(totalPriceParam);
            }
        %>
        <div class="row">
            <div class="col-md-8 offset-md-2">
                <div class="alert alert-info text-center">
                    <strong>Total Price: €<%= String.format("%.2f", totalPrice) %></strong>
                </div>
                
                <form action="PaymentServlet" method="post">
                    <!-- Hidden field to pass total price to PaymentServlet -->
                    <input type="hidden" name="totalPrice" value="<%= totalPrice %>">

                    <!-- Card Number Input -->
                    <div class="form-group">
                        <label for="cardNumber">Card Number</label>
                        <input type="text" class="form-control" id="cardNumber" name="cardNumber" required placeholder="Enter your card number">
                    </div>

                    <!-- Name on Card Input -->
                    <div class="form-group">
                        <label for="cardName">Name on Card</label>
                        <input type="text" class="form-control" id="cardName" name="cardName" required placeholder="Enter name on card">
                    </div>

                    <!-- Expiry Date Input -->
                    <div class="form-group row">
                        <div class="col-md-6">
                            <label for="expiryDate">Expiry Date</label>
                            <input type="text" class="form-control" id="expiryDate" name="expiryDate" placeholder="MM/YY" required>
                        </div>
                        
                        <!-- CVV Input -->
                        <div class="col-md-6">
                            <label for="cvv">CVV</label>
                            <input type="text" class="form-control" id="cvv" name="cvv" required placeholder="CVV">
                        </div>
                    </div>

                    <!-- Submit Button -->
                    <div class="form-group text-center">
                        <button type="submit" class="btn btn-success btn-lg">Complete Payment</button>
                    </div>
                </form>
            </div>
        </div>
    </div>
    <br><!-- <br> -->   
    <br>   
    <br>   
    <br>  
    <br>   
    <br>  
    <br>                       
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
    <!-- JQuery -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
    <!-- Bootstrap Bundle -->
    <script src="js/bootstrap.bundle.js"></script>
</body>
</html>
