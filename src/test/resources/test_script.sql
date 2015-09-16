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

-- DONNEES DE TEST
INSERT INTO UTILISATEUR ( usr_nom, usr_password, usr_mail ) 
VALUES ( 'toto', 'tata', 'toto.toto@gmail.com'); -- ID 1

INSERT INTO PARTICULIER ( prt_prenom, prt_date_naissance, prt_usr_id) 
VALUES ( 'titi', '2015-01-31', 1); -- ID 1

	