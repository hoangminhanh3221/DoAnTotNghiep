package com.shop.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

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
	public String getInfo(Model model) throws ParseException {
	    Account account = accountService.findAccountById("kiet").orElse(null);  
	    Customer customer = account.getCustomers().get(0);
	    Address address = customer.getAddress();
	    model.addAttribute("account", account);
	    model.addAttribute("customer", customer);
	    model.addAttribute("address", address);
	    return "user-page/user-info";
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
	@RequestMapping("/login")
	public String getlogin() {
		return "account/login";
	}
}
