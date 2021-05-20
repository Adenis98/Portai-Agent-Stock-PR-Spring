package com.orbit.portailAgentStockPR;

import com.orbit.portailAgentStockPR.commande.service.LigneCommandeRepository;
import com.orbit.portailAgentStockPR.consulterStockPr.service.DealerStockRepository;
import com.orbit.portailAgentStockPR.devis.service.DevisRepository;
import com.orbit.portailAgentStockPR.utilisateur.service.UserRepository;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.security.config.annotation.method.configuration.EnableGlobalMethodSecurity;

@SpringBootApplication
@EnableJpaRepositories(basePackageClasses = {
		UserRepository.class ,
		DealerStockRepository.class,
		LigneCommandeRepository.class,
		DevisRepository.class
})
@EnableScheduling
public class PortailAgentStockPrApplication {

	public static void main(String[] args) {
		SpringApplication.run(PortailAgentStockPrApplication.class, args);
	}

}
