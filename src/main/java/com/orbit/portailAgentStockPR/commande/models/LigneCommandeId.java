package com.orbit.portailAgentStockPR.commande.models;

import com.orbit.portailAgentStockPR.consulterStockPr.models.ArtMasters;
import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;

import java.io.Serializable;

public class LigneCommandeId implements Serializable {

    private Dealers dealer_number;
    private int numCde ;
    private int numLigne ;
}
