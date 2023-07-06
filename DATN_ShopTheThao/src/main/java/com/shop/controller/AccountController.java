package com.shop.controller;

import java.util.regex.Pattern;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Account;
import com.shop.service.AccountService;



@Controller
public class AccountController {
	
	@Autowired
	AccountService accountService;

	@GetMapping("/changepass")
	public String getChangePassForm(Model model) {
		model.addAttribute("status", 1);
		return "account/change-pass";
	}
	
	@PostMapping("/changepass/change")
	public String getChangePass(Model model,
			@RequestParam("password") String password, 
			@RequestParam("newpass") String newpass, 
			@RequestParam("newpassconfirm")String newpassconfirm) {
		
		String pattern1 = "^(?=.*\\d)(?=.*[a-z])(?=.*[A-Z]).{6,15}$";
		String pattern2 = "^[a-zA-Z\\d]{6,15}";
		
		boolean isMatch1 = Pattern.matches(pattern1, newpass);
		boolean isMatch2 = Pattern.matches(pattern2, newpass);
		
		String message= "";
		String username= "kiet";
	
	if (newpass=="") {
		message="Mật khẩu mới không được để trống";
		model.addAttribute("message", message);
		return "account/change-pass";
	}
		if (!isMatch1) {
			message="Mật khẩu mới cần có số, chữ thường và chữ in hoa và cần phải trên 6 và dưới 15 ký tự";
			model.addAttribute("message", message);
			return "account/change-pass";
		}
		if (!isMatch2) {
			message="Mật khẩu không được có ký tự đặc biệt";
			model.addAttribute("message", message);
			return "account/change-pass";
		}
		
		Account acc = accountService.findAccountById(username).get();
		
		if (acc.getPassword().equalsIgnoreCase(password)) {
			if (newpass.equalsIgnoreCase(newpassconfirm)) {
				acc.setPassword(newpass);
				accountService.updateAccount(acc);
				message="Đổi mật khẩu thành công";
				model.addAttribute("status", 0);
			}else {
				message="Mật khẩu và mật khẩu xác nhận không giống nhau";
			}
		}
		else {
			message="Mật khẩu hiện tại không đúng";
		}
		model.addAttribute("message", message);
		return "account/change-pass";
	}

}
