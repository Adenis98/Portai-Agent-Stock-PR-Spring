package com.orbit.portailAgentStockPR.utilisateur.service;

import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import com.orbit.portailAgentStockPR.utilisateur.models.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.SharedSessionContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transaction;
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

    /*public User update(User usr,int id){
        if (delete(id)) {
            return save(usr);
        }
        return null ;
    }*/
    public User updateUser(User user,int id){
            int nbLigne = userRepository.updateUser(id,user.getUserName(),user.getDealer_Number(),user.getPermis(),user.getPassword());
            return userRepository.findById(id).get();
    }

    private int getIdByUserName(String userName)throws Exception
    {
        int myUserId=-1;
        for (User user:findAll()) {
            if(user.getUserName().equals(userName))
                myUserId=user.getCode();
        }
        if(myUserId==-1)
            throw new Exception("utilisateur inconnue");
        return myUserId ;
    }
    public boolean  majPhoto(String userName,byte[] photo) throws Exception
    {
        int myUserId = getIdByUserName(userName);
        return userRepository.updateimg(myUserId,photo)>0;
    }

    public String getPhoto(String userName)throws Exception
    {
        int myUserId=getIdByUserName(userName);
        byte[] img= userRepository.getOne(myUserId).getImg();
        if(img!=null)
            return Base64.encodeBase64String(img);
        return "pas d'image";
    }
}
