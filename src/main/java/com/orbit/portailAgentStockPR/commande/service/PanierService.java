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
            List<LigneCommande> listeLigneExistant = ligneCommandeRepository.findAll() ;
            Commande cmd= new Commande();
            if(listeLigneExistant.size()>0)
                cmd= listeLigneExistant.get(0).getNumCmnd();
            else{
                cmd.setPanier(-1);
                cmd.setToHt(1695.7);
                Dealers d = dealersRepository.getOne(req.getDealerNumber());
                cmd.setDealer_Number(d);
                //commandeRepository.save(cmd);
                int result = commandeRepository.insertCommande(cmd.getPanier(),cmd.getToHt(),cmd.getDealer_Number().getLdbDealerNumber());
                System.out.println("****"+result);
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

            ligneCommandeRepository.insertPanier(ligne.getNumCmnd().getNumCde(),ligne.getPu(),ligne.getQte(),ligne.getQteFacturee(),ligne.getQteLivree(),ligne.getTotLigneHt(),ligne.getType_Cmd(),ligne.getNumCmnd().getDealer_Number().getLdbDealerNumber());

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
