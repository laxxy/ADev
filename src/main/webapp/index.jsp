<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en-US">
<head>
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8" />
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Sedulous - Best Free Bootstrap Template</title>
    <link href="<c:url value="/assets/main/style.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/main/css/bootstrap.min.css"/>" rel="stylesheet">
    <link href="<c:url value="/assets/main/css/font-awesome.min.css"/>" rel="stylesheet">
    <link href='http://fonts.googleapis.com/css?family=Raleway:400,700,200,100,300,500,600,800,900' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Pacifico' rel='stylesheet' type='text/css'>
    <link href='http://fonts.googleapis.com/css?family=Source+Sans+Pro:200,300,400,600,700,700italic,900,400italic,300italic' rel='stylesheet' type='text/css'>
    <script src="http://ajax.googleapis.com/ajax/libs/jquery/1.9.1/jquery.min.js"></script>
    <script src="<c:url value="/assets/main/js/jquery.sticky.js"/>"></script>
    <script>
        $(window).load(function(){
            $("#menu").sticky({ topSpacing: 0 });
        });
    </script>
    <script>
        jQuery(document).ready(function($) {

            $("#searchform").submit(function(event) {

                // Disble the search button
                enableSearchButton(false);

                // Prevent the form from submitting via the browser.
                event.preventDefault();

                searchViaAjax();

            });

        });

        function searchViaAjax() {

            var search = {};
            search["searchString"] = $("#searchinput").val();

            $.ajax({
                type : "POST",
                contentType : "application/json",
                url : "/search",
                data : JSON.stringify(search),
                dataType : 'json',
                timeout : 100000,
                success : function(data) {
                    display(data);
                },
                error : function(e) {
                    //console.log("ERROR: ", e);
                    display(e);
                },
                done : function(e) {
                    //console.log("DONE");
                    enableSearchButton(true);
                }
            });

        }

        function enableSearchButton(flag) {
            $("#btnsearch").prop("disabled", flag);
        }

        function display(data) {
            var json = "<pre>" + JSON.stringify(data, null, 4) + "</pre>";
            $('#feedback').html(json);
        }
    </script>
</head>
<body>
<div class="container-fluid banner text-center" id="banner">
    <div class="row">
        <div class="col-md-12 line">
            <div class="tablebox">
                <div class="banner-text" id="bannertext">
                    <h1 class="hostyle" id="heads">Sedulous</h1>
                    <p class="pstyle">We are Creative</p>
                    <a href="#features" class="page-scroll arrow"><i class="fa fa-angle-down"></i></a>
                </div>
            </div>
        </div>
    </div>
</div>
<div class="navbar menubar" id="menu">
    <div class="container">
        <div class="navbar-header">
            <button type="button" class="navbar-toggle menu-button" data-toggle="collapse" data-target="#myNavbar">
                <span class="glyphicon glyphicon-align-justify"></span>
            </button>
            <a class="navbar-brand logo" href="#">Sedulous</a>
        </div>
        <div>
            <nav class="collapse navbar-collapse" id="myNavbar" role="navigation">
                <ul class="nav navbar-nav navbar-right navstyle">
                    <li><a href="#banner" class="page-scroll active">Home</a></li>
                    <li><a href="#features" class="page-scroll">Features</a></li>
    <li><a href="#search" class="page-scroll">Поиск</a></li>
    <li><a href="#about" class="page-scroll">About</a></li>
    <li><a href="#testimonials" class="page-scroll">Testimonials</a></li>
    <li><a href="#contact" class="page-scroll">Contact</a></li>
    </ul>
    </nav>
</div>
</div>
</div>
<div class="search" id="search">
    <div class="container">
        <div class="row">
            <div class="col-md-8 col-sm-12 col-xs-12">

                <div id="feedback"></div>

                <div class="subscibe">
                    <form role="form" id="searchform">
                        <div class="col-md-8 col-sm-10 col-xs-12">
                            <div class="form-group">
                                <input type="text" class="form-control" id="searchinput" placeholder="Поиск.....">
                            </div>
                        </div>
                        <div class="col-md-4 col-sm-2 col-xs-12 centertext">
                            <button type="submit" class="btn btn-or" id="btnsearch">Искать</button>
                        </div>
                    </form>
                </div>
            </div>
            <div class="col-md-4 col-sm-12 col-xs-12 text-center">
                <ul class="social">
                    <li><a href="#"><i class="fa fa-twitter-square"></i></a></li>
                    <li><a href="#"><i class="fa fa-facebook-square"></i></a></li>
                    <li><a href="#"><i class="fa fa-google-plus-square"></i></a></li>
                    <li><a href="#"><i class="fa fa-linkedin-square"></i></a></li>
                </ul>
            </div>
        </div>
    </div>
</div>

<footer class="foot">
    <div class="container">
        <div class="row">
            <div class="col-md-12 col-sm-12 col-xs-12">
                <h4 class="foot-logo">Scedulous</h4>
            </div>
            <div class="col-md-12 col-xs-12 col-sm-12 text-center">
                <p class="foot-text">Copyrights &copy; 2015 | Sedulous Designed By : <a href="http://html5layouts.com">HTML5 Layouts</a>.</p>
            </div>
        </div>
    </div>
</footer>
</body>
</html>