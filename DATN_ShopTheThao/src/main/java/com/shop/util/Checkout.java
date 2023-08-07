package com.shop.util;

import java.io.Serializable;

import com.shop.entity.Account;
import com.shop.entity.Address;
import com.shop.entity.Customer;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Checkout implements Serializable{
	private String fullname;
	private String phoneNumber;
	private String email;
	private String homeNumber;
	private String city;
	private String district;
	private String ward;
	private String description;
}
