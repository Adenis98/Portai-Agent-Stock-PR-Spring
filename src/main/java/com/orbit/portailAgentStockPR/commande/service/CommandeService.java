package com.orbit.portailAgentStockPR.commande.service;

import com.orbit.portailAgentStockPR.commande.models.Commande;
import com.orbit.portailAgentStockPR.commande.models.GetCommandeResponse;
import com.orbit.portailAgentStockPR.commande.models.LigneCommande;
import com.orbit.portailAgentStockPR.commande.models.PasserCommandeRequest;
import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;
import com.orbit.portailAgentStockPR.consulterStockPr.service.DealersRepository;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
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

    /************************************ PASSER COMMANDE ****************************************/


    private List<LigneCommande> ligneCommandeRepositoryList(List<LigneCommande> oldList,int dNbr,int typeCmd  )
    {
        List<LigneCommande> newList = new ArrayList<>();
        for(int i =0;i<oldList.size() ; i++ )
        {
            if(oldList.get(i).getNumCmnd().getNumCde() == 9999 &&
                    typeCmd == oldList.get(i).getType_Cmd() &&
                    oldList.get(i).getNumCmnd().getDealer_Number().getLdbDealerNumber() == dNbr
            )
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

    private int updateLigneCmd(int dNbr , int nCmd ,int typeCmd )
    {
        return ligneCommandeRepository.updateNcmd(dNbr , nCmd ,typeCmd);
    }

    private int nombreLigneCmdByDn(int dNbr )
    {
        List<LigneCommande> listLigneCmd = ligneCommandeRepository.findAll();
        int taille =0;
        for(int i = 0 ;i<listLigneCmd.size();i++)
        {
            if(listLigneCmd.get(i).getNumCmnd().getDealer_Number().getLdbDealerNumber() == dNbr &&
                listLigneCmd.get(i).getNumCmnd().getNumCde() == 9999
            )
                taille ++ ;
        }
        return taille ;
    }

    public int  passerCommande(PasserCommandeRequest req )
    {
        try
        {
            int res =0;
            List<LigneCommande> ligneCmdList =ligneCommandeRepositoryList(ligneCommandeRepository.findAll(),req.getDealerNumber(),req.getTypeCmd());
            if(req.getTypeCmd()==1&&ligneCmdList.size()==nombreLigneCmdByDn(req.getDealerNumber()))
            {
                System.out.println("*** COMMANDE FERME ***");
                //Commande ferme sans creation d'un autre ligne commande avec update Commande
                Commande newCmdFerme = existingCmd(req , ligneCmdList);
                //update the existing commande
                int pk = numCommandePasser()+10000000+1;
                res=commandeRepository.passerCommandeIns(
                        pk,
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
                //deleteAllLigneCommande(req.getDealerNumber(),ligneCommandeRepository.findAll());
                updateLigneCmd(req.getDealerNumber() , pk, req.getTypeCmd());

                commandeRepository.deleteCommandeNumCmd9999(req.getDealerNumber());
            }else if(req.getTypeCmd()==0&&ligneCmdList.size()==nombreLigneCmdByDn(req.getDealerNumber()))
            {
                System.out.println("*** COMMANDE NORMAL ***");
                //Commande normal sans creation d'un autre ligne commande avec update Commande
                Commande newCmdNormale = existingCmd(req , ligneCmdList);
                //update the existing commande
                int pk = numCommandePasser()+10000000+1 ;
                res=commandeRepository.passerCommandeIns(
                        pk,
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
                //deleteAllLigneCommande(req.getDealerNumber(),ligneCommandeRepository.findAll());
                int updLigneCmd = updateLigneCmd(req.getDealerNumber() , pk, req.getTypeCmd());
                int del = commandeRepository.deleteCommandeNumCmd9999(req.getDealerNumber());
            }else if(ligneCmdList.size()!=0)
            {
                System.out.println("*** COMMANDE NORMAL et FERME ***");
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
                int pk = numCommandePasser()+10000000+1;
                res=commandeRepository.passerCommandeIns(
                        pk,
                        0,
                        cmd.getTotHt(),
                        cmd.getDealer_Number().getLdbDealerNumber(),
                        cmd.getDate_Creation(),
                        cmd.getType_Cmd(),
                        cmd.getMode_Paiement(),
                        cmd.getRef_Cmd(),
                        cmd.getDate_Liv_S()
                );
                //deleteLigneCommandeType(req.getDealerNumber(),ligneCommandeRepository.findAll(),req.getTypeCmd());
                updateLigneCmd(req.getDealerNumber() , pk, req.getTypeCmd());
            }
            return res ;
        }catch(Exception e )
        {
            throw new ApiRequestException(e.getMessage());
        }
    }
    /*****************************************************************************************/
    public List<GetCommandeResponse> getCommande(int dNbr)
    {
        try
        {

            List<GetCommandeResponse> resList = new ArrayList<>() ;
            List<Commande> oldList = commandeRepository.findAll();
            for(int i = 0 ; i<oldList.size();i++)
            {
                if(oldList.get(i).getDealer_Number().getLdbDealerNumber() == dNbr)
                {
                    GetCommandeResponse rObj = new GetCommandeResponse();
                    rObj.setdNumber(oldList.get(i).getDealer_Number().getLdbDealerNumber());
                    rObj.setNumCde(oldList.get(i).getNumCde());
                    rObj.setPanier(oldList.get(i).getPanier());
                    rObj.setTotHt(oldList.get(i).getTotHt());
                    rObj.setDate_Creation(oldList.get(i).getDate_Creation());
                    rObj.setDate_Cmd(oldList.get(i).getDate_Cmd());
                    rObj.setHeure_Cmd(oldList.get(i).getHeure_Cmd());
                    rObj.setType_Cmd(oldList.get(i).getType_Cmd());
                    rObj.setMode_Paiement(oldList.get(i).getMode_Paiement());
                    rObj.setRef_Cmd(oldList.get(i).getRef_Cmd());

                    rObj.setRef_Enregistrement(oldList.get(i).getRef_Enregistrement());
                    rObj.setDate_Enregistrement(oldList.get(i).getDate_Enregistrement());

                    rObj.setDate_Liv(oldList.get(i).getDate_Liv());
                    rObj.setDate_Liv_S(oldList.get(i).getDate_Liv_S());

                    rObj.setDate_Facture(oldList.get(i).getDate_Facture());

                    rObj.setN_Facture(oldList.get(i).getN_Facture());

                    rObj.setDate_Annulation(oldList.get(i).getDate_Annulation());
                    rObj.setDate_Archivage(oldList.get(i).getDate_Archivage());
                    try{
                        rObj.setAnnulee(oldList.get(i).getAnnulee());
                        rObj.setArchivee(oldList.get(i).getArchivee());
                        rObj.setEnregistree(oldList.get(i).getEnregistree());
                        rObj.setFacturee(oldList.get(i).getFacturee());
                        rObj.setLivree(oldList.get(i).getLivree());
                        rObj.setMontant_Facture(oldList.get(i).getMontant_Facture());
                    }catch(Exception e){

                    }


                    resList.add(rObj);
                }
            }

            return resList ;
        }catch(Exception e ){
            throw new ApiRequestException(""+e);
        }
    }
    /*****************************************************************************************/
    public List<LigneCommande> getLigneCommande(int nCmd)
    {
        try
        {
            List<LigneCommande> listLigneCmd  = ligneCommandeRepository.findAll() ,listLigneCmdResp = new ArrayList<>();
            for(int i = 0 ; i< listLigneCmd.size() ; i++)
            {
                if(listLigneCmd.get(i).getNumCmnd().getNumCde() == nCmd)
                    listLigneCmdResp.add(listLigneCmd.get(i)) ;
            }
            return listLigneCmdResp ;
        }catch(Exception e )
        {
            throw new ApiRequestException(""+e);
        }
    }
}
