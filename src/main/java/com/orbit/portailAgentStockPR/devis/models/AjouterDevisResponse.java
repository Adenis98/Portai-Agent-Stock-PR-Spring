package com.orbit.portailAgentStockPR.devis.models;

import java.util.Date;

public class AjouterDevisResponse {
    private boolean insertionError;
    private int numDevis ;
    private Date dateCreation ;

    public AjouterDevisResponse() {}

    public boolean isInsertionError() {
        return insertionError;
    }

    public void setInsertionError(boolean insertionError) {
        this.insertionError = insertionError;
    }

    public int getNumDevis() {
        return numDevis;
    }

    public void setNumDevis(int numDevis) {
        this.numDevis = numDevis;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }
}
