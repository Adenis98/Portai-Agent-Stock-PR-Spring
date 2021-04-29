package com.orbit.portailAgentStockPR.devis.service;

import com.orbit.portailAgentStockPR.consulterStockPr.models.ArtMasters;
import com.orbit.portailAgentStockPR.consulterStockPr.service.ArtMastersRepository;
import com.orbit.portailAgentStockPR.devis.models.*;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
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
                    req.getDateCreation(),
                    this.dateHeurDevis("yyyy-MM-dd") ,
                    this.dateHeurDevis("hh:mm:ss"),
                    req.getTotHt(),
                    req.getTotTtc()
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
                        devis.getNumDevis() ,
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
    /***************************************************************************/
    public List< GetListeDevisResponse > getDevisList(int dNbr){
        try
        {
            List<GetListeDevisResponse> resp = new ArrayList<>() ;
            List<Devis >allDevis = devisRepository.findAll();
            for(int i = 0 ; i< allDevis.size();i++)
            {
                if(allDevis.get(i).getDealer_Number().getLdbDealerNumber() == dNbr)
                {
                    GetListeDevisResponse response = new GetListeDevisResponse();
                    response.setDealerNbr( allDevis.get(i).getDealer_Number().getLdbDealerNumber() );
                    response.setNumDevis(allDevis.get(i).getNumDevis()) ;
                    response.setNomClient(allDevis.get(i).getNomClient()) ;
                    response.setIdFisc(allDevis.get(i).getIdFisc());
                    response.setToRemise(allDevis.get(i).getToRemise());
                    response.setToTaxes(allDevis.get(i).getToTaxes());
                    response.setTimbre(allDevis.get(i).getTimbre());
                    response.setDate_Creation(allDevis.get(i).getDate_Creation());
                    response.setDate_Devis(allDevis.get(i).getDate_Devis());
                    response.setHeure_Devis(allDevis.get(i).getHeure_Devis());
                    response.setDate_Annulation(allDevis.get(i).getDate_Annulation());
                    response.setDate_Archivage(allDevis.get(i).getDate_Archivage()) ;

                    try{response.setTotHt(allDevis.get(i).getTotHt());      }catch(Exception e){ }
                    try{response.setTotTtc(allDevis.get(i).getTotTtc());    }catch(Exception e){ }
                    try{response.setAnnulee(allDevis.get(i).getAnnulee());  }catch(Exception e){ }
                    try{response.setArchivee(allDevis.get(i).getArchivee());}catch(Exception e){ }

                    resp.add(response);
                }

            }
            return resp ;
        }catch(Exception e)
        {
            throw new ApiRequestException(e.getMessage());
        }
    }

    /***************************************************************************/

    public OneDevisResponse getOneDevis(int numDevis) {
        try
        {
            List<GetListeDevisResponse> resp = new ArrayList<>() ;
            List<Devis >allDevis = devisRepository.findAll();
            OneDevisResponse oneDevisResp =new OneDevisResponse();
            for(int i = 0 ; i< allDevis.size();i++)
            {
                if(allDevis.get(i).getNumDevis() == numDevis)
                {
                    GetListeDevisResponse response = new GetListeDevisResponse();
                    List<GetLigneDevisResponse> ligneDevisL= new ArrayList<>();

                    response.setDealerNbr( allDevis.get(i).getDealer_Number().getLdbDealerNumber() );
                    response.setNumDevis(allDevis.get(i).getNumDevis()) ;
                    response.setNomClient(allDevis.get(i).getNomClient()) ;
                    response.setIdFisc(allDevis.get(i).getIdFisc());
                    response.setToRemise(allDevis.get(i).getToRemise());
                    response.setToTaxes(allDevis.get(i).getToTaxes());
                    response.setTimbre(allDevis.get(i).getTimbre());
                    response.setDate_Creation(allDevis.get(i).getDate_Creation());
                    response.setDate_Devis(allDevis.get(i).getDate_Devis());
                    response.setHeure_Devis(allDevis.get(i).getHeure_Devis());
                    response.setDate_Annulation(allDevis.get(i).getDate_Annulation());
                    response.setDate_Archivage(allDevis.get(i).getDate_Archivage()) ;

                    try{response.setTotHt(allDevis.get(i).getTotHt())      ;}catch(Exception e){ }
                    try{response.setTotTtc(allDevis.get(i).getTotTtc())    ;}catch(Exception e){ }
                    try{response.setAnnulee(allDevis.get(i).getAnnulee())  ;}catch(Exception e){ }
                    try{response.setArchivee(allDevis.get(i).getArchivee());}catch(Exception e){ }
                    List<LigneDevis> allLigneDevis = ligneDevisRepository.findAll();

                    for(int j=0 ; j< allLigneDevis.size();j++){
                        if(allLigneDevis.get(i).getNumDevis().getNumDevis() == numDevis)
                        {
                            GetLigneDevisResponse ligneDevisList = new GetLigneDevisResponse();
                            ligneDevisList.setdNbr(allLigneDevis.get(i).getNumDevis().getDealer_Number().getLdbDealerNumber());
                            ligneDevisList.setCodeArt(allLigneDevis.get(i).getCodeArt());
                            ligneDevisList.setLibelle(allLigneDevis.get(i).getLibelle());
                            ligneDevisList.setQte(allLigneDevis.get(i).getQte());
                            ligneDevisList.setPu(allLigneDevis.get(i).getPu());
                            ligneDevisList.setRemise(allLigneDevis.get(i).getRemise());
                            ligneDevisList.setTotLigne(allLigneDevis.get(i).getTotLigne());

                            ligneDevisL.add(ligneDevisList);
                        }
                    }
                    oneDevisResp =  new OneDevisResponse(response , ligneDevisL);
                    break ;
                }
            }
            return oneDevisResp;
        }catch(Exception e)
        {
            System.out.println("EXCEPTION MSG : "+e.getMessage());
            throw new ApiRequestException(e.getMessage());
        }
    }
}
