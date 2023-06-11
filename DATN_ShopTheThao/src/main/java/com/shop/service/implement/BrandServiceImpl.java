package com.shop.service.implement;

import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.shop.entity.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.ProductService;

public class BrandServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;

    public BrandServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

	@Override
	public Page<Product> findAllProduct(Pageable pageable) {
		return productRepository.findAll(pageable);
	}

	@Override
	public List<Product> findAllProduct() {
		return productRepository.findAll();
	}

	@Override
	public Optional<Product> findProductById(String id) {
		return productRepository.findById(id);
	}

	@Override
	public Product createProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public Product updateProduct(Product product) {
		return productRepository.save(product);
	}

	@Override
	public void deleteProduct(String id) {
		productRepository.deleteById(id);
		
	}
    
}
