<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<header id="header"><!--header-->
  <div class="header_top"><!--header_top-->
    <div class="container">
      <div class="row">
        <div class="col-sm-6">
          <div class="contactinfo">
            <ul class="nav nav-pills">
              <li><a href="#"><i class="fa fa-phone"></i> +0 00 00 00 000</a></li>
              <sec:authorize access="isAuthenticated()">
                <li><a href="/conversations"><i class="fa fa-envelope"></i> Income messages : 0</a></li>
              </sec:authorize>
            </ul>
          </div>
        </div>
        <div class="col-sm-6">
          <div class="social-icons pull-right">
            <ul class="nav navbar-nav">
              <li><a href="#"><i class="fa fa-facebook"></i></a></li>
              <li><a href="#"><i class="fa fa-twitter"></i></a></li>
              <li><a href="#"><i class="fa fa-linkedin"></i></a></li>
              <li><a href="#"><i class="fa fa-dribbble"></i></a></li>
              <li><a href="#"><i class="fa fa-google-plus"></i></a></li>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div><!--/header_top-->

  <div class="header-middle"><!--header-middle-->
    <div class="container">
      <div class="row">
        <div class="col-sm-4">
          <div class="col-sm-4">
            <div class="logo pull-left">
              <a href="/"><img src="<c:url value="/assets/core/images/home/logo.png"/>" alt="" /></a>
            </div>
          </div>
          <div class="btn-group pull-right">
            <div class="search_box pull-right">
              <input type="text" id="search" placeholder="Search" onkeypress="search(event)"/>
              <script>
                function search(event){
                  if(event.keyCode == 13) {
                    window.location = "/filter/" + document.getElementById("search").value;
                  }
                }
              </script>
            </div>
          </div>
        </div>
        <div class="col-sm-8">
          <div class="shop-menu pull-right">
            <ul class="nav navbar-nav">
              <li><a href="/panel"><i class="fa fa-user"></i> Account</a></li>
              <li><a href="/new"><i class="fa fa-star"></i> Post New </a></li>
              <!--<li><a href="checkout.html"><i class="fa fa-crosshairs"></i> Checkout</a></li>
              <li><a href="cart.html"><i class="fa fa-shopping-cart"></i> Cart</a></li>-->
              <sec:authorize access="!isAuthenticated()">
                <li><a href="/login"><i class="fa fa-lock"></i> Login</a></li>
              </sec:authorize>
              <sec:authorize access="isAuthenticated()">
                <li><a href="/logout"><i class="fa fa-lock"></i> Logout</a></li>
              </sec:authorize>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div><!--/header-middle-->

  <div class="header-bottom"><!--header-bottom-->
    <div class="container">
      <div class="row">
        <div class="col-sm-9">
          <div class="navbar-header">
            <button type="button" class="navbar-toggle" data-toggle="collapse" data-target=".navbar-collapse">
              <span class="sr-only">Toggle navigation</span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
              <span class="icon-bar"></span>
            </button>
          </div>
          <div class="mainmenu pull-left">
            <ul class="nav navbar-nav collapse navbar-collapse">
              <c:forEach items="${category}" var="cat">
                <li class="dropdown">
                  <a href="">${cat.name}
                    <i class="fa fa-angle-down"></i></a>
                  <ul role="menu" class="sub-menu">
                    <c:forEach items="${cat.subCategories}" var="subCat">
                      <li><a href="/filter/${subCat.name}">${subCat.name}</a></li>
                    </c:forEach>
                  </ul>
                </li>
              </c:forEach>
            </ul>
          </div>
        </div>
      </div>
    </div>
  </div><!--/header-bottom-->
</header><!--/header-->