package com.example.onebox.service;

import com.example.onebox.dto.CartDto;

import java.util.List;

public interface CartService
{
	CartDto createCart(CartDto cartDto) throws Exception;

	void deleteCartById(Long id) throws Exception;

	CartDto getCartById(Long id) throws Exception;

	CartDto updateCart(CartDto cartDto) throws Exception;

	List<CartDto> getAllCarts();
}