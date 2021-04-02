package com.orbit.portailAgentStockPR.DevisClasses;

import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;

import javax.persistence.Id;
import java.io.Serializable;

public class LigneDevisId implements Serializable {


    private Dealers dealer_Number ;
    private int numDevis ;
    private int numLigne ;
}
