<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>
</head>
<body>
Welcome To Shopping World!!

</br>
</br>
</br>
<a href="${pageContext.request.contextPath}/showProducts"> Show All Products </a>
</br>
</br>
</br>
<a href="${pageContext.request.contextPath}/showCart"> My Cart </a>
</br>
</br>
</br>
<form:form action="${pageContext.request.contextPath}/logout" method="POST">
	<input type="submit" value="Logout">
</form:form>
</body>
</html>