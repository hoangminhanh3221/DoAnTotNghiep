package com.shop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.shop.entity.Account;
import com.shop.service.AccountService;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private AccountService accountService;
	
	@RequestMapping("/info")
	public String getInfo() {
		Account account = accountService.findAccountById("kiet").orElse(null);
		return "user-page/user-info";
	}
	@RequestMapping("/list-order")
	public String getListOrder() {
		Account account = accountService.findAccountById("kiet").orElse(null);
		return "user-page/list-order";
	}
}
