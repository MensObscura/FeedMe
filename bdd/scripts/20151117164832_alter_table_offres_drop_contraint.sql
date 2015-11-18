-- // alter_table_offres_drop_contraint
-- Migration SQL that makes the change goes here.
ALTER TABLE RESERVATION DROP CONSTRAINT TUPLE_UNIQUE_RESERVATION;


-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE RESERVATION ADD CONSTRAINT TUPLE_UNIQUE_RESERVATION UNIQUE (RES_OFF_ID, RES_CON_ID);

