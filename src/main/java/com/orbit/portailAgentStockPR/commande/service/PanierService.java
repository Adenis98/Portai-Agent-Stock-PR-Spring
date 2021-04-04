package com.orbit.portailAgentStockPR.commande.service;

import com.orbit.portailAgentStockPR.commande.models.Commande;
import com.orbit.portailAgentStockPR.commande.models.LigneCommande;
import com.orbit.portailAgentStockPR.commande.models.LignePanierRequest;
import com.orbit.portailAgentStockPR.commande.models.LignePanierResponse;
import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;
import com.orbit.portailAgentStockPR.consulterStockPr.service.DealersRepository;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.text.SimpleDateFormat;
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

    public LignePanierResponse insertLigneCommande(LignePanierRequest req )
    {
        try{
            int result=-999;
            List<LigneCommande> listeLigneExistant = ligneCommandeRepository.findAll() ;
            Commande cmd= new Commande();
            if(listeLigneExistant.size()>0)
            {
                cmd= listeLigneExistant.get(0).getNumCmnd();
                //mise a jours
                // tot_ht
            }
            else{
                cmd.setPanier(-1);
                cmd.setTotHt(1695.7);
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
                result = commandeRepository.insertCommande(cmd.getPanier(),cmd.getTotHt(),cmd.getDealer_Number().getLdbDealerNumber(),cmd.getDate_Creation());
            }

            LigneCommande ligne = new LigneCommande();
            ligne.setNumCmnd(cmd);
            ligne.setCodeArt(req.getCodeArt());
            ligne.setQte(req.getQte());
            ligne.setPu(req.getPu());
            ligne.setType_Cmd(req.getType_Cmd());
            ligne.setVin(req.getVin());
            ligne.setNumInterv(req.getNumInterv());
            ligne.setNomClient(req.getNomClient());
            System.out.println("*****"+ligne.getNumCmnd().getNumCde()+"*****"+result);
            ligneCommandeRepository.insertPanier(1,ligne.getPu(),ligne.getQte(),ligne.getQteFacturee(),ligne.getQteLivree(),ligne.getTotLigneHt(),ligne.getType_Cmd(),ligne.getNumCmnd().getDealer_Number().getLdbDealerNumber());

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
}
