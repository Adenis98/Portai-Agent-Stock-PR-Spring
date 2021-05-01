package com.orbit.portailAgentStockPR.statistique.controller;

import com.orbit.portailAgentStockPR.statistique.models.Stat1Resp;
import com.orbit.portailAgentStockPR.statistique.models.Stat3Resp;
import com.orbit.portailAgentStockPR.statistique.service.StatsService;
import lombok.AllArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RestController
@AllArgsConstructor
@RequestMapping("/stat")
@CrossOrigin
public class StatsController {

    private final StatsService statsService ;

    @GetMapping("/NbrCmdStockFerme/{dNbr}")
    public Stat1Resp stat1(@PathVariable int dNbr ) {
        return this.statsService.stat1(dNbr);
    }
    @GetMapping("/AllCmdStockFermeMonth/{dNbr}")
    public void stat2(@PathVariable int dNbr ) {

    }
    @GetMapping("/CmdLivEnrgFact/{dNbr}")
    public Stat3Resp stat3(@PathVariable int dNbr ) {
        return this.statsService.stat3(dNbr);
    }
    @GetMapping("/Top5/{dNbr}")
    public void stat4(@PathVariable int dNbr ) {

    }

}
