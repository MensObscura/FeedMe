-- // create_table_adresse
-- Migration SQL that makes the change goes here.
CREATE TABLE PAYS (
	PAY_ID SERIAL PRIMARY KEY,
	PAY_CODE_PAYS VARCHAR(2) NOT NULL,
	PAY_NOM VARCHAR(50)
);


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE PAYS;

