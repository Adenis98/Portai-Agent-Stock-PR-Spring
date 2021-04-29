package com.orbit.portailAgentStockPR.statistique.models;

public class Stat1Resp {
    private int cmdFerme;
    private int cmdNormale;

    public Stat1Resp(int cmdFerme, int cmdNormale) {
        this.cmdFerme = cmdFerme;
        this.cmdNormale = cmdNormale;
    }

    public int getCmdFerme() {
        return cmdFerme;
    }

    public void setCmdFerme(int cmdFerme) {
        this.cmdFerme = cmdFerme;
    }

    public int getCmdNormale() {
        return cmdNormale;
    }

    public void setCmdNormale(int cmdNormale) {
        this.cmdNormale = cmdNormale;
    }
}
