-- // alter_table_offre_add_premium
-- Migration SQL that makes the change goes here.

ALTER TABLE OFFRE ADD COLUMN OFF_PREMIUM BOOLEAN NOT NULL;


-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE OFFRE DROP COLUMN OFF_PREMIUM;


