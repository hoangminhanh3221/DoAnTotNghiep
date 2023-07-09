package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/admin")
public class HomeController {
	
	@RequestMapping("/product/do-nu")
	public String getDoNu() {
		return "user-page/product";
	}
	
	@RequestMapping("/product/do-nu/quan-ngan")
	public String getDoNuQuanNgan() {
		return "user-page/product";
	}
	
	@RequestMapping("/product/do-nu/ao-thun")
	public String getDoNuAoThun() {
		return "user-page/product";
	}
	
	@RequestMapping("/product/do-nu/quan-dai")
	public String getDoNuQuanDai() {
		return "user-page/product";
	}
	
	@RequestMapping("/product/do-nu/ao-khoac")
	public String getDoNuAoKhoac() {
		return "user-page/product";
	}
	
	@RequestMapping("/product/do-nu/ao-tanktop")
	public String getDoNuAoTanktop() {
		return "user-page/product";
	}

	@RequestMapping("/product/do-nam")
	public String getDoNam() {
		return "user-page/product";
	}
	
	@RequestMapping("/product/do-nam/ao-balo")
	public String getDoNamAoBalo() {
		return "user-page/product";
	}
	
	@RequestMapping("/product/do-nam/quan-dui")
	public String getDoNamQuanDui() {
		return "user-page/product";
	}
	
	@RequestMapping("/product/do-nam/ao-thun")
	public String getDoNamAoThun() {
		return "user-page/product";
	}
	
	@RequestMapping("/product/do-nam/quan-dai")
	public String getDoNamQuanDai() {
		return "user-page/product";
	}
	
	@RequestMapping("/product/do-nam/ao-khoac")
	public String getDoNamAoKhoac() {
		return "user-page/product";
	}
	
	@RequestMapping("/list-order")
	public String getList() {
		return "user-page/list-order";
	}
	
	@RequestMapping("/size")
	public String admin() {
		return "admin-page/size-add";
	}
	
}
