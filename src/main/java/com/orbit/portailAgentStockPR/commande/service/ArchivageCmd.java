package com.orbit.portailAgentStockPR.commande.service;

import com.orbit.portailAgentStockPR.commande.models.Commande;
import com.orbit.portailAgentStockPR.exception.ApiRequestException;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.temporal.ChronoUnit;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@Component
public class ArchivageCmd {




        private final CommandeRepository commandeRepository  ;

        private Date dateHeurCmd(String pattern) throws ParseException {
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
        @Scheduled(cron = "0/5 * * ? * *")
        public void archiverCmd()
        {
            try
            {
                System.out.println("***** Scheduled ");
                List<Commande> allCmd = commandeRepository.findAll() ;
                for(int i = 0 ; i< allCmd.size();i++){


                    String hereCmd = allCmd.get(i).getHeure_Cmd().substring(11);
                    DateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
                    String dateCmd = dateFormat.format(allCmd.get(i).getDate_Cmd()).substring(0,10);
                    String dateHeureCmd = dateCmd+' '+hereCmd ;
                    Date dateToConvert=new SimpleDateFormat("yyyy-MM-dd hh:mm:ss").parse(dateHeureCmd);

                    long diff = ChronoUnit.DAYS.between(
                            LocalDateTime.now(),
                            dateToConvert.toInstant()
                                    .atZone(ZoneId.systemDefault())
                    );
                    System.out.println(allCmd.get(i).getNumCde()+"***"+dateHeureCmd+" = "+dateToConvert+"**"+Math.abs(diff));
                    if(Math.abs(diff)>=10&&allCmd.get(i).getArchivee()==0)
                        this.commandeRepository.archiverCmdUpd(allCmd.get(i).getNumCde(),dateHeurCmd("yyyy-MM-dd hh:mm:ss"));
                }
            }catch(Exception e){
                throw new ApiRequestException(""+e);
            }
        }


}
