package com.orbit.portailAgentStockPR.utilisateur.controller;

import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import com.orbit.portailAgentStockPR.utilisateur.models.MyUserDetails;
import com.orbit.portailAgentStockPR.utilisateur.models.User;
import com.orbit.portailAgentStockPR.utilisateur.service.CompteService;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
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

    @PreAuthorize("hasAuthority('ADMIN')")
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
    @PreAuthorize("hasAuthority('ADMIN')")
    @PutMapping("/maj/{id}")
    public User majCompte(@RequestBody User user, @PathVariable int id){
        try{
            return compteService.updateUser(user,id);
        }catch(Exception e){
            throw new ApiRequestException(e.getMessage());
        }

    }

    @PreAuthorize("hasAuthority('ADMIN')")
    @DeleteMapping(path="/supprimer/{id}")
    public boolean suppCompte(@PathVariable int id)
    {
        return compteService.delete(id);
    }


    @PreAuthorize("hasAuthority('ADMIN')")
    @GetMapping(path="/avoirTout/{admin}")
    public List<User> getall(@PathVariable int admin){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dNbr = userDetails.getDealerNumber();
        return compteService.findAll(dNbr,admin);
    }


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
