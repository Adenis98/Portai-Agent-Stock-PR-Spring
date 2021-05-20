package com.orbit.portailAgentStockPR.commande.controller;

import com.orbit.portailAgentStockPR.commande.models.*;
import com.orbit.portailAgentStockPR.commande.service.CommandeService;
import com.orbit.portailAgentStockPR.utilisateur.models.MyUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="/commande")
@AllArgsConstructor
public class CommandeController {


    private final CommandeService commandeService ;

    @PreAuthorize("hasAuthority('RESPONSABLE')")
    @PostMapping("/passer")
    public int  passerCommande(@RequestBody PasserCommandeRequest req)
    {
        return commandeService.passerCommande(req);
    }

    @PreAuthorize("hasAuthority('AGENT_RESPONSABLE')")
    @GetMapping("/afficher/{arch}")
    public List<GetCommandeResponse> afficherCommande(@PathVariable int arch)
    {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dNbr = userDetails.getDealerNumber();
        return commandeService.getCommande(dNbr,arch);
    }

    @PreAuthorize("hasAuthority('AGENT_RESPONSABLE')")
    @GetMapping("/afficher/ligneCmd/{nCmd}")
    public List<GetLigneCommandeResponse>  afficherLigneCommande(@PathVariable int nCmd)
    {
        return commandeService.getLigneCommande(nCmd) ;
    }

    @PreAuthorize("hasAuthority('AGENT_RESPONSABLE')")
    @GetMapping("/afficheCmdr/{nCmd}")
    public GetCommandeResponse  getOneCommande(@PathVariable int nCmd)
    {
        return commandeService.getOneCmnd(nCmd);
    }

    @PreAuthorize("hasAuthority('AGENT_RESPONSABLE')")
    @PostMapping("/annuler/{nCmd}")
    public int  annulerCommande(@PathVariable int nCmd)
    {
       return commandeService.annulerCmd(nCmd) ;
    }

    @PreAuthorize("hasAuthority('AGENT_RESPONSABLE')")
    @GetMapping("/filtreRefArt/{refArt}")
    public List<Integer>  filtreCommandeRefArt(@PathVariable String refArt)
    {
        return commandeService.filtreCmdRefArt(refArt) ;
    }
    @PreAuthorize("hasAuthority('AGENT_RESPONSABLE')")
    @GetMapping("/filtreVin/{vin}")
    public List<Integer>  filtreCommandeVin(@PathVariable String vin)
    {
        return commandeService.filtreCmdVin(vin) ;
    }
}
