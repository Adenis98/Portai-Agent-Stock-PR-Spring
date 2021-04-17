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
    @Query( value = "insert into commande  (num_cde,panier,tot_ht,dealer_number,date_creation)" +
            " values (:pk,:panier , :totHt ,:dealer,:datecrea)" , nativeQuery = true)
    int insertCommande(
            @Param("pk") int pk ,
            @Param("panier") int panier ,
            @Param("totHt") double totHt ,
            @Param("dealer")int dealer,
            @Param("datecrea") Date datecrea
    );
    @Modifying
    @Transactional
    @Query( value = "insert into commande  (num_cde,panier,tot_ht,dealer_number,date_creation ," +
             "type_cmd ,mode_paiement,ref_cmd , date_liv_s , date_cmd , heure_cmd)"+
            " values (:pk,:panier , :totHt ,:dealer,:datecrea,:typeCmd,:modeP,:refCmd,:dateLivS ,:dateCmd ,:heureCmd)" , nativeQuery = true)
    int passerCommandeIns(
            @Param("pk") int pk ,
            @Param("panier") int panier ,
            @Param("totHt") double totHt ,
            @Param("dealer")int dealer,
            @Param("datecrea") Date datecrea,
            @Param("typeCmd")int typeCmd,
            @Param("modeP") String modeP,
            @Param("refCmd") String refCmd ,
            @Param("dateLivS") Date dateLivS,
            @Param("dateCmd") Date dateCmd,
            @Param("heureCmd") Date heureCmd
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


    @Modifying
    @Transactional
    @Query( " UPDATE Commande c SET" +
            " c.numCde= :pKey ,c.type_Cmd = :typeCmd , c.mode_Paiement = :modePaiement , c.ref_Cmd = :refCmd, c.date_Liv_S = :dateLS "+
            " WHERE c.panier = -1 and c.ss.ldbDealerNumber =:dealNbr")
    int passerCommandeUpd(
            @Param("pKey") int pKey  ,
            @Param("dealNbr") int dNbr  ,
            @Param("typeCmd") int typeCmd  ,
            @Param("modePaiement") String modePaiement ,
            @Param("refCmd") String refCmd ,
            @Param("dateLS") Date dateLS
    );

    @Modifying
    @Transactional
    @Query("DELETE FROM Commande c WHERE c.ss.ldbDealerNumber= :dNbr and c.numCde = 9999" )
    int deleteCommande(
            @Param("dNbr") int dNbr
    );

    @Modifying
    @Transactional
    @Query("DELETE FROM Commande c WHERE c.ss.ldbDealerNumber= :dNbr and c.numCde = 9999" )
    int deleteCommandeNumCmd9999(
            @Param("dNbr") int dNbr
    );

    @Modifying
    @Transactional
    @Query( " UPDATE Commande c SET" +
            " c.annulee= 1 , c.date_Annulation = :dateAnnulation  "+
            " WHERE c.numCde = :numCmd")
    int annulerCmdUpd(
            @Param("numCmd") int numCmd  ,
            @Param("dateAnnulation") Date dateAnnulation
    );
}
