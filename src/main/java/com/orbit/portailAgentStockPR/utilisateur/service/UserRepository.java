package com.orbit.portailAgentStockPR.utilisateur.service;

import com.orbit.portailAgentStockPR.utilisateur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer>{
    Optional<User> findByUserName(String userName);

    @Override
    void deleteById(Integer integer);

    @Override
    List<User> findAll();

    @Override
    User save(User s);

    @Override
    long count();
}
