<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>

<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">
</head>
<body>
<h3 class="text-center">Create a Product </h3>

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
				<input class="btn btn-outline-warning my-2 my-sm-0" type="submit" value="Logout">
		</form:form>
        
      </div>
    </nav>

<c:if test="${createProductSuccess != null}">
<c:choose>
<c:when test="${createProductSuccess}">
	<h6 class="alert alert-success" role="alert">Product created Successfully!!</h6>
</c:when>
<c:otherwise>
	<h6 class="alert alert-danger" role="alert">Product creation Failed!!</h6>
</c:otherwise>
</c:choose>
</c:if> 
<div class="col-xs-2" style="margin-left:320px;margin-top:50px;">
<form:form action="${pageContext.request.contextPath}/mgr/submitNewProduct" method="POST">
<div class="form-group row">
<label for="productName" class="col-sm-2 col-form-label col-form-label-sm">Please Enter Product Name:</label>
<div class="col-sm-4">
<input id="productName" type="text" size="50" name="productName" required/>
</div>
</div>
<div class="form-group row">
<label for="productAuthor" class="col-sm-2 col-form-label col-form-label-sm">Please Enter Product Author:</label>
<div class="col-sm-4">
<input id="productAuthor" type="text" size="50" name="productAuthor" required/>
</div>
</div>
<div class="form-group row">
<label for="productPrice" class="col-sm-2 col-form-label col-form-label-sm">Please Enter Product Price:</label>
<div class="col-sm-4">
<input id="productPrice" type="text" size="50" name="productPrice" required/>
</div>
</div>
<div>
<input class="btn btn-sm btn-primary" type="submit" value="Create Product">
<input class="btn btn-sm btn-primary" type="reset" value="Reset">
</div>
</form:form>
</div>
</body>
</html>