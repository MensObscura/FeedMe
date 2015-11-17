-- // create_table_role
-- Migration SQL that makes the change goes here.
CREATE TABLE ROLE(
	ROL_ID SERIAL PRIMARY KEY,
	ROL_ROLE VARCHAR(60)
);


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE ROLE;

