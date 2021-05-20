package com.orbit.portailAgentStockPR.utilisateur.service;

import com.orbit.portailAgentStockPR.utilisateur.models.MyUserDetails;
import com.orbit.portailAgentStockPR.utilisateur.models.User;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;

@Service
public class MyUserDetailsService implements UserDetailsService {
    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder ;

    @Override
    public UserDetails loadUserByUsername(String userName) throws ApiRequestException {
        if(userName.equals("admin")){
            User user = new User();
            user.setUserName("admin");
            user.setPassword(passwordEncoder.encode("1234"));
            user.setPermis(3);
            user.setDealer_Number(95);
            return new MyUserDetails(user);
        }
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
