package com.orbit.portailAgentStockPR.utilisateur.service;

import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import com.orbit.portailAgentStockPR.utilisateur.models.User;
import org.apache.tomcat.util.codec.binary.Base64;
import org.hibernate.SharedSessionContract;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transaction;
import java.util.ArrayList;
import java.util.List;
@Service
public class CompteService {

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder passwordEncoder ;

    private boolean userNameExists(String userName)
    {
        int myUserId=-1;
        for (User user:findAll()) {
            if(user.getUserName().equals(userName))
                return true ;
        }
        return false ;
    }

    public User save(User user) throws Exception
    {
        if(userNameExists((user.getUserName())))
            throw new Exception(" nom d'utilisateur existe dèja ");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        User newUser = userRepository.save(user);
        newUser.setPassword("• • • • •");
        return newUser ;
    }

    public boolean delete(int id){
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
            return true ;
        }
        return false;
    }

    public List<User> findAll(int dNbr){
        List<User> allUsers = userRepository.findAll();
        List<User> newList = new ArrayList<>();
        for(int i = 0 ; i<allUsers.size() ; i++)
            if(allUsers.get(i).getDealer_Number()==dNbr)
            {
                allUsers.get(i).setPassword("• • • • •");
                newList.add(allUsers.get(i));
            }

        return newList;
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
    public User updateUser(User user,int id) throws Exception {
        if(!userRepository.findById(id).get().getUserName().equals(user.getUserName())&&userNameExists((user.getUserName())))
            throw new Exception(" nom d'utilisateur existe dèja ");
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        userRepository.updateUser(id,user.getUserName(),user.getDealer_Number(),user.getPermis(),user.getPassword());
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
            throw new Exception("nom d'utilisateur n'existe pas");
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
        throw new Exception("pas d'image ");
    }
}
