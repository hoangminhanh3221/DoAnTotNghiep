package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.entity.Product;
import com.shop.service.ProductService;

@Controller
public class HomeController {
	
	@Autowired
	ProductService productService;

	@RequestMapping("/home")
	public String index(Model model) {
		
		//Lấy danh sách sản phẩm và xắp xếp theo trường ngày nhận với thứ thự giảm dần và thêm vào model
		List<Product> lPDateDesc=	productService.getProductsSortByDateDesc();
		model.addAttribute("lPDateDesc", lPDateDesc);
		
		return "user-page/home";
	}
	
}
