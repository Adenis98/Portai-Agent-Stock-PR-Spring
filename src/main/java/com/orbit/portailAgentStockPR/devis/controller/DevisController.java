package com.orbit.portailAgentStockPR.devis.controller;


import com.orbit.portailAgentStockPR.devis.models.*;
import com.orbit.portailAgentStockPR.devis.service.DevisService;
import com.orbit.portailAgentStockPR.utilisateur.models.MyUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@PreAuthorize("hasAuthority('AGENT_RESPONSABLE')")
@RequestMapping("/devis")
@AllArgsConstructor
public class DevisController {


    private final DevisService devisService ;

    @PostMapping("/ajouterLigneDevis")
    public AjouterDevisResponse ajouterLigneDevis(@RequestBody AjouterDevisRequest req)
    {
        return devisService.ajouterDevis(req);
    }

    @GetMapping("/getAllDevis/{arch}")
    public List<GetListeDevisResponse>  getListeDevis(@PathVariable int arch)
    {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dNbr = userDetails.getDealerNumber();
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
