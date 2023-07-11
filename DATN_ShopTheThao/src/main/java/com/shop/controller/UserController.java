package com.shop.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Customer;
import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.service.AccountService;
import com.shop.service.AddressService;
import com.shop.service.CustomerService;
import com.shop.service.OrderService;
import com.shop.util.XDate;

@Controller
@RequestMapping("/user")
public class UserController {
	
	@Autowired
	private AccountService accountService;
	@Autowired
	private AddressService addressService;
	@Autowired
	private CustomerService customerService;
	@Autowired
	private OrderService orderService;
	@Autowired
	private XDate xDate;
	
	@RequestMapping("/info")
	public String getInfo(Model model) {
	    Account account = accountService.findAccountById("kiet").orElse(null);  
	    Customer customers = account.getCustomers().get(0);
	    model.addAttribute("account", account);
	    model.addAttribute("customer", customers);
	    return "user-page/user-info";
	}

	@RequestMapping(value = "/edit", method = RequestMethod.POST)
	public String editInfo(
			@RequestParam("email") String email
			,@RequestParam("customerName") String name
			,@RequestParam("numberHome") String numberHome
			,@RequestParam("ward") String ward
			,@RequestParam("district") String district
			,@RequestParam("city") String city
			,@RequestParam("gender") boolean gender
			,@RequestParam("birthday") String birthdayStr
		) {
		Account account = accountService.findAccountById("kiet").orElse(null);
		Customer oldCustomer = customerService.findCustomerByAccount(account).get(0);
		Address oldAddress = oldCustomer.getAddress();
		account.setEmail(email);
		oldCustomer.setAccount(account);
		oldAddress.setNumberHome(numberHome);
		oldAddress.setWard(ward);
		oldAddress.setDistrict(district);
		oldAddress.setCity(city);
		oldCustomer.setCustomerName(name);
		oldCustomer.setGender(gender);
		Date birthday = null;
	    try {
	        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
	        birthday = dateFormat.parse(birthdayStr);
	    } catch (ParseException e) {
	        e.printStackTrace();
	    }
	    oldCustomer.setBirthday(birthday);
		oldCustomer.setAddress(oldAddress);
	    accountService.updateAccount(account);
	    customerService.updateCustomer(oldCustomer);
	    return "redirect:/user/info";
	}
	
	@RequestMapping("/list-order")
	public String getListOrder(Model model) {
		Account account = accountService.findAccountById("kiet").orElse(null);
		List<Order> orders = account.getOrders();
	    model.addAttribute("account", account);
	    model.addAttribute("orders", orders);
		return "user-page/list-order";
	}
	@RequestMapping("/order-detail/{orderId}")
	public String getOrderDetail(@PathVariable Integer orderId, Model model) {
	    Order order = orderService.findOrderById(orderId).get();
	    List<OrderDetail> orderDetails = order.getOrderDetails();
	    model.addAttribute("order", order);	
	    model.addAttribute("orderDetails", orderDetails);
	    return "user-page/order-detail";
	}
	@RequestMapping("/home")
	public String gethome() {
		return "user-page/home";
	}
	
	@RequestMapping("/change-pass")
	public String getChangePass(Model model) {
		Account account = accountService.findAccountById("kiet").orElse(null);
		model.addAttribute("account", account);
		return "account/change-pass";
	}

}
