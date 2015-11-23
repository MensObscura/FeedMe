-- // create_table_image
-- Migration SQL that makes the change goes here.
CREATE TABLE IMAGE (
	img_id SERIAL PRIMARY KEY,
	img_path VARCHAR(4096) NOT NULL UNIQUE
);


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE IMAGE;

