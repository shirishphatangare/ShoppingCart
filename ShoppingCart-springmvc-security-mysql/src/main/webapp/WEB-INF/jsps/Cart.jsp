<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<meta name="_csrf" content="${_csrf.token}"/>
	<meta name="_csrf_header" content="${_csrf.headerName}"/>
    <title>Welcome To Shopping Cart</title>
	<link href="${pageContext.request.contextPath}/static/bootstrap.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.request.contextPath}/static/home.css" rel="stylesheet">

</head>
<body>
<h3 class="text-center">My Cart</h3>

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

  <table class="table table-striped">
    <thead>
    <tr>
    <th  scope="col">Product Id</th>
    <th  scope="col">Name</th>
    <th  scope="col">Author</th>
    <th  scope="col">Price</th>
    <th scope="col"> </th>
    <th scope="col"> </th>
    </tr>
    </thead>
    <tbody>
    <c:forEach var="product" items="${pList}" >
    <tr>
        <th scope="row">${product.key.id}</th>
        <td>${product.key.productName}</td>
        <td>${product.key.productAuthor}</td>
        <td>${product.key.productPrice}</td>
        <td>
          <input id="productQuantity${product.key.id}" type="text" maxlength="2" size="2" value="${product.value}" required> </input>
          <button class="btn btn-sm btn-primary" type="button" id="updateQuantityLink" onclick="updateCartQuantity(${product.key.id})"> Update Quantity  </button>
        </td>
        <td>
          <button class="btn btn-sm btn-primary" type="button" id="removeCartLink" onclick="removeFromCart(${product.key.id})"> Remove From Cart  </button>
        </td>
    </tr>
    </c:forEach>
    </tbody>
  </table>

<c:choose>
<c:when test="${pList.size() > 0}">
<form:form action="${pageContext.request.contextPath}/placeOrder" method="POST">
	<div class="text-center">
		<input class="btn btn-sm btn-success" type="submit" value="Place Order">
	</div>
</form:form>

</c:when>
<c:otherwise>
	<h6 class="alert alert-danger" role="alert">Your cart is Empty!! </h6>
</c:otherwise>
</c:choose>

  <!-- Bootstrap core JavaScript
    ================================================== -->
    <!-- Placed at the end of the document so the pages load faster -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
    <script src="${pageContext.request.contextPath}/static/shoppingCart.js"></script>

</body>
</html>