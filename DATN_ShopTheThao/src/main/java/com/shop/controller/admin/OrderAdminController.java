package com.shop.controller.admin;

import java.util.List;
import java.util.Optional;
import java.util.stream.IntStream;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.shop.entity.Order;
import com.shop.repository.OrderRepository;
import com.shop.service.OrderDetailService;
import com.shop.service.OrderService;

@Controller
@RequestMapping("/admin/order")
public class OrderAdminController {
	
	@Autowired
	private OrderService orderService;
	
	@Autowired
	private OrderRepository orderRepository;
	
	@Autowired
	private OrderDetailService orderDetailService;
	
	@RequestMapping("/orderList")
	public String getOrderList(
			Model model,
			@RequestParam("page") Optional<Integer> page,
			@RequestParam("status") Optional<Integer> status
			) {
		Pageable pageable = PageRequest.of(page.orElse(0), 10, Sort.by("createDate").descending());
		Page<Order> list = null;
		
		if(status.isPresent()) {
			if(status.get() == 1) {
				list = orderRepository.findAllByOrderStatus("Đơn mới", pageable);
			} else if(status.get() == 2) {
				list = orderRepository.findAllByOrderStatus("Đơn bị hủy", pageable);
			} else if(status.get() == 3) {
				list = orderRepository.findAllByOrderStatus("Đơn được duyệt", pageable);
			} else {
				list = orderService.findAllOrders(pageable);
			}
			model.addAttribute("status", status.get());
		} else {
			list = orderService.findAllOrders(pageable);
			model.addAttribute("status", 0);
		}
		
		model.addAttribute("orders", list);
		
		if(page.isPresent()) {
			model.addAttribute("page", page.get());
		} else {
			model.addAttribute("page", 0);
		}
		
		int totalPages = list.getTotalPages();
		List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().toList();
		model.addAttribute("pageNumbers", pageNumbers);
		return "admin-page/order-list";
	}
	
	@RequestMapping("/changeStatus/{status}/{id}")
	public String changeStatus(
			@PathVariable("status") String status,
			@PathVariable("id") Integer id,
			Model model
			) {
		Order order = orderService.findOrderById(id).orElse(null);
		if(status.equals("yes")){
			order.setOrderStatus("Đơn được duyệt");
		} else {
			order.setOrderStatus("Đơn bị hủy");
		}
		orderService.updateOrder(order);
		return "redirect:/admin/order/orderList?page=0&status=0";
	}
	
}
