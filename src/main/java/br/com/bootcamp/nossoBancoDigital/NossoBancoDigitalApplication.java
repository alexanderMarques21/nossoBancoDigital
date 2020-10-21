package br.com.bootcamp.nossoBancoDigital;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.retry.annotation.EnableRetry;
import org.springframework.scheduling.annotation.EnableAsync;
import org.springframework.transaction.annotation.EnableTransactionManagement;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import springfox.documentation.swagger2.annotations.EnableSwagger2;

@EnableRetry
@SpringBootApplication
@EnableJpaRepositories(enableDefaultTransactions = false)
@EnableAsync
@EnableSwagger2

public class NossoBancoDigitalApplication {

	public static void main(String[] args) {
		SpringApplication.run(NossoBancoDigitalApplication.class, args);
	}

}
