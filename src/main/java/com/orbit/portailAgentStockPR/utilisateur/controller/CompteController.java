package com.orbit.portailAgentStockPR.utilisateur.controller;

import com.orbit.portailAgentStockPR.utilisateur.models.User;
import com.orbit.portailAgentStockPR.utilisateur.service.CompteService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path = "/compte")
public class CompteController {

    @Autowired
    CompteService compteService;

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


    @PostMapping(path="/majPhoto")
    public boolean majPhoto(@RequestBody String photo)
    {
        return false ;
    }
}