package com.example.onebox.web.controller;

import com.example.onebox.dto.CartDto;
import com.example.onebox.dto.ProductDto;
import com.example.onebox.service.CartService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.ResponseEntity;
import org.springframework.mock.web.MockHttpServletRequest;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CartControllerTest
{
	@InjectMocks
	private CartController cartController;

	@Mock
	private CartService cartService;

	private static CartDto cartDto = new CartDto();
	private final List<ProductDto> productlist = new ArrayList<>();

	@BeforeEach
	void setUp()
	{
		cartDto = createCartDto();
	}

	CartDto createCartDto()
	{
		ProductDto product1 = new ProductDto(1L,"product1", 23.90);
		ProductDto product2 = new ProductDto(2L,"product2", 145.00);
		ProductDto product3 = new ProductDto(3L,"product3", 45.54);

		productlist.add(product1);
		productlist.add(product2);
		productlist.add(product3);

		cartDto.setId(1L);
		cartDto.setProducts(productlist);

		return cartDto;
	}


	@Test
	void whenGetAllCartsThenOK()
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		List<CartDto> cartDtoList = new ArrayList<>();
		cartDtoList.add(cartDto);

		Mockito.when(cartService.getAllCarts()).thenReturn(cartDtoList);

		ResponseEntity<List<CartDto>> result = cartController.getAllCarts();

		Assertions.assertEquals(1,result.getBody().size());
	}

	@Test
	void whenGetCartByIdThen() throws Exception
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		CartDto cartDtoMock = cartDto;

		Mockito.when(cartService.getCartById(Mockito.anyLong())).thenReturn(cartDtoMock);

		ResponseEntity<?> result = cartController.getCartById(1L);

		Assertions.assertEquals(cartDtoMock, result.getBody());
	}

	@Test
	void whenCreateCartThenOK() throws Exception
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		CartDto cartDtoMock = cartDto;

		Mockito.when(cartService.createCart(Mockito.any(CartDto.class))).thenReturn(cartDtoMock);

		ResponseEntity<?> result = cartController.createCart(cartDtoMock);

		Assertions.assertEquals(cartDtoMock, result.getBody());
	}

	@Test
	void whenDeleteCartThenOK() throws Exception
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		cartController.deleteCart(1L);
		Mockito.verify(cartService, Mockito.times(1)).deleteCartById(Mockito.anyLong());
	}

	@Test
	void whenUpdateCartThenOK() throws Exception
	{
		MockHttpServletRequest request = new MockHttpServletRequest();
		RequestContextHolder.setRequestAttributes(new ServletRequestAttributes(request));

		CartDto cartDtoMock = cartDto;

		Mockito.when(cartService.updateCart(Mockito.any(CartDto.class))).thenReturn(cartDtoMock);

		ResponseEntity<?> result = cartController.updateCart(cartDtoMock);

		Assertions.assertEquals(cartDtoMock, result.getBody());
	}
}