-- // create_table_ville
-- Migration SQL that makes the change goes here.
CREATE TABLE VILLE (
	VIL_ID SERIAL PRIMARY KEY,
	VIL_NOM VARCHAR(60) NOT NULL,
	VIL_CP VARCHAR(5) NOT NULL,
	VIL_PAY_ID INTEGER NOT NULL REFERENCES PAYS(PAY_ID)
);


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE VILLE;

