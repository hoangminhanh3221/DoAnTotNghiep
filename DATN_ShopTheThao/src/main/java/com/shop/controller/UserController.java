package com.shop.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Customer;
import com.shop.service.AccountService;
import com.shop.service.AddressService;
import com.shop.service.CustomerService;
import com.shop.util.AddressAPI;
import com.shop.util.AuthenticationFacade;
import com.shop.util.SessionService;

@Controller
public class UserController {

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AuthenticationFacade af;

	@Autowired
	private SessionService sessionService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private AccountService accountService;

	@RequestMapping("/user/info")
	public String getInfo(Model model, @ModelAttribute Customer customer) {
		customer = customerService.findCustomerByUsername(af.getUsername()).orElse(null);

		if (customer.getAddress().equals(null)) {
			customer.setBirthdayString("2023-01-01");
			model.addAttribute("isAddress", true);
		} else {
			// Chuyển đổi birthday sang chuỗi định dạng yyyy-MM-dd
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			String birthdayString = dateFormat.format(customer.getBirthday());
			customer.setBirthdayString(birthdayString);
			
			model.addAttribute("isAddress", false);
		}
		model.addAttribute("customer", customer);
		return "user-page/user-info";
	}

	@RequestMapping("/user/info/edit")
	public String postInfo(
			@ModelAttribute Customer customer,
			@RequestParam("avatar") MultipartFile avatarFile
			) {
		System.out.println(customer.getAddress().getNumberHome());
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = dateFormat.parse(customer.getBirthdayString());
			customer.setBirthday(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		customer.setCustomerImage(String.valueOf(avatarFile));
		customer.setAccount(accountService.findAccountById(af.getUsername()).orElse(null));
		customer.setAddress(customerService.findCustomerByUsername(af.getUsername()).get().getAddress());
		
		customerService.updateCustomer(customer);
		sessionService.remove("addressAPI");

		return "redirect:/user/info";
	}

	@RequestMapping("/user/changeAddress")
	public String changeAddress(@RequestParam("homeNumber") String homeNumber) {
		Customer customer = customerService.findCustomerByUsername(af.getUsername()).orElse(null);

		AddressAPI addressAPI = sessionService.get("addressAPI");

		Address address = customer.getAddress();
		address.setCity(addressAPI.getCity());
		address.setNumberHome(homeNumber);
		address.setDistrict(addressAPI.getDistrict());
		address.setWard(addressAPI.getWard());
		addressService.updateAddress(address);

		sessionService.remove("addressAPI");
		return "redirect:/order/checkout";
	}
}
