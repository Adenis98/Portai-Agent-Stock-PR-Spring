package com.orbit.portailAgentStockPR.commande.service;

import com.orbit.portailAgentStockPR.commande.models.LigneCommande;
import com.orbit.portailAgentStockPR.commande.models.LigneCommandeId;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface LigneCommandeRepository extends JpaRepository<LigneCommande, LigneCommandeId> {

}
