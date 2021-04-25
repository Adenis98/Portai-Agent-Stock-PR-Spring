package com.orbit.portailAgentStockPR.devis.models;

import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;

import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import java.util.Date;
import java.util.List;

public class AjouterDevisRequest {

    private int dealerNbr ;

    private String modele ;

    private int promotion ;
    private Date debutPromo ;
    private Date finPromo ;

    private String nomClient ;
    private String idFisc ;

    private double toRemise ;
    private double toTaxes ;
    private double timbre ;

    private List<LigneArticleRequest> listeArt  ;

    public AjouterDevisRequest() {
    }

    public int getDealerNbr() {
        return dealerNbr;
    }

    public void setDealerNbr(int dealerNbr) {
        this.dealerNbr = dealerNbr;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public Date getDebutPromo() {
        return debutPromo;
    }

    public void setDebutPromo(Date debutPromo) {
        this.debutPromo = debutPromo;
    }

    public Date getFinPromo() {
        return finPromo;
    }

    public void setFinPromo(Date finPromo) {
        this.finPromo = finPromo;
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

    public List<LigneArticleRequest> getListeArt() {
        return listeArt;
    }

    public void setListeArt(List<LigneArticleRequest> listeArt) {
        this.listeArt = listeArt;
    }
}
