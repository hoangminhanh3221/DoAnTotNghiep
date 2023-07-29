package com.shop.restcontroller;

import java.util.List;

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
import com.shop.service.implement.ShoppingCartService;
import com.shop.util.SessionService;
import com.shop.util.ShoppingCart;

@RestController
@RequestMapping("/api/orders")
public class OrderAPI {
	
	@Autowired
	private SessionService sessionService;
	
	@RequestMapping("/getAllCart")
	public ResponseEntity<List<ShoppingCart>> getAll(@RequestBody List<ShoppingCart> shoppingCarts) {
		sessionService.set("carts", shoppingCarts);
		return ResponseEntity.ok().build();
	}
	
}
