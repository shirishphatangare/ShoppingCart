<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
    <%@ taglib prefix="sec" uri="http://www.springframework.org/security/tags" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<link href="${pageContext.request.contextPath}/static/bootstrap.css" rel="stylesheet">
	<!-- Custom styles for this template -->
	<link href="${pageContext.request.contextPath}/static/home.css" rel="stylesheet">
</head>
<body>
<h4>Showing All orders</h4>
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
    <th scope="col">Id</th>
    <th scope="col">Date</th>
    <th scope="col">User Firstname</th>
    <th scope="col">User Lastname</th>
    <th scope="col">User Email</th>
    <th scope="col">Order Price</th>
    </tr>
  </thead>
  <tbody>    
    <c:forEach var="order" items="${oList}" >
    <tr>
    	<th scope="row">${order.id}</th>
    	<td>${order.orderDate}</td>
        <td>${order.user.firstname}</td>
        <td>${order.user.lastname}</td>
        <td>${order.user.email}</td>
        <td>${order.orderPrice}</td>
        <td>
        <a href="${pageContext.request.contextPath}/emp/viewOrderDetails?orderId=${order.id}"> View Details </a>
       </td>
    </tr>
    </c:forEach>
  </tbody>
  </table>
</body>
</html>