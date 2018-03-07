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
   var selectId = "productQuantity" + productId;
   var productQuantity = document.getElementById(selectId).value;	
	
   var token = $("meta[name='_csrf']").attr("content");
   var header = $("meta[name='_csrf_header']").attr("content");		
   
   $.ajax({url: '/ShoppingCart/addToCart',
   type: 'POST', 
   data: {"productId": productId, "productQuantity": productQuantity},
   beforeSend: function(xhr) {
       xhr.setRequestHeader(header, token);
   },
   success: function(data) {
	//alert("Added to Cart: " + productId)
   }
   });
}


</script>
</head>
<body>

<c:forEach var="product" items="${pList}" >
  <table width="70%" height="50px;" border="0">
    <tr>
    <th>Id</th>
    <th>Name</th>
    <th>Author</th>
    <th>Price</th>
    </tr>
    <tr style = "text-align:center">
    	<td>${product.id}</td>
        <td>${product.productName}</td>
        <td>${product.productAuthor}</td>
        <td>${product.productPrice}</td>
        <td>
          Quantity
          <select id="productQuantity${product.id}">
			<c:forEach begin="1" end="10" var="counter">
        		<option value="${counter}" >${counter}</option>
    		</c:forEach>
		  </select>
          <button type="button" id="addCartLink" onclick="addToCart(${product.id})"> Add To Cart  </button>
        </td>
    </tr>
    </br>
  </table>
</c:forEach>
</body>
</html>