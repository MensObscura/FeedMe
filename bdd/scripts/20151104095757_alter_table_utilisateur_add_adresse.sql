-- // alter_table_utilisateur_add_adresse
-- Migration SQL that makes the change goes here.
ALTER TABLE UTILISATEUR ADD COLUMN USR_ADR_ID INTEGER;

ALTER TABLE UTILISATEUR ADD CONSTRAINT adr_id FOREIGN KEY (USR_ADR_ID) REFERENCES ADRESSE (ADR_ID);


-- //@UNDO
ALTER TABLE UTILISATEUR DROP COLUMN USR_ADR_ID;
-- SQL to undo the change goes here.


