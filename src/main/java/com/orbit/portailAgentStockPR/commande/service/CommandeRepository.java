package com.orbit.portailAgentStockPR.commande.service;

import com.orbit.portailAgentStockPR.commande.models.Commande;
import com.orbit.portailAgentStockPR.commande.models.CommandeId;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CommandeRepository extends JpaRepository<Commande, CommandeId> {

}
