package com.orbit.portailAgentStockPR.consulterStockPr.service;

import com.orbit.portailAgentStockPR.consulterStockPr.models.*;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class StockPrService {

    @Autowired
    DealerStockRepository dealerStockRepository ;

    @Autowired
    DealersRepository dealersRepository ;

    @Autowired
    ArtMastersRepository artMastersRepository ;

    public ListeStockAgentResponse getArt(ListeStockAgentRequest req)
    {

        try{
            DealerStock d= dealerStockRepository.getOne(req.getCodeArt());
            Dealers ds =d.getDealer_number();
            ArtMasters am = artMastersRepository.getOne(d.getCodart());
            return new ListeStockAgentResponse(ds.getLdbDealerNumber(),ds.getDealerName(),ds.getDealerPhoneNo(),d.getCodart(),am.getLibelle(),am.getH(),am.getHt(),am.getHtg(),am.getPu_agents(),d.getStock(),am.getRemisable(),d.getQte_achat());
        }catch(Exception e)
        {
            throw new ApiRequestException("referance introuvable !!");
        }

    }

}
