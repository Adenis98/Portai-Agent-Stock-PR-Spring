package com.orbit.portailAgentStockPR.consulterStockPr.models;

import javax.persistence.*;

@Entity
public class Dealers {
    @Id
    @Column(name="LDB_Dealer_Number")
    private int ldbDealerNumber ;

    @Column(name="Dealer_name")
    private String dealerName ;

    @Column(name="sales_Man")
    private String salesMan ;

    @Column(name="dealer_phone_no")
    private String dealerPhoneNo ;

    @Lob
    @Column(name="dealer_logo",length = Integer.MAX_VALUE)
    private byte[] dealerLogo ;

    @Column(name="dealer_Signature")
    private String dealerSignature ;

    public int getLdbDealerNumber() {
        return ldbDealerNumber;
    }

    public void setLdbDealerNumber(int ldbDealerNumber) {
        this.ldbDealerNumber = ldbDealerNumber;
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getSalesMan() {
        return salesMan;
    }

    public void setSalesMan(String salesMan) {
        this.salesMan = salesMan;
    }

    public String getDealerPhoneNo() {
        return dealerPhoneNo;
    }

    public void setDealerPhoneNo(String dealerPhoneNo) {
        this.dealerPhoneNo = dealerPhoneNo;
    }

    public byte[] getDealerLogo() {
        return dealerLogo;
    }

    public void setDealerLogo(byte[] dealerLogo) {
        this.dealerLogo = dealerLogo;
    }

    public String getDealerSignature() {
        return dealerSignature;
    }

    public void setDealerSignature(String dealerSignature) {
        this.dealerSignature = dealerSignature;
    }
}
