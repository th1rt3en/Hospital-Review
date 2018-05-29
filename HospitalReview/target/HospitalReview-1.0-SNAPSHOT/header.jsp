<%@page import="Model.User"%>
<%@page import="DAO.UserDAO"%>
<%
    UserDAO udao = new UserDAO();
    User user = udao.authorization(request.getSession(), request.getCookies());
%>
<!DOCTYPE html>
<html lang="en">

<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1, shrink-to-fit=no">
    <meta http-equiv="x-ua-compatible" content="ie=edge">
    <!-- Font Awesome -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/font-awesome/4.7.0/css/font-awesome.min.css">
    <!-- Bootstrap core CSS -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <!-- Material Design Bootstrap -->
    <link href="css/mdb.min.css" rel="stylesheet">
    <!-- Your custom styles (optional) -->
    <link href="css/style.css" rel="stylesheet">
</head>

<body>

    <!-- Start your project here-->
    
               <!--Main Navigation-->
<header>
    <div style="height: 20px">
        <div class="flex-center flex-column">
            <form method="get" action="LoginServlet">
                <div class="modal fade" id="modalLoginForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header text-center">
                                <h4 class="modal-title w-100 font-weight-bold">Sign in</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <input type="hidden" name="query" value="login"/>
                            <div class="modal-body mx-3">
                                <div class="md-form mb-5">
                                    <i class="fa fa-envelope prefix grey-text"></i>
                                    <input type="email" name="email" id="defaultForm-email" class="form-control validate">
                                    <label data-error="wrong" data-success="right" for="defaultForm-email">Your email</label>
                                </div>

                                <div class="md-form mb-4">
                                    <i class="fa fa-lock prefix grey-text"></i>
                                    <input type="password" name="password" id="defaultForm-pass" class="form-control validate">
                                    <label data-error="wrong" data-success="right" for="defaultForm-pass">Your password</label>
                                </div>

                            </div>
                            <div class="modal-footer d-flex justify-content-center">
                                <input type="submit" value="Login" class="btn btn-default" />
                            </div>
                        </div>
                    </div>
                </div>
            </form>       
            <form method="get" action="RegisterServlet">
                <div class="modal fade" id="modalRegisterForm" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                    <div class="modal-dialog" role="document">
                        <div class="modal-content">
                            <div class="modal-header text-center">
                                <h4 class="modal-title w-100 font-weight-bold">Sign up</h4>
                                <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                                    <span aria-hidden="true">&times;</span>
                                </button>
                            </div>
                            <input type="hidden" name="query" value="register"/>
                            <div class="modal-body mx-3">
                                <div class="md-form mb-5">
                                    <i class="fa fa-user prefix grey-text"></i>
                                    <input type="text" id="orangeForm-name" class="form-control validate">
                                    <label data-error="wrong" data-success="right" for="orangeForm-name">Your name</label>
                                </div>
                                <div class="md-form mb-5">
                                    <i class="fa fa-envelope prefix grey-text"></i>
                                    <input type="email" name="email" id="orangeForm-email" class="form-control validate">
                                    <label data-error="wrong" data-success="right" for="orangeForm-email">Your email</label>
                                </div>

                                <div class="md-form mb-4">
                                    <i class="fa fa-lock prefix grey-text"></i>
                                    <input type="password" name="password" id="orangeForm-pass" class="form-control validate">
                                    <label data-error="wrong" data-success="right" for="orangeForm-pass">Your password</label>
                                </div>

                            </div>
                            <div class="modal-footer d-flex justify-content-center">
                                <input type="submit" value="Sign up" class="btn btn-deep-orange" />
                            </div>
                        </div>
                    </div>
                </div>
            </form>
        </div>
    </div>
    <nav class="navbar fixed-top navbar-expand-lg navbar-dark blue-grey scrolling-navbar">
        <a class="navbar-brand" href="#"><strong>Hospital Project</strong></a>
        <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarSupportedContent">
            <ul class="navbar-nav mr-auto">
                <li class="nav-item active">
                    <a class="nav-link" href="#">Home <span class="sr-only">(current)</span></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Search Doctor</a>
                </li>
                <%
                    if (user != null && user.getType().equals("Hospital")) {
                %>
                <li class="nav-item">
                    <a class="nav-link" href="#">Doctors Control Panel</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Hospital Profile</a>
                </li>
                <%
                    }
                    else if (user != null && user.getType().equals("Admin")) {
                %>
                <li class="nav-item">
                    <a class="nav-link" href="#">Doctors Control Panel</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Hospital Control Panel</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="#">Admin Control Panel</a>
                </li>
                <%
                    }
                %>
            </ul>
            <ul class="navbar-nav nav-flex-icons">
                <%
                    if (user == null) {
                %>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="modal" data-target="#modalLoginForm">Login</a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" data-toggle="modal" data-target="#modalRegisterForm">Register</a>
                </li>
                <%
                    }
                    else {
                %>
                <li class="nav-item">
                    <a class="nav-link"><%= user.getEmail() %></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link" href="LogoutServlet?query=logout">Logout</a>
                </li>
                <%
                    }
                %>
                <li class="nav-item">
                    <a class="nav-link"><i class="fa fa-facebook"></i></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"><i class="fa fa-twitter"></i></a>
                </li>
                <li class="nav-item">
                    <a class="nav-link"><i class="fa fa-instagram"></i></a>
                </li>
            </ul>
        </div>
    </nav>

</header>
<!--Main Navigation-->
    <!-- /Start your project here-->

    <!-- SCRIPTS -->
    <!-- JQuery -->
    <script type="text/javascript" src="js/jquery-3.3.1.min.js"></script>
    <!-- Bootstrap tooltips -->
    <script type="text/javascript" src="js/popper.min.js"></script>
    <!-- Bootstrap core JavaScript -->
    <script type="text/javascript" src="js/bootstrap.min.js"></script>
    <!-- MDB core JavaScript -->
    <script type="text/javascript" src="js/mdb.min.js"></script>
</body>

</html>
