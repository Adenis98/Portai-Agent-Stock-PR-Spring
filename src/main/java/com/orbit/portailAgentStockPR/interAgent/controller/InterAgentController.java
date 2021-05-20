package com.orbit.portailAgentStockPR.interAgent.controller;

import com.orbit.portailAgentStockPR.interAgent.models.GetAllAgentsStockResponse;
import com.orbit.portailAgentStockPR.interAgent.service.GetAllAgentsStockService;
import com.orbit.portailAgentStockPR.utilisateur.models.MyUserDetails;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@CrossOrigin
@RestController
@AllArgsConstructor
public class InterAgentController {


    private final GetAllAgentsStockService getAllAgentsStockService ;

    @PreAuthorize("hasAuthority('AGENT_RESPONSABLE')")
    @GetMapping("/getAllAgentsStock")
    public List<GetAllAgentsStockResponse> getAgentsStock()
    {
        MyUserDetails userDetails = (MyUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        int dNbr = userDetails.getDealerNumber();
        return getAllAgentsStockService.getAllDeallersStock(dNbr);
    }
}
