package com.shop.util;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class ShoppingCart implements Serializable{
	private String productId;
	private Integer quantity;
	private Double productPrice;
}
