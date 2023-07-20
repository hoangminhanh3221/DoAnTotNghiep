package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Discount;
import com.shop.service.DiscountService;


@Controller
public class DiscountController {
	
	@Autowired
	DiscountService discountService;
	
	@GetMapping("/admin")
	public String admin() {
		return "admin-page/discount-add";
	}
	
	@GetMapping("/admin/discount")
	public String index(Model model) {
		
		int pageNumber = 0; // Số trang (bắt đầu từ 0)
		int pageSize = 10; // Số phần tử trên mỗi trang
		Pageable pageable = PageRequest.of(pageNumber, pageSize);
		
		Page<Discount> listDiscount = discountService.findAllDiscount(pageable);
		
		model.addAttribute("page", listDiscount);
		
		return "admin-page/discount-list";
	}
	
	
	@GetMapping("/admin/discount/add")
	public String add(Model model) {
		
		
		return "admin-page/discount-add";
	}
	
	@GetMapping("/admin/discount/update")
	public String update(Model model, @RequestParam("discountid") String id) {
		
		Discount discount = discountService.findDiscountById(id).get();
		return "admin-page/discount-add";
	}
}
