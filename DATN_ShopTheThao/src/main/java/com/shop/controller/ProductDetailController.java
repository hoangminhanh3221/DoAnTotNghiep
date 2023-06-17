package com.shop.controller;


import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.entity.Product;
import com.shop.service.ProductService;

@Controller
public class ProductDetailController {
	
	@Autowired
	ProductService productService;

	@RequestMapping("/product-detail")
	public String index(Model model, @RequestAttribute("id") String id) {
		
		Optional<Product> prdO = productService.findProductById(id);
		Product prd = prdO.get();
		model.addAttribute("prd", prd);
		return "user-page/Product-detail";
	}
	
}
