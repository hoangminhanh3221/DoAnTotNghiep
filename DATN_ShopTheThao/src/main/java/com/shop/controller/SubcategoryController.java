package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;


import com.shop.service.SubcategoryService;
import java.util.List;
import java.util.Optional;
import com.shop.entity.Subcategory;

@Controller
@RequestMapping("/admin/subcategory")
public class SubcategoryController {
	@RequestMapping("/list")
	public String subcategoryList(Model model) {
		return "/admin-page/subcategory-list";
	}
	
	@RequestMapping("/add")
	public String subcategoryAdd(Model model) {
		return "/admin-page/subcategory-add";
	}
	private final SubcategoryService subcategoryService;

    @Autowired
    public SubcategoryController(SubcategoryService subcategoryService) {
        this.subcategoryService = subcategoryService;
    }

    @GetMapping("/subcategory")
    public String getAllSubcategories(Model model) {
        model.addAttribute("subcategories", subcategoryService.findAllSubcategory());
        return "subcategory-list";
    }

    @GetMapping("/edit/{id}")
    public String editSubcategory(@PathVariable("id") String subcategoryId, Model model) {
        Subcategory subcategory = subcategoryService.findSubcategoryById(subcategoryId).orElse(null);
        model.addAttribute("subcategory", subcategory);
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteSubcategory(@PathVariable("id") String subcategoryId) {
        subcategoryService.deleteSubcategory(subcategoryId);
        return "redirect:/subcategories";
    }
}

