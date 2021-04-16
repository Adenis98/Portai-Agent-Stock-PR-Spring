package com.orbit.portailAgentStockPR.commande.service;

import com.orbit.portailAgentStockPR.commande.models.*;
import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;
import com.orbit.portailAgentStockPR.consulterStockPr.service.DealersRepository;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.hibernate.exception.ConstraintViolationException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.*;

@Service
public class PanierService {


    @Autowired
    LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    DealersRepository dealersRepository;
    @Autowired
    CommandeRepository commandeRepository;

    //**************************** Ajouter Ligne PANIER  ********************************
    private double totHtCommande(List<LigneCommande> listLigneCom,int dealerNbr)
    {
        double result =0;
        for(int i = 0 ; i< listLigneCom.size();i++)
            if(listLigneCom.get(i).getNumCmnd().getDealer_Number().getLdbDealerNumber()==dealerNbr &&
                    listLigneCom.get(i).getNumCmnd().getNumCde() == 9999)
                result = result + listLigneCom.get(i).getTotLigneHt();
        return result ;
    }
    private Commande ancienLigneCmd(int dealerNbr )
    {
        List<Commande> listLigneCom = commandeRepository.findAll();
        for(int i = 0 ; i< listLigneCom.size();i++)
            if(listLigneCom.get(i).getDealer_Number().getLdbDealerNumber() == dealerNbr && listLigneCom.get(i).getNumCde()==9999)
                return listLigneCom.get(i);
        return null;
    }

