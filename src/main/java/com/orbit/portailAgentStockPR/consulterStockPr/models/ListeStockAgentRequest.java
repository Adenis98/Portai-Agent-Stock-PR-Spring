package com.orbit.portailAgentStockPR.consulterStockPr.models;

public class ListeStockAgentRequest {

    private String codeArt ;
    private String libelle ;

    public ListeStockAgentRequest() {
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
}
