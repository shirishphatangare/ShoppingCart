function addToCart(productId){
   var selectId = "productQuantity" + productId;
   var productQuantity = document.getElementById(selectId).value;	
	
   var token = $("meta[name='_csrf']").attr("content");
   var header = $("meta[name='_csrf_header']").attr("content");		
   
   $.ajax({url: '/ShoppingCart/addToCart',
   //$.ajax({url: '/addToCart',
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

function removeFromCart(productId){
	//alert(productId);
   var token = $("meta[name='_csrf']").attr("content");
   var header = $("meta[name='_csrf_header']").attr("content");		
   
   $.ajax({url: '/ShoppingCart/removeFromCart',
   //$.ajax({url: '/removeFromCart',
   type: 'POST', 
   data: "productId="+productId,
   //contentType: "application/x-www-form-urlencoded; charset=UTF-8",
   //dataType: "text",
   beforeSend: function(xhr) {
       // here it is
       xhr.setRequestHeader(header, token);
   },
   success: function(data) {
	//alert(data);
	if(data.indexOf("success") !== -1){
		/*var res = data.split("-");
		document.getElementById("cartAlert").innerHTML = "Product "+ res[1] + "removed Succesfully"*/
		location.reload();
	}   
   }
   });
}


function updateCartQuantity(productId){
	//alert(productId);
   var inputId = "productQuantity" + productId;
   var productQuantity = document.getElementById(inputId).value;	
   var token = $("meta[name='_csrf']").attr("content");
   var header = $("meta[name='_csrf_header']").attr("content");		
   $.ajax({url: '/ShoppingCart/updateCartQuantity',
   //$.ajax({url: '/updateCartQuantity',
   type: 'POST', 
   data: {"productId": productId, "productQuantity": productQuantity},
   beforeSend: function(xhr) {
       // here it is
       xhr.setRequestHeader(header, token);
   },
   success: function(data) {
	//alert("Added to Cart: " + productId)
   }
   });
}


