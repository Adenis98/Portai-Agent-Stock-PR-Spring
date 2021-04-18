package com.orbit.portailAgentStockPR.interAgent.controller;

import com.orbit.portailAgentStockPR.interAgent.models.GetAllAgentsStockResponse;
import com.orbit.portailAgentStockPR.interAgent.service.GetAllAgentsStockService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class InterAgentController {

    @Autowired
    GetAllAgentsStockService getAllAgentsStockService ;

    @GetMapping("/getAllAgentsStock/{dNbr}")
    public List<GetAllAgentsStockResponse> getAgentsStock(@PathVariable int dNbr)
    {
        return getAllAgentsStockService.getAllDeallersStock(dNbr);
    }
}
