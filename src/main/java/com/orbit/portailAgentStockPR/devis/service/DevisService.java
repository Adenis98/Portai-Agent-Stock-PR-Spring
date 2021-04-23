package com.orbit.portailAgentStockPR.devis.service;

import com.orbit.portailAgentStockPR.devis.models.AjouterDevisRequest;
import com.orbit.portailAgentStockPR.devis.models.AjouterDevisResponse;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.stereotype.Service;

@Service
public class DevisService {
    public AjouterDevisResponse ajouterDevis(AjouterDevisRequest req)
    {
        try
        {
            //implements logic
            return new AjouterDevisResponse();
        }catch(Exception e)
        {
            throw new ApiRequestException(""+e);
        }
    }
}
