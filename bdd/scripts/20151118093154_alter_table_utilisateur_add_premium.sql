-- // alter_table_utilisateur_add_premium
-- Migration SQL that makes the change goes here.
ALTER TABLE UTILISATEUR ADD COLUMN USR_PREMIUM BOOLEAN NOT NULL;



-- //@UNDO
ALTER TABLE UTILISATEUR DROP COLUMN USR_PREMIUM;
-- SQL to undo the change goes here.


