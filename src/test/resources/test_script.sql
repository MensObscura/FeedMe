-- RESET BDD CONTENT
TRUNCATE 
UTILISATEUR,
PARTICULIER,
VILLE,
ADRESSE,
OFFRE,
RESERVATION;
	
-- RESTART ALL SEQUENCE	
ALTER SEQUENCE utilisateur_usr_id_seq RESTART WITH 1;
ALTER SEQUENCE particulier_prt_id_seq RESTART WITH 1;
ALTER SEQUENCE ville_vil_id_seq RESTART WITH 1;
ALTER SEQUENCE adresse_adr_id_seq RESTART WITH 1;
ALTER SEQUENCE offre_off_id_seq RESTART WITH 1;
ALTER SEQUENCE reservation_res_id_seq RESTART WITH 1;

-- DONNEES DE TEST

-- ---------------------------
-- Table UTILISATEUR
-- ---------------------------
INSERT INTO UTILISATEUR ( usr_nom, usr_password, usr_mail ) VALUES 
( 'toto', 'tata', 'toto.toto@gmail.com'), -- ID 1
( 'foo', 'bar', 'foo.bar@gmail.com'), -- ID 2
( 'jean', 'bomber', 'jambon-beurre@gmail.com'); -- ID 3

-- ---------------------------
-- Table PARTICULIER
-- ---------------------------
INSERT INTO PARTICULIER ( prt_prenom, prt_date_naissance, prt_usr_id) VALUES 
( 'titi', '2015-01-31', 1); -- ID 1

-- ---------------------------
-- Table VILLE
-- ---------------------------
INSERT INTO VILLE (vil_nom, vil_cp, vil_pay_id) VALUES ('Lille', '59000', 1); -- ID 1

-- ---------------------------
-- Table ADRESSE
-- ---------------------------
INSERT INTO ADRESSE (adr_voie, adr_vil_id) VALUES ('4 rue guillaume apollinaire', 1);


-- Donnees pour AdresseDao
INSERT INTO VILLE (vil_nom, vil_cp, vil_pay_id)
VALUES ('Lille', '59000', 1);

INSERT INTO ADRESSE (adr_voie, adr_vil_id)
VALUES ('4 rue guillaume apollinaire', 1);

INSERT INTO offre(off_date_creation, off_titre, off_prix, off_nombre_personne, off_duree_minute, off_date_repas, off_menu, off_animaux, off_note, off_age_min, off_age_max, off_adr_id, off_typ_id, off_usr_id) VALUES
('2015-01-01', 'MonTitre', 999, 5, 120, '2015-02-01 19:45:00', 'DescriptionDuMenu', FALSE, 0, 20, 30, 1, 3, 1),
('2015-02-01', 'MonTitre2', 999, 5, 120, '2015-03-01 21:00:00', 'DescriptionDuMenu', FALSE, 0, 30, 50, 1, 3, 2),
('2015-03-01', 'MonTitre3', 999, 5, 120, '2015-04-01 20:30:00', 'DescriptionDuMenu', FALSE, 0, 20, 99, 1, 3, 3);

-- ---------------------------
-- Table RESERVATION
-- ---------------------------
INSERT INTO RESERVATION(res_off_id, res_con_id, res_date_reservation) VALUES
(1, 1, CURRENT_TIMESTAMP );
