package com.shop.controller;

import java.util.Date;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Customer;
import com.shop.repository.AccountRepository;
import com.shop.service.AccountService;
import com.shop.service.AddressService;
import com.shop.service.CustomerService;
import com.shop.service.EmailService;

@Controller
@RequestMapping("/account")
public class RegisterController {
	
	@Autowired
	CustomerService customerService;
	
	@Autowired
	AccountService accountService;
	
	@Autowired
	EmailService emailService;
	
	@Autowired
	AccountRepository aRep;
	
	@Autowired
	private AddressService addressService;
	
	@GetMapping("/register")
	public String accRegister(Model model) {
		return "/account/register";
	}
	
	@PostMapping("/register")
	public String createRegister(
			@RequestParam("username") String username,
			@RequestParam("email")    String email,
			@RequestParam("password") String password,
			Model model
		) {
		
		// Kiểm tra xem username đã tồn tại hay chưa
	    Optional<Account> existingAccount = accountService.findAccountById(username);
	    if (existingAccount.isPresent()) {
	    	System.out.println("username");
	        model.addAttribute("message", "Tên tài khoản đã tồn tại !");
	        return "/account/register";
	    }

	    // Kiểm tra xem email đã tồn tại hay chưa
	    Optional<Account> existingEmail = aRep.findAccountByEmail(email);
	    if (existingEmail.isPresent()) {
	    	System.out.println("password");
	    	model.addAttribute("message", "Email đã tồn tại !");
	        return "/account/register";
	    }
	    
		// Tạo một đối tượng User mới và lưu vào CSDL
        Account acc = new Account();
        acc.setUsername(username);
        acc.setEmail(email);
        acc.setPassword(password);
        acc.setRole("user");
        // Cập nhật createDate trước khi lưu vào CSDL
        acc.setCreateDate(new Date());
        
        aRep.save(acc);
        
        Address address = new Address();
        addressService.createAddress(address);
        
        Customer customer = new Customer();
        customer.setBirthday(new Date());
        customer.setAccount(acc);
        customer.setAddress(address);
        customerService.createCustomer(customer);
        
        // Đăng ký thành công, thêm thông báo vào Model
        model.addAttribute("status",4);

        // Trả về trang register_page.html nhưng thông báo sẽ được xử lý bởi JavaScript để hiển thị modal
        return "redirect:/account/login/form";
	}
}