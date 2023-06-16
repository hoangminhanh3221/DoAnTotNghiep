package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
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

}
