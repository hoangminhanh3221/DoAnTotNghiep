package com.shop.util;

import java.util.Collection;

import javax.annotation.PostConstruct;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

@Component
public class AuthenticationFacade {

	public Authentication getAuthentication() {
		return SecurityContextHolder.getContext().getAuthentication();
	}

	public boolean hasRole(String roleName) {
		Authentication authentication = getAuthentication();
		Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
		return authorities.stream().anyMatch(auth -> auth.getAuthority().equals(roleName));
	}

	public String getUsername() {
		Authentication authentication = getAuthentication();
		return authentication.getName();
	}
	
	public boolean isAuthenticated() {
	    Authentication authentication = getAuthentication();
	    return authentication != null && authentication.isAuthenticated();
	}

}