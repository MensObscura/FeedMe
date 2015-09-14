-- RESET BDD CONTENT
TRUNCATE 
UTILISATEUR,
PARTICULIER;
	
-- RESTART ALL SEQUENCE	
ALTER SEQUENCE utilisateur_usr_id_seq RESTART WITH 1;
ALTER SEQUENCE particulier_prt_id_seq RESTART WITH 1;

-- DONNEES DE TEST
INSERT INTO UTILISATEUR ( usr_nom, usr_password, usr_mail ) 
VALUES ( 'toto', 'tata', 'toto.toto@gmail.com');
	
	
