<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Page Metadata -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <!-- Page Title -->
    <title>Silage spot - Register</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/style1.css" />
    <!-- Fork Awesome for Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fork-awesome@1.1.7/css/fork-awesome.min.css" integrity="sha256-gsmEoJAws/Kd3CjuOQzLie5Q3yshhvmo7YNtBG7aaEY=" crossorigin="anonymous">
    <!-- Favicon -->
    <link rel="icon" href="img/Silage-Logo.png" type="image/png" />
    <style>
      footer {
    margin-top: auto; /* Pushes the footer to the bottom of the flex container */
    }  
    </style>
    <!-- Responsive Meta Tag -->
</head>
<body>
        <!-- Navigation Bar -->
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
    <!-- Payment Success Message -->
    <div class="container8">
        <h2>Payment Successful</h2>
        <p>Thank you for your purchase! Your payment has been successfully processed.</p>
        <a href="index.html" class="btn btn-primary">Return to Home</a>
    </div>
    <br><!-- <br> -->   
    <br>   
    <br>   
    <br>  
    <br>   
    <br>  
    <br>
    <br>  
    <br>   
    <br>  
    <br>   
    <br>  
    <br>   
    <br>  
    <br>
    <br>  
    <br>   
    <br>  
    <br>
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
