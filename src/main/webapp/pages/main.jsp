<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
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
  <script src="<c:url value="/assets/core/js/jquery.js"/>"></script>
  <script src="<c:url value="/assets/core/js/bootstrap.min.js"/>"></script>
  <script src="<c:url value="/assets/core/js/jquery.scrollUp.min.js"/>"></script>
  <script src="<c:url value="/assets/core/js/price-range.js"/>"></script>
  <script src="<c:url value="/assets/core/js/main.js"/>"></script>
  <script src="<c:url value="/assets/core/js/ajax.req.js"/>"></script>
  <script src="<c:url value="/assets/core/js/jquery.bootpag.min.js"/>"></script>
</head><!--/head-->

<body>
<tiles:insertDefinition name="header"/>
<section>
  <div class="container">
    <div class="row">
      <div class="col-sm-3">
        <div class="left-sidebar">
          <div class="price-range"><!--price-range-->
            <h2>Price Range</h2>
            <div class="well text-center">
              <input type="text" class="span2" value="" data-slider-min="0" data-slider-max="600" data-slider-step="5" data-slider-value="[250,450]" id="sl2" ><br />
              <b class="pull-left">$ 0</b> <b class="pull-right">$ 600</b>
            </div>
          </div><!--/price-range-->
        </div>
      </div>

      <div class="col-sm-9 padding-right">
        <div id="page-selection">${noData}</div>
        <div id="content"></div>
        <script>
          getData();
          $('#page-selection').bootpag({
            total: ${size},
            maxVisible: 10
          }).on("page", function(event, num){
            getData(num)
          });
        </script>
      </div>
    </div>
  </div>
</section>
<tiles:insertDefinition name="footer"/>
</body>
</html>