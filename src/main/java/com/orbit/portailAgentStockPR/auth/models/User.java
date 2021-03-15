package com.orbit.portailAgentStockPR.auth.models;


import javax.persistence.*;

@Entity
@Table(name = "sDealer_Modules")
public class User {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private String code ;
    private String userName ;
    private String dealer_Number ;
    private int permis ;
    private String password ;

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getDealer_Number() {
        return dealer_Number;
    }

    public void setDealer_Number(String dealer_Number) {
        this.dealer_Number = dealer_Number;
    }

    public int getPermis() {
        return permis;
    }

    public void setPermis(int permis) {
        this.permis = permis;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
