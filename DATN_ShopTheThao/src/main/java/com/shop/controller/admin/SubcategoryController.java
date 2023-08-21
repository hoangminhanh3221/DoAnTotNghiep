package com.shop.controller.admin;

import java.util.ArrayList;
import java.util.Iterator;
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

import com.shop.entity.Category;
import com.shop.entity.Subcategory;
import com.shop.service.CategoryService;
import com.shop.service.SubcategoryService;

@RequestMapping("/admin")
@Controller
public class SubcategoryController {
	@Autowired
	private SubcategoryService subcategoryService;
	@Autowired
	private CategoryService categoryService;

	@RequestMapping("/subcategoryList")
	public String getSubcategoryList(Model model, @RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 10);
		Page<Subcategory> listSubcategory = subcategoryService.findAllSubcategory(pageable);
		model.addAttribute("subcategorys", listSubcategory);
		
		if(page.isPresent()) {
			model.addAttribute("page", page.get());
		} else {
			model.addAttribute("page", 0);
		}
		
		int totalPages = listSubcategory.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
		model.addAttribute("pageNumbers", pageNumbers);
		return "/admin-page/subcategory-list";
	}

	@RequestMapping("/subcategoryAdd")
	public String getSubcategoryAdd(Model model, @ModelAttribute Subcategory subcategory) {
		model.addAttribute("title", "Thêm Danh Mục Con");
		List<Category> categories = categoryService.findAllCategory();
		model.addAttribute("categories", categories);
		return "/admin-page/subcategory-add";
	}
	
	@RequestMapping("/subcategoryAdd/create")
	public String createSubcategoryAdd(
			@ModelAttribute Subcategory subcategory,
			@RequestParam("selectedCategoryId") String categoryId) {
		subcategory.setCategory(categoryService.findCategoryById(categoryId).get());
		subcategoryService.createSubcategory(subcategory);
		return "redirect:/admin/subcategoryList";
	}
	
	@RequestMapping("/subcategoryEdit/{id}")
	public String getSubcategoryEdit(Model model, @PathVariable("id") String id) {
		Subcategory subcategory = subcategoryService.findSubcategoryById(id).get();
		model.addAttribute("title", "Sửa Danh Mục Con");
		
		List<Category> categories = categoryService.findAllCategory();
		model.addAttribute("categories", categories);
		
		model.addAttribute("subcategory", subcategory);
		model.addAttribute("selectedCategoryId", subcategory.getCategory().getCategoryId());
		return "/admin-page/subcategory-add";
	}
	
	@RequestMapping("/subcategory/delete/{id}")
	public String getDeleteSubcategory(@PathVariable("id") String id) {
		System.out.println("id" + id);
		subcategoryService.deleteSubcategory(id);
		return "redirect:/admin/subcategoryList";
	}
}
