-- ---------------------------
-- Table IMAGE
-- ---------------------------
INSERT INTO IMAGE (IMG_PATH) VALUES
('resources\img\upload\uagQo1WxIUrKa0i1_1902161157_1455879432903.jpg');
INSERT INTO IMAGE (IMG_PATH) VALUES
('resources\img\upload\uagQo1WxIUrKa0i1_1902161157_1455879432903.jpg');
INSERT INTO IMAGE (IMG_PATH) VALUES
('resources\img\upload\uagQo1WxIUrKa0i1_1902161157_1455879432903.jpg');
INSERT INTO IMAGE (IMG_PATH) VALUES
('resources\img\upload\uagQo1WxIUrKa0i1_1902161157_1455879432903.jpg');
INSERT INTO IMAGE (IMG_PATH) VALUES
('resources\img\upload\uagQo1WxIUrKa0i1_1902161157_1455879432903.jpg');
INSERT INTO IMAGE (IMG_PATH) VALUES
('resources\img\upload\BUIudMPwLFH2mWx3_1902161215_1455880503852.jpg');
INSERT INTO IMAGE (IMG_PATH) VALUES
('resources\img\upload\HGXFIpz6Uvt6C7jz_1902161234_1455881698792.png');
INSERT INTO IMAGE (IMG_PATH) VALUES
('resources\img\upload\PXtEp4d9QWLPxMnI_1902161245_1455882322616.jpg');

-- ---------------------------
-- Table VILLE
-- ---------------------------
INSERT INTO VILLE(VIL_NOM, VIL_CP, VIL_PAY_ID) VALUES
('Lille','59650',1); -- ID 1
INSERT INTO VILLE(VIL_NOM, VIL_CP, VIL_PAY_ID) VALUES
('Reims','51100',1); -- ID 2
INSERT INTO VILLE(VIL_NOM, VIL_CP, VIL_PAY_ID) VALUES
('Cayeux-sur-mer','80410',1); -- ID 3
INSERT INTO VILLE(VIL_NOM, VIL_CP, VIL_PAY_ID) VALUES
('Groléjac','24250',1); -- ID 4
INSERT INTO VILLE(VIL_NOM, VIL_CP, VIL_PAY_ID) VALUES
('Quimper','29000',1); -- ID 5
INSERT INTO VILLE(VIL_NOM, VIL_CP, VIL_PAY_ID) VALUES
('Villeneuve d asq','59650',1); -- ID 6
INSERT INTO VILLE(VIL_NOM, VIL_CP, VIL_PAY_ID) VALUES
('Watte sur Bled','45980',1); -- ID 7
-- ---------------------------
-- Table ADRESSE
-- ---------------------------
INSERT INTO ADRESSE(adr_voie, adr_vil_id) VALUES
('45 Rue des sacres',1);  -- ID 1
INSERT INTO ADRESSE(adr_voie, adr_vil_id) VALUES
('21 Avenue des champs',2);  -- ID 2
INSERT INTO ADRESSE(adr_voie, adr_vil_id) VALUES
('54 Rue de la gare',3); -- ID 3
INSERT INTO ADRESSE(adr_voie, adr_vil_id) VALUES
('38 Les Barthes',4); -- ID 4
INSERT INTO ADRESSE(adr_voie, adr_vil_id) VALUES
('6 Rue de Kergariou',5);  -- ID 5
INSERT INTO ADRESSE(adr_voie, adr_vil_id) VALUES
('5 rue des Julies',6);  -- ID 6
INSERT INTO ADRESSE(adr_voie, adr_vil_id) VALUES
('6 Avenue de Théobald Le Grand',1);  -- ID 7
INSERT INTO ADRESSE(adr_voie, adr_vil_id) VALUES
('7 Rue des œufs  primordiaux',1);  -- ID 8
INSERT INTO ADRESSE(adr_voie, adr_vil_id) VALUES
('8 Impasse de la mauvaise compagnie Entrée E',7);  -- ID 9
-- ---------------------------
-- Table UTILISATEUR
-- ---------------------------

INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_adr_id, usr_premium ) VALUES 
( 'Me', 'feed.me@univ-lille1.fr',1, true);
INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_adr_id, usr_premium ) VALUES 
( 'Legrand', 'theo.legrand@legrand.com',2, false);
INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_adr_id, usr_premium ) VALUES 
( 'Landerzac', 'slanderzac@grolejac.fr',3, false);
INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_adr_id, usr_premium ) VALUES 
( 'Olivier', 'herveolivier@gemail.com',4, true);
INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_adr_id, usr_premium ) VALUES 
( 'Bot', 'GenLeBot@gemail.com',5, true); 
INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_adr_id ,usr_premium, usr_description, usr_img_id ) VALUES 
( 'SORA', 'thibault@rosa.com',6, false, 'Plus que tout j aime manger et partager. Venez à moi vous tous qui êtes affamés, je vous donnerai du gigot. (Enfin... c est pas gratuit)',5); 
INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_adr_id, usr_premium, usr_description, usr_img_id ) VALUES 
( 'BAREVERE', 'thibaud@barevere.fr',7, false, 'La suprématie de ma cuisine n a d égal que la grandeur de la Théobaldie.', 6);
INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_adr_id, usr_premium, usr_description, usr_img_id ) VALUES 
( 'CHEBADA', 'yassine@chebada.fr',8, true, 'Je ne jure que par la cuillère et sachez qu en toute chose la cuillère ce suffit à elle même !',7 );
INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_adr_id, usr_premium, usr_description, usr_img_id ) VALUES 
( 'DETERELA', 'jb@deterela.fr',9, true, 'Rien de tel qu un petit barbecue au milieu du feu... euh autour du feu pardon.', 8 );
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
 INSERT INTO PARTICULIER ( prt_prenom, prt_date_naissance, prt_usr_id) VALUES 
( 'Thibault', '1997-06-08', 6); -- ID 6
INSERT INTO PARTICULIER ( prt_prenom, prt_date_naissance, prt_usr_id) VALUES 
( 'Thibaud', '1917-06-12', 7); -- ID 7
INSERT INTO PARTICULIER ( prt_prenom, prt_date_naissance, prt_usr_id) VALUES 
( 'Yassine', '1997-11-18', 8); -- ID 8
INSERT INTO PARTICULIER ( prt_prenom, prt_date_naissance, prt_usr_id) VALUES 
( 'Jean-Baptiste', '1998-02-01', 9); -- ID 9
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
INSERT INTO AUTHENTIFICATION ( aut_usr_id, aut_password, aut_rol_id ) VALUES
(6, '$2a$10$JLWfbNC60AIeh1sQOfc7N.STbmfrj1lH9w34PwOmFxQL6n3n.S2Aq', 1); -- MDP : thibault
INSERT INTO AUTHENTIFICATION ( aut_usr_id, aut_password, aut_rol_id ) VALUES
(7, '$2a$10$d4EVGhJuwmYSwHlgBLkpzOBOJ0DUjnH9izyymL9.08MoKShAvUf6m', 1); -- MDP : thibaud
INSERT INTO AUTHENTIFICATION ( aut_usr_id, aut_password, aut_rol_id ) VALUES
(8, '$2a$10$2thDGEcLLLUdLoFytEIO5ez53Qkhw4jMNBemUcQ.O9sqXncwKhYsK', 1); -- MDP : yassine
INSERT INTO AUTHENTIFICATION ( aut_usr_id, aut_password, aut_rol_id ) VALUES
(9, '$2a$10$LuWoFSNGvqdetdpk5DBrsOOJ.8IwqDOeDLCOJ9lYDUOErkUWl4RlC', 1); -- MDP : jb
-- ---------------------------
-- Table OFFRE
-- ---------------------------
INSERT INTO OFFRE(OFF_DATE_CREATION,OFF_TITRE,OFF_PRIX, OFF_NOMBRE_PERSONNE,OFF_DUREE_MINUTE, OFF_DATE_REPAS, OFF_NOTE, OFF_ENTREE, OFF_PLAT, OFF_DESSERT, OFF_BOISSON, OFF_AGE_MIN, OFF_AGE_MAX, OFF_ANIMAUX, OFF_ADR_ID, OFF_TYP_ID, OFF_USR_ID, off_premium) VALUES
('2012-10-13','Tomates nordique',1250,5,123,'2012-10-19 03:14:07','Des tomates délicieuses','Salade composée', 'Tomates Farcies, riz à la crème fraiche et au vin blanc', 'Poires belle Hélène', null, 10,45,'true',1,1,1, true);

INSERT INTO OFFRE(OFF_DATE_CREATION,OFF_TITRE,OFF_PRIX, OFF_NOMBRE_PERSONNE,OFF_DUREE_MINUTE, OFF_DATE_REPAS, OFF_NOTE, OFF_ENTREE, OFF_PLAT, OFF_DESSERT, OFF_BOISSON, OFF_AGE_MIN, OFF_AGE_MAX, OFF_ANIMAUX, OFF_ADR_ID, OFF_TYP_ID, OFF_USR_ID, off_premium) VALUES
('2012-10-13','Poulet subsaharien',580,4,168,'2012-10-19 03:14:07','Attention les arachides sont un allergène','Buffet froid', 'Poulet aux arachides, riz, carottes, piment', 'Kakis et autres fruits exotiques', null, 18,29,'true',2,1,1, true);

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

