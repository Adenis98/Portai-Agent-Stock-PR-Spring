package com.orbit.portailAgentStockPR.commande.controller;


import com.orbit.portailAgentStockPR.commande.models.GetPanierWsResponse;
import com.orbit.portailAgentStockPR.commande.models.LignePanierRequest;
import com.orbit.portailAgentStockPR.commande.models.LignePanierResponse;
import com.orbit.portailAgentStockPR.commande.service.PanierService;
import com.orbit.portailAgentStockPR.utilisateur.models.MyUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@CrossOrigin
@RestController
@RequestMapping(path="/panier")
@AllArgsConstructor
public class PanierController {


    private final PanierService panierService ;

    @PostMapping("/LignePanier")
    public LignePanierResponse lignePanier(@RequestBody LignePanierRequest lpr){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        lpr.setDealerNumber(userDetails.getDealerNumber());
        return panierService.insertLigneCommande(lpr);
    }

    @GetMapping("/GetPanierWS")
    public GetPanierWsResponse getPanierWS(){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dNbr = userDetails.getDealerNumber();
        return panierService.getPanier(dNbr);
    }

    @DeleteMapping("/DeleteLignePanier/{numLigne}")
    public String  deletePanierWS(@PathVariable("numLigne") int numLigne){
        return panierService.supprimerLigne(numLigne) ;
    }

    @GetMapping("/GetPanierSize")
    public int getPanierSize(){
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dNbr = userDetails.getDealerNumber();
        return panierService.getPanierSize(dNbr);
    }
}
