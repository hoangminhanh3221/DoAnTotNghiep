package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.Address;
import com.shop.repository.AddressRepository;
import com.shop.service.AddressService;

@Service
public class AddressServiceImpl implements AddressService {

	private final AddressRepository addressRepository;

	public AddressServiceImpl(AddressRepository addressRepository) {
		this.addressRepository = addressRepository;
	}
	
	@Override
	public Page<Address> findAllAddress(Pageable pageable) {
		return addressRepository.findAll(pageable);
	}

	@Override
	public List<Address> findAllAddress() {
		return addressRepository.findAll();
	}

	@Override
	public Optional<Address> findAddressById(Integer id) {
		return addressRepository.findById(id);
	}

	@Override
	public Address createAddress(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public Address updateAddress(Address address) {
		return addressRepository.save(address);
	}

	@Override
	public void deleteAddress(Integer id) {
		addressRepository.deleteById(id);

	}

}
