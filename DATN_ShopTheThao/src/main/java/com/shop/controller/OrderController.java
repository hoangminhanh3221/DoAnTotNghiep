package com.shop.controller;

import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Customer;
import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.entity.OrderInfo;
import com.shop.entity.Payment;
import com.shop.entity.Product;
import com.shop.entity.Transport;
import com.shop.service.AccountService;
import com.shop.service.AddressService;
import com.shop.service.CustomerService;
import com.shop.service.OrderDetailService;
import com.shop.service.OrderInfoService;
import com.shop.service.OrderService;
import com.shop.service.PaymentService;
import com.shop.service.ProductService;
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
	
	@Autowired
	private ProductService productService;

	@RequestMapping("/checkout")
	public String checkout(Model model, @ModelAttribute OrderInfo orderInfo, @ModelAttribute Customer customer) {
		if (af.isAuthenticated()) {
			customer = customerService.findCustomerByUsername(af.getUsername()).orElse(null);
			model.addAttribute("customer", customer);
		}
		return "user-page/checkout";
	}

	@RequestMapping("/complete/true")
	public String completeTrue(
			@ModelAttribute Customer customer,
			@RequestParam("description") String description
			) {
		Customer currentCustomer = customerService.findCustomerByUsername(af.getUsername()).get();
		
		Transport transport = new Transport();
		transport.setTransportDate(new Date());
		transport.setOrderInfos(new ArrayList<>());
		transport.setTransportFee(0.0);
		transport.setTransportMethod("Giao hàng nhanh");
		transport.setTransportStatus(false);
		transportService.createTransport(transport);
		
		Payment payment = new Payment();
		payment.setOrderInfos(new ArrayList<>());
		payment.setPaymentAmount(0.0);
		payment.setPaymentDate(new Date());
		payment.setPaymentMethod("Nhận tiền trực tiếp");
		payment.setPaymentStatus(false);
		paymentService.createPayment(payment);
		
		OrderInfo orderInfo = new OrderInfo();
		orderInfo.setFullName(customer.getCustomerName());
		orderInfo.setPhoneNumber(customer.getPhoneNumber());
		orderInfo.setEmail(customer.getAccount().getEmail());
		orderInfo.setDescription(description);
		orderInfo.setOrders(new ArrayList<>());
		orderInfo.setPayment(payment);
		orderInfo.setTransport(transport);
		orderInfo.setAddress(currentCustomer.getAddress());
		orderInfoService.createOrderInfo(orderInfo);

		Order order = new Order();
		order.setOrderAmount(shoppingCartService.getTotalAmount());
		order.setCreateDate(new Date());
		order.setOrderStatus("Đơn mới");
		order.setQuantity(shoppingCartService.getTotalQuantity());
	    order.setAccount(accountService.findAccountById(af.getUsername()).get());
	    order.setOrderInfo(orderInfo);
		orderService.createOrder(order);
		
		List<ShoppingCart> carts = shoppingCartService.getCarts();
		for(ShoppingCart cart : carts) {
			Product product = productService.findProductById(cart.getProductId()).orElse(null);
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setProduct(product);
			orderDetail.setProductPrice(cart.getProductPrice());
			orderDetail.setProductQuantity(cart.getQuantity());
			orderDetail.setOrder(order);
			orderDetailService.createOrderDetail(orderDetail);
		}
		
		return "user-page/order-complete";
	}
	
	@RequestMapping("/complete/false")
	public String completeFalse(
			@ModelAttribute OrderInfo orderInfo,
			@RequestParam("homeNumber") String homeNumber,
			@RequestParam("description") String description
			) {
		AddressAPI addressAPI = sessionService.get("addressAPI");
		Address address = new Address();
		address.setCity(addressAPI.getCity());
		address.setNumberHome(homeNumber);
		address.setDistrict(addressAPI.getDistrict());
		address.setWard(addressAPI.getWard());
		addressService.createAddress(address);
		
		Transport transport = new Transport();
		transport.setTransportDate(new Date());
		transport.setTransportFee(0.0);
		transport.setTransportMethod("Giao hàng nhanh");
		transport.setTransportStatus(false);
		transportService.createTransport(transport);
		
		Payment payment = new Payment();
		payment.setPaymentAmount(0.0);
		payment.setPaymentDate(new Date());
		payment.setPaymentMethod("Nhận tiền trực tiếp");
		payment.setPaymentStatus(false);
		paymentService.createPayment(payment);
		
		orderInfo.setPayment(payment);
		orderInfo.setTransport(transport);
		orderInfo.setAddress(address);
		orderInfoService.createOrderInfo(orderInfo);

		Order order = new Order();
		order.setOrderAmount(shoppingCartService.getTotalAmount());
		order.setCreateDate(new Date());
		order.setOrderStatus("Đơn mới");
		order.setQuantity(shoppingCartService.getTotalQuantity());
	    order.setOrderInfo(orderInfo);
		orderService.createOrder(order);
		
		List<ShoppingCart> carts = shoppingCartService.getCarts();
		for(ShoppingCart cart : carts) {
			Product product = productService.findProductById(cart.getProductId()).orElse(null);
			OrderDetail orderDetail = new OrderDetail();
			orderDetail.setProduct(product);
			orderDetail.setProductPrice(cart.getProductPrice());
			orderDetail.setProductQuantity(cart.getQuantity());
			orderDetail.setOrder(order);
			orderDetailService.createOrderDetail(orderDetail);
		}
		sessionService.remove("addressAPI");
		
		return "user-page/order-complete";
	}
	
	@RequestMapping("/changeAddress")
	public String changeAddress(@RequestParam("homeNumber")String homeNumber) {
		Customer customer = customerService.findCustomerByUsername(af.getUsername()).orElse(null);
		
		AddressAPI addressAPI = sessionService.get("addressAPI");
		
		Address address = customer.getAddress();
		address.setCity(addressAPI.getCity());
		address.setNumberHome(homeNumber);
		address.setDistrict(addressAPI.getDistrict());
		address.setWard(addressAPI.getWard());
		addressService.createAddress(address);
		
		sessionService.remove("addressAPI");
		return "redirect:/order/checkout";
	}

}
