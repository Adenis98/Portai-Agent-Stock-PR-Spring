package com.orbit.portailAgentStockPR.devis.controller;


import com.orbit.portailAgentStockPR.devis.models.AjouterLigneDevisRequest;
import com.orbit.portailAgentStockPR.devis.models.AjouterLigneDevisResponse;
import com.orbit.portailAgentStockPR.devis.service.DevisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/devis")
public class DevisController {

    @Autowired
    DevisService devisService ;

    @PostMapping("/ajouterLigneDevis")
    public AjouterLigneDevisResponse ajouterLigneDevis(@RequestBody AjouterLigneDevisRequest req)
    {
        return devisService.ajouterLigne(req);
    }
}
