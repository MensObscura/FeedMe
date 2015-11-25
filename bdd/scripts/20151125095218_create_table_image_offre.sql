-- // create_table_image_offre
-- Migration SQL that makes the change goes here.
CREATE TABLE IMAGE_OFFRE(
	imo_off_id INTEGER NOT NULL REFERENCES OFFRE ( off_id ),
	imo_img_id INTEGER NOT NULL REFERENCES IMAGE ( img_id )
);


-- //@UNDO
-- SQL to undo the change goes here.


