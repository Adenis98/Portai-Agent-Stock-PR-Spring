package com.orbit.portailAgentStockPR.utilisateur.controller;

import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import com.orbit.portailAgentStockPR.utilisateur.models.User;
import com.orbit.portailAgentStockPR.utilisateur.service.CompteService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.websocket.server.PathParam;
import java.io.FileOutputStream;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/compte")
@AllArgsConstructor
public class CompteController {


    private final CompteService compteService;

    private final ServletContext servletContext;


    @RequestMapping(path = "/ajout",method = RequestMethod.POST)
    public User ajouterCompte(@RequestBody User user)
    {
        try{
            return compteService.save(user);
        }catch(Exception e){
            throw new ApiRequestException(e.getMessage());
        }

    }

   //UPDATE retourne null si le user n'existe pas
    @PutMapping("/maj/{id}")
    public User majCompte(@RequestBody User user, @PathVariable int id){
        try{
            return compteService.updateUser(user,id);
        }catch(Exception e){
            throw new ApiRequestException(e.getMessage());
        }

    }

    @DeleteMapping(path="/supprimer/{id}")
    public boolean suppCompte(@PathVariable int id)
    {
        return compteService.delete(id);
    }


    @GetMapping(path="/avoirTout/{dNbr}/{admin}")
    public List<User> getall(@PathVariable int dNbr,@PathVariable int admin)
    {
        return compteService.findAll(dNbr,admin);
    }


  /*  @PostMapping(path="/majPhoto/{id}")
    public boolean majPhoto(@RequestBody String photo,@PathVariable int id)
    {
        return compteService.majPhoto(id, photo);
    }*/

    @PostMapping(path="/majPhoto/{userName}")
    public String uploadImage(@RequestBody String photo,@PathVariable String userName)
    {
        try
        {
            //This will decode the String which is encoded by using Base64 class
            byte[] imageByte;
            imageByte = Base64.decodeBase64(photo);

            compteService.majPhoto(userName,imageByte);
            return "success ";
        }
        catch(Exception e)
        {
            return "error = "+e;
        }
    }


    @GetMapping(path="/getPhoto/{userName}")
    public String getPhoto(@PathVariable String userName )
    {
        try
        {
            return compteService.getPhoto(userName);
        }catch(Exception e)
        {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
