package com.orbit.portailAgentStockPR.consulterStockPr.models;

import javax.persistence.*;

@Entity
@IdClass(DealerStockId.class)
public class DealerStock {

    @ManyToOne
    @JoinColumn(name="dealer_number")
    @Id
    private Dealers dealer_number;
    @Id
    private int ug;
    @Id
    private String codArt;
    private int stock;
    private int qte_achat;

    public Dealers getDealer_number() {
        return dealer_number;
    }

    public void setDealer_number(Dealers dealer_number) {
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
