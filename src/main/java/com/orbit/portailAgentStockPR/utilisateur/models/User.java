package com.orbit.portailAgentStockPR.utilisateur.models;


import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;

@Entity
@Table(name = "s_dealer_modules")
public class User {

    @GeneratedValue(
            strategy= GenerationType.AUTO,
            generator="native"
    )
    @GenericGenerator(
            name = "native",
            strategy = "native"
    )
    @Id
    private int code ;
    private String userName ;
    private int dealer_Number ;
    private int permis ;
    private String password ;
    @Lob
    @Column(length = Integer.MAX_VALUE)
    private byte[] img ;

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getDealer_Number() {
        return dealer_Number;
    }

    public void setDealer_Number(int dealer_Number) {
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
