package com.shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Customer;
import com.shop.entity.Order;
import com.shop.entity.OrderInfo;
import com.shop.entity.Payment;
import com.shop.entity.Transport;
import com.shop.service.AccountService;
import com.shop.service.AddressService;
import com.shop.service.CustomerService;
import com.shop.service.OrderDetailService;
import com.shop.service.OrderInfoService;
import com.shop.service.OrderService;
import com.shop.service.PaymentService;
import com.shop.service.TransportService;
import com.shop.service.implement.ShoppingCartService;
import com.shop.util.AddressAPI;
import com.shop.util.AuthenticationFacade;
import com.shop.util.SessionService;
import com.shop.util.ShoppingCart;

@Controller
@RequestMapping("/order")
public class OrderController {

	@Autowired
	private TransportService transportService;

	@Autowired
	private PaymentService paymentService;

	@Autowired
	private AuthenticationFacade af;

	@Autowired
	private AccountService accountService;

	@Autowired
	private CustomerService customerService;

	@Autowired
	private AddressService addressService;

	@Autowired
	private OrderService orderService;

	@Autowired
	private OrderDetailService orderDetailService;

	@Autowired
	private OrderInfoService orderInfoService;

	@Autowired
	private ShoppingCartService shoppingCartService;

	@Autowired
	private SessionService sessionService;

	@RequestMapping("/checkout")
	public String checkout(Model model, @ModelAttribute OrderInfo orderInfo, @ModelAttribute Customer customer) {
		if (af.isAuthenticated()) {
			customer = customerService.findCustomerByUsername(af.getUsername()).orElse(null);
			model.addAttribute("customer", customer);
		}
		return "user-page/checkout";
	}

	@RequestMapping("/complete")
	public String complete(@ModelAttribute OrderInfo orderInfo) {
//		Transport transport = new Transport();
//		transport.setTransportDate(new Date());
//		transport.setOrderInfos(new ArrayList<>());
//		transport.setTransportFee(0.0);
//		transport.setTransportMethod("Giao hàng nhanh");
//		transport.setTransportStatus(false);
//		
//		Payment payment = new Payment();
//		payment.setOrderInfos(new ArrayList<>());
//		payment.setPaymentAmount(0.0);
//		payment.setPaymentDate(new Date());
//		payment.setPaymentMethod("Nhận tiền trực tiếp");
//		payment.setPaymentStatus(false);

//		Order order = new Order();
//		order.setOrderAmount(shoppingCartService.getTotalAmount());
//		order.setCreateDate(new Date());
//		order.setOrderStatus("Đơn mới");
//		order.setQuantity(shoppingCartService.getTotalQuantity());
//		if (af.isAuthenticated()) {
//			Optional<Account> account = accountService.findAccountById(af.getUsername());
//	        if(account.isPresent()) {
//	        	order.setAccount(account.get());
//	        }
//	    }
//		order.setOrderInfo(orderInfoService.findOrderInfoById(1).get());
//		orderService.createOrder(order);

		System.out.println(orderInfo.getFullName());
		System.out.println(orderInfo.getEmail());
		System.out.println(orderInfo.getPhoneNumber());
		AddressAPI addressAPI = sessionService.get("addressAPI");
		return "user-page/order-complete";
	}

}
