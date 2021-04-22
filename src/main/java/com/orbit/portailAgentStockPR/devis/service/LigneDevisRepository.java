package com.orbit.portailAgentStockPR.devis.service;

import com.orbit.portailAgentStockPR.devis.models.LigneDevis;
import com.orbit.portailAgentStockPR.devis.models.LigneDevisId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface LigneDevisRepository extends JpaRepository<LigneDevis , LigneDevisId> {
}
