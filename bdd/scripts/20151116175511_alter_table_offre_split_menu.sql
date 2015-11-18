-- // alter_table_offre_split_menu
-- Migration SQL that makes the change goes here.
ALTER TABLE OFFRE DROP COLUMN off_menu;
ALTER TABLE OFFRE ADD COLUMN off_plat VARCHAR(1014) NOT NULL;
ALTER TABLE OFFRE ADD COLUMN off_dessert VARCHAR(1014);
ALTER TABLE OFFRE ADD COLUMN off_entree VARCHAR(1014);
ALTER TABLE OFFRE ADD COLUMN off_boisson VARCHAR(1014);


-- //@UNDO
-- SQL to undo the change goes here.
ALTER TABLE OFFRE DROP COLUMN off_plat VARCHAR(1014);
ALTER TABLE OFFRE DROP COLUMN off_dessert VARCHAR(1014);
ALTER TABLE OFFRE DROP COLUMN off_entree VARCHAR(1014);
ALTER TABLE OFFRE DROP COLUMN off_boisson VARCHAR(1014);
ALTER TABLE OFFRE ADD COLUMN off_menu VARCHAR(1014) NOT NULL;
