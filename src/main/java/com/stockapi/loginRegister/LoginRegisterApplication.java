package com.stockapi.loginRegister;

import com.stockapi.loginRegister.entity.Client;
import com.stockapi.loginRegister.repository.ClientRepository;
import com.ulisesbocchio.jasyptspringboot.annotation.EnableEncryptableProperties;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
@EnableEncryptableProperties
public class LoginRegisterApplication {

	public static void main(String[] args) {
		SpringApplication.run(LoginRegisterApplication.class, args);
	}

	@Bean
	public CommandLineRunner demo(ClientRepository clientRepository) {
		return (args) -> {
			Client client = new Client();
			client.setUsername("admin");
			client.setPassword("admin");
			client.setEmail("admin@admin.com");
			clientRepository.save(client);
		};
	}
}

