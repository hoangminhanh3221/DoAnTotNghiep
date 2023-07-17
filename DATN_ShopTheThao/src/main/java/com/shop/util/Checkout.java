package com.shop.util;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Checkout {
	private Account account;
	private Customer customer;
	private Address address;
}
