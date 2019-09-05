<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
   <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">

</head>
<body>
<h4>Showing All order Details</h4>

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
    <tr>
    <thead>
    <th scope="col">Id</th>
    <th scope="col">Name</th>
    <th scope="col">Author</th>
    <th scope="col">Product Price</th>
    <th scope="col">Quantity</th>
    <th scope="col">Product Total Price</th>
    </thead>
    </tr>
    <tbody>
    <c:forEach var="productDetail" items="${pList}" >
    <tr>
    	<th scope="row">${productDetail.product.id}</th>
        <td>${productDetail.product.productName}</td>
        <td>${productDetail.product.productAuthor}</td>
        <td>${productDetail.product.productPrice}</td>
        <td>${productDetail.productQuantity}</td>
        <td>${productDetail.productTotalPrice}</td>
    </tr>
    </c:forEach>
</tbody>
  </table>
</body>
</html>