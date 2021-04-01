package com.orbit.portailAgentStockPR.consulterStockPr.controller;

import com.orbit.portailAgentStockPR.consulterStockPr.models.ListeStockAgentRequest;
import com.orbit.portailAgentStockPR.consulterStockPr.service.StockPrService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

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
}
