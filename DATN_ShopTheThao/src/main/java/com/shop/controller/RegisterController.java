package com.shop.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Account;
import com.shop.repository.AccountRepository;
import com.shop.service.AccountService;
import com.shop.service.EmailService;

@Controller
public class RegisterController {
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	AccountRepository aRep;
	
	@RequestMapping("/account/register")
	public String accRegister(Model model) {
		return "account/register";
	}
	
	@PostMapping("/account/regist")
	public String createRegister(@RequestParam("username") String username,
								 @RequestParam("email")    String email,
								 @RequestParam("password") String password,
								 Model model) {
		// Tạo một đối tượng User mới và lưu vào CSDL
        Account acc = new Account();
        acc.setUsername(username);
        acc.setEmail(email);
        acc.setPassword(password);
        acc.setRole("user");
        // Cập nhật createDate trước khi lưu vào CSDL
        acc.setCreateDate(new Date());
        
        aRep.save(acc);
        
     // Chuyển đến trang thông báo đăng ký thành công 
        model.addAttribute("username", username);
        return "/account/register-success";
	}
}
