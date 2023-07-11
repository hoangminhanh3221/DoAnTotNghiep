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
		
	@Autowired
	AccountService accountService;
	
	//Cung cấp nguồn dữ liệu đăng nhập
	protected void configure(AuthenticationManagerBuilder auth) throws Exception {
	    auth.userDetailsService(username -> {
	        try {
	            Optional<Account> user = accountService.findAccountById(username);

	            String password = getBCryptPasswordEncoder().encode(user.get().getPassword());

	            String[] roles = user.stream()
	                    .map(er -> er.getRole())
	                    .collect(Collectors.toList())
	                    .toArray(new String[0]);

	            return User.withUsername(username).password(password).roles(roles).build();
	        } catch (NoSuchElementException e) {
	            throw new UsernameNotFoundException(username + "Not Found");
	        }
	    });
	}

	
	//Phân quyền 
	@Override
	protected void configure(HttpSecurity http) throws Exception{
		//CSRF,CORS
        http.csrf().disable().cors().disable();
        
        //Phân quyền sử dụng
		http.authorizeHttpRequests()
			.antMatchers("/admin/authorities").hasRole("admin")
			.antMatchers("/admin/**").hasAnyRole("admin","employee")
			.antMatchers("/user/**").authenticated()
			.anyRequest().permitAll();//khách chưa có tham gia
		
		//Giao diện login
        http.formLogin(login -> login
                .loginPage("/account/login/form")
                .loginProcessingUrl("/account/login")
                .defaultSuccessUrl("/account/login/success", false)
                .failureUrl("/account/login/error")
                .usernameParameter("email-username")
                .passwordParameter("password"));
        http.rememberMe(me -> me
                .rememberMeParameter("remember"));
        
        http.exceptionHandling(handling -> handling
                .accessDeniedPage("/account/unauthoried"));
        //Đăng xuất
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
