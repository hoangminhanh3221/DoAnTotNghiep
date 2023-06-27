package com.shop.restcontroller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.shop.entity.Product;
import com.shop.service.ProductService;

@CrossOrigin("*")
@RestController
@RequestMapping("/api/products")
public class ProductAPI {
@Autowired private ProductService productService;
	
	@GetMapping()
	public List<Product>getAll(){
		return productService.findAllProduct();
	}
	
	@GetMapping("/{productId}")
	public Product getOne(@PathVariable("productId") String id) {
		System.out.println(id);
		return productService.findProductById(id).get();
	}
	
	@PostMapping("/create")
	public Product create(@RequestBody Product product) {
		return productService.createProduct(product);
	}
	
	@PutMapping("/update/{id}")
	public Product update(@RequestBody Product product,@PathVariable("id") String id) {
		return productService.updateProduct(product);
	}
	
	@DeleteMapping("/delete/{id}")
	public void delete(@PathVariable("id") String id) {
		productService.deleteProduct(id);
	}
}
