package br.uece.contasapagarapi;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ComponentScan;

@SpringBootApplication
@ComponentScan("br.uece.contasapagarapi")
public class ContasAPagarApiApplication {

	public static void main(String[] args) {
		SpringApplication.run(ContasAPagarApiApplication.class, args);
	}

}
