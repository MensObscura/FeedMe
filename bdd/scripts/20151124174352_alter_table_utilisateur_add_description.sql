-- // alter_table_utilisateur_add_description
-- Migration SQL that makes the change goes here.
ALTER TABLE UTILISATEUR ADD COLUMN USR_DESCRIPTION VARCHAR(300);


-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE UTILISATEUR DROP COLUMN USR_DESCRIPTION;
