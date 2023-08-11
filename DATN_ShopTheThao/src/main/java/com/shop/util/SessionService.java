package com.shop.util;

import javax.servlet.http.HttpSession;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SessionService {
	@Autowired
	private HttpSession session;
	
	public <T>T get(String name){
		return (T) session.getAttribute(name);
	}
	public <T>T getValue(String name, T defaultValue){
		T value = get(name);
		return value != null ? value : defaultValue;
	}
	public void set(String name, Object value) {
		session.setAttribute(name, value);
	}
	public void remove(String name) {
		session.removeAttribute(name);
	}
}