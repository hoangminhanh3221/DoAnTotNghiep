package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Brand;
import com.shop.entity.Color;
import com.shop.entity.Discount;
import com.shop.entity.Product;
import com.shop.entity.Size;
import com.shop.entity.Subcategory;
import com.shop.service.BrandService;
import com.shop.service.ColorService;
import com.shop.service.DiscountService;
import com.shop.service.ProductService;
import com.shop.service.SizeService;
import com.shop.service.SubcategoryService;

@Controller
public class AdminProductController {
	
	@Autowired
	ProductService productService;
	@Autowired
	SubcategoryService subcategoryService;
	@Autowired
	BrandService brandService;
	@Autowired
	ColorService colorService;
	@Autowired
	SizeService sizeService;
	@Autowired
	DiscountService discountService;
	
	@GetMapping("/admin/product")
	public String ProductList(@RequestParam(defaultValue = "0") int page, Model model) {
		
		int pageSize = 10; // Số phần tử trên mỗi trang
		Pageable pageable = PageRequest.of(page, pageSize);
		
		List<Product> list = productService.findAllProduct();
		Page<Product> pageProduct = productService.findAllProduct(pageable);
		model.addAttribute("list", list);
		model.addAttribute("page", pageProduct);
		return "admin-page/product-list";
	}
	
	@GetMapping("/admin/product/add")
	public String ProductAdd(Model model) {
		
		Product pro = new Product();
		
		List<Size> SizeL = sizeService.findAllSize();
		List<Color> ColorL = colorService.findAllColor();
		List<Brand> BrandL = brandService.findAllBrand();
		List<Discount> DiscountL = discountService.findAllDiscount();
		List<Subcategory> SubcategoryL = subcategoryService.findAllSubcategory();
		
		model.addAttribute("subcategoryL", SubcategoryL);
		model.addAttribute("sizeL", SizeL);
		model.addAttribute("colorL", ColorL);
		model.addAttribute("discountL", DiscountL);
		model.addAttribute("brandL", BrandL);
		model.addAttribute("product", pro);
		model.addAttribute("status", 1);
		return "admin-page/product-add";
	}
	
	@PostMapping("/admin/product/add/save")
	public String ProductAddSave(Model model,@ModelAttribute("product") Product product) {
		
		return "admin-page/product-add";
	}
}
