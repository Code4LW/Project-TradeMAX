package com.example.trademax;

import com.example.trademax.models.Item;
import com.example.trademax.models.User;
import com.example.trademax.repositories.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class TrademaxApplication {

	public static void main(String[] args) {
		SpringApplication.run(TrademaxApplication.class, args);
	}
}
