package com.orbit.portailAgentStockPR.DevisClasses;

import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;
import com.sun.istack.NotNull;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.ManyToOne;

/*@Entity
@IdClass(LigneDevisId.class)*/
public class LigneDevis {
    /*@Id
    @NotNull
    @ManyToOne*/
    private Dealers dealer_Number ;
    /*@Id
    @NotNull*/
    private int numDevis ;//F.key
    //@Id
    //@NotNull
    private int numLigne ;
    //@NotNull
    private String codeArt ;
    //@NotNull
    private String libelle ;
    private double qte ;
    private double pu ;
    private double remise ;
    private double totLigne ;

    public Dealers getDealer_Number() {
        return dealer_Number;
    }

    public void setDealer_Number(Dealers dealer_Number) {
        this.dealer_Number = dealer_Number;
    }

    public int getNumDevis() {
        return numDevis;
    }

    public void setNumDevis(int numDevis) {
        this.numDevis = numDevis;
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
