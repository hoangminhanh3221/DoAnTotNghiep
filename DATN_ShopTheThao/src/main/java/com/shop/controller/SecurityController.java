package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	@RequestMapping("/account/login/form")
	public String loginForm(Model model) {
		model.addAttribute("message","Vui lòng đăng nhập");
		return "/account/login";
	}
	
	@RequestMapping("/account/login/success")
	public String loginSuccess(Model model) {
		model.addAttribute("message","Đăng nhập thành công");
		return "/account/login";
	}
	
	@RequestMapping("/account/login/error")
	public String loginError(Model model) {
		model.addAttribute("message","Sai thông tin");
		return "/account/login";
	}
	
	@RequestMapping("/account/unauthoried")
	public String unauthoried(Model model) {
		model.addAttribute("message","Không có quyền");
		return "/account/login";
	}
	
	@RequestMapping("/account/logoff/success")
	public String logoffSuccess(Model model) {
		model.addAttribute("message","Bạn đã đăng xuất");
		return "/account/login";
	}
}
