package com.example.springbootsecurity.shopping.user;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springbootsecurity.shopping.entity.ShoppingCartBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductDetailBean;


@Controller
public class ShoppingUserController {

	
	@Autowired
	private ShoppingUserService shoppingUserService;	

	@Autowired
	private ShoppingCartBean shoppingCart;
	
	@RequestMapping("/addToCart")	
	public void addToCart(HttpServletRequest req) {
		int productId = Integer.parseInt(req.getParameter("productId"));
		int productQuantity =  Integer.parseInt( req.getParameter("productQuantity"));
		shoppingCart.addToCart(productId, productQuantity);
	}	
		
	@RequestMapping("/updateCartQuantity")
	public void updateCartQuantity(HttpServletRequest req) {
		int productId = Integer.parseInt(req.getParameter("productId"));
		int productQuantity =  Integer.parseInt( req.getParameter("productQuantity"));
		shoppingCart.updateCart(productId, productQuantity);
	}
	
	@RequestMapping("/removeFromCart")	
	@ResponseBody
	public String removeFromCart(HttpServletRequest req) {
		int productId = Integer.parseInt(req.getParameter("productId"));
		shoppingCart.removeFromCart(productId);
		return "success-"+productId;
	}	

	
	@RequestMapping("/showCart")
	public String showCart(Model m) {
		Map<ShoppingProductBean,Integer> productsList = new ConcurrentHashMap<ShoppingProductBean,Integer>();
		Iterator<Integer> iter = shoppingCart.getCartIterator();
		int productId;
		while(iter.hasNext()) {
			productId = iter.next();
			ShoppingProductBean cartProduct = shoppingUserService.getProduct(productId);
			productsList.put(cartProduct, shoppingCart.getProductQuantity(productId));
		}
		m.addAttribute("pList",productsList);
		return "Cart";
	}

	

	@RequestMapping("/showProducts")
	public String showProducts(Model m) {
		List<ShoppingProductBean> productsList=shoppingUserService.showAllProducts();
		m.addAttribute("pList",productsList);
		return "productsList";
	}


	@RequestMapping("/placeOrder")
	public String placeOrder() {
		Iterator<Integer> iter = shoppingCart.getCartIterator();
		List<ShoppingProductDetailBean> orderDetails = new ArrayList<ShoppingProductDetailBean>();
		int productId;
		int productQuantity;
		int productTotalPrice;
		while(iter.hasNext()) {
			ShoppingProductDetailBean productDetail = new ShoppingProductDetailBean();

			productId = iter.next();
			ShoppingProductBean cartProduct = shoppingUserService.getProduct(productId);
			productDetail.setProduct(cartProduct);
			productQuantity = shoppingCart.getProductQuantity(productId);
			productDetail.setProductQuantity(productQuantity);
			productTotalPrice = cartProduct.getProductPrice() * productQuantity;
			productDetail.setProductTotalPrice(productTotalPrice);
			orderDetails.add(productDetail);
		}
		shoppingUserService.placeOrder(orderDetails);
		shoppingCart.emptyCart();
		return "checkOut";
	}

}