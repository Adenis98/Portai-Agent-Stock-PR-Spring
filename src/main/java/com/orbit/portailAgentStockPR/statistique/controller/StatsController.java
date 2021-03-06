package com.orbit.portailAgentStockPR.statistique.controller;

import com.orbit.portailAgentStockPR.commande.models.LigneCommande;
import com.orbit.portailAgentStockPR.statistique.models.Stat1Resp;
import com.orbit.portailAgentStockPR.statistique.models.Stat2Resp;
import com.orbit.portailAgentStockPR.statistique.models.Stat3Resp;
import com.orbit.portailAgentStockPR.statistique.models.Stat4Resp;
import com.orbit.portailAgentStockPR.statistique.service.StatsService;
import com.orbit.portailAgentStockPR.utilisateur.models.MyUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@PreAuthorize("hasAuthority('AGENT_RESPONSABLE')")
@RequestMapping("/stat")
@CrossOrigin
public class StatsController {

    private final StatsService statsService ;

    @GetMapping("/NbrCmdStockFerme")
    public Stat1Resp stat1( ) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dNbr = userDetails.getDealerNumber();
        return this.statsService.stat1(dNbr);
    }
    @GetMapping("/AllCmdStockFermeMonth")
    public Stat2Resp stat2( ) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dNbr = userDetails.getDealerNumber();
        return this.statsService.stat2(dNbr);
    }
    @GetMapping("/CmdLivEnrgFact")
    public Stat3Resp stat3() {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dNbr = userDetails.getDealerNumber();
        return this.statsService.stat3(dNbr);
    }
    @GetMapping("/Top5")
    public List<Stat4Resp> stat4( ) {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dNbr = userDetails.getDealerNumber();
        return this.statsService.stat4(dNbr);
    }

}
