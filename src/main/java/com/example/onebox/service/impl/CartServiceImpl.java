package com.example.onebox.service.impl;

import com.example.onebox.domain.Cart;
import com.example.onebox.domain.Product;
import com.example.onebox.dto.CartDto;
import com.example.onebox.persistence.repository.CartRepository;
import com.example.onebox.service.CartService;
import com.example.onebox.service.ProductService;
import com.example.onebox.web.converter.CartMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class CartServiceImpl implements CartService
{
	@Autowired
	private CartRepository cartRepository;

	@Autowired
	private CartMapper cartMapper;

	@Autowired
	private ProductService productService;

	@Override
	public CartDto createCart(CartDto cartDto) throws Exception
	{
		Cart cart = cartMapper.toCart(cartDto);

		if (!cart.getProducts().isEmpty())
		{
			List<Product> productsNotExists = productService.checkProductExists(cart.getProducts());

			if (!productsNotExists.isEmpty())
			{
				throw new Exception("There are Products that not exists");
			}
		}
		return cartMapper.toCartDTO(cartRepository.save(cart));
	}

	@Override
	public void deleteCartById(Long id) throws Exception
	{
		if (!cartRepository.existsById(id))
		{
			throw new Exception("Cart with id " + id + " does not exist");
		}
		cartRepository.deleteById(id);
	}

	@Override
	public CartDto getCartById(Long id) throws Exception
	{
		if(!cartRepository.existsById(id))
		{
			throw new Exception("Cart with id " + id + " does not exist");
		}
		return cartMapper.toCartDTO(cartRepository.getById(id));
	}

	@Override
	public CartDto updateCart(CartDto cartDto) throws Exception
	{
		Cart cart = cartMapper.toCart(cartDto);

		if (!cartRepository.existsById(cart.getId()))
		{
			throw new IllegalArgumentException("Cart with id " + cartDto.getId() + " does not exist");
		}

		List<Product> products = cart.getProducts();

		List<Product> productsNotExists = productService.checkProductExists(products);

		if(productsNotExists.isEmpty())
		{
			throw new Exception("This products " + productsNotExists.toString() + " does not exist");
		}

		return cartMapper.toCartDTO(cartRepository.saveAndFlush(cart));
	}

	@Override
	public List<CartDto> getAllCarts()
	{
		return cartMapper.toCartDTOs(cartRepository.findAll());
	}
}
