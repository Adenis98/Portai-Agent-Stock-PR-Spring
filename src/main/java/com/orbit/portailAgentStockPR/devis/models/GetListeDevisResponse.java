package com.orbit.portailAgentStockPR.devis.models;

import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

public class GetListeDevisResponse {


    private String nomClient ;
    private String idFisc ;
    private String heure_Devis ;

    private Double totHt ;
    private Double toRemise ;
    private Double toTaxes ;
    private Double timbre ;
    private Double totTtc ;

    private Date date_Creation ;
    private Date date_Devis ;
    private Date date_Annulation ;
    private Date date_Archivage ;

    private Integer  dealerNbr ;
    private Integer numDevis ;
    private Integer Archivee ;
    private Integer annulee ;

    public GetListeDevisResponse() { }

    public Integer getDealerNbr() {
        return dealerNbr;
    }

    public void setDealerNbr(Integer dealerNbr) {
        this.dealerNbr = dealerNbr;
    }

    public Integer getNumDevis() {
        return numDevis;
    }

    public void setNumDevis(Integer numDevis) {
        this.numDevis = numDevis;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getIdFisc() {
        return idFisc;
    }

    public void setIdFisc(String idFisc) {
        this.idFisc = idFisc;
    }

    public Double getTotHt() {
        return totHt;
    }

    public void setTotHt(Double totHt) {
        this.totHt = totHt;
    }

    public Double getToRemise() {
        return toRemise;
    }

    public void setToRemise(Double toRemise) {
        this.toRemise = toRemise;
    }

    public Double getToTaxes() {
        return toTaxes;
    }

    public void setToTaxes(Double toTaxes) {
        this.toTaxes = toTaxes;
    }

    public Double getTimbre() {
        return timbre;
    }

    public void setTimbre(Double timbre) {
        this.timbre = timbre;
    }

    public Double getTotTtc() {
        return totTtc;
    }

    public void setTotTtc(Double totTtc) {
        this.totTtc = totTtc;
    }

    public Date getDate_Creation() {
        return date_Creation;
    }

    public void setDate_Creation(Date date_Creation) {
        this.date_Creation = date_Creation;
    }

    public Date getDate_Devis() {
        return date_Devis;
    }

    public void setDate_Devis(Date date_Devis) {
        this.date_Devis = date_Devis;
    }

    public String getHeure_Devis() {
        return heure_Devis;
    }

    public void setHeure_Devis(String heure_Devis) {
        this.heure_Devis = heure_Devis;
    }

    public Integer getAnnulee() {
        return annulee;
    }

    public void setAnnulee(Integer annulee) {
        this.annulee = annulee;
    }

    public Date getDate_Annulation() {
        return date_Annulation;
    }

    public void setDate_Annulation(Date date_Annulation) {
        this.date_Annulation = date_Annulation;
    }

    public Integer getArchivee() {
        return Archivee;
    }

    public void setArchivee(Integer archivee) {
        Archivee = archivee;
    }

    public Date getDate_Archivage() {
        return date_Archivage;
    }

    public void setDate_Archivage(Date date_Archivage) {
        this.date_Archivage = date_Archivage;
    }
}
