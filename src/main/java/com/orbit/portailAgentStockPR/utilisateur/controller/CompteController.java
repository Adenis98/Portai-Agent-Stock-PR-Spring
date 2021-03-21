package com.orbit.portailAgentStockPR.utilisateur.controller;

import com.orbit.portailAgentStockPR.utilisateur.models.User;
import com.orbit.portailAgentStockPR.utilisateur.service.CompteService;
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
public class CompteController {

    @Autowired
    CompteService compteService;
    @Autowired
    ServletContext servletContext;


    @RequestMapping(path = "/ajout",method = RequestMethod.POST)
    public User ajouterCompte(@RequestBody User user)
    {
        return compteService.save(user);
    }

   //UPDATE retourne null si le user n'existe pas
    @PutMapping("/maj/{id}")
    public User majCompte(@RequestBody User user, @PathVariable int id){
        System.out.println("************"+id+"****"+user);
        return compteService.update(user,id);
    }

    @DeleteMapping(path="/supprimer/{id}")
    public boolean suppCompte(@PathVariable int id)
    {
        return compteService.delete(id);
    }


    @GetMapping(path="/avoirTout")
    public List<User> getall()
    {
        return compteService.findAll();
    }


  /*  @PostMapping(path="/majPhoto/{id}")
    public boolean majPhoto(@RequestBody String photo,@PathVariable int id)
    {
        return compteService.majPhoto(id, photo);
    }*/

    @PostMapping(path="/majPhoto/{id}")
    public String uploadImage(@RequestBody String photo,@PathVariable int id)
    {
        try
        {
            //This will decode the String which is encoded by using Base64 class
            byte[] imageByte;
            imageByte = Base64.decodeBase64(photo);

            compteService.majPhoto(id,imageByte);
            return "success ";
        }
        catch(Exception e)
        {
            return "error = "+e;
        }
    }


    @GetMapping(path="/getPhoto/{id}")
    public String getPhoto(@PathVariable int id )
    {
        try
        {
            return compteService.getPhoto(id);
        }catch(Exception e)
        {
            return "error : "+e;
        }
    }
}
