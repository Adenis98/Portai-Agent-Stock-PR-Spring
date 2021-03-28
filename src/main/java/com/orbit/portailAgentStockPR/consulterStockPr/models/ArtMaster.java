package com.orbit.portailAgentStockPR.consulterStockPr.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class ArtMaster {
    @Id
    private String codArt ;
    private String libelle ;
    private String h ;
    private String ht ;
    private String htg ;
    private Double pu_agents ;
    private int remisable ;

    public String getCodArt() {
        return codArt;
    }

    public void setCodArt(String codArt) {
        this.codArt = codArt;
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

    public Double getPu_agents() {
        return pu_agents;
    }

    public void setPu_agents(Double pu_agents) {
        this.pu_agents = pu_agents;
    }

    public int getRemisable() {
        return remisable;
    }

    public void setRemisable(int remisable) {
        this.remisable = remisable;
    }
}
