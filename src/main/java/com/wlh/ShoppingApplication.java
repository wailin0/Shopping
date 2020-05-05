package com.wlh;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.wlh.model.Product;
import com.wlh.repository.ProductRepository;

@SpringBootApplication
public class ShoppingApplication implements CommandLineRunner{

	@Autowired
	ProductRepository productRepository;
	
	public static void main(String[] args) {
		SpringApplication.run(ShoppingApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {
		Product p = new Product();
		p.setName("bdo");
		p.setPrice(10.0);
		p.setInfo("shitty p2w mmo");
		p.setCategory("mmo");
		productRepository.save(p);
		
		Product p2 = new Product();
		p2.setName("pubg");
		p2.setPrice(30.0);
		p2.setInfo("fullof fcking chingchong");
		p2.setCategory("battle royale");
		productRepository.save(p2);
	}

}
