-- ---------------------------
-- Table UTILISATEUR
-- ---------------------------
INSERT INTO UTILISATEUR ( usr_nom, usr_mail ) VALUES 
( 'Me', 'feed.me@univ-lille1.fr');

-- ---------------------------
-- Table PARTICULIER
-- ---------------------------
INSERT INTO PARTICULIER ( prt_prenom, prt_date_naissance, prt_usr_id) VALUES 
( 'Feed', '2015-01-31', 1); -- ID 1

-- ---------------------------
-- Table AUTHENTIFICATION
-- ---------------------------
INSERT INTO AUTHENTIFICATION ( aut_usr_id, aut_password, aut_rol_id ) VALUES
(1, '$2a$10$L00YE3VqyxIvn43zvWbxkut6WNqKAp0WA0XRZGMriRzIRV04qxkqi', 1); -- MDP : feedme