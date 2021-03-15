package com.orbit.portailAgentStockPR.utilisateur.service;

import com.orbit.portailAgentStockPR.utilisateur.models.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class CompteService {

    @Autowired
    UserRepository userRepository;

    public User save(User user){

        return userRepository.save(user);
    }

    public boolean delete(int id){
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true ;
        }
        return false;
    }

    public List<User> findAll(){
        return userRepository.findAll();
    }
}
