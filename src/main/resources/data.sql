INSERT INTO dealers (ldb_dealer_number ,dealer_name  , dealer_phone_no  , sales_man )VALUES (001,"COMPTOIR CC","128787","CL000001")  ;
INSERT INTO dealers (ldb_dealer_number ,dealer_name  , dealer_phone_no  , sales_man )VALUES (002,"ATELIER CC","11878","CL000001")  ;
INSERT INTO dealers (ldb_dealer_number ,dealer_name  , dealer_phone_no  , sales_man )VALUES (095,"GARANTIE CC","13278","CL000001")  ;
INSERT INTO dealers (ldb_dealer_number ,dealer_name  , dealer_phone_no  , sales_man )VALUES (004,"GARANTIE AGENCE","224578","CL000001")  ;

INSERT INTO art_masters (cod_art, h, ht, htg, libelle , pu_agents, remisable) VALUES ("1C0121253A","","","","REFR. EAU",547.47,0) ;
INSERT INTO art_masters (cod_art, h, ht, htg, libelle , pu_agents, remisable) VALUES ("1C0121253C","","","","REFR. EAU",1015.87,0)  ;
INSERT INTO art_masters (cod_art, h, ht, htg, libelle , pu_agents, remisable) VALUES ("5F1955426","","","","BALAIESSUI",53.29,0)  ;
INSERT INTO art_masters (cod_art, h, ht, htg, libelle , pu_agents, remisable) VALUES ("AAE8111500","","","","DEMO",0.001,0)  ;

INSERT INTO dealer_stock (cod_art, dealer_number, qte_achat, stock, ug) VALUES ('1C0121253A',95,52,20,1) ;
INSERT INTO dealer_stock (cod_art, dealer_number, qte_achat, stock, ug) VALUES ('1C0121253C',95,52,6,1) ;
INSERT INTO dealer_stock (cod_art, dealer_number, qte_achat, stock, ug) VALUES ('5F1955426',95,52,0,1) ;


/*inserer les ligne de dealerStock */
/*INSERT INTO dealer_stock (cod_art, dealer_number, qte_achat, stock, ug) VALUES ('1C0121253A',95,52,20,1) ;
INSERT INTO dealer_stock (cod_art, dealer_number, qte_achat, stock, ug) VALUES ('1C0121253C',95,52,6,1) ;
INSERT INTO dealer_stock (cod_art, dealer_number, qte_achat, stock, ug) VALUES ('5F1955426',95,52,0,1) ;*/
/* heroku*/
/*INSERT INTO s_dealer_modules (code, dealer_number,  password, permis, user_name) VALUES ('0',95,'zormati',2,'ramez') ;*/
/*inserer les ligne de ArtMasters */
/*INSERT INTO art_masters (cod_art, h, ht, htg, libelle , pu_agents, remisable) VALUES ("1C0121253A","","","","REFR. EAU",547.47,0) ;
INSERT INTO art_masters (cod_art, h, ht, htg, libelle , pu_agents, remisable) VALUES ("1C0121253C","","","","REFR. EAU",1015.87,0)  ;
INSERT INTO art_masters (cod_art, h, ht, htg, libelle , pu_agents, remisable) VALUES ("5F1955426","","","","BALAIESSUI",53.29,0)  ;
INSERT INTO art_masters (cod_art, h, ht, htg, libelle , pu_agents, remisable) VALUES ("AAE8111500","","","","DEMO",0.001,0)  ;*/

/*inserer les ligne de Dealers */

/*INSERT INTO dealers*/ /*(ldb_dealer_number ,dealer_name  , dealer_phone_no  , sales_man )VALUES (00,"AGENCE KRAM","","CL000001")  ;
INSERT INTO dealers (ldb_dealer_number ,dealer_name  , dealer_phone_no  , sales_man )VALUES (001,"COMPTOIR CC","","CL000001")  ;
INSERT INTO dealers (ldb_dealer_number ,dealer_name  , dealer_phone_no  , sales_man )VALUES (002,"ATELIER CC","","CL000001")  ;
INSERT INTO dealers (ldb_dealer_number ,dealer_name  , dealer_phone_no  , sales_man )VALUES (003,"GARANTIE CC","","CL000001")  ;
INSERT INTO dealers (ldb_dealer_number ,dealer_name  , dealer_phone_no  , sales_man )VALUES (004,"GARANTIE AGENCE","","CL000001")  ;
INSERT INTO dealers (ldb_dealer_number ,dealer_name  , dealer_phone_no  , sales_man )VALUES (95,"AGENCE CHARGUIA","","CL000078")  ;*/

/*inserer les ligne Commande */
/*INSERT INTO commande (   date_creation, enregistree, livree, panier, to_ht, dealer_number)VALUES('2020-08-11',0,0,-1,1695.7,95) ;*/