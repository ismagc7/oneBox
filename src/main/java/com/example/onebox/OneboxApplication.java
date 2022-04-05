package com.example.onebox;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

@EnableWebMvc
@SpringBootApplication
public class OneboxApplication
{

	public static void main(String[] args)
	{
		SpringApplication.run(OneboxApplication.class, args);
	}

}
