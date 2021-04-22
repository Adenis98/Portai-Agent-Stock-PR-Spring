package com.orbit.portailAgentStockPR.devis.models;

import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;
import com.sun.istack.NotNull;

import javax.persistence.*;

@Entity
@IdClass(LigneDevisId.class)
public class LigneDevis {

    @Id
    @ManyToOne
    private Devis ss ;//F.key
    @Id
    private Integer numLigne ;

    private String codeArt ;

    private String libelle ;
    private Double qte ;
    private Double pu ;
    private Double remise ;
    private Double totLigne ;


    public Devis getNumDevis() {
        return ss;
    }

    public void setNumDevis(Devis dd) {
        this.ss = dd;
    }

    public int getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(int numLigne) {
        this.numLigne = numLigne;
    }

    public String getCodeArt() {
        return codeArt;
    }

    public void setCodeArt(String codeArt) {
        this.codeArt = codeArt;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public double getRemise() {
        return remise;
    }

    public void setRemise(double remise) {
        this.remise = remise;
    }

    public double getTotLigne() {
        return totLigne;
    }

    public void setTotLigne(double totLigne) {
        this.totLigne = totLigne;
    }
}
