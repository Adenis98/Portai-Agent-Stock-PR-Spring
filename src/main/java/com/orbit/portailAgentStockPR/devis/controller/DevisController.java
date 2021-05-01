package com.orbit.portailAgentStockPR.devis.controller;


import com.orbit.portailAgentStockPR.devis.models.*;
import com.orbit.portailAgentStockPR.devis.service.DevisService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping("/devis")
@AllArgsConstructor
public class DevisController {


    private final DevisService devisService ;

    @PostMapping("/ajouterLigneDevis")
    public AjouterDevisResponse ajouterLigneDevis(@RequestBody AjouterDevisRequest req)
    {
        return devisService.ajouterDevis(req);
    }

    @GetMapping("/getAllDevis/{dNbr}/{arch}")
    public List<GetListeDevisResponse>  getListeDevis(@PathVariable int dNbr,@PathVariable int arch)
    {
        return devisService.getDevisList(dNbr,arch);
    }

    @GetMapping("/getOneAllDevis/{nDevis}")
    public OneDevisResponse getOneDevis(@PathVariable int nDevis)
    {
        return devisService.getOneDevis(nDevis);
    }

    @PostMapping("/annuler/{nDevis}")
    public int annulerDevis(@PathVariable int nDevis)
    {
        return devisService.annulerDevis(nDevis);
    }
}
