package com.orbit.portailAgentStockPR.utilisateur.service;

import com.orbit.portailAgentStockPR.utilisateur.models.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import javax.transaction.Transactional;
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

    @Override
    User getOne(Integer integer);

    @Modifying
    @Transactional
    @Query("UPDATE User u SET u.img = :photo WHERE u.code = :userId")
    int updateimg(@Param("userId") int id, @Param("photo") byte[] photo);

    @Modifying
    @Transactional
    @Query( " UPDATE User u SET" +
            " u.userName = :usrName ," +
            " u.dealer_Number = :usrNumber," +
            " u.permis = :usrPermis," +
            " u.password = :usrPassword WHERE u.code = :usrId")
    int updateUser(@Param("usrId") int id ,
                   @Param("usrName") String userName ,
                   @Param("usrNumber")String dNumber ,
                   @Param("usrPermis")int permis ,
                   @Param("usrPassword") String password );

}
