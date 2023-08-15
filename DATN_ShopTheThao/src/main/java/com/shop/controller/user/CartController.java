package com.shop.controller.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.entity.Order;
import com.shop.service.OrderService;
import com.shop.service.ProductService;

@Controller
public class CartController {

	@Autowired
	private ProductService productService;

	@Autowired
	private OrderService orderService;

	@RequestMapping("/cart/view")
	public String getViewCart() {
		return "user-page/shopping-cart";
	}

}
