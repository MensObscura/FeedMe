-- RESET BDD CONTENT
TRUNCATE
IMAGE_OFFRE,
IMAGE,
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
ALTER SEQUENCE image_img_id_seq RESTART WITH 1;

-- DONNEES DE TEST

-- ---------------------------
-- Table IMAGE
-- ---------------------------
INSERT INTO IMAGE ( img_path ) values
('/monPath/0.jpg'); -- ID 1


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
INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_premium , usr_description) VALUES 
( 'toto', 'toto.toto@gmail.com', true, 'ceci est la description de toto'), -- ID 1
( 'foo', 'foo.bar@gmail.com', true, null), -- ID 2
( 'jean', 'jambon-beurre@gmail.com', true, null); -- ID 3

INSERT INTO UTILISATEUR (usr_nom, usr_mail, usr_adr_id, usr_premium, usr_description) VALUES
( 'hall', 'kolick@gmail.com', 1, true, 'ceci est la description de hall'); -- ID 4

-- ---------------------------
-- Table PARTICULIER
-- ---------------------------
INSERT INTO PARTICULIER ( prt_prenom, prt_date_naissance, prt_usr_id) VALUES 
( 'titi', '2015-01-31', 1), -- ID 1
( 'toto', '2015-01-31', 4); -- ID 2

-- ---------------------------
-- Table AUTHENTIFICATION
-- ---------------------------
INSERT INTO AUTHENTIFICATION ( aut_usr_id, aut_password, aut_rol_id ) VALUES
(1, '$2a$10$z9Ka6HSwJN/YDqZsC8a3Y.VVxC1DPQNZegHH4mXzRU/hDfetKNEvG', 1); -- MDP : test

-- ---------------------------
-- Table OFFRE
-- ---------------------------

INSERT INTO offre(off_date_creation, off_titre, off_prix, off_nombre_personne, off_duree_minute, off_date_repas, off_entree, off_plat, off_dessert, off_boisson, off_animaux, off_note, off_age_min, off_age_max, off_adr_id, off_typ_id, off_usr_id, off_premium) VALUES
('2015-01-01', 'MonTitre', 999, 5, 120, '2015-02-01 19:45:00', 'MonEntree', 'MonPlat', 'MonDessert', 'MaBoisson', FALSE, 'Note', 20, 30, 1, 3, 1, true), -- ID 1
('2015-02-01', 'MonTitre2', 999, 5, 120, '2015-03-01 21:00:00', 'MonEntree', 'MonPlat', 'MonDessert', 'MaBoisson', FALSE, 'Note', 30, 50, 1, 3, 2, true), -- ID 2
('2015-03-01', 'MonTitre3', 999, 5, 120, '2015-04-01 20:30:00', 'MonEntree', 'MonPlat', 'MonDessert', 'MaBoisson', FALSE, 'Note', 20, 99, 1, 3, 3, false), -- ID 3
('2015-01-01', 'MonTitre', 999, 5, 120, '2015-02-01 19:45:00', 'MonEntree', 'MonPlat', 'MonDessert', 'MaBoisson', FALSE, 'Note', 20, 30, 1, 3, 1, false); -- ID 4

-- ---------------------------
-- Table RESERVATION
-- ---------------------------
INSERT INTO RESERVATION(res_off_id, res_con_id, res_date_reservation, res_nb_places) VALUES
(1, 1, CURRENT_TIMESTAMP, 2), -- ID 1
(4, 1, CURRENT_TIMESTAMP, 1), -- ID 2
(4, 2, CURRENT_TIMESTAMP, 1); -- ID 3

-- ---------------------------
-- Table IMAGE_OFFRE
-- ---------------------------
INSERT INTO image_offre(imo_off_id, imo_img_id) VALUES
(1, 1);

