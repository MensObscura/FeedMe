-- // create_table_reservation
-- Migration SQL that makes the change goes here.
CREATE TABLE RESERVATION (
	RES_ID SERIAL PRIMARY KEY,
	RES_OFF_ID INTEGER NOT NULL REFERENCES OFFRE(OFF_ID),
	RES_CON_ID INTEGER NOT NULL REFERENCES UTILISATEUR(USR_ID),
	RES_DATE_RESERVATION TIMESTAMP NOT NULL,
	RES_NB_PLACES INTEGER NOT NULL,
	CONSTRAINT TUPLE_UNIQUE_RESERVATION UNIQUE (RES_OFF_ID, RES_CON_ID)
);


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE RESERVATION;

