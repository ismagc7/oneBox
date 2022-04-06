package com.example.onebox.service;

import com.example.onebox.domain.Product;
import com.example.onebox.dto.ProductDto;

import java.util.List;

public interface ProductService
{
	ProductDto createProduct(ProductDto product) throws Exception;

	List<ProductDto> getAllProducts();

	ProductDto getProductById(Long id) throws Exception;

	void createProducts(List<Product> products);

	List<Product>checkProductExists(List<Product> productList);

	void deleteProduct(Long id) throws Exception;

	ProductDto updateProduct(ProductDto productDto);
}
