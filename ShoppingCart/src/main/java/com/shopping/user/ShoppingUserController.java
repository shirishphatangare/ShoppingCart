package com.shopping.user;

import java.util.Hashtable;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shopping.entity.ShoppingCartBean;
import com.shopping.entity.ShoppingProductBean;


@Controller
public class ShoppingUserController {

	
	@Autowired
	private ShoppingUserService suService;	

	@Autowired
	private ShoppingCartBean cart ;
	
	

@RequestMapping("/updateCart")	
public void updateCart(HttpServletRequest req) {
	int productId = Integer.parseInt(req.getParameter("productId"));
	//int productQuantity =  Integer.parseInt( req.getParameter("productQuantity"));
	
	//cart.setProducts(products);
	Map<Integer,Integer> cartProducts = cart.getProducts();
	
	Set<Integer> keys = cartProducts.keySet();
	
	Iterator<Integer> iter = cartProducts.keySet().iterator();
	
	int quantity ; 
	int key;
	if(keys.size() == 0 ) {
		//quantity = 1;
		cartProducts.put(productId, 1);
		System.out.println("key: "+productId+" value: 1");
	}else {
		while(iter.hasNext()){
			key = iter.next();
    	if(productId == key) {
			quantity = cartProducts.get(key);
    		cartProducts.put(key, ++quantity);
    		System.out.println("key: "+key+" value: "+quantity);
    		break;
    	}
    	else if(cartProducts.get(productId) == null){
    		cartProducts.put(productId, 1);
    		System.out.println("key: "+productId+" value: 1");
    		break;
    	}
    }
	}
	
	
	cart.setProducts(cartProducts);
	//cart.setProductsQuantity(productQuantity);
	//cart.getProducts().add(productId);
	//System.out.println(cart.getProducts().size());
	
}	
	
@RequestMapping("/showProducts")
public String showProducts(Model m) {
	
	//product.setBookName("New Book3");
	//product.setBookAuthor("New Author2");
	//product.setPrice(2001);
	
	List<ShoppingProductBean> productsList= suService.showProducts();
	
	m.addAttribute("pList",productsList);
	
	/*m.addAttribute("pName",product.getBookName());
	m.addAttribute("pAuthor",product.getBookAuthor());
	m.addAttribute("pRate",product.getPrice());*/
	
	
	return "productsList";
	
}






	

	
}
