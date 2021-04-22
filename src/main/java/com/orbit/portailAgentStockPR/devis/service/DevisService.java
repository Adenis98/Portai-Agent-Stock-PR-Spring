package com.orbit.portailAgentStockPR.devis.service;

import com.orbit.portailAgentStockPR.devis.models.AjouterLigneDevisRequest;
import com.orbit.portailAgentStockPR.devis.models.AjouterLigneDevisResponse;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.stereotype.Service;

@Service
public class DevisService {
    public AjouterLigneDevisResponse ajouterLigne(AjouterLigneDevisRequest req)
    {
        try
        {
            //implements logic
            return new AjouterLigneDevisResponse();
        }catch(Exception e)
        {
            throw new ApiRequestException(""+e);
        }
    }
}
