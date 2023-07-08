package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class SecurityController {
	@RequestMapping("/account/login/form")
	public String loginForm(Model model) {
		return "/account/login";
	}
	
	@RequestMapping("/account/forgot-password")
	public String forgotPass(Model model) {
		return "/account/forgot-password";
	}
	
	@RequestMapping("/account/login/success")
	public String loginSuccess(Model model) {
		return "/user-page/home";
	}
	
	@RequestMapping("/account/login/error")
	public String loginError(Model model) {
		return "/account/login";
	}
	
	@RequestMapping("/user-page/home")
	public String homeLoad(Model model) {
		return "/user-page/home";
	}
	
	@RequestMapping("/account/unauthoried")
	public String unauthoried(Model model) {
		return "/account/login";
	}
	
	@RequestMapping("/account/logoff/success")
	public String logoffSuccess(Model model) {
		return "/account/login";
	}
	
	@RequestMapping("/account/register")
	public String accRegister(Model model) {
		return "/account/register";
	}
	
}
