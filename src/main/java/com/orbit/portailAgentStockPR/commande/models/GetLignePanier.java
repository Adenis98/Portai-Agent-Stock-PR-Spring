package com.orbit.portailAgentStockPR.commande.models;

public class GetLignePanier {
    private String codeArt ;
    private String libelle ;
    private int numLigne ;
    private Double qte ;
    private Double pu ;
    private Double totLigneHt ;
    private int typeCmd ;
    private String vin ;
    private String numInterv ;
    private String NomClient ;

    public GetLignePanier() {
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

    public int getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(int numLigne) {
        this.numLigne = numLigne;
    }

    public Double getQte() {
        return qte;
    }

    public void setQte(Double qte) {
        this.qte = qte;
    }

    public Double getPu() {
        return pu;
    }

    public void setPu(Double pu) {
        this.pu = pu;
    }

    public Double getTotLigneHt() {
        return totLigneHt;
    }

    public void setTotLigneHt(Double totLigneHt) {
        this.totLigneHt = totLigneHt;
    }

    public int getTypeCmd() {
        return typeCmd;
    }

    public void setTypeCmd(int typeCmd) {
        this.typeCmd = typeCmd;
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
        return NomClient;
    }

    public void setNomClient(String nomClient) {
        NomClient = nomClient;
    }
}
/*"Ligne":{
        "CodeArt":"1C0121253A          ",
        "Libelle":"",
        "NumLigne":"1",
        "Qte":"3","PU":"547.47",
        "TotLigneHt":"1642.41",
        "Type_Cmd":"0   ",
        "VIN":"",
        "NumInterv":"",
        "NomClient":""
        }*/