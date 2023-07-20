package com.shop.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import com.shop.entity.Size;
import com.shop.service.SizeService;

@Controller
public class SizeController {
	
	@Autowired
	SizeService sizeService;
	
	@GetMapping("/admin/size")
	public String SizeList(Model model) {
		
		List<Size> list = sizeService.findAllSize();
		model.addAttribute("list", list);
		return "admin-page/size-list";
	}
}
