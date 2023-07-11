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
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Color;
import com.shop.service.ColorService;
//@RequestMapping("/admin")
@Controller
public class ColorController {
	@Autowired
	private ColorService colorService;
	
	@RequestMapping("/colorList")
	public String getColorList(Model model,@RequestParam("page") Optional<Integer> page) {
		
		Pageable pageable = PageRequest.of(page.orElse(0), 16);
		Page<Color> listColor = colorService.findAllColor(pageable);
		model.addAttribute("colors", listColor);
		int totalPages = listColor.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
		model.addAttribute("pageNumbers", pageNumbers);
			return "/admin-page/color-list";
	}
	@RequestMapping("/colorAdd")
	public String getColorAdd(Model model, @ModelAttribute Color color) {
		model.addAttribute("title", "Thêm Màu");
		return "/admin-page/color-add";
	}
	@RequestMapping("/colorEdit/{id}")
	public String getColorEdit(Model model, @PathVariable("id") String id) {
		Color color = colorService.findColorById(id).get();
		model.addAttribute("title", "Sửa Màu");
		model.addAttribute("color", color);
		return "/admin-page/color-add";
	}
}
