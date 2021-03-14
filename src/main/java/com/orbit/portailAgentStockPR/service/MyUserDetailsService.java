package com.orbit.portailAgentStockPR.service;

import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.context.annotation.Bean;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Override
    public UserDetails loadUserByUsername(String userName) throws ApiRequestException {
        //obejet user
        if(!userName.equals("ramez"))
            throw new ApiRequestException("nom d'utilisateur  inconnu");
        return new User("ramez","zormati",new ArrayList<>());
    }

}
