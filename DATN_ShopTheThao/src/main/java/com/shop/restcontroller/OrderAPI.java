package com.shop.restcontroller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entity.Product;
import com.shop.util.ShoppingCart;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/orders")
public class OrderAPI {
	
	@RequestMapping("/getAllCart")
	public ResponseEntity<HttpStatus> getOne() {
		return ResponseEntity.ok().build();
	}
	
}
