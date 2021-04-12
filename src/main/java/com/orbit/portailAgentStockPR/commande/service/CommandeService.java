package com.orbit.portailAgentStockPR.commande.service;

import com.orbit.portailAgentStockPR.commande.models.Commande;
import com.orbit.portailAgentStockPR.commande.models.LigneCommande;
import com.orbit.portailAgentStockPR.commande.models.PasserCommandeRequest;
import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;
import com.orbit.portailAgentStockPR.consulterStockPr.service.DealersRepository;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Service
public class CommandeService {

    @Autowired
    CommandeRepository commandeRepository ;

    @Autowired
    LigneCommandeRepository ligneCommandeRepository  ;

    @Autowired
    DealersRepository dealersRepository ;
    private List<LigneCommande> ligneCommandeRepositoryList(List<LigneCommande> oldList,int dNbr,int typeCmd  )
    {
        List<LigneCommande> newList = new ArrayList<>();
        for(int i =0;i<oldList.size() ; i++ )
        {
            if(oldList.get(i).getNumCmnd().getDealer_Number().getLdbDealerNumber() == dNbr&&typeCmd == oldList.get(i).getType_Cmd())
                newList.add(oldList.get(i));

        }
        return newList ;
    }

    private int numCommandePasser()
    {
        List<Commande> existingCmd = commandeRepository.findAll();
        int  j = 0;
        for(int i = 0 ; i< existingCmd.size() ; i++ )
        {
            if(existingCmd.get(i).getNumCde()!=9999)
                j++;
        }
        return j;
    }
    private Commande existingCmd(PasserCommandeRequest req,List<LigneCommande> ligneCmdList)
    {
        Commande newCmd = ligneCmdList.get(0).getNumCmnd();
        newCmd.setType_Cmd(req.getTypeCmd());
        newCmd.setMode_Paiement(req.getModePaiement());
        newCmd.setRef_Cmd(req.getRefCmd());
        newCmd.setDate_Liv_S(req.getDateLivS());
        newCmd.setPanier(0);
        //newCmd.setNumCde(numCommandePasser());
        return newCmd;
    }
    private int deleteAllLigneCommande(int dNbr , List<LigneCommande> listLigneCmd)
    {
        int ligneSupprimee =0;
        for(int i = 0 ; i<listLigneCmd.size();i++)
        {
            if(listLigneCmd.get(i).getNumCmnd().getDealer_Number().getLdbDealerNumber()==dNbr)
                ligneSupprimee+=ligneCommandeRepository.deleteLignePanier(listLigneCmd.get(i).getNumLigne());
        }
        return ligneSupprimee;
    }
    private int deleteLigneCommandeType(int dNbr , List<LigneCommande> listLigneCmd,int typeCmd)
    {
        int ligneSupprimee =0;
        for(int i = 0 ; i<listLigneCmd.size();i++)
        {
            if(listLigneCmd.get(i).getNumCmnd().getDealer_Number().getLdbDealerNumber()==dNbr && typeCmd ==listLigneCmd.get(i).getType_Cmd() )
                ligneSupprimee+=ligneCommandeRepository.deleteLignePanier(listLigneCmd.get(i).getNumLigne());
        }
        return ligneSupprimee;
    }
    public int  passerCommande(PasserCommandeRequest req )
    {
        try
        {
            int res =0;
            List<LigneCommande> ligneCmdList =ligneCommandeRepositoryList(ligneCommandeRepository.findAll(),req.getDealerNumber(),req.getTypeCmd());

            if(req.getTypeCmd()==1&&ligneCmdList.size()==ligneCommandeRepository.findAll().size())
            {
                //Commande ferme sans creation d'un autre ligne commande avec update Commande
                Commande newCmdFerme = existingCmd(req , ligneCmdList);
                //update the existing commande

                res=commandeRepository.passerCommandeIns(
                        numCommandePasser()+1,
                        newCmdFerme.getPanier(),
                        newCmdFerme.getTotHt(),
                        newCmdFerme.getDealer_Number().getLdbDealerNumber(),
                        newCmdFerme.getDate_Creation(),
                        newCmdFerme.getType_Cmd(),
                        newCmdFerme.getMode_Paiement(),
                        newCmdFerme.getRef_Cmd(),
                        newCmdFerme.getDate_Liv_S()
                );
                //delete all "ligne commande"
                deleteAllLigneCommande(req.getDealerNumber(),ligneCommandeRepository.findAll());
                commandeRepository.deleteCommandeNumCmd9999(req.getDealerNumber());
            }else if(req.getTypeCmd()==2&&ligneCmdList.size()==ligneCommandeRepository.findAll().size())
            {
                //Commande normal sans creation d'un autre ligne commande avec update Commande
                Commande newCmdNormale = existingCmd(req , ligneCmdList);
                //update the existing commande

                res=commandeRepository.passerCommandeIns(
                        numCommandePasser()+1,
                        0,
                        newCmdNormale.getTotHt(),
                        newCmdNormale.getDealer_Number().getLdbDealerNumber(),
                        newCmdNormale.getDate_Creation(),
                        newCmdNormale.getType_Cmd(),
                        newCmdNormale.getMode_Paiement(),
                        newCmdNormale.getRef_Cmd(),
                        newCmdNormale.getDate_Liv_S()
                );
                //delete all "ligne commande"
                deleteAllLigneCommande(req.getDealerNumber(),ligneCommandeRepository.findAll());
                commandeRepository.deleteCommandeNumCmd9999(req.getDealerNumber());
            }else if(ligneCmdList.size()!=0)
            {
                //creation d'un nouveau ligne commande avec changement du clé étrangère des ligne commande
                Commande cmd = new Commande();
                cmd = existingCmd(req , ligneCmdList);
                cmd.setPanier(-1);
                cmd.setTotHt(00);
                Dealers d = dealersRepository.getOne(req.getDealerNumber());
                cmd.setDealer_Number(d);
                //************* date de creation *************
                Date now = new Date();
                String pattern = "yyyy-MM-dd hh:mm:ss";
                SimpleDateFormat formatter = new SimpleDateFormat(pattern);
                String mysqlDateString = formatter.format(now);
                Date dateCreation = new SimpleDateFormat(pattern).parse(mysqlDateString);
                cmd.setDate_Creation(dateCreation);
                //************************************************
                res=commandeRepository.passerCommandeIns(
                        numCommandePasser()+1,
                        cmd.getPanier(),
                        cmd.getTotHt(),
                        cmd.getDealer_Number().getLdbDealerNumber(),
                        cmd.getDate_Creation(),
                        cmd.getType_Cmd(),
                        cmd.getMode_Paiement(),
                        cmd.getRef_Cmd(),
                        cmd.getDate_Liv_S()
                );
                deleteAllLigneCommande(req.getDealerNumber(),ligneCommandeRepository.findAll());
            }
            return res ;
        }catch(Exception e )
        {
            throw new ApiRequestException(e.getMessage());
        }
    }
}
