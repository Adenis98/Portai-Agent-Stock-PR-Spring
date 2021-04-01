package com.orbit.portailAgentStockPR.consulterStockPr.service;

import com.orbit.portailAgentStockPR.consulterStockPr.models.DealerStock;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;

@Repository
public interface DealerStockRepository extends JpaRepository<DealerStock, String > {

    @Query("SELECT d from DealerStock d where d.codArt = :artCode")
    ArrayList<DealerStock> findByArtCode(@Param("artCode") String artCode);

}
