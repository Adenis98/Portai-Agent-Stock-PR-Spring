package com.orbit.portailAgentStockPR.devis.service;

import com.orbit.portailAgentStockPR.devis.models.Devis;
import com.orbit.portailAgentStockPR.devis.models.LigneDevis;
import com.orbit.portailAgentStockPR.devis.models.LigneDevisId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface LigneDevisRepository extends JpaRepository<LigneDevis , LigneDevisId> {

    @Modifying
    @Transactional
    @Query( value = "insert into ligne_devis  (code_art,libelle,pu,qte,remise , tot_ligne,ss_num_devis,ss_dealer_number )" +
            " values (:codArt,:libelle ,:pu,:qte , :remise,:totLigne,:numDevis,:dNbr)" , nativeQuery = true)
    int insertLigneDevis(
            @Param("codArt") String codArt ,
            @Param("libelle") String libelle ,
            @Param("remise") double remise ,
            @Param("pu") double pu,
            @Param("qte") int qte ,
            @Param("totLigne") double totLigne ,
            @Param("numDevis") int numDevis ,
            @Param("dNbr") int dNbr
    );


}
