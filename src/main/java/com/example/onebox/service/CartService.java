package com.example.onebox.service;

import com.example.onebox.dto.CartDto;

import java.util.List;

public interface CartService
{
	CartDto createCart(CartDto cartDto) throws Exception;

	void deleteCartById(Long id);

	CartDto getCartById(Long id);

	CartDto updateCart(CartDto cartDto) throws Exception;

	List<CartDto> getAllCarts();
}