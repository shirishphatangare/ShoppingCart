<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!Doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<title>Login ShoppingCart</title>

<!-- Bootstrap core CSS -->
<link href="${pageContext.request.contextPath}/static/bootstrap.css" rel="stylesheet">

<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/static/login.css" rel="stylesheet">
</head>

<body class="text-center">
<form:form class="form-signin" action="${pageContext.request.contextPath}/authenticateLogin" method="POST">

		<c:if test="${param.error != null}">
			<h6 class="alert alert-danger" role="alert">Login Error! Please check username and password</h6>
		</c:if>

		<c:if test="${param.logout != null}">
			<h6 class="alert alert-danger" role="alert">You have been logged out</h6>
		</c:if>


		<c:if test="${usercreatedsuccess != null}">
			<c:choose>
				<c:when test="${usercreatedsuccess}">
					<h6 class="alert alert-success" role="alert">user created Successfully!! </h6>
				</c:when>
				<c:otherwise>
					<h6 class="alert alert-danger" role="alert">user creation Failed!! </h6>
				</c:otherwise>
			</c:choose>
		</c:if>

		<h1 class="h3 mb-3 font-weight-normal">Please sign in</h1>
		<label for="inputEmail" class="sr-only">Email address</label> 
			<input
			type="text" name="username" id="inputEmail" class="form-control"
			placeholder="Username" required autofocus> 
		<label for="inputPassword" class="sr-only">Password</label> 
			<input
			type="password" name="password" id="inputPassword" class="form-control"
			placeholder="Password" required>
		<div class="checkbox mb-3">
			<label> <input type="checkbox" value="remember-me">
				Remember me
			</label>
		</div>
		<div>
			<button class="btn btn-sm btn-primary" type="submit">Sign in</button>
			<input class="btn btn-sm btn-primary" type="reset" value="Reset">
		</div>
		<p> Not Signed In?  <a href="${pageContext.request.contextPath}/register"> Sign Up Now  </a> </p>
		
		<span class="mt-5 mb-3 text-muted">&copy; 2017-2018</span>
	</form:form>
</body>
</html>
