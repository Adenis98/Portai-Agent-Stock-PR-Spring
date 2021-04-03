package com.orbit.portailAgentStockPR.commande.service;

import com.orbit.portailAgentStockPR.commande.models.LigneCommande;
import com.orbit.portailAgentStockPR.commande.models.LigneCommandeId;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import javax.transaction.Transactional;
@Repository
public interface LigneCommandeRepository extends JpaRepository<LigneCommande, LigneCommandeId> {

    @Modifying
    @Transactional
    @Query( value = "insert into ligne_commande  (ss_num_cde,pu,qte,qte_facturee,qte_livree,tot_ligne_ht,type_cmd,ss_dealer_number)" +
            " values (:num_cde , :pu,:qte,:qte_facturee,:qte_livree,:tot_ligne_ht,:type_cmd,:dealer_nbr)" , nativeQuery = true)
    int insertPanier(
                       @Param("num_cde") int num_cmd ,
                       @Param("pu")double pu ,
                       @Param("qte")double qte ,
                       @Param("qte_facturee") double qte_facturee ,
                       @Param("qte_livree")double qte_livree ,
                       @Param("tot_ligne_ht")double tot_ligne_ht ,
                       @Param("type_cmd")int type_cmd ,
                       @Param("dealer_nbr")int dealerNbr
    );

}
