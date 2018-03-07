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
Welcome To Shopping Registration

<form:form action="${pageContext.request.contextPath}/registerNewUser" method="POST">
<p>Please Enter FirstName: <input type="text" name="firstname"/></p>
<p>Please Enter LastName: <input type="text" name="lastname"/></p>
<p>Please Enter Email: <input type="text" name="email"/></p>
<p>Please Enter Username: <input type="text" name="username"/> </p>
<p>Please Enter Password: <input type="password" name="password"/></p>
<input type="submit" value="Register">
<input type="reset" value="Reset">
</form:form>
</body>
</html>