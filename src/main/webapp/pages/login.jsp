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
    <title>Login | J-PLATFORM</title>
	<link href="<c:url value="/assets/core/css/bootstrap.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/assets/core/css/font-awesome.min.css"/>" rel="stylesheet">
	<link href="<c:url value="/assets/core/css/price-range.css"/>" rel="stylesheet">
	<link href="<c:url value="/assets/core/css/animate.css"/>" rel="stylesheet">
	<link href="<c:url value="/assets/core/css/main.css"/>" rel="stylesheet">
	<link href="<c:url value="/assets/core/css/responsive.css"/>" rel="stylesheet">
</head><!--/head-->

<body>
<tiles:insertDefinition name="header"/>

<sec:authorize access="!isAuthenticated()">
	<section id="form"><!--form-->
		<div class="container">
			<div class="row">
				<div class="col-sm-4 col-sm-offset-1">
					<c:url value="/j_spring_security_check" var="loginUrl" />
					<div class="login-form"><!--login form-->
						<h2>Login to your account</h2>
						<form action="${loginUrl}" method="post">
							<input type="email" placeholder="email" name="sec_username" />
							<input type="password" placeholder="password" name="sec_password" />
							<span>
								<input type="checkbox" class="checkbox"> 
								Keep me signed in
							</span>
							<button type="submit" class="btn btn-default">Login</button>
						</form>
					</div><!--/login form-->
				</div>
				<div class="col-sm-1">
					<h2 class="or">OR</h2>
				</div>
				<div class="col-sm-4">
					<div class="signup-form"><!--sign up form-->
						<h2>New User Signup!</h2>
						<form:form method="post" action="/register" modelAttribute="user" commandName="user">
							<form:input path="email" placeholder="Email Address" cssClass="form-control"/>
							<form:errors path="email" cssClass="alert-danger" />

							<form:input path="login" placeholder="Name" cssClass="form-control"/>
							<form:errors path="login" cssClass="alert-danger" />

							<form:password path="password" placeholder="Password" cssClass="form-control"/>
							<form:errors path="password" cssClass="alert-danger" />

							<button type="submit" class="btn btn-default">Signup</button>
						</form:form>
						<h1>${done}</h1>
					</div><!--/sign up form-->
				</div>
			</div>
		</div>
	</section><!--/form-->
</sec:authorize>

<tiles:insertDefinition name="footer"/>

	<script src="<c:url value="/assets/core/js/jquery.js"/>"></script>
	<script src="<c:url value="/assets/core/js/bootstrap.min.js"/>"></script>
	<script src="<c:url value="/assets/core/js/jquery.scrollUp.min.js"/>"></script>
	<script src="<c:url value="/assets/core/js/price-range.js"/>"></script>
	<script src="<c:url value="/assets/core/js/main.js"/>"></script>
</body>
</html>