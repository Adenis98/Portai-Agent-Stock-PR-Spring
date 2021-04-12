package com.orbit.portailAgentStockPR.commande.models;

import java.util.Date;

public class PasserCommandeRequest {
    private int typeCmd ;
    private String  modePaiement ;  // e espèce ou c chèque
    private String refCmd ;
    private Date dateLivS ;
    private int dealerNumber ;

    public PasserCommandeRequest() {
    }

    public int getTypeCmd() {
        return typeCmd;
    }

    public void setTypeCmd(int typeCmd) {
        this.typeCmd = typeCmd;
    }

    public String getModePaiement() {
        return modePaiement;
    }

    public void setModePaiement(String modePaiement) {
        this.modePaiement = modePaiement;
    }

    public String getRefCmd() {
        return refCmd;
    }

    public void setRefCmd(String refCmd) {
        this.refCmd = refCmd;
    }

    public Date getDateLivS() {
        return dateLivS;
    }

    public void setDateLivS(Date dateLivS) {
        this.dateLivS = dateLivS;
    }

    public int getDealerNumber() {
        return dealerNumber;
    }

    public void setDealerNumber(int dealerNumber) {
        this.dealerNumber = dealerNumber;
    }
}
