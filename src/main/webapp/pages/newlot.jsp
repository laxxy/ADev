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
  <link href="<c:url value="/assets/core/css/prettyPhoto.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/price-range.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/animate.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/main.css"/>" rel="stylesheet">
  <link href="<c:url value="/assets/core/css/responsive.css"/>" rel="stylesheet">
</head><!--/head-->

<body>
<!--/ins. header-->
<tiles:insertDefinition name="header"/>

<section id="cart_items">
  <div class="container">
    <div class="breadcrumbs">
      <ol class="breadcrumb">
        <li><a href="/">Home</a></li>
        <li class="active">New Lot</li>
      </ol>
    </div><!--/breadcrums-->

    <div class="step-one">
      <h2 class="heading">Step1</h2>
    </div>
    <form:form method="post" commandName="lot" modelAttribute="lot" action="/new" enctype="multipart/form-data">
      <div class="shopper-informations">
        <div class="row">
          <div class="col-sm-3">
            <div class="shopper-info">
              <p>New lot information</p>
              <form:input path="lotName" placeholder="Lot name" cssClass="form-control"/>
              <h2>Select Category</h2>
              <div class="container">
                <div class="row" id="drop">
                  <div class="dropdown" id="select">
                    <a id="dLabel" role="button" data-toggle="dropdown" class="btn btn-primary" data-target="#">
                      Dropdown <span class="caret"></span>
                    </a>
                    <ul class="dropdown-menu multi-level" role="menu" aria-labelledby="dropdownMenu">
                    <c:forEach items="${categories}" var="category">
                      <li class="dropdown-submenu">
                        <a tabindex="-1" href="#">${category.name}</a>
                        <ul class="dropdown-menu">
                          <c:forEach items="${category.subCategories}" var="subcategory">
                          <li><a tabindex="-1" onclick="selectCategory('${subcategory.name}')">${subcategory.name}</a></li>
                          </c:forEach>
                        </ul>
                      </li>
                    </c:forEach>
                      <script>
                        function selectCategory(name) {
                          document.getElementById("select").remove();
                          $('#drop').append('<input name="subname" value="'+name+'" readonly/>');
                        }
                      </script>
                    </ul>
                  </div>
                </div>
              </div>
            </div>
          </div>
          <div class="col-sm-5 clearfix">
              <div class="info">
                <p>User information</p>
                <div class="form-one">
                  <form>
                    <form:label path="user" cssClass="form-control">${user.firstName}*</form:label>
                    <form:label path="user" cssClass="form-control">${user.email}*</form:label>
                    <form:label path="user" cssClass="form-control">${user.telephone}*</form:label>
                    <form:label path="user" cssClass="form-control">Skype*</form:label>
                  </form>
                </div>
                <div class="form-two">
                  <div class="form-group">
                    <label for="image1">File input</label>
                    <input type="file" name="image1" id="image1">
                    <p class="help-block"></p>
                    <label for="image2">File input</label>
                    <input type="file" name="image2" id="image2">
                    <p class="help-block"></p>
                    <label for="image3">File input</label>
                    <input type="file" name="image3" id="image3">
                    <p class="help-block"></p>
                  </div>
                </div>
              </div>
          </div>
          <div class="col-sm-4">
            <div class="order-message">
              <p>Information</p>
              <textarea name="message" placeholder="Notes about your lot" rows="16"></textarea>
            </div>
          </div>
          <button type="submit" class="btn btn-primary">Submit</button>
        </div>
      </div>
    </form:form>
  </div>
</section>

<tiles:insertDefinition name="footer"/>

<script src="<c:url value="/assets/core/js/jquery.js"/>"></script>
<script src="<c:url value="/assets/core/js/bootstrap.min.js"/>"></script>
<script src="<c:url value="/assets/core/js/jquery.scrollUp.min.js"/>"></script>
<script src="<c:url value="/assets/core/js/price-range.js"/>"></script>
<script src="<c:url value="/assets/core/js/jquery.prettyPhoto.js"/>"></script>
<script src="<c:url value="/assets/core/js/main.js"/>"></script>
<script src="<c:url value="/assets/core/js/ajax.req.js"/>"></script>
</body>
</html>