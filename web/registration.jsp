<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <!-- Page Metadata -->
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>The Silage Spot - Register</title>

    <!-- CSS Files -->
    <link rel="stylesheet" href="css/bootstrap.min.css" />
    <link rel="stylesheet" href="css/style1.css" />
    <link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/fork-awesome@1.1.7/css/fork-awesome.min.css" integrity="sha256-gsmEoJAws/Kd3CjuOQzLie5Q3yshhvmo7YNtBG7aaEY=" crossorigin="anonymous">
    <link rel="icon" href="img/Silage-Logo.png" type="image/png" />
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
                    <a class="btn btn-outline-primary" href="login.html"><i class="fa fa-key"></i> Login</a>
                    <a class="btn btn-outline-primary" href="registration.html"><i class="fa fa-user-plus"></i> Register</a>
                </div>
            </div>
        </div>
    </nav>

<!-- Registration Form Section -->
<section class="welcome">
    <div class="container">
        <div class="row justify-content-center">
            <div class="col-lg-6 login">
                <form action="RegistrationServlet" method="post" class="form">
                    <h1 class="ProductSans text-black bold">Sign Up</h1>
                    <p class="montlight text-black bold">Fill out your information below to Sign Up!</p>

                    <!-- Error Message Display -->
                    <c:if test="${not empty errorMessage}">
                        <div class="alert alert-danger" role="alert">
                            ${errorMessage}
                        </div>
                    </c:if>

                    <!-- Form Fields -->
                    <div class="form-group">
                        <input type="text" class="form-control" id="name" name="name" placeholder="Username" required>
                    </div>
                    <div class="form-group">
                        <input type="password" class="form-control" name="pass" placeholder="Password" required>
                    </div>
                    <div class="form-group">
                        <input type="email" class="form-control" name="email" placeholder="Email" required>
                    </div>
                    <div class="form-group">
                        <select name="PostalRegion" id="postal" class="form-control">
                            <option value="" disabled selected>Select Your Region</option>
                            <option>Africa</option>
                            <option>Australia</option>
                            <option>Asia</option>
                            <option>Europe</option>
                            <option>North America</option>
                            <option>South America</option>
                        </select>
                    </div>
                    <input type="submit" class="btn btn-outline-warning ProductSans" value="REGISTER"/>
                    <p class="montlight black bold">Already a member? <a href="login.html">Login!</a></p>
                </form>
            </div>
        </div>
    </div>
</section>

<!-- JQuery -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<!-- Bootstrap tooltips -->
<script type="text/javascript" src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.14.4/umd/popper.min.js"></script>
<script src="js/bootstrap.bundle.js"></script>
</body>
</html>
