package com.shop.controller;

import java.util.Collection;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.util.AuthenticationFacade;

@Controller
public class SecurityController {
	
	@Autowired
    private AuthenticationFacade authenticationFacade;
	
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
		if(authenticationFacade.hasRole("ROLE_user")) {
			System.out.println("1");
			return "/user-page/home";
		}
		System.out.println("2");
		return "/admin-page/dashboard";
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
