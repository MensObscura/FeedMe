-- // fill_table_type_cuisine
-- Migration SQL that makes the change goes here.
INSERT INTO TYPE_CUISINE (TYP_CUISINE) VALUES 
('Cuisine régionale'),
('Africaine'),
('Steak House'),
('Gastronomique'),
('Grec'),
('Asiatique'),
('Espagnole'),
('Barbecue'),
('Provencale'),
('Bretonne'),
('Italienne'),
('Savoyard'),
('Alsacienne'),
('Mexicaine');


-- //@UNDO
-- SQL to undo the change goes here.
DELETE FROM TYPE_CUISINE WHERE TYP_ID BETWEEN 1 AND 14;

