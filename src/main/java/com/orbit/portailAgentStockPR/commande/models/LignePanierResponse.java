package com.orbit.portailAgentStockPR.commande.models;

public class LignePanierResponse {
    private int retCd;
    private String retMsg ;

    public LignePanierResponse() {
    }

    public int getRetCd() {
        return retCd;
    }

    public void setRetCd(int retCd) {
        this.retCd = retCd;
    }

    public String getRetMsg() {
        return retMsg;
    }

    public void setRetMsg(String retMsg) {
        this.retMsg = retMsg;
    }
}
