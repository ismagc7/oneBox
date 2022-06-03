package com.example.onebox.web.controller;


import com.example.onebox.dto.CartDto;
import com.example.onebox.service.CartService;
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
@RequestMapping("/carts")
public class CartController
{

	@Autowired
	CartService cartService;


	@GetMapping
	public ResponseEntity<List<CartDto>> getAllCarts()
	{
		return new ResponseEntity<List<CartDto>>(cartService.getAllCarts(), HttpStatus.OK);
	}

	@GetMapping(value="/{id}")
	public ResponseEntity<?> getCartById(@PathVariable Long id)
	{
		try
		{
			return new ResponseEntity<CartDto>(cartService.getCartById(id), HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PostMapping
	public ResponseEntity<?> createCart(@RequestBody CartDto cartDto)
	{
		try
		{
			return new ResponseEntity<CartDto>(cartService.createCart(cartDto), HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@DeleteMapping(value="/{id}")
	public ResponseEntity<?> deleteCart(@PathVariable Long id)
	{
		try
		{
			cartService.deleteCartById(id);

			return new ResponseEntity<String>(String.format("Cart with id %s deleted correctly", id.toString()), HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

	@PutMapping
	public ResponseEntity<?> updateCart(@RequestBody CartDto cartDto)
	{
		try
		{
			return new ResponseEntity<CartDto>(cartService.updateCart(cartDto), HttpStatus.OK);
		}
		catch (Exception e)
		{
			return new ResponseEntity<String>(e.getMessage(), HttpStatus.NOT_FOUND);
		}
	}

}
