package com.orbit.portailAgentStockPR.consulterStockPr.controller;

import com.orbit.portailAgentStockPR.consulterStockPr.models.DealerStock;
import com.orbit.portailAgentStockPR.consulterStockPr.models.ListeStockAgentRequest;
import com.orbit.portailAgentStockPR.consulterStockPr.service.StockPrService;
import com.orbit.portailAgentStockPR.interAgent.models.DealerStockList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@CrossOrigin
@RestController
@RequestMapping(path="/ListeStockAgent")
public class StockPrController {

    @Autowired
    StockPrService stockPrService;

    @PostMapping
    public ResponseEntity<?> listeStockAgentWS(@RequestBody ListeStockAgentRequest req)
    {
        return ResponseEntity.ok(stockPrService.getArt(req));
    }
    @GetMapping("/monStock/{dNbr}")
    public List<DealerStockList> getMonStock(@PathVariable int dNbr)
    {
        return stockPrService.getMonStock(dNbr);
    }
}
