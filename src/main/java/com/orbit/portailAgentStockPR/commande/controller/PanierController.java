package com.orbit.portailAgentStockPR.commande.controller;


import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(path="/panier")
public class PanierController {


    @PostMapping("insertLignePanierWS")
    public void insertLignePanierWS(){

    }

    @GetMapping("GetPanierWS")
    public void GetPanierWS(){

    }
}
