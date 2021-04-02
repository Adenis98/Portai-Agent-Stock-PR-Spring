package com.orbit.portailAgentStockPR.commande.models;

import com.orbit.portailAgentStockPR.consulterStockPr.models.Dealers;

import java.io.Serializable;

public class LigneCommandeId implements Serializable {

    private int numCde ;
    private Dealers dealer_Number ;

    private int numLigne ;
}
