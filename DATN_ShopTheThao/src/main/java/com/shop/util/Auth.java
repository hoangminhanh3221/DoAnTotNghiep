package com.shop.util;

import com.shop.entity.Account;

public class Auth {
	
	public static Account account = null;
	
	public static boolean isLogin() {
		return Auth.account != null;
	}
	
	public static boolean isAdmin() {
		return Auth.isLogin() && account.getRole().equals("Admin");
	}
	
	public static void logoff() {
		Auth.account = null;
	}
	
}
