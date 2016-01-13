-- // create_table_vote
-- Migration SQL that makes the change goes here.
CREATE TABLE VOTE (
	vot_id SERIAL PRIMARY KEY,
	vot_usr_id INTEGER NOT NULL REFERENCES UTILISATEUR(usr_id),
	vot_off_id INTEGER NOT NULL REFERENCES OFFRE(off_id),
	vot_note INTEGER NOT NULL CONSTRAINT vote_valide CHECK (vot_note > 0 AND vot_note < 6 ),
	CONSTRAINT tuple_unique_vote UNIQUE (vot_usr_id, vot_off_id)
);


-- //@UNDO
-- SQL to undo the change goes here.
DROP TABLE VOTE;

