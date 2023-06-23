package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.entity.Product;
import com.shop.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {
	
	@Autowired
	private final ProductService productService;

	public ProductController(ProductService productService) {
		this.productService = productService;
	}
	
	@RequestMapping("/list-all-product")
	public String getProduct(Model model) {
		List<Product> lisProducts = productService.findAllProduct();
		model.addAttribute("products", lisProducts);
		return "user-page/product";
	}
	
	@RequestMapping("/product-detail/{id}")
	public String getProductDetail(@PathVariable("id") String maSP, Model model) {
		Product product = productService.findProductById(maSP).orElse(null);
		model.addAttribute("product", product);
		return "user-page/product-detail";
		
	}
	
}
