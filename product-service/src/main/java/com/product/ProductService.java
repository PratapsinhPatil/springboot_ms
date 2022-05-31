package com.product;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class ProductService {

	@Autowired
	ProductRepository productRepository;
	
	public void saveProduct(ProductVO product) {
		productRepository.save(product);
	}
	public List<ProductVO> getProducts() {
		return productRepository.findAll();
	}

	public List<ProductVO> getProduct(List<Integer> id) {
		return productRepository.findAllById(id);
	}
//	public List<ProductVO> getProductByPartialName(String name) {
//		return productRepository.findAllById(id);
//	}

	public void deleteProduct(Integer id) {
		productRepository.deleteById(id);
	}
}
