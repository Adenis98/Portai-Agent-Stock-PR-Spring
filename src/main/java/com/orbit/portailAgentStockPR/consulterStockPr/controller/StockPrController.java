package com.orbit.portailAgentStockPR.consulterStockPr.controller;

import com.orbit.portailAgentStockPR.consulterStockPr.models.ListeStockAgentRequest;
import com.orbit.portailAgentStockPR.consulterStockPr.models.ListeStockAgentResponse;
import com.orbit.portailAgentStockPR.consulterStockPr.service.StockPrService;
import com.orbit.portailAgentStockPR.utilisateur.models.AuthenticationResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/ListeStockAgent")
public class StockPrController {

    @Autowired
    StockPrService stockPrService;

    @GetMapping
    public ResponseEntity<?> listeStockAgentWS(@RequestBody ListeStockAgentRequest req)
    {
        return ResponseEntity.ok(stockPrService.getArt(req));
    }
}
