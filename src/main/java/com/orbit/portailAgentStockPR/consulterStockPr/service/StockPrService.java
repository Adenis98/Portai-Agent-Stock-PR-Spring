package com.orbit.portailAgentStockPR.consulterStockPr.service;

import com.orbit.portailAgentStockPR.consulterStockPr.models.*;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import com.orbit.portailAgentStockPR.interAgent.models.DealerStockList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
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
            if(ds.getLdbDealerNumber() == 95) // *********** 95 supposé le seul  concessionnaire
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
                {
                    ListeStockAgentResponse liste = new ListeStockAgentResponse(0,"inconnu","inconnu",req.getCodeArt(),"inconnu","","","",0,-1,0,0);
                    ArrayList<ListeStockAgentResponse> retList = new ArrayList<>();
                    retList.add(liste);
                    return  retList;
                }

            }
            else if(req.getLibelle().length()>0)
            {
                ArrayList<ArtMasters>  aml= artMastersRepository.findByLibelle("%"+req.getLibelle()+"%");
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
    /******************************************************/
    public List<DealerStockList> getMonStock(int dNbr)
    {
        try
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

        }catch(Exception e)
        {
            throw new ApiRequestException(""+e);
        }
    }


    /******************************** Dealer Info **********************************/

    public DealerInfo getDealerInfo(int dNbr)
    {
        try
        {
            Dealers dealers=dealersRepository.getOne(dNbr);
            DealerInfo dInfo = new DealerInfo() ;
            dInfo.setDealerName(dealers.getDealerName());
            dInfo.setSalesMan(dealers.getSalesMan());
            return dInfo;
        }catch(Exception e)
        {
            throw new ApiRequestException(""+e.getMessage());
        }
    }
}
