package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class HomeController {
	@RequestMapping("/product")
	public String getProduct() {
		return "user-page/product";
	}
	
	@RequestMapping("/listorder")
	public String getListOrder() {
		return "user-page/list-order";
	}
	
	@RequestMapping("/shopping-cart")
	public String getShopping() {
		return "user-page/shopping-cart";
	}
	
	@RequestMapping("/product-detail")
	public String getProductDetail() {
		return "user-page/product-detail";
	}
	
	@RequestMapping("/home")
	public String getHome() {
		return "user-page/home";
	}
	
	@RequestMapping("/checkout")
	public String getCheckout() {
		return "user-page/checkout";
	}
	
	@RequestMapping("/order-complete")
	public String getOrderComplete() {
		return "user-page/order-complete";
	}
	
	@RequestMapping("/user-info")
	public String getUserInfo() {
		return "user-page/user-info";
	}
	

	@RequestMapping("/register")
	public String getRegister() {
		return "account/register";
	}	
	
	@RequestMapping("/login")
	public String getLogin() {
		return "account/login";
	}	
	
	@RequestMapping("/forgot-password")
	public String getForgetPassword() {
		return "account/forgot-password";
	}
}
