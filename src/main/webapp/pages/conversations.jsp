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
<tiles:insertDefinition name="header"/>
<section id="cart_items">
    <div class="container">
        <div class="table-responsive cart_info">
            <table class="table table-condensed">
                <thead>
                <tr class="cart_menu">
                    <td class="chat">Conversations</td>
                    <td class="description"></td>
                    <td class="price">Price</td>
                    <td class="quantity">Quantity</td>
                    <td class="total">Total</td>
                    <td></td>
                </tr>
                </thead>
                ${nodata}
                <tbody>
                <c:forEach items="${chats}" var="chat">
                <tr>
                    <td class="cart_product">
                        <a href=""><img src="images/cart/one.png" alt=""></a>
                    </td>
                    <td class="cart_description">
                        <h4><a href="">${chat.lot.user.login}</a></h4>
                        <p>${chat.lot.lotName}</p>
                    </td>
                    <td class="cart_delete">
                        <a class="cart_quantity_delete" href=""><i class="fa fa-times"></i></a>
                    </td>
                </tr>
                </c:forEach>
                </tbody>
            </table>
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
</body>
</html>