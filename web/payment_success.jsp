<%-- 
    Document   : payment_success
    Created on : 30 Jul 2024, 11:13:03
    Author     : arets
--%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <!-- Page Title -->
    <title>Payment Success</title>
    <!-- Bootstrap CSS -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <!-- Custom CSS -->
    <link rel="stylesheet" href="css/style1.css" />
    <link rel="stylesheet" href="css/payment_style.css" />
    <!-- Fork Awesome for Icons -->
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fork-awesome@1.1.7/css/fork-awesome.min.css" integrity="sha256-gsmEoJAws/Kd3CjuOQzLie5Q3yshhvmo7YNtBG7aaEY=" crossorigin="anonymous">
    <!-- Favicon -->
    <link rel="icon" href="img/Logo-Bike-96x96.png" type="image/png" />
    <!-- Responsive Meta Tag -->
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
</head>
<body>
<!-- Navigation Bar -->
<nav class="navbar navbar-expand-lg navbar-dark navbar-black bg-black fixed-top pt-4 pb-4 text-center">
    <div class="container">
        <!-- Brand Logo -->
        <a class="navbar-brand montblack" href="index.html"><img src="img/Logo-Bike-96x96.png" width="40" height="40" /> Mountain Ride </a>
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
<!-- Payment Success Message -->
<div class="container8">
    <h2>Payment Successful</h2>
    <p>Thank you for your purchase! Your payment has been successfully processed.</p>
    <a href="index.html" class="btn btn-primary">Return to Home</a>
</div>
<!-- JQuery -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
<!-- Bootstrap Bundle -->
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>
