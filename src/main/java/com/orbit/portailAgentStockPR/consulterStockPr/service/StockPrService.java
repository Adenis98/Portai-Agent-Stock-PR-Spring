package com.orbit.portailAgentStockPR.consulterStockPr.service;

import com.orbit.portailAgentStockPR.consulterStockPr.models.*;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
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

    private ArrayList<ListeStockAgentResponse> getListArt(ArtMasters am)
    {
        ArrayList<DealerStock> dstocklist = dealerStockRepository.findByArtCode(am.getCodArt());
        ArrayList<ListeStockAgentResponse> returnList = new ArrayList<ListeStockAgentResponse>();
        for(int i = 0 ; i< dstocklist.size();i++)
        {
            DealerStock d = dstocklist.get(i);
            Dealers ds =d.getDealer_number();
            returnList.add(new ListeStockAgentResponse(ds.getLdbDealerNumber(),ds.getDealerName(),ds.getDealerPhoneNo(),d.getCodart(),am.getLibelle(),am.getH(),am.getHt(),am.getHtg(),am.getPu_agents(),d.getStock(),am.getRemisable(),d.getQte_achat()));
        }
        return returnList;
    }

    public ArrayList<ListeStockAgentResponse>  getArt(ListeStockAgentRequest req)
    {
        try{
            ArrayList<ListeStockAgentResponse> l = new ArrayList<>();
            if(req.getCodeArt().length()>0)
            {
                ArtMasters am = artMastersRepository.getOne(req.getCodeArt());
                l =  this.getListArt(am) ;
                if(l.size()==0)
                    throw new ApiRequestException("Code article introuvable !!");
            }
            else if(req.getLibelle().length()>0)
            {
                ArrayList<ArtMasters>  aml= artMastersRepository.findByLibelle(req.getLibelle());
                if(aml.size()==0)
                    throw new ApiRequestException("Libelle introuvable !!");
                l =  this.getListArt(aml.get(0)) ;
            }else
                throw new ApiRequestException("veuillez spécifier un codArt ou un libellé ");
            return l;
        }catch(Exception e)
        {
            throw new ApiRequestException("message d'erreur : "+e.getMessage());
        }
    }

}
