package com.orbit.portailAgentStockPR.devis.service;

import com.orbit.portailAgentStockPR.consulterStockPr.models.ArtMasters;
import com.orbit.portailAgentStockPR.consulterStockPr.service.ArtMastersRepository;
import com.orbit.portailAgentStockPR.devis.models.AjouterDevisRequest;
import com.orbit.portailAgentStockPR.devis.models.Devis;
import com.orbit.portailAgentStockPR.devis.models.LigneArticleRequest;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class DevisService {

    @Autowired
    DevisRepository devisRepository ;

    @Autowired
    LigneDevisRepository ligneDevisRepository ;

    @Autowired
    ArtMastersRepository artMastersRepository;

    public int ajouterDevis(AjouterDevisRequest req)
    {
        try
        {
            int insertion = this.devisRepository.insertDevis(
                    req.getDealerNbr() ,
                    req.getNomClient() ,
                    req.getIdFisc() ,
                    req.getToRemise() ,
                    req.getToTaxes() ,
                    req.getTimbre()
            );
            List<Devis> listeDevis =  devisRepository.findAll();

            Devis numDevis = listeDevis.get(listeDevis.size()-1);

            int insertLigneDevisResp = 1;
            for(int i = 0 ; i<req.getListeArt().size() ; i++){
                LigneArticleRequest art =req.getListeArt().get(i);
                ArtMasters artMast = artMastersRepository.getOne(art.getCodArt()) ;

                insertLigneDevisResp *= this.ligneDevisRepository.insertLigneDevis(
                        art.getCodArt() ,
                        artMast.getLibelle(),
                        req.getToRemise() ,
                        artMast.getPu_agents() ,
                        art.getQte() ,
                        artMast.getPu_agents() * art.getQte() ,
                        numDevis.getNumDevis() ,
                        req.getDealerNbr()
                );
            }

            if(insertion !=0 && insertLigneDevisResp !=0)
                return 1;
            else
                return 0 ;
        }catch(Exception e)
        {
            throw new ApiRequestException(""+e);
        }
    }
}
