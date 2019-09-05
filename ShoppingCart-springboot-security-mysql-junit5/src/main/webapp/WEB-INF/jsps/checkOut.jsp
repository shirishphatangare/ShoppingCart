<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
     <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>

<c:if test="${orderSuccess != null}">
<c:choose>
<c:when test="${orderSuccess}">
	<h6 class="alert alert-success" role="alert">Order for ${orderSize} products placed successfully</h6>
</c:when>
<c:otherwise>
	<h6 class="alert alert-danger" role="alert">Order creation Failed</h6>
</c:otherwise>
</c:choose>
</c:if> 


</body>
</html>