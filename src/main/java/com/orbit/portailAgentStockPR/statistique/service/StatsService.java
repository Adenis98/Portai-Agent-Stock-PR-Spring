package com.orbit.portailAgentStockPR.statistique.service;

import com.orbit.portailAgentStockPR.commande.models.Commande;
import com.orbit.portailAgentStockPR.commande.models.LigneCommande;
import com.orbit.portailAgentStockPR.commande.service.CommandeRepository;
import com.orbit.portailAgentStockPR.commande.service.LigneCommandeRepository;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import com.orbit.portailAgentStockPR.statistique.models.Stat1Resp;
import com.orbit.portailAgentStockPR.statistique.models.Stat2Resp;
import com.orbit.portailAgentStockPR.statistique.models.Stat3Resp;
import com.orbit.portailAgentStockPR.statistique.models.Stat4Resp;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

@Service
public class StatsService {

    @Autowired
    CommandeRepository commandeRepository ;
    @Autowired
    LigneCommandeRepository ligneCommandeRepository ;

    /***************************************  Ring Stat   ****************************************/
    public Stat1Resp stat1(int dNbr ) {
        try{
            List<Commande> allCmd = commandeRepository.findAll();
            int cmdFermeNbr = 0 ,cmdNormNbr=0 ;
            for(int i = 0 ; i< allCmd.size() ; i++ ){
                if(allCmd.get(i).getNumCde()!=9999&&allCmd.get(i).getDealer_Number().getLdbDealerNumber() == dNbr && allCmd.get(i).getType_Cmd() == 0)
                    cmdNormNbr++;
                else if(allCmd.get(i).getNumCde()!=9999&&allCmd.get(i).getDealer_Number().getLdbDealerNumber() == dNbr && allCmd.get(i).getType_Cmd() == 1)
                    cmdFermeNbr++;
            }
            return new Stat1Resp(cmdFermeNbr,cmdNormNbr);

        }catch(Exception e)
        {
            System.out.println("******************"+e);
            throw new ApiRequestException(e.getMessage());
        }
    }
    /***************************************  Bar Stat typeCmd/month   ****************************************/
    public Stat2Resp stat2(int dNbr ) {
        try{
            List<Commande> allCmd = commandeRepository.findAll();
            for(int i = 0 ; i< allCmd.size(); i ++){
                if(allCmd.get(i).getDealer_Number().getLdbDealerNumber() != dNbr){
                    allCmd.remove(i);
                    i--;
                }
            }
            List<Integer> dataCmdFerme = new ArrayList<>();
            List<Integer> dataCmdStock = new ArrayList<>();
            int numCmdFerme = 0  ;
            int numCmdStock = 0 ;
            for(int i = 0 ; i< 12 ; i++){
                numCmdFerme = 0;
                numCmdStock = 0 ;
                for(int j = 0 ; j< allCmd.size() ; j++){
                    if(allCmd.get(j).getNumCde()!=9999&&allCmd.get(j).getDate_Cmd().getMonth()==i&&allCmd.get(j).getType_Cmd()==1){
                        numCmdFerme++;
                    }else if(allCmd.get(j).getNumCde()!=9999&&allCmd.get(j).getDate_Cmd().getMonth()==i&&allCmd.get(j).getType_Cmd()==0){
                        numCmdStock++;
                    }
                }
                dataCmdFerme.add(numCmdFerme);
                dataCmdStock.add(numCmdStock);
            }

            return new Stat2Resp(dataCmdFerme,dataCmdStock);
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
                if(allCmd.get(i).getNumCde()!=9999&&allCmd.get(i).getDealer_Number().getLdbDealerNumber() == dNbr && allCmd.get(i).getEnregistree() == 1)
                    enrg++;
                if(allCmd.get(i).getNumCde()!=9999&&allCmd.get(i).getDealer_Number().getLdbDealerNumber() == dNbr && allCmd.get(i).getLivree() == 1)
                    liv++;
                if(allCmd.get(i).getNumCde()!=9999&&allCmd.get(i).getDealer_Number().getLdbDealerNumber() == dNbr && allCmd.get(i).getFacturee() == 1)
                    fact++;
            }
            return new Stat3Resp(enrg , liv , fact);

        }catch(Exception e)
        {
            throw new ApiRequestException(e.getMessage());
        }
    }
    /***************************************  Bar Stat top5Art   ****************************************/
    private int  maxOccurence(List<Integer> occurences){
        int max = occurences.get(0);
        int maxIndex = 0 ;
        for(int i = 0 ; i< occurences.size();i++){
            if(occurences.get(i)>max)
            {
                max = occurences.get(i);
                maxIndex =i ;
            }
        }
        return maxIndex ;
    }
    public List<Stat4Resp> stat4(int dNbr ) {
        try{
            List<LigneCommande> allLigneCmd = ligneCommandeRepository.findAll();
            System.out.println("taille : "+allLigneCmd.size());
            for(int i = 0 ; i< allLigneCmd.size(); i ++){
                if(allLigneCmd.get(i).getNumCmnd().getDealer_Number().getLdbDealerNumber() != dNbr){
                    allLigneCmd.remove(i);
                    i--;
                }

            }
            List<Integer> occurences = new ArrayList<>();
            int occ =0;
            System.out.println("taille : "+allLigneCmd.size());
            for(int i=0 ; i< allLigneCmd.size();i++){
                occ = 1 ;
                for(int j=i+1;j<allLigneCmd.size();j++){
                    String firstCodArt = allLigneCmd.get(i).getCodeArt() ;
                    if(allLigneCmd.get(j).getCodeArt().equals(firstCodArt)){
                        occ++;
                        allLigneCmd.remove(allLigneCmd.get(j));
                        j--;
                    }
                }
                occurences.add(occ);
            }
            List<Stat4Resp> top5LigneCmd = new ArrayList<>();
            System.out.println("taille : "+allLigneCmd.size());
            int nbPiece=5;
            if(allLigneCmd.size()<5)
                nbPiece=allLigneCmd.size();
            for(int i = 1 ; i<=nbPiece;i++){
                int maxIndex = maxOccurence(occurences);
                top5LigneCmd.add(new Stat4Resp(allLigneCmd.get(maxIndex).getCodeArt(),occurences.get(maxIndex)));
                occurences.remove(maxIndex);
                allLigneCmd.remove(maxIndex);
            }
            System.out.println("taille : "+allLigneCmd.size());
            return top5LigneCmd;
        }catch(Exception e)
        {
            System.out.println(e.getMessage()+"<= message");
            throw new ApiRequestException(e.getMessage());
        }
    }

}
