package com.orbit.portailAgentStockPR.statistique.models;

import com.orbit.portailAgentStockPR.commande.models.LigneCommande;

import java.util.ArrayList;
import java.util.List;

public class Stat4Resp {
    private String label ;
    private List<Integer> data ;

    public Stat4Resp(String label, int data) {
        this.label = label;
        this.data =new ArrayList<>( );
        this.data.add(data);
    }

    public String getLabel() {
        return label;
    }

    public void setLabel(String label) {
        this.label = label;
    }

    public List<Integer> getData() {
        return data;
    }

    public void setData(List<Integer> data) {
        this.data = data;
    }
}
