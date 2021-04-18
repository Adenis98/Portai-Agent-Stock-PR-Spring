package com.orbit.portailAgentStockPR.interAgent.models;

import com.orbit.portailAgentStockPR.consulterStockPr.models.DealerStock;

import java.util.List;

public class GetAllAgentsStockResponse {
    private String dealerName ;
    private String dealerPhoneNumber ;
    private List<DealerStockList>  dealerStockList ;

    public GetAllAgentsStockResponse() {
    }

    public String getDealerName() {
        return dealerName;
    }

    public void setDealerName(String dealerName) {
        this.dealerName = dealerName;
    }

    public String getDealerPhoneNumber() {
        return dealerPhoneNumber;
    }

    public void setDealerPhoneNumber(String dealerPhoneNumber) {
        this.dealerPhoneNumber = dealerPhoneNumber;
    }

    public List<DealerStockList>  getDealerStockList() {
        return dealerStockList;
    }

    public void setDealerStockList(List<DealerStockList>  dealerStockList) {
        this.dealerStockList = dealerStockList;
    }
}
