package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.Discount;
import com.shop.repository.DiscountRepository;
import com.shop.service.DiscountService;

@Service
public class DiscountServiceImpl implements DiscountService{
	
	private final DiscountRepository discountRepository;

	public DiscountServiceImpl(DiscountRepository discountRepository) {
		this.discountRepository = discountRepository;
	}

	@Override
	public Page<Discount> findAllDiscount(Pageable pageable) {
		return discountRepository.findAll(pageable);
	}

	@Override
	public List<Discount> findAllDiscount() {
		return discountRepository.findAll();
	}

	@Override
	public Optional<Discount> findDiscountById(String id) {
		return discountRepository.findById(id);
	}

	@Override
	public Discount createProduct(Discount Discount) {
		return discountRepository.save(Discount);
	}

	@Override
	public Discount updateProduct(Discount Discount) {
		return discountRepository.save(Discount);
	}

	@Override
	public void deleteDiscount(String id) {
		discountRepository.deleteById(id);
	}


}
