package com.orbit.portailAgentStockPR.statistique.models;

public class Stat3Resp {
    private int enrg ;
    private int liv ;
    private int fact;

    public Stat3Resp(int enrg, int liv, int fact) {
        this.enrg = enrg;
        this.liv = liv;
        this.fact = fact;
    }

    public int getEnrg() {
        return enrg;
    }

    public void setEnrg(int enrg) {
        this.enrg = enrg;
    }

    public int getLiv() {
        return liv;
    }

    public void setLiv(int liv) {
        this.liv = liv;
    }

    public int getFact() {
        return fact;
    }

    public void setFact(int fact) {
        this.fact = fact;
    }
}
