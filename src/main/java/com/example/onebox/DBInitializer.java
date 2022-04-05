package com.example.onebox;

import com.example.onebox.domain.Product;

import com.example.onebox.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;

public class DBInitializer implements ServletContextListener
{

	@Autowired
	private ProductService productService;

	public void contextInitialized(ServletContextEvent arg0) {

		List<Product> productList = new ArrayList<>();
		// init DB with products
		for (int i=0; i<100; i++){
			productList.add(simulateProducts(i));
		}

		productService.createProducts(productList);
	}

	private Product simulateProducts(int index){

		DecimalFormat df = new DecimalFormat("#.00");
		Product product = new Product();
		product.setDescription("Product"+index);
		product.setAmount(Math.random()*100);
		return product;
	}
}
