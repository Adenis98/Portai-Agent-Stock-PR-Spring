package com.orbit.portailAgentStockPR.commande.models;

import java.util.Date;
import java.util.List;

public class GetPanierWsResponse {
    private int retCd ;
    private String retMsg ;
    private int dNbr ;
    private Date dateCreation ;
    private Double totHt ;
    private List<GetLignePanier> lignesPanier ;

    public GetPanierWsResponse() {
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

    public int getdNbr() {
        return dNbr;
    }

    public void setdNbr(int dNbr) {
        this.dNbr = dNbr;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public Double getTotHt() {
        return totHt;
    }

    public void setTotHt(Double totHt) {
        this.totHt = totHt;
    }

    public List<GetLignePanier> getLignesPanier() {
        return lignesPanier;
    }

    public void setLignesPanier(List<GetLignePanier> lignesPanier) {
        this.lignesPanier = lignesPanier;
    }
}


/*
{
        "GetPanierResultat":{
            "RetCd":"0",
            "RetMsg":"",
            "Dealer_Number":"95",
            "DateCreation":"3/26/2021 12:00:00 AM",
            "TotHt":"1642.41",
            "Lignes":{
                    "Ligne":{
                        "CodeArt":"1C0121253A          ",
                        "Libelle":"",
                        "NumLigne":"1",
                        "Qte":"3","PU":"547.47",
                        "TotLigneHt":"1642.41",
                        "Type_Cmd":"0   ",
                        "VIN":"",
                        "NumInterv":"",
                        "NomClient":""
                    }
                }
        }

}*/