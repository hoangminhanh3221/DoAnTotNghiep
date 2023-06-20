package com.shop.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/user")
public class UserController {
	@RequestMapping("/info")
	public String getInfo() {
		return "user-page/user-info";
	}
	@RequestMapping("/list-order")
	public String getListOrder() {
		return "user-page/list-order";
	}
}
