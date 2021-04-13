package com.orbit.portailAgentStockPR.commande.controller;

import com.orbit.portailAgentStockPR.commande.models.*;
import com.orbit.portailAgentStockPR.commande.service.CommandeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

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
    public List<GetCommandeResponse> afficherCommande(@PathVariable int dNbr)
    {
        return commandeService.getCommande(dNbr);
    }

    @GetMapping("/afficher/ligneCmd/{nCmd}")
    public List<GetLigneCommandeResponse>  afficherLigneCommande(@PathVariable int nCmd)
    {
        return commandeService.getLigneCommande(nCmd) ;
    }
}
