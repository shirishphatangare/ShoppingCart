<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>

<!doctype html>
<html lang="en">
  <head>
    <meta charset="utf-8">
    <title>Welcome To Shopping Cart</title>

	<link href="${pageContext.request.contextPath}/static/bootstrap.css" rel="stylesheet">

	<!-- Custom styles for this template -->
	<link href="${pageContext.request.contextPath}/static/home.css" rel="stylesheet">
  </head>

  <body>
    <nav class="navbar navbar-expand-md navbar-dark bg-dark fixed-top">
      <a class="navbar-brand" href="#">ShoppingCart</a>
      <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarsExampleDefault" aria-controls="navbarsExampleDefault" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
      </button>

      <div class="collapse navbar-collapse" id="navbarsExampleDefault">
        <ul class="navbar-nav mr-auto">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/showProducts">Show Products</a>
          </li>
          <sec:authorize access="hasAnyRole('EMPLOYEE','MANAGER')">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/emp/showOrders">Show Orders</a>
          </li>
          </sec:authorize> 
          <sec:authorize access="hasRole('MANAGER')">
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/mgr/createProduct">Create A Product</a>
          </li>
          </sec:authorize>
          <li class="nav-item">
            <a class="nav-link" href="${pageContext.request.contextPath}/showCart">My Cart</a>
          </li>
        </ul>
        
        <form:form  class="form-inline my-2 my-lg-0" action="${pageContext.request.contextPath}/logout" method="POST">
				<input class="btn btn-outline-success my-2 my-sm-0" type="submit" value="Logout">
		</form:form>
        
      </div>
    </nav>

    <main role="main" class="container">

      <div class="starter-template">
        <h2>Welcome To ShoppingCart</h2>
      </div>

    </main><!-- /.container -->

  </body>
</html>
