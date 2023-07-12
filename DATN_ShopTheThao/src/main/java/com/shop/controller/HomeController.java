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
public class HomeController {

	@Autowired
	ProductService productService;

	@Autowired
	FavoriteService favoriteService;

	@Autowired
	DiscountService discountService;

	@Autowired
	OrderDetailService orderDetailService;

	private List<Product> productsDC;

	@GetMapping("/home")
	public String index(Model model) {

		model.addAttribute("lPDiscount", productService.findProductsOnSale());
		model.addAttribute("lPDateDesc", productService.getProductsSortByDateDesc());
		model.addAttribute("lPSaleDesc", productService.findBestSellingProducts());
		model.addAttribute("lPFavorite", productService.findMostLikedProducts());
		return "user-page/home";
	}

	@Scheduled(fixedDelay = 60000) // Tải lại sau mỗi 1 phút (60 giây)
	public void updateProducts() {
		// Gọi phương thức loadProducts() định kỳ để tải sản phẩm khuyến mãi mới

	}
}
