package com.orbit.portailAgentStockPR.consulterStockPr.service;

import com.orbit.portailAgentStockPR.consulterStockPr.models.*;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Optional;

@Service
public class StockPrService {

    @Autowired
    DealerStockRepository dealerStockRepository ;

    @Autowired
    DealersRepository dealersRepository ;

    @Autowired
    ArtMastersRepository artMastersRepository ;

    public ArrayList<ListeStockAgentResponse>  getArt(ListeStockAgentRequest req)
    {

        try{
            ArtMasters am = artMastersRepository.getOne(req.getCodeArt());
            ArrayList<DealerStock> dstocklist = dealerStockRepository.findByArtCode(req.getCodeArt());
            ArrayList<ListeStockAgentResponse> returnList = new ArrayList<ListeStockAgentResponse>();
            for(int i = 0 ; i< dstocklist.size();i++)
            {
                DealerStock d = dstocklist.get(i);
                Dealers ds =d.getDealer_number();
                returnList.add(new ListeStockAgentResponse(ds.getLdbDealerNumber(),ds.getDealerName(),ds.getDealerPhoneNo(),d.getCodart(),am.getLibelle(),am.getH(),am.getHt(),am.getHtg(),am.getPu_agents(),d.getStock(),am.getRemisable(),d.getQte_achat()));
            }
            return returnList ;
        }catch(Exception e)
        {
            throw new ApiRequestException("message d'erreur : "+e.getMessage());
        }

    }

}
