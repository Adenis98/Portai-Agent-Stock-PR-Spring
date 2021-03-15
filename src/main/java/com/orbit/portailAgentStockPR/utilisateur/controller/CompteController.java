package com.orbit.portailAgentStockPR.utilisateur.controller;

import com.orbit.portailAgentStockPR.utilisateur.models.User;
import com.orbit.portailAgentStockPR.utilisateur.service.CompteService;
import com.orbit.portailAgentStockPR.utilisateur.service.UserRepository;
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
/* //UPDATE
    @PutMapping("/maj/{id}")
    public boolean majCompte(@RequestBody User user,@PathVariable int id){
        return false;
    }
*/
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
}
