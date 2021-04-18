package com.orbit.portailAgentStockPR.interAgent.service;

import com.orbit.portailAgentStockPR.consulterStockPr.models.ArtMasters;
import com.orbit.portailAgentStockPR.consulterStockPr.models.DealerStock;
import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;
import com.orbit.portailAgentStockPR.consulterStockPr.service.ArtMastersRepository;
import com.orbit.portailAgentStockPR.consulterStockPr.service.DealerStockRepository;
import com.orbit.portailAgentStockPR.consulterStockPr.service.DealersRepository;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import com.orbit.portailAgentStockPR.interAgent.models.DealerStockList;
import com.orbit.portailAgentStockPR.interAgent.models.GetAllAgentsStockResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class GetAllAgentsStockService {

    @Autowired
    DealersRepository dealersRepository ;
    @Autowired
    DealerStockRepository dealerStockRepository ;
    @Autowired
    ArtMastersRepository artMastersRepository ;
    private List<Dealers> allDealerExceptOne(int dNbr )
    {
        List<Dealers> res = dealersRepository.findAll() ;
        for(int i = 0 ; i< res.size() ; i++)
        {
            if(res.get(i).getLdbDealerNumber() == dNbr )
            {
                res.remove(i);
                break;
            }
        }
        return res ;
    }

    private List<DealerStockList>  getDealerStock(int dNbr )
    {
        List<DealerStock> ds = dealerStockRepository .findAll() ;
        List<ArtMasters> artList = artMastersRepository.findAll() ;
        DealerStockList dsl ;
        List<DealerStockList> res = new ArrayList<>( );
        for(int i = 0 ; i< ds.size();i++){
            if(ds.get(i).getDealer_number().getLdbDealerNumber() == dNbr)
            {
                dsl = new DealerStockList() ;
                dsl.setStock(ds.get(i).getStock());
                dsl.setUg(ds.get(i).getUg());
                dsl.setQteAch(ds.get(i).getQte_achat());
                dsl.setCodArt(ds.get(i).getCodart());
                for(int j = 0 ; j< artList.size(); j++)
                {
                    if(artList.get(j).getCodArt().equals( ds.get(i).getCodart()))
                    {
                        dsl.setLibelle(artList.get(j).getLibelle());
                        dsl.setPuAgents(artList.get(j).getPu_agents());
                        break ;
                    }
                }
                res.add(dsl);
            }
        }
        return res ;
    }
    public List<GetAllAgentsStockResponse> getAllDeallersStock(int dNbr)
    {
        try
        {
            List<GetAllAgentsStockResponse> res = new ArrayList<>() ;
            List<Dealers> newDealerList =  allDealerExceptOne(dNbr);
            GetAllAgentsStockResponse oneResp ;
            for(int i = 0 ; i<newDealerList.size();i++)
            {
                oneResp = new GetAllAgentsStockResponse() ;
                oneResp.setDealerName(newDealerList.get(i).getDealerName());
                oneResp.setDealerPhoneNumber(newDealerList.get(i).getDealerPhoneNo());
                oneResp.setDealerStockList(getDealerStock(newDealerList.get(i).getLdbDealerNumber()));
                res.add(oneResp);
            }
            return res ;
        }catch(Exception e)
        {
            throw new ApiRequestException(""+e);
        }
    }
}
