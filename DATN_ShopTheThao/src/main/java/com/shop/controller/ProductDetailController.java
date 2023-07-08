package com.shop.controller;


import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Color;
import com.shop.entity.Feedback;
import com.shop.entity.Product;
import com.shop.entity.Size;
import com.shop.service.ColorService;
import com.shop.service.FeedbackService;
import com.shop.service.ProductService;
import com.shop.service.SizeService;

@Controller
public class ProductDetailController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	FeedbackService feedbackService;
	
	@Autowired
	SizeService sizeService ;
	
	@Autowired
	ColorService colorService;

	@RequestMapping("/product-detail")
	public String index(Model model, @RequestParam("id") String id) {
		
		Optional<Product> prdO = productService.findProductById(id);
		Product prd = prdO.get();
		
		List<Feedback> feedbackList = feedbackService.getListFeedbackByPrdId(id);
		
		Size Size = sizeService.findSizeById(prd.size.sizeId).get();
		
		Color Color = colorService.findColorById(prd.color.colorId).get();
		
		model.addAttribute("prd", prd);
		model.addAttribute("quantity", 1);
		model.addAttribute("size", Size.sizeName);
		model.addAttribute("color", Color.colorName);
		model.addAttribute("review", feedbackList);
		return "user-page/Product-detail";
	}
	
}
