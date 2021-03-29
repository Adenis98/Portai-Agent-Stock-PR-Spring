package com.orbit.portailAgentStockPR.consulterStockPr.models;

public class ListeStockAgentResponse {
    private int codeSte ;
    private String dealerName;
    private String dealerPhone ;
    private String codeArt ;
    private String libelle ;
    private String h ;
    private String ht ;
    private String htg ;
    private double pu ;
    private int stock ;
    private int remplacement ;
    private int qteAchat ;

    public ListeStockAgentResponse(int codeSte, String dealerName, String dealerPhone, String codeArt, String libelle, String h, String ht, String htg, double pu, int stock, int remplacement, int qteAchat) {
        this.codeSte = codeSte;
        this.dealerName = dealerName;
        this.dealerPhone = dealerPhone;
        this.codeArt = codeArt;
        this.libelle = libelle;
        this.h = h;
        this.ht = ht;
        this.htg = htg;
        this.pu = pu;
        this.stock = stock;
        this.remplacement = remplacement;
        this.qteAchat = qteAchat;
    }

    public int getCodeSte() {
        return codeSte;
    }

    public void setCodeSte(int codeSte) {
        this.codeSte = codeSte;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerPhone() {
        return dealerPhone;
    }

    public void setDealerPhone(String dealerPhone) {
        this.dealerPhone = dealerPhone;
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

    public String getH() {
        return h;
    }

    public void setH(String h) {
        this.h = h;
    }

    public String getHt() {
        return ht;
    }

    public void setHt(String ht) {
        this.ht = ht;
    }

    public String getHtg() {
        return htg;
    }

    public void setHtg(String htg) {
        this.htg = htg;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getRemplacement() {
        return remplacement;
    }

    public void setRemplacement(int remplacement) {
        this.remplacement = remplacement;
    }

    public int getQteAchat() {
        return qteAchat;
    }

    public void setQteAchat(int qteAchat) {
        this.qteAchat = qteAchat;
    }
}
/*{"ListeStockAgentReponse":
        {
            "ListeStockAgentResultat":
            {
                "RetCd":"0",
                "RetMsg":"",
                "Articles":
                {
                    "Article":
                        {
                            "CodeSte":"95",
                            "Dealer_Name":"AGENCE CHARGUIA",
                            "Dealer_Phone":"CHA",
                            "CodeArt":"1C0121253A ",
                            "Libelle":"REFR. EAU",
                            "H":"",
                            "HT":"",
                            "HTG":"",
                            "PU":"547.47",
                            "Stock":"20",
                            "Remplacement":"0",
                            "QteAchat":"0"
                        }
                }
            }
        }
} */