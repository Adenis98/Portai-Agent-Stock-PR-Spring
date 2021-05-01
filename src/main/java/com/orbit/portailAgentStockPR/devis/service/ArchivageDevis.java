package com.orbit.portailAgentStockPR.devis.service;

import com.orbit.portailAgentStockPR.devis.models.Devis;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;
@AllArgsConstructor
@Component
public class ArchivageDevis {

    private final DevisRepository devisRepository  ;

    private Date dateHeurDevis(String pattern) throws ParseException {
        Date now = new Date();
        //String patternDate = "yyyy-MM-dd";
        //String patternHeure = "hh:mm:ss";
        SimpleDateFormat formatter = new SimpleDateFormat(pattern);
        String mysqlDateString = formatter.format(now);
        return new SimpleDateFormat(pattern).parse(mysqlDateString);
    }
    /*********************************** Archivage *************************************/

    /*
    * every 5 minutes : "0 0/15 * * * *"
    * every 5 seconds : "0/5 * * ? * *"
    * every 10 minutes: "0 0/10 * * * *"
    *  */
    @Scheduled(cron = "0 0/10 * * * *")
    public void archiverDevis()
    {
        try
        {
            System.out.println("***** Scheduled ");
            List<Devis> allDevis = devisRepository.findAll() ;
            for(int i = 0 ; i< allDevis.size();i++){


                String hereDevis = allDevis.get(i).getHeure_Devis().substring(11);
                DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                String dateDevis = dateFormat.format(allDevis.get(i).getDate_Devis()).substring(0,10);
                String dateHeureDevis = dateDevis+' '+hereDevis ;
                Date dateToConvert=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateHeureDevis);

                long diff = ChronoUnit.MINUTES.between(
                        LocalDateTime.now(),
                        dateToConvert.toInstant()
                                .atZone(ZoneId.systemDefault())
                );
                if(Math.abs(diff)>=15&&allDevis.get(i).getArchivee()==0)
                    this.devisRepository.archiverCmdUpd(allDevis.get(i).getNumDevis(),dateHeurDevis("yyyy-MM-dd hh:mm:ss"));
            }
        }catch(Exception e){
            throw new ApiRequestException(""+e);
        }
    }
}
