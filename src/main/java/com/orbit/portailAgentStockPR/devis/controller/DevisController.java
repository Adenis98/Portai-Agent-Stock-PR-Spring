package com.orbit.portailAgentStockPR.devis.controller;


import com.orbit.portailAgentStockPR.devis.models.AjouterDevisRequest;
import com.orbit.portailAgentStockPR.devis.models.AjouterDevisResponse;
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
    public int ajouterLigneDevis(@RequestBody AjouterDevisRequest req)
    {
        return devisService.ajouterDevis(req);
    }
}
