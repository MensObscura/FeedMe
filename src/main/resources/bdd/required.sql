	TRUNCATE UTILISATEUR;
	
	ALTER SEQUENCE utilisateur_usr_id_seq RESTART WITH 1;
	
	INSERT INTO UTILISATEUR ( usr_nom, usr_password, usr_mail ) 
	VALUES ( 'toto', 'tata', 'toto.toto@gmail.com');
	
	
