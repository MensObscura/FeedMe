-- // create_table_utilisateur
-- Migration SQL that makes the change goes here.
CREATE TABLE UTILISATEUR (
	USR_ID SERIAL PRIMARY KEY,
	USR_NOM VARCHAR(60) NOT NULL,
	USR_PASSWORD VARCHAR(60) NOT NULL,
	USR_MAIL VARCHAR(60) NOT NULL
);

-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE UTILISATEUR;

