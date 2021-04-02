package com.orbit.portailAgentStockPR.DevisClasses;

import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;

import javax.persistence.Id;
import java.io.Serializable;

public class DevisId implements Serializable {
    private Dealers dealer_Number ;
    private int numDevis ;
}
