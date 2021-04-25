package com.orbit.portailAgentStockPR.devis.models;

public class LigneArticleRequest {

    String codArt ;
    int qte ;

    public LigneArticleRequest() {
    }

    public String getCodArt() {
        return codArt;
    }

    public void setCodArt(String codArt) {
        this.codArt = codArt;
    }

    public int getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }
}
