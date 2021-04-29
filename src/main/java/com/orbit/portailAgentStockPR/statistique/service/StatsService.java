package com.orbit.portailAgentStockPR.statistique.service;

import com.orbit.portailAgentStockPR.commande.models.Commande;
import com.orbit.portailAgentStockPR.commande.service.CommandeRepository;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import com.orbit.portailAgentStockPR.statistique.models.Stat1Resp;
import com.orbit.portailAgentStockPR.statistique.models.Stat2Resp;
import com.orbit.portailAgentStockPR.statistique.models.Stat3Resp;
import com.orbit.portailAgentStockPR.statistique.models.Stat4Resp;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@AllArgsConstructor
public class StatsService {

    private final CommandeRepository commandeRepository ;

    /***************************************  Ring Stat   ****************************************/
    public Stat1Resp stat1(int dNbr ) {
        try{
            List<Commande> allCmd = commandeRepository.findAll();
            int cmdFermeNbr = 0 ,cmdNormNbr=0 ;
            for(int i = 0 ; i< allCmd.size() ; i++ ){
                if(allCmd.get(i).getDealer_Number().getLdbDealerNumber() == dNbr && allCmd.get(i).getType_Cmd() == 0)
                    cmdNormNbr++;
                else if(allCmd.get(i).getDealer_Number().getLdbDealerNumber() == dNbr && allCmd.get(i).getType_Cmd() == 1)
                    cmdFermeNbr++;
            }
            return new Stat1Resp(cmdFermeNbr,cmdNormNbr);

        }catch(Exception e)
        {
            throw new ApiRequestException(e.getMessage());
        }
    }
    /***************************************  Bar Stat typeCmd/month   ****************************************/
    public Stat2Resp stat2(int dNbr ) {
        try{
            List<Commande> allCmd = commandeRepository.findAll();

            return new Stat2Resp();

        }catch(Exception e)
        {
            throw new ApiRequestException(e.getMessage());
        }
    }
    /***************************************  Radar Stat   ****************************************/
    public Stat3Resp stat3(int dNbr ) {
        try{
            List<Commande> allCmd = commandeRepository.findAll();
            int enrg = 0 ,liv=0  , fact=0;
            for(int i = 0 ; i< allCmd.size() ; i++ ){
                if(allCmd.get(i).getDealer_Number().getLdbDealerNumber() == dNbr && allCmd.get(i).getEnregistree() == 1)
                    enrg++;
                if(allCmd.get(i).getDealer_Number().getLdbDealerNumber() == dNbr && allCmd.get(i).getLivree() == 1)
                    liv++;
                if(allCmd.get(i).getDealer_Number().getLdbDealerNumber() == dNbr && allCmd.get(i).getFacturee() == 1)
                    fact++;
            }
            return new Stat3Resp(enrg , liv , fact);

        }catch(Exception e)
        {
            throw new ApiRequestException(e.getMessage());
        }
    }
    /***************************************  Bar Stat top5Art   ****************************************/
    public Stat4Resp stat4(int dNbr ) {
        try{
            List<Commande> allCmd = commandeRepository.findAll();

            return new Stat4Resp();

        }catch(Exception e)
        {
            throw new ApiRequestException(e.getMessage());
        }
    }

}
