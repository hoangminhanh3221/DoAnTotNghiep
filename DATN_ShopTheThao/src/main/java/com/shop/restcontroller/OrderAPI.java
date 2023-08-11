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

import com.shop.util.AddressAPI;
import com.shop.util.SessionService;


@RestController
@RequestMapping("/api/orders")
public class OrderAPI {
	
	@Autowired
	private SessionService sessionService;
	
	@RequestMapping("/address")
	public ResponseEntity<AddressAPI> getAddress(@RequestBody AddressAPI addressAPI) {
		sessionService.set("addressAPI", addressAPI);
		return ResponseEntity.ok().build();
	}
	
}
