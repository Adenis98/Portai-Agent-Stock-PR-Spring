package com.orbit.portailAgentStockPR.commande.controller;


import com.orbit.portailAgentStockPR.commande.models.LignePanierRequest;
import com.orbit.portailAgentStockPR.commande.models.LignePanierResponse;
import com.orbit.portailAgentStockPR.commande.service.PanierService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path="/panier")
public class PanierController {

    @Autowired
    PanierService panierService ;

    @PostMapping("/LignePanier")
    public LignePanierResponse LignePanier(@RequestBody LignePanierRequest lpr){
        return panierService.insertLigneCommande(lpr);
    }

    @GetMapping("/GetPanierWS")
    public void GetPanierWS(){

    }
}
