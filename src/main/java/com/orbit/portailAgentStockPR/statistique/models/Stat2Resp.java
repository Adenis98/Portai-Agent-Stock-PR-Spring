package com.orbit.portailAgentStockPR.statistique.models;

import java.util.List;

public class Stat2Resp {
    List<Integer> dataCmdFerme;
    List<Integer> dataCmdStock;

    public Stat2Resp(List<Integer> dataCmdFerme, List<Integer> dataCmdStock) {
        this.dataCmdFerme = dataCmdFerme;
        this.dataCmdStock = dataCmdStock;
    }

    public List<Integer> getDataCmdFerme() {
        return dataCmdFerme;
    }

    public void setDataCmdFerme(List<Integer> dataCmdFerme) {
        this.dataCmdFerme = dataCmdFerme;
    }

    public List<Integer> getDataCmdStock() {
        return dataCmdStock;
    }

    public void setDataCmdStock(List<Integer> dataCmdStock) {
        this.dataCmdStock = dataCmdStock;
    }
}
