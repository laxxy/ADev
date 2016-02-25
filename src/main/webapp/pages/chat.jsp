<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://www.springframework.org/security/tags" prefix="sec" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="tiles" uri="http://tiles.apache.org/tags-tiles" %>
<!DOCTYPE html>
<html lang="en">
<head>
  <meta charset="utf-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <meta name="description" content="">
  <meta name="author" content="">
  <title>Home | J-PLATFORM</title>
  <link href="<c:url value="/assets/core/css/bootstrap.min.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/font-awesome.min.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/animate.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/main.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/responsive.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/chat.css"/>" rel="stylesheet">
</head><!--/head-->
<header id="header"><!--header-->
  <div class="header_top"><!--header_top-->
    <div class="container">
      <div class="row">
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
</header><!--/header-->
<body onload="getMessages(${messages.size()}, '${user.login}')">
<section id="cart_items">
  <div class="container">
        <div class="chat">
          <div class="chat-header clearfix">
            <div class="chat-about">
              <div class="chat-with">Messages : ${messages.size()}</div>
              <div class="chat-num-messages">${lot.lotName} </div>
            </div>
          </div>

          <div class="chat-history">
            <ul id="ms-data">
              <c:forEach items="${messages}" var="message">
                <c:choose>
                  <c:when test="${user.login eq message.author}">
                    <li class="clearfix">
                      <div class="message-data align-right">
                        <span class="message-data-time">${message.date}</span> &nbsp; &nbsp;
                        <span class="message-data-name">${message.author}</span> <i class="fa fa-circle me"></i>

                      </div>
                      <div class="message other-message float-right">
                        ${message.message}
                      </div>
                    </li>
                  </c:when>
                  <c:otherwise>
                    <li>
                      <div class="message-data">
                        <span class="message-data-name"><i class="fa fa-circle online"></i> ${message.author}</span>
                        <span class="message-data-time">${message.date}</span>
                      </div>
                      <div class="message my-message">
                          ${message.message}
                      </div>
                    </li>
                  </c:otherwise>
                </c:choose>
              </c:forEach>
            </ul>

          </div>

          <div class="chat-message clearfix">
            <textarea name="message-to-send" id="message-to-send" placeholder="Type your message" rows="3"></textarea>
            <button onclick="postMessage(document.getElementById('message-to-send').value)">Send</button>

          </div>

        </div>
    </div>
  </section>
<script id="message-template" type="text/x-handlebars-template">
  <li class="clearfix">
    <div class="message-data align-right">
      <span class="message-data-time" >{{time}}, Today</span> &nbsp; &nbsp;
      <span class="message-data-name" >Olia</span> <i class="fa fa-circle me"></i>
    </div>
    <div class="message other-message float-right">
      {{messageOutput}}
    </div>
  </li>
</script>
<script id="message-response-template" type="text/x-handlebars-template">
  <li>
    <div class="message-data">
      <span class="message-data-name"><i class="fa fa-circle online"></i> Vincent</span>
      <span class="message-data-time">{{time}}, Today</span>
    </div>
    <div class="message my-message">
      {{response}}
    </div>
  </li>
</script>
<script src="<c:url value="/assets/core/js/jquery.js"/>"></script>
<script src="<c:url value="/assets/core/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/assets/core/js/jquery.scrollUp.min.js"/>"></script>
<script src="<c:url value="/assets/core/js/price-range.js"/>"></script>
<script src="<c:url value="/assets/core/js/main.js"/>"></script>
<script src="<c:url value="/assets/core/js/chat.js"/>"></script>
</body>
</html>