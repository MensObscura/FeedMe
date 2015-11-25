-- // alter_table_utilisateur_add_visibilite_adresse
-- Migration SQL that makes the change goes here.
ALTER TABLE UTILISATEUR ADD COLUMN usr_adresse_visible BOOLEAN DEFAULT FALSE;


-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE UTILISATEUR DROP COLUMN usr_adresse_visible;

