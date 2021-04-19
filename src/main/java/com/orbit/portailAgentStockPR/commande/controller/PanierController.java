package com.orbit.portailAgentStockPR.commande.controller;


import com.orbit.portailAgentStockPR.commande.models.GetPanierWsResponse;
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
    public LignePanierResponse lignePanier(@RequestBody LignePanierRequest lpr){
        return panierService.insertLigneCommande(lpr);
    }

    @GetMapping("/GetPanierWS")
    public GetPanierWsResponse getPanierWS(@RequestHeader("DealerNumber") int dNbr){
        return panierService.getPanier(dNbr);
    }

    @DeleteMapping("/DeleteLignePanier/{numLigne}")
    public String  deletePanierWS(@PathVariable("numLigne") int numLigne){
        return panierService.supprimerLigne(numLigne) ;
    }

    @GetMapping("/GetPanierSize/{dNbr}")
    public int getPanierSize(@PathVariable int dNbr){
        return panierService.getPanierSize(dNbr);
    }
}
