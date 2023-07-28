package com.shop.controller.adminController;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Size;
import com.shop.service.SizeService;

@RequestMapping("admin")
@Controller
public class SizeController {
	
	@Autowired
	SizeService sizeService;
	
	@RequestMapping("/sizeList")
	public String getsizeList(Model model,@RequestParam("page") Optional<Integer> page) {
		Pageable pageable = PageRequest.of(page.orElse(0), 10);
		Page<Size> listSize = sizeService.findAllSize(pageable);
		model.addAttribute("sizes", listSize);
		int totalPages = listSize.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
		model.addAttribute("pageNumbers", pageNumbers);
		return "admin-page/size-list";
	}
	@RequestMapping("/sizeAdd")
	public String getSizeAdd(Model model, @ModelAttribute Size size) {
		model.addAttribute("title", "Thêm Kích thước");
		return "/admin-page/size-add";
	}
	
	@RequestMapping("/sizeAdd/create")
	public String createSizeAdd(
			@RequestParam("sizeId") String sizeId,
			@RequestParam("sizeName") String sizeName) {
		Size size = new Size(sizeId, sizeName);
		sizeService.createSize(size);
		return "redirect:/admin/sizeList";
	}
	
	@RequestMapping("/sizeEdit/{id}")
	public String getSizeEdit(Model model, @PathVariable("id") String id) {
		Size size = sizeService.findSizeById(id).get();
		model.addAttribute("title", "Sửa Kích thước");
		model.addAttribute("size", size);
		return "/admin-page/size-add";
	}
	
	@RequestMapping("/size/delete/{id}")
	public String deleteSize(@PathVariable("id") String id) {
		sizeService.deleteSize(id);
		return "redirect:/admin/sizeList";
	}
}