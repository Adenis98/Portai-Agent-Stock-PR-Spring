package com.orbit.portailAgentStockPR.commande.service;

import com.orbit.portailAgentStockPR.commande.models.Commande;
import com.orbit.portailAgentStockPR.commande.models.CommandeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;
import java.util.Date;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, CommandeId> {


    @Modifying
    @Transactional
    @Query( value = "insert into commande  (panier,tot_ht,dealer_number,date_creation)" +
            " values (:panier , :totHt ,:dealer,:datecrea)" , nativeQuery = true)
    int insertCommande(
            @Param("panier") int panier ,
            @Param("totHt") double totHt ,
            @Param("dealer")int dealer,
            @Param("datecrea") Date datecrea
    );

    @Modifying
    @Transactional
    @Query( " UPDATE Commande c SET" +
            " c.totHt = :totHt  WHERE c.numCde = :numCde and c.ss.ldbDealerNumber =:dealNbr")
    int updateTot(
            @Param("totHt") double totHt ,
            @Param("numCde") int numCde ,
            @Param("dealNbr") int dealNbr
    );
}
