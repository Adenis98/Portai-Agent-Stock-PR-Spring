package com.orbit.portailAgentStockPR.consulterStockPr.controller;

import com.orbit.portailAgentStockPR.consulterStockPr.models.DealerInfo;
import com.orbit.portailAgentStockPR.consulterStockPr.models.DealerStock;
import com.orbit.portailAgentStockPR.consulterStockPr.models.ListeStockAgentRequest;
import com.orbit.portailAgentStockPR.consulterStockPr.service.StockPrService;
import com.orbit.portailAgentStockPR.interAgent.models.DealerStockList;
import com.orbit.portailAgentStockPR.utilisateur.models.MyUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="/ListeStockAgent")
@AllArgsConstructor
public class StockPrController {


    private final StockPrService stockPrService;

    @PostMapping
    public ResponseEntity<?> listeStockAgentWS(@RequestBody ListeStockAgentRequest req)
    {
        return ResponseEntity.ok(stockPrService.getArt(req));
    }
    @GetMapping("/monStock")
    public List<DealerStockList> getMonStock()
    {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dNbr = userDetails.getDealerNumber();
        return stockPrService.getMonStock(dNbr);
    }

    @GetMapping("/getDealerInfo")
    public DealerInfo getDealerInfo()
    {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dNbr = userDetails.getDealerNumber();
        return stockPrService.getDealerInfo(dNbr);
    }
}
