package com.orbit.portailAgentStockPR.auth.service;

import com.orbit.portailAgentStockPR.auth.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User,String>{
    Optional<User> findByUserName(String userName);
}
