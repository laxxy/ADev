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
	<section id="slider"><!--slider-->
		<div class="container">
			<div class="row">
				<div class="col-sm-12">
					<div id="slider-carousel" class="carousel slide" data-ride="carousel">
						<ol class="carousel-indicators">
							<li data-target="#slider-carousel" data-slide-to="0" class="active"></li>
							<li data-target="#slider-carousel" data-slide-to="1"></li>
							<li data-target="#slider-carousel" data-slide-to="2"></li>
						</ol>

						<div class="carousel-inner">
							<div class="item active">
								<div class="col-sm-6">
									<h1><span>E</span>-SHOPPER</h1>
									<h2>Free E-Commerce Template</h2>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
									<button type="button" class="btn btn-default get">Get it now</button>
								</div>
								<div class="col-sm-6">
									<!--<img src="images/home/girl1.jpg" class="girl img-responsive" alt="" />
									<img src="images/home/pricing.png"  class="pricing" alt="" />-->
								</div>
							</div>
							<div class="item">
								<div class="col-sm-6">
									<h1><span>E</span>-SHOPPER</h1>
									<h2>100% Responsive Design</h2>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
									<button type="button" class="btn btn-default get">Get it now</button>
								</div>
								<div class="col-sm-6">
									<!--<img src="images/home/girl2.jpg" class="girl img-responsive" alt="" />
									<img src="images/home/pricing.png"  class="pricing" alt="" />-->
								</div>
							</div>

							<div class="item">
								<div class="col-sm-6">
									<h1><span>E</span>-SHOPPER</h1>
									<h2>Free Ecommerce Template</h2>
									<p>Lorem ipsum dolor sit amet, consectetur adipisicing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. </p>
									<button type="button" class="btn btn-default get">Get it now</button>
								</div>
								<div class="col-sm-6">
									<!--<img src="images/home/girl3.jpg" class="girl img-responsive" alt="" />
									<img src="images/home/pricing.png" class="pricing" alt="" />-->
								</div>
							</div>

						</div>

						<a href="#slider-carousel" class="left control-carousel hidden-xs" data-slide="prev">
							<i class="fa fa-angle-left"></i>
						</a>
						<a href="#slider-carousel" class="right control-carousel hidden-xs" data-slide="next">
							<i class="fa fa-angle-right"></i>
						</a>
					</div>

				</div>
			</div>
		</div>
	</section><!--/slider-->

	<section>
		<div class="container">
			<div class="row">
				
				<div class="col-sm-9 padding-right">
					
					<div class="recommended_items"><!--recommended_items-->
						<h2 class="title text-center">recommended items</h2>
						
						<div id="recommended-item-carousel" class="carousel slide" data-ride="carousel">
							<div class="carousel-inner">
								<div class="item active">
									<c:forEach items="${recommended1}" var="item">
										<div class="col-sm-4">
											<div class="product-image-wrapper">
												<div class="single-products">
													<div class="productinfo text-center">
														<img src="<c:url value="/assets/core/images/home/empty_cart.png"/>" alt="" />
														<h2>${item.bidCurrent} $</h2>
														<p>${item.lotInfo}</p>
														<a href="/lot/${lot.id}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>More</a>
													</div>
												</div>
											</div>
										</div>
									</c:forEach>
								<div class="item">
									<c:forEach items="${recommended2}" var="item">
										<div class="col-sm-4">
											<div class="product-image-wrapper">
												<div class="single-products">
													<div class="productinfo text-center">
														<img src="<c:url value="/assets/core/images/home/empty_cart.png"/>" alt="" />
														<h2>${item.bidCurrent} $</h2>
														<p>${item.lotInfo}</p>
														<a href="/lot/${lot.id}" class="btn btn-default add-to-cart"><i class="fa fa-shopping-cart"></i>More</a>
													</div>
												</div>
											</div>
										</div>
										</c:forEach>
									</div>
								</div>
							</div>
							 <a class="left recommended-item-control" href="#recommended-item-carousel" data-slide="prev">
								<i class="fa fa-angle-left"></i>
							  </a>
							  <a class="right recommended-item-control" href="#recommended-item-carousel" data-slide="next">
								<i class="fa fa-angle-right"></i>
							  </a>			
						</div>
					</div><!--/recommended_items-->
					
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