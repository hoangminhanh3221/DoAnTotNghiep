package com.shop.controller;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Product;
import com.shop.service.ProductService;

@Controller
@RequestMapping("/product")
public class ProductController {

	@Autowired
	private ProductService productService;
	
	@RequestMapping("/list")
	public String list(
			Model model, 
			@RequestParam(value = "categoryId", defaultValue = "null") Optional<String> categoryId,
			@RequestParam(value = "subcategoryId", defaultValue = "null") Optional<String> subcategoryId,
			@RequestParam("page") Optional<Integer> page
			//@RequestParam(value = "sortBy", defaultValue = "null") Optional<String> sort
		) {
		// Pageable
		Pageable pageable = PageRequest.of(page.orElse(0), 16);
		Page<Product> list = null;
		//Sort sortOption = null;
		// sort by category
		if (!categoryId.get().equals("null")) {
			list = productService.findByCategoryID(categoryId.get(), pageable);
			model.addAttribute("products", list);
		} else if (!subcategoryId.get().equals("null")) {
			list = productService.findBySubcategoryID(subcategoryId.get(), pageable);
			model.addAttribute("products", list);
		} else {
			list = productService.findAllProduct(pageable);
			model.addAttribute("products", list);
		}
		
		if(list == null) {
			model.addAttribute("isProductNull", false);
		} else {
			model.addAttribute("isProductNull", true);
		}
		
		int totalPages = list.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
		model.addAttribute("pageNumbers", pageNumbers);
		return "user-page/product";
	}

	@RequestMapping("/product-detail/{id}")
	public String getProductDetail(@PathVariable("id") String maSP, Model model) {
		Optional<Product> optionalProduct = productService.findProductById(maSP);
		System.out.println(maSP);
		if (optionalProduct.isPresent()) {
			Product product = optionalProduct.get();
			System.out.println(product.getProductName());
			model.addAttribute("product", product);
			return "user-page/product-detail";
		} else {
			System.out.println("Không tìm thấy product");
			return "redirect:/product/list-all-product";
		}
	}
	

}
