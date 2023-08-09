package com.shop.controller;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Account;
import com.shop.repository.AccountRepository;
import com.shop.service.AccountService;
import com.shop.service.EmailService;

@Controller
public class ForgotPassController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	EmailService emailService;
	
	@PostMapping("/account/forgot-password")
	public String sendPass(@RequestParam("email") String email,Model model) {
		
		Optional<Account> acc = accountService.findByEmail(email);
		
		if (acc.isEmpty()) {
			model.addAttribute("message","Không tìm thấy email!");
			return "/account/forgot-password";
		} else {
			model.addAttribute("status",1);
			emailService.sendEmail(acc.get().getEmail(), "Mật khẩu", "Mật khẩu của bạn trong trang fsport shop là:" + acc.get().getPassword());
			return "/account/login";
		}
		
		
	}
	
	
	
}
