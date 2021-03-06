package com.orbit.portailAgentStockPR.commande.models;


import com.orbit.portailAgentStockPR.consulterStockPr.models.ArtMasters;
import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@IdClass(LigneCommandeId.class)
public class LigneCommande {

   /* @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )*/

    @Id
    @GeneratedValue (strategy = GenerationType.IDENTITY)
    private int numLigne ;

    @Id
    @ManyToOne
    private Commande ss;

    private String codeArt ;
    private double qte ;
    private double pu ;
    private double totLigneHt ;
    private String libelle ;
    private double qteLivree ;
    private int type_Cmd ;
    private String vin ;
    private String numInterv ;
    private String nomClient ;
    private double qteFacturee ;

    public int getNumLigne() {
        return numLigne;
    }

    public void setNumLigne(int numLigne) {
        this.numLigne = numLigne;
    }


    public Commande getNumCmnd() {
        return ss;
    }

    public void setNumCmnd(Commande ss) {
        this.ss = ss;
    }

    public String getCodeArt() {
        return codeArt;
    }

    public void setCodeArt(String codeArt) {
        this.codeArt = codeArt;
    }

    public double getQte() {
        return qte;
    }

    public void setQte(double qte) {
        this.qte = qte;
    }

    public double getPu() {
        return pu;
    }

    public void setPu(double pu) {
        this.pu = pu;
    }

    public double getTotLigneHt() {
        return totLigneHt;
    }

    public void setTotLigneHt(double totLigneHt) {
        this.totLigneHt = totLigneHt;
    }

    public String getLibelle() {
        return libelle;
    }

    public void setLibelle(String libelle) {
        this.libelle = libelle;
    }

    public double getQteLivree() {
        return qteLivree;
    }

    public void setQteLivree(double qteLivree) {
        this.qteLivree = qteLivree;
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

    public double getQteFacturee() {
        return qteFacturee;
    }

    public void setQteFacturee(double qteFacturee) {
        this.qteFacturee = qteFacturee;
    }
}
