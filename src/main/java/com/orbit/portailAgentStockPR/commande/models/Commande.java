package com.orbit.portailAgentStockPR.commande.models;


import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(CommandeId.class)
public class Commande {

    @Id
    private int numCde ;


    @Id
    @ManyToOne
    @JoinColumn(name = "dealer_number")
    private Dealers ss ;

    private int panier ;
    private Double totHt ;
    private Date date_Creation;
    private Date date_Cmd ;
    private String heure_Cmd ;
    private Integer type_Cmd ;
    private String mode_Paiement ;
    private String ref_Cmd ;
    @Column(nullable=true)
    private Integer enregistree ;
    private String ref_Enregistrement ;
    private Date date_Enregistrement ;
    @Column(nullable=true)
    private Integer livree ;
    private Date date_Liv ;
    private Date date_Liv_S;
    @Column(nullable=true)
    private Integer facturee;
    private Date date_Facture ;
    @Column(nullable=true)
    private Double montant_Facture ;
    private String n_Facture ;
    @Column(nullable=true)
    private Integer annulee ;
    @Column(nullable=true)
    private Integer archivee ;
    private Date date_Annulation ;
    private Date date_Archivage ;


    public int getNumCde() {
        return numCde;
    }

    public void setNumCde(int numCde) {
        this.numCde = numCde;
    }

    public Dealers getDealer_Number() {
        return ss;
    }

    public void setDealer_Number(Dealers dealer_Number) {
        this.ss = dealer_Number;
    }

    public int getPanier() {
        return panier;
    }

    public void setPanier(int panier) {
        this.panier = panier;
    }

    public double getTotHt() {
        return totHt;
    }

    public void setTotHt(double totHt) {
        this.totHt = totHt;
    }

    public Date getDate_Creation() {
        return date_Creation;
    }

    public void setDate_Creation(Date date_Creation) {
        this.date_Creation = date_Creation;
    }

    public Date getDate_Cmd() {
        return date_Cmd;
    }

    public void setDate_Cmd(Date date_Cmd) {
        this.date_Cmd = date_Cmd;
    }

    public String getHeure_Cmd() {
        return heure_Cmd;
    }

    public void setHeure_Cmd(String heure_Cmd) {
        this.heure_Cmd = heure_Cmd;
    }

    public int getType_Cmd() {
        return type_Cmd;
    }

    public void setType_Cmd(int type_Cmd) {
        this.type_Cmd = type_Cmd;
    }

    public String getMode_Paiement() {
        return mode_Paiement;
    }

    public void setMode_Paiement(String mode_Paiement) {
        this.mode_Paiement = mode_Paiement;
    }

    public String getRef_Cmd() {
        return ref_Cmd;
    }

    public void setRef_Cmd(String ref_Cmd) {
        this.ref_Cmd = ref_Cmd;
    }

    public int getEnregistree() {
        return enregistree;
    }

    public void setEnregistree(int enregistree) {
        this.enregistree = enregistree;
    }

    public String getRef_Enregistrement() {
        return ref_Enregistrement;
    }

    public void setRef_Enregistrement(String ref_Enregistrement) {
        this.ref_Enregistrement = ref_Enregistrement;
    }

    public Date getDate_Enregistrement() {
        return date_Enregistrement;
    }

    public void setDate_Enregistrement(Date date_Enregistrement) {
        this.date_Enregistrement = date_Enregistrement;
    }

    public int getLivree() {
        return livree;
    }

    public void setLivree(int livree) {
        this.livree = livree;
    }

    public Date getDate_Liv() {
        return date_Liv;
    }

    public void setDate_Liv(Date date_Liv) {
        this.date_Liv = date_Liv;
    }

    public Date getDate_Liv_S() {
        return date_Liv_S;
    }

    public void setDate_Liv_S(Date date_Liv_S) {
        this.date_Liv_S = date_Liv_S;
    }

    public int getFacturee() {
        return facturee;
    }

    public void setFacturee(int facturee) {
        this.facturee = facturee;
    }

    public Date getDate_Facture() {
        return date_Facture;
    }

    public void setDate_Facture(Date date_Facture) {
        this.date_Facture = date_Facture;
    }

    public double getMontant_Facture() {
        return montant_Facture;
    }

    public void setMontant_Facture(double montant_Facture) {
        this.montant_Facture = montant_Facture;
    }

    public String getN_Facture() {
        return n_Facture;
    }

    public void setN_Facture(String n_Facture) {
        this.n_Facture = n_Facture;
    }

    public int getAnnulee() {
        return annulee;
    }

    public void setAnnulee(int annulee) {
        this.annulee = annulee;
    }

    public int getArchivee() {
        return archivee;
    }

    public void setArchivee(int archivee) {
        this.archivee = archivee;
    }

    public Date getDate_Annulation() {
        return date_Annulation;
    }

    public void setDate_Annulation(Date date_Annulation) {
        this.date_Annulation = date_Annulation;
    }

    public Date getDate_Archivage() {
        return date_Archivage;
    }

    public void setDate_Archivage(Date date_Archivage) {
        this.date_Archivage = date_Archivage;
    }
}
