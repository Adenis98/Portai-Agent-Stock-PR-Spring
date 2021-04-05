package com.orbit.portailAgentStockPR.utilisateur.models;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;

public class MyUserDetails implements UserDetails {

    private String userName;
    private String password ;
    private int code ;
    private int dealerNumber ;
    private int permis ;

    public MyUserDetails(User user)
    {
        this.userName=user.getUserName();
        this.password = user.getPassword();
        this.code = user.getCode();
        this.dealerNumber = user.getDealer_Number();
        this.permis = user.getPermis() ;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return null;
    }

    public int getPermis() {
        return permis;
    }

    public int getDealerNumber() {
        return dealerNumber;
    }

    public int getCode() {
        return this.code;
    }

    @Override
    public String getPassword() {
        return this.password;
    }

    @Override
    public String getUsername() {
        return this.userName;
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
