-- // fill_table_role
-- Migration SQL that makes the change goes here.
INSERT INTO ROLE(ROL_ROLE) VALUES
('ROLE_PARTICULIER');


-- //@UNDO
-- SQL to undo the change goes here.
DELETE FROM ROLE WHERE ROL_ID = 1;

