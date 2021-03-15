package com.orbit.portailAgentStockPR.utilisateur.service;

import com.orbit.portailAgentStockPR.utilisateur.models.MyUserDetails;
import com.orbit.portailAgentStockPR.utilisateur.models.User;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Override
    public UserDetails loadUserByUsername(String userName) throws ApiRequestException {

        Optional<User> user=userRepository.findByUserName(userName);

        //obejet user
        if(!user.isPresent())
            throw new ApiRequestException("nom d'utilisateur "+userName+" est incorrect");

        //return new MyUserDetails(user);

        UserDetails userDetails = user.map(MyUserDetails::new).get();

        return  userDetails ;
        //return new User("ramez","zormati",new ArrayList<>());
    }

}
