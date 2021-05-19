DROP TABLE articleTest ;
CREATE TABLE articleTest
(
idarticle 	NUMBER,
designation 	VARCHAR2 (20),
prixunit 	NUMBER (7,2),
qtestock 	NUMBER  DEFAULT 0,
CONSTRAINT pk_articleTest PRIMARY KEY (idarticle)
) ;
INSERT INTO articleTest
        VALUES (1, 'classeur', 15.7, 100) ;
INSERT INTO articleTest
        VALUES (2, 'cahier', 4.55, 250) ;
INSERT INTO articleTest
        VALUES (3, 'stylo', 22.7, 300) ;
INSERT INTO articleTest
        VALUES (4, 'chemise', 3.75, 200) ;
INSERT INTO articleTest
        VALUES (5, 'crayon', 7.8, 1000) ;
COMMIT;
