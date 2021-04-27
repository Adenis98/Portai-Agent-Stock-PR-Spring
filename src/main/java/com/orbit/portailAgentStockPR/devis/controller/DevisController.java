package com.orbit.portailAgentStockPR.devis.controller;


import com.orbit.portailAgentStockPR.devis.models.AjouterDevisRequest;
import com.orbit.portailAgentStockPR.devis.service.DevisService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping("/devis")
@AllArgsConstructor
public class DevisController {


    private final DevisService devisService ;

    @PostMapping("/ajouterLigneDevis")
    public int ajouterLigneDevis(@RequestBody AjouterDevisRequest req)
    {
        return devisService.ajouterDevis(req);
    }
}
