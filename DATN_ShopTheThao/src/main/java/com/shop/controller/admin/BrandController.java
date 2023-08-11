package com.shop.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Brand;
import com.shop.entity.Color;
import com.shop.service.BrandService;

@RequestMapping("/admin")
@Controller
public class BrandController {
	
	@Autowired
	private BrandService brandService;
	
	@RequestMapping("/brandList")
		public String getBarndList(Model model,@RequestParam("page") Optional<Integer> page) {
		
		Pageable pageable = PageRequest.of(page.orElse(0), 10);
		Page<Brand> listBrand = brandService.findAllBrand(pageable);
		model.addAttribute("brands", listBrand);
		int totalPages = listBrand.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
		model.addAttribute("pageNumbers", pageNumbers);
			return "/admin-page/brand-list";
		}
	@RequestMapping("/brandAdd")
	public String getBrandAdd(Model model,@ModelAttribute Brand brand) {
		model.addAttribute("title", "Thêm Thương Hiệu");
		return "/admin-page/brand-add";
	}
	  @RequestMapping("/brandAdd/create")
	  public String createBrandAdd(
	  		@RequestParam("brandId") String brandId,
	  		@RequestParam("brandName") String brandName,
	  		@RequestParam("origin") String origin)
	  		{
		  			Brand brand = new Brand(brandId,brandName,origin);
		  			brandService.createBrand(brand);
					return "redirect:/admin/brandList";
	  		}
	@RequestMapping("/brandEdit/{id}")
	public String getBrandEdit(Model model, @PathVariable("id") String id) {
		Brand brand = brandService.findBrandById(id).get();
		model.addAttribute("title", "Sửa Thương Hiệu");
		model.addAttribute("brand", brand);
		return "/admin-page/brand-add";
	}
	  @RequestMapping("/brand/delete/{id}")
	  public String getDeleteBrand(@PathVariable("id") String id) {
	  	System.out.println("id" + id);
	      brandService.deleteBrand(id);
	      return "redirect:/admin/brandList";
	  }
}
