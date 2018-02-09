<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
    <%@ taglib uri = "http://java.sun.com/jsp/jstl/core" prefix = "c" %>
<!DOCTYPE html>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<meta name="_csrf" content="${_csrf.token}"/>
<meta name="_csrf_header" content="${_csrf.headerName}"/>
<title>Insert title here</title>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.3.1/jquery.min.js"></script>
<script>


function addToCart(productId){
	//alert(productId);
   var token = $("meta[name='_csrf']").attr("content");
   var header = $("meta[name='_csrf_header']").attr("content");		
   
   $.ajax({url: '/ShoppingCart/updateCart',
   type: 'POST', 
   data: "productId="+productId,
   beforeSend: function(xhr) {
       // here it is
       xhr.setRequestHeader(header, token);
   },
   success: function(data) {
	alert("Added to Cart: " + productId)
   }
   });
}


</script>
</head>
<body>

<c:forEach var="product" items="${pList}" >
  <table width="70%" height="50px;" border="0">
    <tr>
    <th>Name</th>
    <th>Author</th>
    <th>Price</th>
    </tr>
    <tr style = "text-align:center">
        <td>${product.productName}</td>
        <td>${product.productAuthor}</td>
        <td>${product.productPrice}</td>
        <td>
        
        <button type="button" `id="addCartLink" onclick="addToCart(${product.id})""> Add To Cart  </button>
        
        
        </td>
    </tr>
    </br>
  </table>
</c:forEach>
</body>
</html>