package com.orbit.portailAgentStockPR.statistique.models;

import com.orbit.portailAgentStockPR.commande.models.LigneCommande;

import java.util.List;

public class Stat4Resp {
    private String codeArt ;
    private int occurence ;

    public Stat4Resp(String codeArt, int occurence) {
        this.codeArt = codeArt;
        this.occurence = occurence;
    }

    public String getCodeArt() {
        return codeArt;
    }

    public void setCodeArt(String codeArt) {
        this.codeArt = codeArt;
    }

    public int getOccurence() {
        return occurence;
    }

    public void setOccurence(int occurence) {
        this.occurence = occurence;
    }
}
