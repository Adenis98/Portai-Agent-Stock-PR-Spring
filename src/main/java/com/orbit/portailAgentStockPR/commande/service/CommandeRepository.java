package com.orbit.portailAgentStockPR.commande.service;

import com.orbit.portailAgentStockPR.commande.models.Commande;
import com.orbit.portailAgentStockPR.commande.models.CommandeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import javax.transaction.Transactional;

@Repository
public interface CommandeRepository extends JpaRepository<Commande, CommandeId> {


    @Modifying
    @Transactional
    @Query( value = "insert into commande  (panier,to_ht,dealer_number)" +
            " values (:panier , :totHt ,:dealer)" , nativeQuery = true)
    int insertCommande(
            @Param("panier") int panier ,
            @Param("totHt") double totHt ,
            @Param("dealer")int dealer
    );
}
