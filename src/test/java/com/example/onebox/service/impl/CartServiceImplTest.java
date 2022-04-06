package com.example.onebox.service.impl;

import com.example.onebox.domain.Cart;
import com.example.onebox.domain.Product;
import com.example.onebox.dto.CartDto;
import com.example.onebox.dto.ProductDto;
import com.example.onebox.persistence.repository.CartRepository;
import com.example.onebox.service.ProductService;
import com.example.onebox.web.converter.CartMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
class CartServiceImplTest
{

	@Autowired
	private CartServiceImpl cartService;

	@Autowired
	private CartMapper cartMapper;

	@MockBean
	private CartRepository cartRepository;

	@MockBean
	private ProductService productService;

	private final List<Product> productlist = new ArrayList<>();

	private static Cart cart = new Cart();
	private static CartDto cartToGet = new CartDto();

	@BeforeEach
	public void setUp()
	{
		cart = createCart();
		cartToGet = createCartDto();
	}

	Cart createCart()
	{
		Product product1 = new Product(1L,"product1", 23.90);
		Product product2 = new Product(2L,"product2", 145.00);
		Product product3 = new Product(3L,"product3", 45.54);

		productlist.add(product1);
		productlist.add(product2);
		productlist.add(product3);

		cart.setId(1L);
		cart.setProducts(productlist);

		return cart;
	}

	CartDto createCartDto()
	{
		return cartMapper.toCartDTO(cart);
	}

	@Test
	void whenCreateCartThenOK() throws Exception
	{
		CartDto cartToCreate = new CartDto();
		cartToCreate.setId(1L);
		ProductDto product1 = new ProductDto(1L,"foo", 23.90);
		ProductDto product2 = new ProductDto(2L,"bar", 145.00);

		List<ProductDto> productList = new ArrayList<>();

		productList.add(product1);
		productList.add(product2);

		cartToCreate.setProducts(productList);

		Mockito.when(cartRepository.save(Mockito.any(Cart.class))).thenReturn(cart);
		Mockito.when(productService.checkProductExists(Mockito.anyList())).thenReturn(new ArrayList<>());

		CartDto createdCart = cartService.createCart(cartToCreate);

		Assertions.assertEquals(cartToCreate.getId(), createdCart.getId());

	}

	@Test
	void whenDeleteCartByIdThenOK()
	{
		Mockito.when(cartRepository.existsById(Mockito.anyLong())).thenReturn(Boolean.TRUE);
		cartRepository.deleteById(1L);
		Mockito.verify(cartRepository, Mockito.times(1)).deleteById(Mockito.anyLong());

	}

	@Test
	void whenDeleteCartByIdThenKO()
	{
		Mockito.when(cartRepository.existsById(Mockito.anyLong())).thenReturn(Boolean.FALSE);
		cartRepository.deleteById(1L);
		Mockito.verify(cartRepository, Mockito.times(1)).deleteById(Mockito.anyLong());

	}


	@Test
	void whenGetCartByIdThenOK() throws Exception
	{

		Mockito.when(cartRepository.existsById(Mockito.anyLong())).thenReturn(Boolean.TRUE);
		Mockito.when(cartRepository.getById(Mockito.anyLong())).thenReturn(cart);

		CartDto cartDtoFound = cartService.getCartById(1L);

		Assertions.assertEquals(cartToGet.getId(), cartDtoFound.getId());
		Assertions.assertEquals(cartToGet.getProducts().get(0).getDescription(), cartDtoFound.getProducts().get(0).getDescription());
		Assertions.assertEquals(cartToGet.getProducts().get(0).getId(), cartDtoFound.getProducts().get(0).getId());


	}

	@Test
	void whenGetAllCartsThenOK()
	{
		List<Cart> cartDtoList = new ArrayList<>();
		cartDtoList.add(cart);

		Mockito.when(cartRepository.findAll()).thenReturn(cartDtoList);

		List<CartDto> cartDtoListFound = cartService.getAllCarts();

		Assertions.assertEquals(1,cartDtoListFound.size());
		Assertions.assertNotNull(cartDtoListFound);
	}

}