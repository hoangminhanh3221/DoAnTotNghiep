package com.shop;

import java.util.Properties;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

@Configuration
public class MailConfig {

	@Bean
	public JavaMailSender javaMailSender() {
	    JavaMailSenderImpl mailSender = new JavaMailSenderImpl();
	    
	    mailSender.setHost("smtp.gmail.com"); // SMTP server của Gmail
	    mailSender.setPort(587); // Port thường sử dụng cho TLS
	    mailSender.setUsername("fsportstores@gmail.com"); // Tài khoản email của bạn
	    mailSender.setPassword("pykphktfnjkmkmdi"); // Mật khẩu email của bạn
	    
	    Properties props = mailSender.getJavaMailProperties();
	    props.put("mail.transport.protocol", "smtp");
	    props.put("mail.smtp.auth", "true");
	    props.put("mail.smtp.starttls.enable", "true");
	    props.put("mail.debug", "true"); // Sẽ hiển thị log để theo dõi quá trình gửi email
	    
	    return mailSender;
	}

}
