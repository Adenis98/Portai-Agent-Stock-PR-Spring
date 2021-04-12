package com.orbit.portailAgentStockPR.commande.controller;

import com.orbit.portailAgentStockPR.commande.models.PasserCommandeRequest;
import com.orbit.portailAgentStockPR.commande.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path="/commande")
public class CommandeController {

    @Autowired
    CommandeService commandeService ;
    @PostMapping("/passer")
    public int  passerCommande(@RequestBody PasserCommandeRequest req)
    {
        return commandeService.passerCommande(req);
    }

    @GetMapping("/afficher/{dNbr}")
    public void afficherCommande(@PathVariable int dNbr)
    {

    }
}
