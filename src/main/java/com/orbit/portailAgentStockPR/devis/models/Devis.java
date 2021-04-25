package com.orbit.portailAgentStockPR.devis.models;

import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import java.util.Date;

@Entity
@IdClass(DevisId.class)
public class Devis {
    @Id
    @ManyToOne
    @JoinColumn(name = "dealer_number")
    private Dealers ss ;
    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Id
    private Integer numDevis ;
    private String modele ;
    @Column(nullable=true)
    private Integer promotion ;
    private Date debutPromo ;
    private Date finPromo ;
    private String nomClient ;
    private String idFisc ;
    @Column(nullable=true)
    private Double totHt ;
    @Column(nullable=true)
    private Double toRemise ;
    @Column(nullable=true)
    private Double toTaxes ;
    @Column(nullable=true)
    private Double timbre ;
    @Column(nullable=true)
    private Double totTtc ;
    private Date date_Creation ;
    private Date date_Devis ;
    private String heure_Devis ;
    @Column(nullable=true)
    private Integer annulee ;
    private Date date_Annulation ;
    @Column(nullable=true)
    private Integer Archivee ;
    private Date date_Archivage ;

    public Dealers getDealer_Number() {
        return ss;
    }

    public void setDealer_Number(Dealers ss) {
        this.ss = ss;
    }

    public int getNumDevis() {
        return numDevis;
    }

    public void setNumDevis(int numDevis) {
        this.numDevis = numDevis;
    }

    public String getModele() {
        return modele;
    }

    public void setModele(String modele) {
        this.modele = modele;
    }

    public int getPromotion() {
        return promotion;
    }

    public void setPromotion(int promotion) {
        this.promotion = promotion;
    }

    public Date getDebutPromo() {
        return debutPromo;
    }

    public void setDebutPromo(Date debutPromo) {
        this.debutPromo = debutPromo;
    }

    public Date getFinPromo() {
        return finPromo;
    }

    public void setFinPromo(Date finPromo) {
        this.finPromo = finPromo;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }

    public String getIdFisc() {
        return idFisc;
    }

    public void setIdFisc(String idFisc) {
        this.idFisc = idFisc;
    }

    public double getTotHt() {
        return totHt;
    }

    public void setTotHt(double totHt) {
        this.totHt = totHt;
    }

    public double getToRemise() {
        return toRemise;
    }

    public void setToRemise(double toRemise) {
        this.toRemise = toRemise;
    }

    public double getToTaxes() {
        return toTaxes;
    }

    public void setToTaxes(double toTaxes) {
        this.toTaxes = toTaxes;
    }

    public double getTimbre() {
        return timbre;
    }

    public void setTimbre(double timbre) {
        this.timbre = timbre;
    }

    public double getTotTtc() {
        return totTtc;
    }

    public void setTotTtc(double totTtc) {
        this.totTtc = totTtc;
    }

    public Date getDate_Creation() {
        return date_Creation;
    }

    public void setDate_Creation(Date date_Creation) {
        this.date_Creation = date_Creation;
    }

    public Date getDate_Devis() {
        return date_Devis;
    }

    public void setDate_Devis(Date date_Devis) {
        this.date_Devis = date_Devis;
    }

    public String getHeure_Devis() {
        return heure_Devis;
    }

    public void setHeure_Devis(String heure_Devis) {
        this.heure_Devis = heure_Devis;
    }

    public int getAnnulee() {
        return annulee;
    }

    public void setAnnulee(int annulee) {
        this.annulee = annulee;
    }

    public Date getDate_Annulation() {
        return date_Annulation;
    }

    public void setDate_Annulation(Date date_Annulation) {
        this.date_Annulation = date_Annulation;
    }

    public int getArchivee() {
        return Archivee;
    }

    public void setArchivee(int archivee) {
        Archivee = archivee;
    }

    public Date getDate_Archivage() {
        return date_Archivage;
    }

    public void setDate_Archivage(Date date_Archivage) {
        this.date_Archivage = date_Archivage;
    }
}
