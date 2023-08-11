package com.shop.controller;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Order;
import com.shop.entity.OrderDetail;
import com.shop.service.OrderDetailService;
import com.shop.service.OrderService;

@Controller
public class OrderController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@RequestMapping("/admin/order")
	public String admorder(Model model,@RequestParam (value = "status", required = false) String status) {
		
		if (status == null) {
			status = "Đơn mới";
		}
		
		List<Order> orders = orderService.getOrderByStatus(status);
		System.out.println(status);
        List<Double> listTP = new ArrayList<>();
        double totalPrice =0;
        for (Order order : orders) {
			for (OrderDetail orderdetail : order.getOrderDetails()) {
				totalPrice = totalPrice + orderdetail.getProductPrice() * orderdetail.getProductQuantity();
			}
			listTP.add(totalPrice);
			totalPrice= 0;
		}
        model.addAttribute("orders", orders);
        model.addAttribute("status", status);
        model.addAttribute("total", listTP);
        return "admin-page/order-list"; // Trả về tên của trang HTML
	}
	

}