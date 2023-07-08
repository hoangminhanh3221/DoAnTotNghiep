package com.shop;

import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpMethod;
import org.springframework.security.config.annotation.authentication.builders.AuthenticationManagerBuilder;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.builders.WebSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;

import com.shop.entity.Account;
import com.shop.service.AccountService;

@Configuration
@EnableWebSecurity
public class SecurityConfig extends WebSecurityConfigurerAdapter{
	
	@Bean
	public BCryptPasswordEncoder passwordEncoder() {
	    return new BCryptPasswordEncoder();
	}
	
	@Autowired
	AccountService accountService;
	
	//Cung cấp nguồn dữ liệu đăng nhập
	protected void configure(AuthenticationManagerBuilder auth) throws Exception{
		auth.userDetailsService(username -> {
			try { 
				  Optional<Account> user = accountService.findAccountById(username);
				  
				  BCryptPasswordEncoder passwordEncoder = passwordEncoder();
				  String password = passwordEncoder.encode(user.get().getPassword());;
				  
				  String[] roles = user.stream() 
						  		.map(er -> er.getRole())
						  		.collect(Collectors.toList()).toArray(new String[0]);
				  return User.withUsername(username).password(password).roles(roles).build();
				  
			}catch (NoSuchElementException e) {
				throw new UsernameNotFoundException(username + "Not Found");
			}
		});
	}
	
	//Phân quyền sử dụng
	@Override
	protected void configure(HttpSecurity http) throws Exception{
        http.csrf().disable();
		http.authorizeHttpRequests()
			.antMatchers("/order/**").authenticated()
			.antMatchers("/admin/**").hasAnyRole("STAF","DIRE")
			.antMatchers("/rest/authorities").hasRole("DIRE")
			.anyRequest().permitAll();
        http.formLogin(login -> login
                .loginPage("/account/login/form")
                .loginProcessingUrl("/account/login/go")
                .defaultSuccessUrl("/account/login/success", false)
                .failureUrl("/account/login/error"));
        http.rememberMe(me -> me
                .tokenValiditySeconds(86400));
        http.exceptionHandling(handling -> handling
                .accessDeniedPage("/account/unauthoried"));
        http.logout(logout -> logout
                .logoutUrl("/account/logoff")
                .logoutSuccessUrl("/account/logoff/success"));
	}
	//Mã hóa mk
	@Bean
	public BCryptPasswordEncoder getBCryptPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
	 //Allow truy xuất REST API từ bên ngoài
	public void configure(WebSecurity web)throws Exception{
		web.ignoring().antMatchers(HttpMethod.OPTIONS,"/**");
	}
}
