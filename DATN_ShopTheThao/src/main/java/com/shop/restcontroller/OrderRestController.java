package com.shop.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.JsonNode;
import com.shop.entity.Order;
import com.shop.service.OrderService;
@CrossOrigin("*")
@RestController
@RequestMapping("/rest/orders")
public class OrderRestController {
//	@Autowired
//	private final OrderService orderService;
//	
//	@PostMapping()
//	public Order create(@RequestBody JsonNode orderData) {
//		return orderService.createJson(orderData);
//	}
}
