-- // create_table_adresse
-- Migration SQL that makes the change goes here.
CREATE TABLE ADRESSE (
	ADR_ID SERIAL PRIMARY KEY,
	ADR_VOIE VARCHAR(60) NOT NULL,
	ADR_VIL_ID INTEGER NOT NULL REFERENCES VILLE(VIL_ID)
);


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE ADRESSE;

