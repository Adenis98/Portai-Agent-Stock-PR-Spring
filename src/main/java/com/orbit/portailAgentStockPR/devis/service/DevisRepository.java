package com.orbit.portailAgentStockPR.devis.service;

import com.orbit.portailAgentStockPR.devis.models.Devis;
import com.orbit.portailAgentStockPR.devis.models.DevisId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface DevisRepository extends JpaRepository<Devis, DevisId> {

    @Modifying
    @Transactional
    @Query( value = "insert into devis  (" +
            "dealer_number,modele,promotion,debut_promo,fin_promo," +
            "id_fisc,nom_client,to_remise,to_taxes,timbre )" +
            " values (:dNbr,:modele , :promotion ,:dPromo,:fPromo,:idFisc,:nomClient,:toRemise,:toTaxe,:timbre)" , nativeQuery = true)
    int insertDevis(
            @Param("dNbr") int dNbr ,
            @Param("modele") String modele ,
            @Param("promotion") int promotion ,
            @Param("dPromo") Date dPromo,
            @Param("fPromo") Date fPromo ,
            @Param("nomClient") String nomClient ,
            @Param("idFisc") String idFisc ,
            @Param("toRemise") double toRemise ,
            @Param("toTaxe") double toTaxe ,
            @Param("timbre") double timbre
    );
}
