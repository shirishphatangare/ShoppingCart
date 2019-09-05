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
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.example.springbootsecurity.shopping.entity.ShoppingCartBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductBean;
import com.example.springbootsecurity.shopping.entity.ShoppingProductDetailBean;


@Controller
public class ShoppingUserController {

	@Autowired
	private ShoppingUserService shoppingUserService;	

	@Autowired
	private ShoppingCartBean shoppingcartBean;
	
	@PostMapping("/addToCart")	
	public void addToCart(HttpServletRequest req) {
		long productId = Integer.parseInt(req.getParameter("productId"));
		int productQuantity =  Integer.parseInt( req.getParameter("productQuantity"));
		shoppingcartBean.addToCart(productId, productQuantity);
	}	

		
	@PostMapping("/updateCartQuantity")
	public void updateCartQuantity(HttpServletRequest req) {
		int productId = Integer.parseInt(req.getParameter("productId"));
		int productQuantity =  Integer.parseInt( req.getParameter("productQuantity"));
		shoppingcartBean.updateCart(productId, productQuantity);
	}
	

	@PostMapping("/removeFromCart")	
	@ResponseBody
	public String removeFromCart(HttpServletRequest req) {
		int productId = Integer.parseInt(req.getParameter("productId"));
		shoppingcartBean.removeFromCart(productId);
		return "success-"+productId;
	}	

	
	@GetMapping("/showCart")
	public String showCart(Model m) {
		Map<ShoppingProductBean,Integer> productsList = new ConcurrentHashMap<ShoppingProductBean,Integer>();
		Iterator<Long> iter = shoppingcartBean.getCartIterator();
		long productId;
		while(iter.hasNext()) {
			productId = iter.next();
			ShoppingProductBean cartProduct = shoppingUserService.getProduct(productId);
			productsList.put(cartProduct, shoppingcartBean.getProductQuantity(productId));
		}
		m.addAttribute("pList",productsList); 
		return "Cart";
	}

	

	@GetMapping("/showProducts")
	public String showProducts(Model m) {
		List<ShoppingProductBean> productsList=shoppingUserService.showAllProducts();
		m.addAttribute("pList",productsList);
		return "productsList";
	}


	@PostMapping("/placeOrder")
	public String placeOrder(Model m) {
		Iterator<Long> iter = shoppingcartBean.getCartIterator();
		List<ShoppingProductDetailBean> orderDetails = new ArrayList<ShoppingProductDetailBean>();
		long productId;
		int productQuantity;
		int productTotalPrice;
		while(iter.hasNext()) {
			ShoppingProductDetailBean productDetail = new ShoppingProductDetailBean();

			productId = iter.next();
			ShoppingProductBean cartProduct = shoppingUserService.getProduct(productId);
			productDetail.setProduct(cartProduct);
			productQuantity = shoppingcartBean.getProductQuantity(productId);
			productDetail.setProductQuantity(productQuantity);
			productTotalPrice = cartProduct.getProductPrice() * productQuantity;
			productDetail.setProductTotalPrice(productTotalPrice);
			orderDetails.add(productDetail);
		}
		
		if(shoppingUserService.placeOrder(orderDetails) == true) {
			m.addAttribute("orderSize",orderDetails.size());
			m.addAttribute("orderSuccess",true);
			shoppingcartBean.emptyCart();
		}else {
			m.addAttribute("orderSuccess",false);
		}
		
		return "checkOut";
	}

}