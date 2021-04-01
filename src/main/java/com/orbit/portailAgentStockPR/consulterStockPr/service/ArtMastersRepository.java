package com.orbit.portailAgentStockPR.consulterStockPr.service;

import com.orbit.portailAgentStockPR.consulterStockPr.models.ArtMasters;
import com.orbit.portailAgentStockPR.consulterStockPr.models.DealerStock;
import com.orbit.portailAgentStockPR.consulterStockPr.models.ListeStockAgentResponse;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface ArtMastersRepository extends JpaRepository<ArtMasters , String> {

    @Query("SELECT a from ArtMasters a where a.libelle = :lib")
    ArrayList<ArtMasters> findByLibelle(@Param("lib") String lib);
}
