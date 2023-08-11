package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import com.shop.entity.Discount;
import com.shop.entity.Favorite;
import com.shop.entity.OrderDetail;
import com.shop.entity.Product;
import com.shop.service.DiscountService;
import com.shop.service.FavoriteService;
import com.shop.service.OrderDetailService;
import com.shop.service.ProductService;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;
import java.util.Map;

@Controller
public class DashboardController {
	
	@Autowired
	ProductService productService;
	
	@Autowired
	FavoriteService favoriteService;
	
	@Autowired
	DiscountService discountService;
	
	@Autowired
	OrderDetailService orderDetailService;
	
	private List<Product> productsDC;
	
	

	@GetMapping("/admin/dashboard")
	public String index(Model model) {
		
	
		return "admin-page/dashboard";
	}
	
}