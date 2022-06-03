package com.example.onebox.web.controller;


import com.example.onebox.dto.ProductDto;
import com.example.onebox.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;


@RestController
@RequestMapping("/products")
public class ProductController
{

	@Autowired
	ProductService productService;


	@GetMapping
	public ResponseEntity<List<ProductDto>> getAllProducts()
	{
		return new ResponseEntity<List<ProductDto>>(productService.getAllProducts(), HttpStatus.OK);
	}

	@GetMapping(value="/{id}")
	public ResponseEntity<?> getProductById(@PathVariable Long id)
	{
		try
		{
			return new ResponseEntity<ProductDto>(productService.getProductById(id), HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<?> createProduct(@RequestBody ProductDto productDto)
	{
		try
		{
			return new ResponseEntity<ProductDto>(productService.createProduct(productDto), HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> deleteProduct(@PathVariable Long id)
	{
		try
		{
			productService.deleteProduct(id);

			return new ResponseEntity<String>(String.format("Product with id %s deleted correctly", id.toString()), HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping
	public ResponseEntity<?> updateProduct(@RequestBody ProductDto productDto)
	{
		try
		{
			return new ResponseEntity<ProductDto>(productService.updateProduct(productDto), HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
