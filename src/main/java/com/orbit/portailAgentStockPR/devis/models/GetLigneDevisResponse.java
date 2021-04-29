package com.orbit.portailAgentStockPR.devis.models;
public class GetLigneDevisResponse {


    private int dNbr ;
    private String codeArt ;
    private String libelle ;
    private Double qte ;
    private Double pu ;
    private Double remise ;
    private Double totLigne ;

    public GetLigneDevisResponse() {
    }

    public int getdNbr() {
        return dNbr;
    }

    public void setdNbr(int dNbr) {
        this.dNbr = dNbr;
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

    public Double getQte() {
        return qte;
    }

    public void setQte(Double qte) {
        this.qte = qte;
    }

    public Double getPu() {
        return pu;
    }

    public void setPu(Double pu) {
        this.pu = pu;
    }

    public Double getRemise() {
        return remise;
    }

    public void setRemise(Double remise) {
        this.remise = remise;
    }

    public Double getTotLigne() {
        return totLigne;
    }

    public void setTotLigne(Double totLigne) {
        this.totLigne = totLigne;
    }
}
