package com.orbit.portailAgentStockPR.devis.models;

import java.util.List;

public class OneDevisResponse {
    private GetListeDevisResponse devis ;
    private List<GetLigneDevisResponse> listeLigneDevis ;

    public OneDevisResponse() {
    }

    public OneDevisResponse(GetListeDevisResponse devis, List<GetLigneDevisResponse> listeLigneDevis) {
        this.devis = devis;
        this.listeLigneDevis = listeLigneDevis;
    }

    public GetListeDevisResponse getDevis() {
        return devis;
    }

    public void setDevis(GetListeDevisResponse devis) {
        this.devis = devis;
    }

    public List<GetLigneDevisResponse> getListeLigneDevis() {
        return listeLigneDevis;
    }

    public void setListeLigneDevis(List<GetLigneDevisResponse> listeLigneDevis) {
        this.listeLigneDevis = listeLigneDevis;
    }
}
