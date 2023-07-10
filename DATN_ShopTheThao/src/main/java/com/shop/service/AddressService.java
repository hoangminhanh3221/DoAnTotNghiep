package com.shop.service;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Address;

public interface AddressService {
	
	Page<Address> findAllAddress(Pageable pageable);
	
	List<Address> findAllAddress();

	Optional<Address> findAddressById(Integer id);
	
	Address createAddress(Address address);

	Address updateAddress(Address address);

	void deleteAddress(Integer id);
	
}
