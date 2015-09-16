-- // create_table_type_cuisine
-- Migration SQL that makes the change goes here.
CREATE TABLE TYPE_CUISINE (
	TYP_ID SERIAL PRIMARY KEY,
	TYP_CUISINE VARCHAR(60) NOT NULL UNIQUE
);


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE TYPE_CUISINE;

