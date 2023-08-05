package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Account;
import com.shop.service.AccountService;
import com.shop.service.EmailService;

@Controller
public class ForgotPassController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	EmailService emailService;
	
	@GetMapping("/account/forgot-password")
	public String forgotPass(Model model) {
		model.addAttribute("status",3);
		return "/account/forgot-password";
	}
	
	
	@PostMapping("/account/forgotpass")
	public String sendPass(@RequestParam("email") String email,Model model) {
		
		Account acc = accountService.findByEmail(email);
		
		if (acc==null) {
			model.addAttribute("status",0);
			return "/account/forgot-password";
		} else {
			model.addAttribute("status",1);
			emailService.sendEmail(acc.getEmail(),"Mật khẩu","Mật khẩu của bạn trong trang fsport shop là:"+acc.getPassword());
			return "/account/login";
		}
		
		
	}
	
	
	
}
