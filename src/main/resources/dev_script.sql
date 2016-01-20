-- ---------------------------
-- Table VILLE
-- ---------------------------
INSERT INTO VILLE(VIL_NOM, VIL_CP, VIL_PAY_ID) VALUES
('Lille','59650',1);
INSERT INTO VILLE(VIL_NOM, VIL_CP, VIL_PAY_ID) VALUES
('Reims','51100',1);
INSERT INTO VILLE(VIL_NOM, VIL_CP, VIL_PAY_ID) VALUES
('Cayeux-sur-mer','80410',1);
INSERT INTO VILLE(VIL_NOM, VIL_CP, VIL_PAY_ID) VALUES
('Groléjac','24250',1);
INSERT INTO VILLE(VIL_NOM, VIL_CP, VIL_PAY_ID) VALUES
('Quimper','29000',1);

-- ---------------------------
-- Table ADRESSE
-- ---------------------------
INSERT INTO ADRESSE(adr_voie, adr_vil_id) VALUES
('45 Rue des sacres',1);
INSERT INTO ADRESSE(adr_voie, adr_vil_id) VALUES
('21 Avenue des champs',2);
INSERT INTO ADRESSE(adr_voie, adr_vil_id) VALUES
('54 Rue de la gare',3);
INSERT INTO ADRESSE(adr_voie, adr_vil_id) VALUES
('38 Les Barthes',4);
INSERT INTO ADRESSE(adr_voie, adr_vil_id) VALUES
('6 Rue de Kergariou',5);

-- ---------------------------
-- Table UTILISATEUR
-- ---------------------------

INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_adr_id, usr_premium ) VALUES 
( 'Me', 'feed.me@univ-lille1.fr',1, true);
INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_adr_id, usr_premium ) VALUES 
( 'Legrand', 'theo.legrand@legrand.com',3, false);
INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_adr_id, usr_premium ) VALUES 
( 'Landerzac', 'slanderzac@grolejac.fr',4, false);
INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_adr_id, usr_premium ) VALUES 
( 'Olivier', 'herveolivier@gemail.com',5, true);
INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_adr_id, usr_premium ) VALUES 
( 'Bot', 'GenLeBot@gemail.com',5, true); 
-- ---------------------------
-- Table PARTICULIER
-- ---------------------------
INSERT INTO PARTICULIER ( prt_prenom, prt_date_naissance, prt_usr_id) VALUES 
( 'Feed', '2015-01-31', 1); -- ID 1
INSERT INTO PARTICULIER ( prt_prenom, prt_date_naissance, prt_usr_id) VALUES 
( 'Théo', '1977-03-09', 2); -- ID 2
INSERT INTO PARTICULIER ( prt_prenom, prt_date_naissance, prt_usr_id) VALUES 
( 'Sandrine', '1988-08-29', 3); -- ID 3
INSERT INTO PARTICULIER ( prt_prenom, prt_date_naissance, prt_usr_id) VALUES 
( 'Hervé', '1997-12-11', 4); -- ID 4
 -- BOT ID 5
-- ---------------------------
-- Table AUTHENTIFICATION
-- ---------------------------
INSERT INTO AUTHENTIFICATION ( aut_usr_id, aut_password, aut_rol_id ) VALUES
(1, '$2a$10$L00YE3VqyxIvn43zvWbxkut6WNqKAp0WA0XRZGMriRzIRV04qxkqi', 1); -- MDP : feedme
INSERT INTO AUTHENTIFICATION ( aut_usr_id, aut_password, aut_rol_id ) VALUES
(2, '$2a$10$mIyhiVmzbMAUewAolld8H.ViW/Vch7wEfQu5SCQDgRMzP.OQA9rsa', 1); -- MDP : theo
INSERT INTO AUTHENTIFICATION ( aut_usr_id, aut_password, aut_rol_id ) VALUES
(3, '$2a$10$5APlvjPGjBJBVvlaonocsO/1aZKhf9skqVCv2aUR/XdayADlwvdS2', 1); -- MDP : sandrine
INSERT INTO AUTHENTIFICATION ( aut_usr_id, aut_password, aut_rol_id ) VALUES
(4, '$2a$10$5CK7dxLzpaWz54nF6q9oSOoy5Xh1BPBH1jeZWihS2GqZvuU3LZ0t6', 1); -- MDP : olivier

-- ---------------------------
-- Table OFFRE
-- ---------------------------
INSERT INTO OFFRE(OFF_DATE_CREATION,OFF_TITRE,OFF_PRIX, OFF_NOMBRE_PERSONNE,OFF_DUREE_MINUTE, OFF_DATE_REPAS, OFF_NOTE, OFF_ENTREE, OFF_PLAT, OFF_DESSERT, OFF_AGE_MIN, OFF_AGE_MAX, OFF_ANIMAUX, OFF_ADR_ID, OFF_TYP_ID, OFF_USR_ID, OFF_PREMIUM) VALUES
('2012-10-13','Tomates nordiques',1200,5,123,'2012-10-19 03:14:07','Des tomates delicieuses','Salade composee.', 'Tomates Farcies, riz a la creme fraiche et au vin blanc.', 'Poires belle Helene',10,45,'true',1,1,1, true);

INSERT INTO OFFRE(OFF_DATE_CREATION,OFF_TITRE,OFF_PRIX, OFF_NOMBRE_PERSONNE,OFF_DUREE_MINUTE, OFF_DATE_REPAS, OFF_NOTE, OFF_ENTREE, OFF_PLAT, OFF_DESSERT, OFF_AGE_MIN, OFF_AGE_MAX, OFF_ANIMAUX, OFF_ADR_ID, OFF_TYP_ID, OFF_USR_ID, OFF_PREMIUM) VALUES
('2012-10-13','Poulet subsaharien',1900,4,168,'2012-10-19 03:14:07','Attention les arachides sont un allergene','Buffet froid.', 'Poulet aux arachides, riz, carottes, piment.', 'Kakis et autres fruits exotiques',18,29,'true',2,1,1, true);

-- ---------------------------
-- Table RESERVATION
-- ---------------------------
INSERT INTO RESERVATION(RES_OFF_ID, RES_CON_ID, RES_DATE_RESERVATION, RES_NB_PLACES) VALUES
(2, 3, '2015-12-26', 1);
INSERT INTO RESERVATION(RES_OFF_ID, RES_CON_ID, RES_DATE_RESERVATION, RES_NB_PLACES) VALUES
(2, 4, '2015-12-26', 1);
INSERT INTO RESERVATION(RES_OFF_ID, RES_CON_ID, RES_DATE_RESERVATION, RES_NB_PLACES) VALUES
(1, 2, '2015-12-26', 2);
INSERT INTO RESERVATION(RES_OFF_ID, RES_CON_ID, RES_DATE_RESERVATION, RES_NB_PLACES) VALUES
(1, 3, '2015-12-26', 1);
INSERT INTO RESERVATION(RES_OFF_ID, RES_CON_ID, RES_DATE_RESERVATION, RES_NB_PLACES) VALUES
(1, 4, '2015-12-26', 1);
