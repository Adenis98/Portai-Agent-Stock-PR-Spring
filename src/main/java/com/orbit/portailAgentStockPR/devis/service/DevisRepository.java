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
    @Query( value = "insert into devis  (num_devis," +
            "dealer_number,id_fisc,nom_client,to_remise,to_taxes,timbre,date_creation ,date_devis,heure_devis,tot_ht,tot_ttc,annulee,archivee)" +
            " values (:numDevis,:dNbr,:idFisc,:nomClient,:toRemise,:toTaxe,:timbre,:dateCreation,:dateDevis,:heureDevis,:totHt,:totTtc,0,0)" , nativeQuery = true)
    int insertDevis(
            @Param("numDevis") int numDevis ,
            @Param("dNbr") int dNbr ,
            @Param("nomClient") String nomClient ,
            @Param("idFisc") String idFisc ,
            @Param("toRemise") double toRemise ,
            @Param("toTaxe") double toTaxe ,
            @Param("timbre") double timbre ,
            @Param("dateCreation") Date dateCreation,
            @Param("dateDevis") Date dateDevis ,
            @Param("heureDevis") Date heureDevis,
            @Param("totHt") double totHt ,
            @Param("totTtc") double totTtc
    );

    @Modifying
    @Transactional
    @Query( " UPDATE Devis d SET" +
            " d.annulee= 1 , d.date_Annulation = :dateAnnulation  "+
            " WHERE d.numDevis = :numDevis")
    int annulerCmdUpd(
            @Param("numDevis") int numDevis  ,
            @Param("dateAnnulation") Date dateAnnulation
    );

    @Modifying
    @Transactional
    @Query( " UPDATE Devis d SET" +
            " d.Archivee = 1 , d.date_Archivage = :date_Archivage  "+
            " WHERE d.numDevis = :numDevis")
    int archiverCmdUpd(
            @Param("numDevis") int numDevis  ,
            @Param("date_Archivage") Date dateArchivage
    );
}
