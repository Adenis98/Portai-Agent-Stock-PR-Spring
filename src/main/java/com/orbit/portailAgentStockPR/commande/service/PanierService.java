package com.orbit.portailAgentStockPR.commande.service;

import com.orbit.portailAgentStockPR.commande.models.*;
import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;
import com.orbit.portailAgentStockPR.consulterStockPr.service.DealersRepository;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class PanierService {


    @Autowired
    LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    DealersRepository dealersRepository;
    @Autowired
    CommandeRepository commandeRepository;

    private double totHtCommande(List<LigneCommande> listLigneCom)
    {
        double result =0;
        for(int i = 0 ; i< listLigneCom.size();i++)
            result = result + listLigneCom.get(i).getTotLigneHt();
        return result ;
    }
    /*private LigneCommande ancienLigneCmd(List<LigneCommande> listLigneCom,int dealerNbr )
    {
        for(int i = 0 ; i< listLigneCom.size();i++)
            if(listLigneCom.get(i).getNumCmnd().getDealer_Number().getLdbDealerNumber() == dealerNbr)
                return listLigneCom.get(i);
        return null;
    }*/
    public LignePanierResponse insertLigneCommande(LignePanierRequest req )
    {
        try{
            List<LigneCommande> listeLigneExistant = ligneCommandeRepository.findAll() ;
            Commande cmd= new Commande();
            if(listeLigneExistant.size()>0)
            {
                cmd= listeLigneExistant.get(0).getNumCmnd();
                cmd.setTotHt(totHtCommande(listeLigneExistant)+req.getQte()*req.getPu());
                commandeRepository.updateTot(cmd.getTotHt(),cmd.getNumCde(),cmd.getDealer_Number().getLdbDealerNumber());
            }
            else{
                cmd.setPanier(-1);
                cmd.setTotHt(req.getQte()*req.getPu());
                Dealers d = dealersRepository.getOne(req.getDealerNumber());
                cmd.setDealer_Number(d);
                //************* datte de creation *************
                Date now = new Date();
                String pattern = "yyyy-MM-dd hh:mm:ss";
                SimpleDateFormat formatter = new SimpleDateFormat(pattern);
                String mysqlDateString = formatter.format(now);
                Date dateCreation = new SimpleDateFormat(pattern).parse(mysqlDateString);
                cmd.setDate_Creation(dateCreation);
                //************************************************
                commandeRepository.insertCommande(cmd.getPanier(),cmd.getTotHt(),cmd.getDealer_Number().getLdbDealerNumber(),cmd.getDate_Creation());
            }
            List<Commande> listCmd = commandeRepository.findAll();
            Commande lastElmnt = listCmd.get(listCmd.size()-1);
            LigneCommande ligne = new LigneCommande();
            ligne.setNumCmnd(cmd);
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
                    lastElmnt.getNumCde(),
                    ligne.getPu(),
                    ligne.getQte(),
                    ligne.getQteFacturee(),
                    ligne.getQteLivree(),
                    ligne.getTotLigneHt(),
                    ligne.getType_Cmd(),
                    ligne.getNumCmnd().getDealer_Number().getLdbDealerNumber(),
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
            throw new ApiRequestException("message d'erreur : "+e.getMessage());
        }
    }
    public void  deleteLigneCommande(LignePanierRequest req )
    {

    }


    //***********************************   GET PANIER API ***********************************
    private List<LigneCommande> findLigneCmndByDealerNbr(List<LigneCommande> all , int dealerNbr )
    {
        List<LigneCommande> newList = new ArrayList<>();
        for(int i=0;i< all.size();i++)
        {
            if(all.get(i).getNumCmnd().getDealer_Number().getLdbDealerNumber()==dealerNbr)
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
            throw new ApiRequestException("message d'erreur getPanier : "+e.getMessage());
        }
    }

    //*****************************************************************************
}
