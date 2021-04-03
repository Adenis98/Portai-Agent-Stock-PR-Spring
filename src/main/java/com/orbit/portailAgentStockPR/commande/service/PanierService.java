package com.orbit.portailAgentStockPR.commande.service;

import com.orbit.portailAgentStockPR.commande.models.LigneCommande;
import com.orbit.portailAgentStockPR.commande.models.LignePanierRequest;
import com.orbit.portailAgentStockPR.commande.models.LignePanierResponse;
import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;
import com.orbit.portailAgentStockPR.consulterStockPr.service.DealersRepository;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PanierService {


    @Autowired
    LigneCommandeRepository ligneCommandeRepository;
    @Autowired
    DealersRepository dealersRepository;

    public LignePanierResponse insertLigneCommande(LignePanierRequest req )
    {
        try{
            List<LigneCommande> listeLigneExistant = ligneCommandeRepository.findAll() ;
            int numCmd=999;
            if(listeLigneExistant.size()>0)
                numCmd= listeLigneExistant.get(0).getNumCde();

            LigneCommande ligne = new LigneCommande();
            ligne.setNumLigne(415);
            ligne.setNumCde(numCmd);
            Dealers dealer = dealersRepository.getOne(req.getDealerNumber());
            ligne.setDealer_number(dealer);
            ligne.setCodeArt(req.getCodeArt());
            ligne.setQte(req.getQte());
            ligne.setPu(req.getPu());
            ligne.setType_Cmd(req.getType_Cmd());
            ligne.setVin(req.getVin());
            ligne.setNumInterv(req.getNumInterv());
            ligne.setNomClient(req.getNomClient());

            ligneCommandeRepository.insertPanier(ligne.getNumCde(),ligne.getNumLigne(),ligne.getPu(),ligne.getQte(),ligne.getQteFacturee(),ligne.getQteLivree(),ligne.getTotLigneHt(),ligne.getType_Cmd(),ligne.getDealer_number().getLdbDealerNumber());

            LignePanierResponse resp =  new LignePanierResponse();
            resp.setRetMsg("Ajouté avec succès !!");
            resp.setRetCd(0);
            return resp ;
        }catch(Exception e){
            throw new ApiRequestException(e.toString()+"*/*//*/**/*/*//*/*: "+e.getMessage());
        }
    }
    public void  deleteLigneCommande(LignePanierRequest req )
    {

    }
}
