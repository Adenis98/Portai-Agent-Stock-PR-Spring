package com.orbit.portailAgentStockPR.devis.models;

import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

public class AjouterDevisRequest {

    private int dealerNbr ;

    private String nomClient ;
    private String idFisc ;

    private double toRemise ;
    private double toTaxes ;
    private double timbre ;
    private Date dateCreation ;
    private List<LigneArticleRequest> listeArt  ;

    public AjouterDevisRequest() {
    }

    public int getDealerNbr() {
        return dealerNbr;
    }

    public void setDealerNbr(int dealerNbr) {
        this.dealerNbr = dealerNbr;
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

    public double getToRemise() {
        return toRemise;
    }

    public void setToRemise(double toRemise) {
        this.toRemise = toRemise;
    }

    public double getToTaxes() {
        return toTaxes;
    }

    public void setToTaxes(double toTaxes) {
        this.toTaxes = toTaxes;
    }

    public double getTimbre() {
        return timbre;
    }

    public void setTimbre(double timbre) {
        this.timbre = timbre;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public List<LigneArticleRequest> getListeArt() {
        return listeArt;
    }

    public void setListeArt(List<LigneArticleRequest> listeArt) {
        this.listeArt = listeArt;
    }
}
