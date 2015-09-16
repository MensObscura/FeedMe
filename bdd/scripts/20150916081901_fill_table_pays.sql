-- // fill_table_pays
-- Migration SQL that makes the change goes here.
INSERT INTO PAYS (PAY_CODE_PAYS, PAY_NOM) VALUES
('FR', 'France');


-- //@UNDO
-- SQL to undo the change goes here.
DELETE FROM PAYS WHERE PAY_ID = 1;

