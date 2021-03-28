package com.orbit.portailAgentStockPR.consulterStockPr.models;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class DealerStock {

    private int dealer_number;
    private int ug;
    @Id
    private String codArt;
    private int stock;
    private int qte_achat;

    public int getDealer_number() {
        return dealer_number;
    }

    public void setDealer_number(int dealer_number) {
        this.dealer_number = dealer_number;
    }

    public int getUg() {
        return ug;
    }

    public void setUg(int ug) {
        this.ug = ug;
    }

    public String getCodart() {
        return codArt;
    }

    public void setCodart(String codart) {
        this.codArt = codart;
    }

    public int getStock() {
        return stock;
    }

    public void setStock(int stock) {
        this.stock = stock;
    }

    public int getQte_achat() {
        return qte_achat;
    }

    public void setQte_achat(int qte_achat) {
        this.qte_achat = qte_achat;
    }
}
