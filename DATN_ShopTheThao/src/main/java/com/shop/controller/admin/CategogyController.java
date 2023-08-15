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

import com.shop.entity.Category;
import com.shop.service.CategoryService;
@RequestMapping("/admin")
@Controller
public class CategogyController {
	@Autowired
	private CategoryService categoryService;
	
	@RequestMapping("/categoryList")
	public String getCategoryList(Model model,@RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 10);
		Page<Category> listCategory = categoryService.findAllCategory(pageable);
		model.addAttribute("categorys", listCategory);
		int totalPages = listCategory.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
		model.addAttribute("pageNumbers", pageNumbers);
		return "/admin-page/category-list";
	}
	
	@RequestMapping("/categoryAdd")
	public String getCategoryAdd(Model model, @ModelAttribute Category category) {
		model.addAttribute("title", "Thêm Danh Mục");
		return "/admin-page/category-add";
	}
	
	@RequestMapping("/categoryAdd/create")
	public String createCategoryAdd(
			@RequestParam("categoryId") String categoryId,
			@RequestParam("categoryName") String categoryName) {
		Category category = new Category(categoryId, categoryName);
		categoryService.createCategory(category);
		return "redirect:/admin/categoryList";
	}
	
	@RequestMapping("/categoryEdit/{id}")
	public String getCategoryEdit(Model model, @PathVariable("id") String id) {
		Category category = categoryService.findCategoryById(id).get();
		model.addAttribute("title", "Sửa Danh Mục");
		model.addAttribute("category", category);
		return "/admin-page/category-add";
	}
	
	@RequestMapping("/category/delete/{id}")
	public String getDeleteCategory(@PathVariable("id") String id) {
		System.out.println("id" + id);
		categoryService.deleteCategory(id);
		return "redirect:/admin/categoryList";
	}
}

