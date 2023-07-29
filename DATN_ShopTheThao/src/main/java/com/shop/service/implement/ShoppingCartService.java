package com.shop.service.implement;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.shop.util.SessionService;
import com.shop.util.ShoppingCart;

@Service
public class ShoppingCartService {
	
	@Autowired
	private SessionService sessionService;
	
	private List<ShoppingCart> carts;
	
	public List<ShoppingCart> getCarts() {
		return carts;
	}

	public void setCarts(List<ShoppingCart> carts) {
		this.carts = carts;
	}

	public double getTotalAmount() {
		this.carts = sessionService.get("carts");
		if(this.carts != null) {
			double total = 0.0;
			for(ShoppingCart cart : this.carts) {
				total += cart.getProductPrice() * cart.getQuantity();
			}
			return total;
		} else {
			return 0.0;
		}
	}
	
	public int getTotalQuantity() {
		this.carts = sessionService.get("carts");
		if(this.carts != null) {
			int total = 0;
			for(ShoppingCart cart : this.carts) {
				total += cart.getQuantity();
			}
			return total;
		} else {
			return 0;
		}
	}
	
	
	
}
