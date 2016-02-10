-- RESET BDD CONTENT
TRUNCATE
MESSAGE,
IMAGE_OFFRE,
IMAGE,
VOTE,
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
ALTER SEQUENCE message_msg_id_seq RESTART WITH 1;
ALTER SEQUENCE vote_vot_id_seq RESTART WITH 1;

-- DONNEES DE TEST
-- ---------------------------
-- BOT
-- ---------------------------
INSERT INTO UTILISATEUR ( usr_id, usr_nom, usr_mail, usr_premium , usr_description, usr_adresse_visible) VALUES 
( -1, 'bot', 'bot@feedme.com', false, '', false);

-- ---------------------------
-- Table IMAGE
-- ---------------------------
INSERT INTO IMAGE ( img_path ) values
('/monPath/0.jpg'), -- ID 1
('/monPath/1.jpg'); -- ID 2


-- ---------------------------
-- Table VILLE
-- ---------------------------
INSERT INTO VILLE (vil_nom, vil_cp, vil_pay_id) VALUES
('Lille', '59000', 1), -- ID 1
('Ronchin', '59790', 1); -- ID 2

-- ---------------------------
-- Table ADRESSE
-- ---------------------------
INSERT INTO ADRESSE (adr_voie, adr_vil_id) VALUES
('4 rue guillaume apollinaire', 1), -- ID 1
('4 rue guillaume apollinaire', 2); -- ID 2

-- ---------------------------
-- Table UTILISATEUR
-- ---------------------------
INSERT INTO UTILISATEUR ( usr_nom, usr_mail, usr_premium , usr_description, usr_adresse_visible) VALUES 
( 'toto', 'toto.toto@gmail.com', true, 'ceci est la description de toto', true), -- ID 1
( 'foo', 'foo.bar@gmail.com', true, null, true), -- ID 2
( 'jean', 'jambon-beurre@gmail.com', false, null, true); -- ID 3

INSERT INTO UTILISATEUR (usr_nom, usr_mail, usr_adr_id, usr_premium, usr_description, usr_img_id) VALUES
( 'hall', 'kolick@gmail.com', 1, true, 'ceci est la description de hall', 1), -- ID 4
( 'john', 'johndoe@gmail.com', 1, false, 'une description', 1); -- ID 5

-- ---------------------------
-- Table PARTICULIER
-- ---------------------------
INSERT INTO PARTICULIER ( prt_prenom, prt_date_naissance, prt_usr_id) VALUES 
( 'titi', '1980-01-01', 1), -- ID 1
( 'toto', '1995-01-01', 4), -- ID 2
( 'toto', '1995-06-01', 2), -- ID 3
( 'toto', '1995-08-01', 3); -- ID 4

-- ---------------------------
-- Table AUTHENTIFICATION
-- ---------------------------
INSERT INTO AUTHENTIFICATION ( aut_usr_id, aut_password, aut_rol_id ) VALUES
(1, '$2a$10$z9Ka6HSwJN/YDqZsC8a3Y.VVxC1DPQNZegHH4mXzRU/hDfetKNEvG', 1); -- MDP : test

-- ---------------------------
-- Table OFFRE
-- ---------------------------

INSERT INTO offre(off_date_creation, off_titre, off_prix, off_nombre_personne, off_duree_minute, off_date_repas, off_entree, off_plat, off_dessert, off_boisson, off_animaux, off_note, off_age_min, off_age_max, off_adr_id, off_typ_id, off_usr_id, off_premium) VALUES
('2015-01-01', 'MonTitre', 999, 5, 120, current_date + interval '3 days', 'MonEntree', 'MonPlat', 'MonDessert', 'MaBoisson', FALSE, 'Note', 20, 30, 1, 3, 1, true), -- ID 1
('2015-02-01', 'MonTitre2', 999, 5, 120, '2015-03-01 21:00:00', 'MonEntree', 'MonPlat', 'MonDessert', 'MaBoisson', FALSE, 'Note', 30, 50, 1, 3, 2, true), -- ID 2
('2015-03-01', 'MonTitre3', 999, 5, 120, '2015-01-01 20:30:00', 'MonEntree', 'MonPlat', 'MonDessert', 'MaBoisson', FALSE, 'Note', 20, 99, 1, 3, 3, false), -- ID 3
('2015-01-01', 'MonTitre', 999, 5, 120, '2015-01-01 19:45:00', 'MonEntree', 'MonPlat', 'MonDessert', 'MaBoisson', FALSE, 'Note', 20, 30, 1, 3, 1, false), -- ID 4

('2015-01-01', 'couscous a l''ancienne', 1299, 5, 90, '2015-01-01 19:45:00', 'MonEntree', 'MonPlat', 'MonDessert', 'MaBoisson', TRUE, 'Note', 20, 40, 1, 15, 1, false), -- ID 5
('2015-01-01', 'Delicieux Co U sCoùs chez Papa', 1499, 5, 360, '2015-01-01 19:45:00', 'MonEntree', 'MonPlat', 'MonDessert', 'MaBoisson', TRUE, 'Note', 40, 50, 2, 15, 1, false); -- ID 6

-- ---------------------------
-- Table RESERVATION
-- ---------------------------
INSERT INTO RESERVATION(res_off_id, res_con_id, res_date_reservation, res_nb_places) VALUES
(1, 1, CURRENT_TIMESTAMP, 2), -- ID 1
(4, 1, CURRENT_TIMESTAMP, 1), -- ID 2
(4, 4, CURRENT_TIMESTAMP, 1); -- ID 3

-- ---------------------------
-- Table IMAGE_OFFRE
-- ---------------------------
INSERT INTO image_offre(imo_off_id, imo_img_id) VALUES
(1, 1);

-- ---------------------------
-- Table MESSAGE
-- ---------------------------
INSERT INTO message(msg_id_usr_expediteur, msg_id_usr_destinataire, msg_date, msg_lu, msg_objet, msg_texte) VALUES
(1, 2, CURRENT_TIMESTAMP, false, 'premier objet', 'premier texte'), -- ID 1
(1, 2, CURRENT_TIMESTAMP, false, 'deuxieme objet', 'deuxieme texte'), -- ID 2
(1, 2, CURRENT_TIMESTAMP, true, 'deuxieme objet', 'deuxieme texte'); -- ID 3

-- ---------------------------
-- Table VOTE
-- ---------------------------
INSERT INTO VOTE(vot_usr_id, vot_off_id, vot_note) VALUES
(2, 1, 5), -- ID 1 - L'offre 1 a reçu la note de 5 par l'utilisateur 2
(3, 1, 3), -- ID 2 - L'offre 1 a reçu la note de 3 par l'utilisateur 3
(1, 1, 4); -- ID 3 - L'offre 1 a reçu la note de 4 par l'utilisateur 1

