<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link href="${pageContext.request.contextPath}/css/bootstrap.css" rel="stylesheet">
<!-- Custom styles for this template -->
<link href="${pageContext.request.contextPath}/css/home.css" rel="stylesheet">


</head>
<body>
<h4 class="text-center">Welcome To ShoppingCart Registration</h4>

<div class="col-xs-2" style="margin-left:320px;margin-top:50px;">
<form:form action="${pageContext.request.contextPath}/registerNewUser" method="POST">
<div class="form-group row">
<label for="firstname" class="col-sm-2 col-form-label col-form-label-sm">Please Enter FirstName:</label>
<div class="col-sm-4">
<input id="firstname" type="text" size="50" name="firstname" required/>
</div>
</div>
<div class="form-group row">
<label for="lastName" class="col-sm-2 col-form-label col-form-label-sm">Please Enter LastName:</label>
<div class="col-sm-4">
<input id="lastname" type="text" size="50" name="lastname" required/>
</div>
</div>
<div class="form-group row">
<label for="enterEmail" class="col-sm-2 col-form-label col-form-label-sm">Please Enter Email:</label>
<div class="col-sm-4">
<input id="enterEmail" type="email" size="50" name="enterEmail" required/>
</div>
</div>
<div class="form-group row">
<label for="enterUser" class="col-sm-2 col-form-label col-form-label-sm">Please Enter Username:</label>
<div class="col-sm-4">
<input id="enterUser" type="text" size="50" name="enterUser" required/>
</div>
</div>
<div class="form-group row">
<label for="enterPass" class="col-sm-2 col-form-label col-form-label-sm">Please Enter Password:</label>
<div class="col-sm-4">
<input id="enterPass" type="password" size="50" name="enterPass" required/>
</div>
</div>
<div class="form-group row">
<label for="confirmPass" class="col-sm-2 col-form-label col-form-label-sm">Please Confirm Password:</label>
<div class="col-sm-4">
<input id="confirmPass" type="password" size="50" name="confirmPass" required/>
</div>
</div>
<div>
<input class="btn btn-sm btn-primary" type="submit" value="Register">
<input class="btn btn-sm btn-primary" type="reset" value="Reset">
</div>
</form:form>
<span id="disAlert"></span>
</div>
<script type="text/javascript" src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>

<script type="text/javascript">
$(document).ready(function(){
$("#confirmPass").keyup(function () {
    var password = $("#enterPass").val();
    var confirmPassword = $("#confirmPass").val();
    if (password != confirmPassword) {
        //alert("Do not match");
        document.getElementById("disAlert").className = "alert alert-danger";
    	document.getElementById("disAlert").innerHTML = "Passwords do not match";
        return false;
    }
    
    document.getElementById("disAlert").className = "alert alert-success";
	document.getElementById("disAlert").innerHTML = "Passwords matched";
    return true;
});
});
</script>

</body>
</html>