package com.orbit.portailAgentStockPR;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@SpringBootApplication
@RestController
public class PortailAgentStockPrApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortailAgentStockPrApplication.class, args);
	}

	@GetMapping("/ramez")
	public String hello(){
		return "ramezz roamti " ;
	}
}
