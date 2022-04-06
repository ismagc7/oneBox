package com.example.onebox.config;

import com.example.onebox.domain.Cart;
import com.example.onebox.persistence.repository.CartRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;

import java.util.Date;
import java.util.List;

@Slf4j
@EnableScheduling
@Configuration
public class SchedulerConfig {

	public static final int TTL = 600000;
	public static final int EVERY_MINUT = 10000;

	@Autowired
	private CartRepository cartRepository;

	@Scheduled(fixedRate = EVERY_MINUT)
	public void deleteProcesor() {

		log.info("Checking time...");

		List<Cart> cartList = cartRepository.findAll();


		cartList.forEach( cart -> {

			Date now = new Date();

			long time = now.getTime() - cart.getCreatedAt().getTime();

			if(time > TTL) {

				log.info(cart.toString()+" has been deleted");
				cartRepository.deleteById(cart.getId());
			}
		});
	}
}