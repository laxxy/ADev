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

    <div class="shopper-informations">
      <div class="row">
        <div class="col-sm-3">
          <div class="shopper-info">
            <p>New lot information</p>
            <form>
              <input type="text" placeholder="Name">
              <form>
                <input type="text" placeholder="Category*">
                <select>
                  <option>-- Category --</option>
                  <option>United States</option>
                  <option>Bangladesh</option>
                  <option>UK</option>
                  <option>India</option>
                  <option>Pakistan</option>
                  <option>Ucrane</option>
                  <option>Canada</option>
                  <option>Dubai</option>
                </select>
              </form>
            </form>
          </div>
        </div>
        <div class="col-sm-5 clearfix">
          <div class="info">
            <p>Bill To</p>
            <div class="form-one">
              <form>
                <input type="text" placeholder="${user.firstName}*">
                <input type="text" placeholder="${user.email}*">
                <input type="text" placeholder="${user.telephone}*">
                <input type="text" placeholder="Skype*">
              </form>
            </div>
            <div class="form-two">
              <div class="form-group">
                <label for="InputFile1">File input</label>
                <input type="file" id="InputFile1">
                <p class="help-block"></p>
                <label for="InputFile2">File input</label>
                <input type="file" id="InputFile2">
                <p class="help-block"></p>
                <label for="InputFile3">File input</label>
                <input type="file" id="InputFile3">
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
        <a class="btn btn-primary" href="">Continue</a>
      </div>
    </div>
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