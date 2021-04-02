package com.orbit.portailAgentStockPR.commande.models;

public class LignePanierRequest {
    private int editMode ;
    private int numLigne ;
    private int dealerNumber ;
    private String codeArt ;
    private String libelle ;
    private double qte ;
    private double pu ;
    private int type_Cmd ;
    private String vin ;
    private String numInterv ;
    private String nomClient ;

    public LignePanierRequest() {
    }

    public int getEditModel() {
        return editMode;
    }

    public void setEditModel(int editModel) {
        this.editMode = editModel;
    }

    public int getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(int numLigne) {
        this.numLigne = numLigne;
    }

    public int getDealerNumber() {
        return dealerNumber;
    }

    public void setDealerNumber(int dealerNumber) {
        this.dealerNumber = dealerNumber;
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

    public double getQte() {
        return qte;
    }

    public void setQte(int qte) {
        this.qte = qte;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public int getType_Cmd() {
        return type_Cmd;
    }

    public void setType_Cmd(int type_Cmd) {
        this.type_Cmd = type_Cmd;
    }

    public String getVin() {
        return vin;
    }

    public void setVin(String vin) {
        this.vin = vin;
    }

    public String getNumInterv() {
        return numInterv;
    }

    public void setNumInterv(String numInterv) {
        this.numInterv = numInterv;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
}
