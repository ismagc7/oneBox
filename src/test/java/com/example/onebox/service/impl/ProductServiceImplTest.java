package com.example.onebox.service.impl;

import com.example.onebox.domain.Product;
import com.example.onebox.dto.ProductDto;
import com.example.onebox.persistence.repository.CartRepository;
import com.example.onebox.persistence.repository.ProductRepository;
import com.example.onebox.service.ProductService;
import com.example.onebox.web.converter.ProductMapper;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;

import java.util.ArrayList;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;

@SpringBootTest
class ProductServiceImplTest
{
	@Autowired
	private ProductService productService;

	@Autowired
	private ProductMapper productMapper;

	@MockBean
	private ProductRepository productRepository;

	private final List<Product> productlist = new ArrayList<>();

	@BeforeEach
	void setUp()
	{
		Product product = new Product();
		product.setId(1L);
		product.setDescription("Description1");
		product.setAmount(23.44);
		productlist.add(product);

		product = new Product();
		product.setId(2L);
		product.setDescription("Description2");
		product.setAmount(124.44);
		productlist.add(product);
	}

	@Test
	void whenGetAllProducts()
	{
		Mockito.when(productRepository.findAll()).thenReturn(productlist);

		List<ProductDto> products = productService.getAllProducts();

		Assertions.assertEquals(2, products.size());
		Assertions.assertNotNull(products);
	}

	@Test
	void whenCreateProductThenOK() throws Exception
	{
		ProductDto productDtoMock = new ProductDto();
		productDtoMock.setId(1L);
		productDtoMock.setDescription("Description1");
		productDtoMock.setAmount(23.44);

		Mockito.when(productRepository.save(Mockito.any(Product.class))).thenReturn(productMapper.toEntity(productDtoMock));
		Mockito.when(productRepository.findByDescriptionIsLike(Mockito.anyString())).thenReturn(Boolean.FALSE);

		ProductDto productDto = productService.createProduct(productDtoMock);

		Assertions.assertEquals(productDtoMock.getId(), productDto.getId());
		Assertions.assertEquals(productDtoMock.getDescription(), productDto.getDescription());
	}

	@Test
	void whenCreateProductThenKO() throws Exception
	{
		ProductDto productDtoMock = new ProductDto();
		productDtoMock.setId(1L);
		productDtoMock.setDescription("Description1");
		productDtoMock.setAmount(23.44);

		Mockito.when(productRepository.findByDescriptionIsLike(Mockito.anyString())).thenReturn(Boolean.TRUE);
		Exception exception = Assertions.assertThrows(Exception.class, () -> { productService.createProduct(new ProductDto(1L,"Product1",23.44)); }
		);

		assertEquals("Product with id 1 already exist", exception.getMessage());
	}

	@Test
	void whenGetProductByIdThenOK() throws Exception
	{
		Mockito.when(productRepository.getById(Mockito.anyLong())).thenReturn(productlist.get(0));
		Mockito.when(productRepository.existsById(Mockito.anyLong())).thenReturn(Boolean.TRUE);

		ProductDto productDto = productService.getProductById(1L);

		Assertions.assertEquals(1L, productDto.getId());
		Assertions.assertEquals("Description1", productDto.getDescription());
		Assertions.assertEquals(23.44, productDto.getAmount());
	}

	@Test
	void whenGetProductByIdThenKO()
	{
		Mockito.when(productRepository.getById(Mockito.anyLong())).thenReturn(productlist.get(0));
		Mockito.when(productRepository.existsById(Mockito.anyLong())).thenReturn(Boolean.FALSE);

		Exception exception = Assertions.assertThrows(Exception.class, () -> { productService.getProductById(1L); }
		);

		assertEquals("Product with id 1 don't exist", exception.getMessage());
	}


	@Test
	void whenDeleteProductThenOK() throws Exception
	{
		Mockito.when(productRepository.existsById(Mockito.anyLong())).thenReturn(Boolean.TRUE);
		productService.deleteProduct(1L);
		Mockito.verify(productRepository, Mockito.times(1)).deleteById(Mockito.anyLong());

	}

}