package com.orbit.portailAgentStockPR.devis.service;

import com.orbit.portailAgentStockPR.consulterStockPr.models.ArtMasters;
import com.orbit.portailAgentStockPR.consulterStockPr.service.ArtMastersRepository;
import com.orbit.portailAgentStockPR.devis.models.AjouterDevisRequest;
import com.orbit.portailAgentStockPR.devis.models.AjouterDevisResponse;
import com.orbit.portailAgentStockPR.devis.models.Devis;
import com.orbit.portailAgentStockPR.devis.models.LigneArticleRequest;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
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

    private Date dateHeurDevis(String pattern) throws ParseException {
        Date now = new Date();
        //String patternDate = "yyyy-MM-dd";
        //String patternHeure = "hh:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(now);
        return new SimpleDateFormat(pattern).parse(mysqlDateString);
    }
    public AjouterDevisResponse ajouterDevis(AjouterDevisRequest req)
    {
        try
        {
            int insertion = this.devisRepository.insertDevis(
                    req.getDealerNbr() ,
                    req.getNomClient() ,
                    req.getIdFisc() ,
                    req.getToRemise() ,
                    req.getToTaxes() ,
                    req.getTimbre(),
                    this.dateHeurDevis("yyyy-MM-dd hh:mm:ss")
            );

            List<Devis> listeDevis =  devisRepository.findAll();
            Devis devis = listeDevis.get(listeDevis.size()-1);
            int numDevis =1;
            for(int i =0;i<listeDevis.size();i++)
            {
                if(listeDevis.get(i).getNumDevis()>numDevis&&listeDevis.get(i).getDealer_Number().getLdbDealerNumber()==req.getDealerNbr())
                    devis=listeDevis.get(i) ;
            }

            AjouterDevisResponse resp = new AjouterDevisResponse();
            if(insertion == 1 )
            {
                resp.setInsertionError(false);
                resp.setNumDevis(devis.getNumDevis());
                resp.setDateCreation(devis.getDate_Creation());
            }

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
                return resp;
            else
            {
                resp.setInsertionError(true);
                return resp ;
            }
        }catch(Exception e)
        {
            throw new ApiRequestException(""+e);
        }
    }
}
