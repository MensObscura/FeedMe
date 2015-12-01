-- // alter_table_utilisateur_add_image
-- Migration SQL that makes the change goes here.
ALTER TABLE UTILISATEUR ADD COLUMN USR_IMG_ID INTEGER;

ALTER TABLE UTILISATEUR ADD CONSTRAINT img_id FOREIGN KEY (USR_IMG_ID) REFERENCES IMAGE (IMG_ID);


-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE UTILISATEUR DROP COLUMN USR_IMG_ID;