    public LignePanierResponse insertLigneCommande(LignePanierRequest req )
    {
        try{
            int pk = -1 ;
            List<LigneCommande> listeLigneExistant = ligneCommandeRepository.findAll() ;
            Commande cmd= new Commande();
            Commande oldCmdLigne = ancienLigneCmd(req.getDealerNumber());
            for(int i =0; i< listeLigneExistant.size() ; i++)
            {
                if(listeLigneExistant.get(i).getCodeArt().equals(req.getCodeArt())  &&
                        req.getDealerNumber()==listeLigneExistant.get(i).getNumCmnd().getDealer_Number().getLdbDealerNumber()  &&
                        listeLigneExistant.get(i).getNumCmnd().getNumCde() == 9999
                )
                    throw new ApiRequestException("article existe dèja !!");
            }
            if(oldCmdLigne!=null)
            {
                pk = oldCmdLigne.getNumCde() ;
                cmd= oldCmdLigne;
                cmd.setTotHt(totHtCommande(listeLigneExistant,req.getDealerNumber())+req.getQte()*req.getPu());
                int res = commandeRepository.updateTot(cmd.getTotHt(),cmd.getNumCde(),cmd.getDealer_Number().getLdbDealerNumber());
            }
            else{
                pk = 9999 ;
                cmd.setPanier(-1);
                cmd.setTotHt(req.getQte()*req.getPu());
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
                commandeRepository.insertCommande(9999,cmd.getPanier(),cmd.getTotHt(),cmd.getDealer_Number().getLdbDealerNumber(),cmd.getDate_Creation());
                List<Commande> cmndList= commandeRepository.findAll();
                cmd = cmndList.get(cmndList.size()-1);
            }

            LigneCommande ligne = new LigneCommande();
            ligne.setCodeArt(req.getCodeArt());
            ligne.setQte(req.getQte());
            ligne.setPu(req.getPu());
            ligne.setType_Cmd(req.getType_Cmd());
            ligne.setVin(req.getVin());
            ligne.setNumInterv(req.getNumInterv());
            ligne.setNomClient(req.getNomClient());
            ligne.setTotLigneHt(req.getQte()*req.getPu());
            ligne.setLibelle(req.getLibelle());

            ligneCommandeRepository.insertPanier(
                    pk,
                    ligne.getPu(),
                    ligne.getQte(),
                    ligne.getQteFacturee(),
                    ligne.getQteLivree(),
                    ligne.getTotLigneHt(),
                    ligne.getType_Cmd(),
                    cmd.getDealer_Number().getLdbDealerNumber(),
                    ligne.getVin(),
                    ligne.getLibelle(),
                    ligne.getNomClient(),
                    ligne.getNumInterv(),
                    ligne.getCodeArt()
            );
            LignePanierResponse resp =  new LignePanierResponse();
            resp.setRetMsg("Ajouté avec succès !!");
            resp.setRetCd(0);
            return resp ;
        }catch(Exception e){
            throw new ApiRequestException(""+e.getMessage());
        }
    }
    //******************************************************************************
    //**************************** GET Ligne PANIER  ********************************
    private List<LigneCommande> findLigneCmndByDealerNbr(List<LigneCommande> all , int dealerNbr )
    {
        List<LigneCommande> newList = new ArrayList<>();
        for(int i=0;i< all.size();i++)
        {
            if(all.get(i).getNumCmnd().getDealer_Number().getLdbDealerNumber()==dealerNbr && all.get(i).getNumCmnd().getNumCde()==9999)
                newList.add(all.get(i));
        }
        return newList ;
    }
    private List<GetLignePanier>  createResponseList(List<LigneCommande> newList)
    {
        List<GetLignePanier> respList = new ArrayList<>();
        for(int i=0;i< newList.size();i++)
        {
            GetLignePanier ligneGet = new GetLignePanier();
            ligneGet.setLibelle(newList.get(i).getLibelle());
            ligneGet.setCodeArt(newList.get(i).getCodeArt());
            ligneGet.setNumLigne(newList.get(i).getNumLigne());
            ligneGet.setTotLigneHt(newList.get(i).getTotLigneHt());
            ligneGet.setNomClient(newList.get(i).getNomClient());
            ligneGet.setPu(newList.get(i).getPu());
            ligneGet.setNumInterv(newList.get(i).getNumInterv());
            ligneGet.setVin(newList.get(i).getVin());
            ligneGet.setQte(newList.get(i).getQte());
            ligneGet.setTypeCmd(newList.get(i).getType_Cmd());
            respList.add(ligneGet);
        }
        return respList ;
    }
    public GetPanierWsResponse  getPanier(int dealerNbr )
    {
        try
        {
            List<LigneCommande> newList = findLigneCmndByDealerNbr(ligneCommandeRepository.findAll(),dealerNbr);

            GetPanierWsResponse resp = new GetPanierWsResponse();
            //**********************declaration des variables*********************
            Date dateCreation = new Date();
            double totHt=0;
            int retCd = 1 ;
            String message = "Panier vide";
            if(newList.size()>0)
            {
                dateCreation= newList.get(0).getNumCmnd().getDate_Creation();
                totHt=newList.get(0).getNumCmnd().getTotHt();
                retCd = 0 ;
                message = "Panier non vide";
            }
            //********************************************************************
            resp.setdNbr(dealerNbr);
            resp.setDateCreation(dateCreation);
            resp.setRetMsg(message);
            resp.setRetCd(retCd);
            resp.setTotHt(totHt);
            resp.setLignesPanier(createResponseList(newList));
            return resp ;
        }
        catch(Exception e)
        {
            throw new ApiRequestException(e+" | "+e.getMessage());
        }
    }
    //********************************************************************************
    //************************** Supprimer ligne PANIER ******************************
    private int numLigneCmdEncours(int dNbr )
    {
        List<LigneCommande> listLigneCmd = ligneCommandeRepository.findAll();
        int res = 0;
        for(int i=0 ; i< listLigneCmd.size() ; i++)
        {
            if(listLigneCmd.get(i).getNumCmnd().getNumCde() == 9999 && listLigneCmd.get(i).getNumCmnd().getDealer_Number().getLdbDealerNumber() == dNbr)
                res ++ ;
        }
        return res ;
    }
    public String supprimerLigne(int lignePanier)
    {
        try{
            List<LigneCommande> listeLigne = ligneCommandeRepository.findAll();
            Iterator<LigneCommande> it = listeLigne.iterator();
            LigneCommande ligneCom =null;
            while(it.hasNext())
            {
                LigneCommande ligne = it.next() ;
                if(ligne.getNumLigne() == lignePanier )
                    ligneCom = ligne ;
            }
            Commande cmd = ligneCom.getNumCmnd() ;
            int res = ligneCommandeRepository.deleteLignePanier(lignePanier);
            if(res==1 )
            {
                double newTot = cmd.getTotHt()-ligneCom.getTotLigneHt();
                if(numLigneCmdEncours(cmd.getDealer_Number().getLdbDealerNumber())>0)
                {
                    //update ligne commande
                    cmd.setTotHt(newTot);
                    commandeRepository.updateTot(cmd.getTotHt(),cmd.getNumCde(),cmd.getDealer_Number().getLdbDealerNumber());
                }else
                    commandeRepository.deleteCommande(cmd.getDealer_Number().getLdbDealerNumber());
                return "supprimé avec succès ";
            }
            else
                return "sppression impossible !! ";
        }catch(Exception e )
        {
            throw  new ApiRequestException(""+e);
        }
    }
    //********************************************************************************
}
