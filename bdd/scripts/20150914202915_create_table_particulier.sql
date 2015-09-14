-- // create_table_particulier
-- Migration SQL that makes the change goes here.
CREATE TABLE PARTICULIER (
	PRT_ID SERIAL PRIMARY KEY,
	PRT_PRENOM VARCHAR(60) NOT NULL,
	PRT_DATE_NAISSANCE DATE,
	PRT_USR_ID INTEGER REFERENCES UTILISATEUR(USR_ID)
);


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE PARTICULIER;

