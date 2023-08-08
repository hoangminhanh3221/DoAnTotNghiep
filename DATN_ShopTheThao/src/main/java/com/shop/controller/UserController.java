package com.shop.controller;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Customer;
import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.service.AccountService;
import com.shop.service.AddressService;
import com.shop.service.CustomerService;
import com.shop.service.OrderService;
import com.shop.util.AddressAPI;
import com.shop.util.AuthenticationFacade;
import com.shop.util.ImageService;
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
	
	@Autowired
	private ImageService imageService;
	
	@Autowired
	private OrderService orderService;

	@RequestMapping("/user/info")
	public String getInfo(Model model, @ModelAttribute Customer customer) {
		customer = customerService.findCustomerByUsername(af.getUsername()).orElse(null);
		
		int randomInt = (int) (Math.random() * (99999 - 10000 + 1)) + 10000;

		model.addAttribute("image", "/user-page/images/avatars/" + customer.getCustomerImage() + "?version=" + randomInt);
		// Chuyển đổi birthday sang chuỗi định dạng yyyy-MM-dd
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		String birthdayString = dateFormat.format(customer.getBirthday());
		customer.setBirthdayString(birthdayString);
		
		if (customer.getAddress().getNumberHome() == null) {
			model.addAttribute("isAddress", true);
		} else {
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
		Customer currentCustomer = customerService.findCustomerByUsername(af.getUsername()).orElse(null);
		
		Address currentAddress = currentCustomer.getAddress();
		if(currentAddress.getNumberHome() == null) {
			AddressAPI addressAPI = sessionService.get("addressAPI");
			currentAddress.setNumberHome(customer.getAddress().getNumberHome());
			currentAddress.setCity(addressAPI.getCity());
			currentAddress.setDistrict(addressAPI.getDistrict());
			currentAddress.setWard(addressAPI.getWard());
			sessionService.remove("addressAPI");
		}
		
		Account currentAccount = currentCustomer.getAccount();
		currentAccount.setEmail(customer.getAccount().getEmail());
		
		try {
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Date birthday = dateFormat.parse(customer.getBirthdayString());
			currentCustomer.setBirthday(birthday);
		} catch (ParseException e) {
			e.printStackTrace();
		}
		currentCustomer.setCustomerName(customer.getCustomerName());
		currentCustomer.setGender(customer.getGender());
		currentCustomer.setPhoneNumber(customer.getPhoneNumber());
		System.out.println(avatarFile.getOriginalFilename());
		try {
			if(avatarFile.equals(null)) {
				System.out.println("trueee");
				currentCustomer.setCustomerImage(customer.getCustomerImage());
			} else {
				System.out.println("falseee");
				currentCustomer.setCustomerImage(imageService.saveImage(avatarFile, "customer"+currentCustomer.getCustomerId(), "avatars"));
			}
		} catch (IOException e1) {
			e1.printStackTrace();
		}
		currentCustomer.setAddress(currentAddress);
		currentCustomer.setAccount(currentAccount);
		
		try {
			accountService.updateAccount(currentAccount);
			addressService.updateAddress(currentAddress);
			customerService.updateCustomer(currentCustomer);
		} catch (Exception e) {
			System.out.println(e);
		}

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
	
	@RequestMapping("/user/list-order")
	public String getListOrder(Model model) {
		Account account = accountService.findAccountById(af.getUsername()).orElse(null);
		List<Order> orders = account.getOrders();
	    model.addAttribute("account", account);
	    model.addAttribute("orders", orders);
		return "user-page/list-order";
	}
	@RequestMapping("/user/order-detail/{orderId}")
	public String getOrderDetail(@PathVariable Integer orderId, Model model) {
	    Order order = orderService.findOrderById(orderId).get();
	    List<OrderDetail> orderDetails = order.getOrderDetails();
	    model.addAttribute("order", order);	
	    model.addAttribute("orderDetails", orderDetails);
	    return "user-page/order-detail";
	}
}
