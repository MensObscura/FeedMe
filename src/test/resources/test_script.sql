-- RESET BDD CONTENT
TRUNCATE
AUTHENTIFICATION,
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
-- Table VILLE
-- ---------------------------
INSERT INTO VILLE (vil_nom, vil_cp, vil_pay_id) VALUES
('Lille', '59000', 1), -- ID 1
('Lille', '59000', 1); -- ID 2

-- ---------------------------
-- Table ADRESSE
-- ---------------------------
INSERT INTO ADRESSE (adr_voie, adr_vil_id) VALUES
('4 rue guillaume apollinaire', 1), -- ID 1
('4 rue guillaume apollinaire', 1); -- ID 2

-- ---------------------------
-- Table UTILISATEUR
-- ---------------------------
INSERT INTO UTILISATEUR ( usr_nom, usr_mail ) VALUES 
( 'toto', 'toto.toto@gmail.com'), -- ID 1
( 'foo', 'foo.bar@gmail.com'), -- ID 2
( 'jean', 'jambon-beurre@gmail.com'); -- ID 3

INSERT INTO UTILISATEUR (usr_nom, usr_mail, usr_adr_id) VALUES
( 'hall', 'kolick@gmail.com', 1); -- ID 4

-- ---------------------------
-- Table PARTICULIER
-- ---------------------------
INSERT INTO PARTICULIER ( prt_prenom, prt_date_naissance, prt_usr_id) VALUES 
( 'titi', '2015-01-31', 1); -- ID 1

-- ---------------------------
-- Table AUTHENTIFICATION
-- ---------------------------
INSERT INTO AUTHENTIFICATION ( aut_usr_id, aut_password, aut_rol_id ) VALUES
(1, '$2a$10$z9Ka6HSwJN/YDqZsC8a3Y.VVxC1DPQNZegHH4mXzRU/hDfetKNEvG', 1); -- MDP : test

-- ---------------------------
-- Table OFFRE
-- ---------------------------

INSERT INTO offre(off_date_creation, off_titre, off_prix, off_nombre_personne, off_duree_minute, off_date_repas, off_entree, off_plat, off_dessert, off_boisson, off_animaux, off_note, off_age_min, off_age_max, off_adr_id, off_typ_id, off_usr_id) VALUES
('2015-01-01', 'MonTitre', 999, 5, 120, '2015-02-01 19:45:00', 'MonEntree', 'MonPlat', 'MonDessert', 'MaBoisson', FALSE, 'Note', 20, 30, 1, 3, 1), -- ID 1
('2015-02-01', 'MonTitre2', 999, 5, 120, '2015-03-01 21:00:00', 'MonEntree', 'MonPlat', 'MonDessert', 'MaBoisson', FALSE, 'Note', 30, 50, 1, 3, 2), -- ID 2
('2015-03-01', 'MonTitre3', 999, 5, 120, '2015-04-01 20:30:00', 'MonEntree', 'MonPlat', 'MonDessert', 'MaBoisson', FALSE, 'Note', 20, 99, 1, 3, 3), -- ID 3
('2015-01-01', 'MonTitre', 999, 5, 120, '2015-02-01 19:45:00', 'MonEntree', 'MonPlat', 'MonDessert', 'MaBoisson', FALSE, 'Note', 20, 30, 1, 3, 1); -- ID 4

-- ---------------------------
-- Table RESERVATION
-- ---------------------------
INSERT INTO RESERVATION(res_off_id, res_con_id, res_date_reservation, res_nb_places) VALUES
(1, 1, CURRENT_TIMESTAMP, 2), -- ID 1
(4, 1, CURRENT_TIMESTAMP, 1), -- ID 2
(4, 2, CURRENT_TIMESTAMP, 1); -- ID 3

