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
  <link href="<c:url value="/assets/core/css/price-range.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/animate.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/main.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/responsive.css"/>" rel="stylesheet">
</head><!--/head-->
<body>
<tiles:insertDefinition name="header"/>
<section>
  <sec:authorize access="isAuthenticated()">
  <div class="container">
    <div class="row">
      <div class="col-sm-9 padding-right">
        <div class="category-tab"><!--panel-tab-->
          <div class="col-sm-12">
            <ul class="nav nav-tabs">
              <li class="active"><a href="#active" data-toggle="tab">Active lots</a></li>
              <li><a href="#blazers" data-toggle="tab">Past</a></li>
            </ul>
          </div>
          <div class="tab-content">
            <div class="tab-pane fade active in" id="active">
              <c:forEach items="${lots}" var="lot">
                <div class="col-sm-3">
                  <div class="product-image-wrapper">
                    <div class="single-products">
                      <div class="productinfo text-center">
                        <!--<img src="images/home/gallery1.jpg" alt="">-->
                        <h2>${lot.bidCurrent}</h2>
                        <p>${lot.lotName}</p>
                        <a href="/panel/chat" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>Income Messages</a>
                      </div>
                    </div>
                  </div>
                </div>
              </c:forEach>
            </div>
          </div>
        </div>
      </div>
    </div>
  </div>
  </sec:authorize>
  <sec:authorize access="!isAuthenticated()">
    <script>
      window.location = "/login";
    </script>
  </sec:authorize>
</section>

<tiles:insertDefinition name="footer"/>

<script src="<c:url value="/assets/core/js/jquery.js"/>"></script>
<script src="<c:url value="/assets/core/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/assets/core/js/jquery.scrollUp.min.js"/>"></script>
<script src="<c:url value="/assets/core/js/price-range.js"/>"></script>
<script src="<c:url value="/assets/core/js/jquery.prettyPhoto.js"/>"></script>
<script src="<c:url value="/assets/core/js/main.js"/>"></script>
</body>
</html>