package com.example.onebox.service.impl;

import com.example.onebox.domain.Product;
import com.example.onebox.dto.ProductDto;
import com.example.onebox.persistence.repository.ProductRepository;
import com.example.onebox.service.ProductService;
import com.example.onebox.web.converter.ProductMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;


@Service
public class ProductServiceImpl implements ProductService
{
	@Autowired
	private ProductRepository productRepository;

	@Autowired
	private ProductMapper productMapper;

	@Override
	public ProductDto createProduct(ProductDto product) throws Exception
	{
		Product productToCreate = productMapper.toEntity(product);


		if(productRepository.findByDescriptionIsLike(productToCreate.getDescription()))
		{
			throw new Exception("Product with id " + productToCreate.getId() + " already exist");
		}
		return productMapper.toDto(productRepository.save(productToCreate));
	}

	@Override
	public List<ProductDto> getAllProducts()
	{
		return productMapper.toDtoList(productRepository.findAll());
	}

	@Override
	public ProductDto getProductById(Long id) throws Exception
	{
		if (!productRepository.existsById(id))
		{
			throw new Exception("Product with id " + id + " don't exist");
		}

		return productMapper.toDto( productRepository.getById(id));
	}

	@Override
	public void createProducts(List<Product> products)
	{
		productRepository.saveAll(products);
	}

	@Override
	public void deleteProduct(Long id) throws Exception
	{
		if(! productRepository.existsById(id))
		{
			throw new Exception("Product not found");
		}

		productRepository.deleteById(id);
	}

	@Override
	public ProductDto updateProduct(ProductDto productDto)
	{
		Product productToUpdate = productMapper.toEntity(productDto);

		if(!productRepository.existsById(productToUpdate.getId()))
		{
			throw new IllegalArgumentException("Product with id " + productToUpdate.getId() + " don't exist");
		}

		return productMapper.toDto(productRepository.save(productToUpdate));
	}

	public List<Product>checkProductExists(List<Product> productList)
	{
		List<Product> productNotEsxist = new ArrayList<>();

		for(Product product : productList)
		{
			if(! productRepository.existsById(product.getId()))
			{
				productNotEsxist.add(product);
			}

		}
		return productNotEsxist;
	}

}
