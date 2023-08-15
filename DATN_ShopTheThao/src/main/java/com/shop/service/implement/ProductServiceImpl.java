package com.shop.service.implement;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.shop.entity.Product;
import com.shop.repository.ProductRepository;
import com.shop.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService{
	
	private final ProductRepository productRepository;

    public ProductServiceImpl(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }

	@Override
	public Page<Product> findAllProduct(Pageable pageable) {
		return productRepository.findAll(pageable);
	}
	
	@Override
	public Page<Product> findBySubcategoryID(String subcategoryId, Pageable pageable) {
		return productRepository.findBySubcategoryID(subcategoryId, pageable);
	}

	@Override
	public Page<Product> findByCategoryID(String categoryId, Pageable pageable) {
		return productRepository.findByCategoryID(categoryId, pageable);
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
	
	@Override
	public List<Product> getProductsSortByDateDesc() {
		return productRepository.getProductsSortByDateDesc();
	}

	@Override
	public List<Product> findBestSellingProducts() {
		List<Product> lists = new ArrayList<>();
		for (String result : productRepository.findBestSellingProducts()) {
		    Optional<Product> product = this.findProductById(result);
		    if(product.isPresent()) {
		    	lists.add(product.get());
		    }
		}
		return lists;
	}

	@Override
	public List<Product> findMostLikedProducts() {
		List<Product> lists = new ArrayList<>();
		for (String result : productRepository.findMostLikedProducts()) {
			Optional<Product> product = this.findProductById(result);
		    if(product.isPresent()) {
		    	lists.add(product.get());
		    }
		}
		return lists;
	}

	@Override
	public List<Product> findProductsOnSale() {
		return productRepository.findProductsOnSale();
	}
}
