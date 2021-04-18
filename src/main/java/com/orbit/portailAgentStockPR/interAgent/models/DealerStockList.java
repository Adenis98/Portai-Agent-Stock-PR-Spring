package com.orbit.portailAgentStockPR.interAgent.models;

public class DealerStockList {
    private int ug ;
    private int stock ;
    private int qteAch ;
    private String codArt ;

    private String libelle ;
    private double puAgents ;

    public DealerStockList() {
    }

    public int getUg() {
        return ug;
    }

    public void setUg(int ug) {
        this.ug = ug;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getQteAch() {
        return qteAch;
    }

    public void setQteAch(int qteAch) {
        this.qteAch = qteAch;
    }

    public String getCodArt() {
        return codArt;
    }

    public void setCodArt(String codArt) {
        this.codArt = codArt;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getPuAgents() {
        return puAgents;
    }

    public void setPuAgents(double puAgents) {
        this.puAgents = puAgents;
    }
}
