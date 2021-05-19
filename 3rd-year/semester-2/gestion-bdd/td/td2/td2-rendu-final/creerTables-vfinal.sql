/*
* @author: 
* @date: 11/03/2021
* @title: TD 2
*/

/* Réponses questions DU TP */

-- Question 1
-- suppression des tables si elles existent
DROP TABLE affectation;
DROP TABLE avion;
DROP TABLE appareil;
DROP TABLE pilote;
DROP TABLE vol;

-- création table vol
CREATE TABLE vol (
    idVol          VARCHAR2(6),
    villeDepart    VARCHAR2(20)
    CONSTRAINT volVilleDepNotNull NOT NULL,
    villeArrivee   VARCHAR2(20)
    CONSTRAINT volVilleArrNotNull NOT NULL,
    heureDepart    NUMBER(10,2)
    CONSTRAINT volHeureDep0_24 CHECK (heureDepart >= 0 AND heureDepart < 24),
    heureArrivee   NUMBER(10,2)
    CONSTRAINT volHeureArr0_24 CHECK (heureArrivee >= 0 AND heureArrivee < 24),
    decalage       NUMBER(3) DEFAULT 0, 
    jour           NUMBER(1) DEFAULT 0
    CONSTRAINT volJourDecal CHECK (jour = 0 OR jour = 1 OR jour = -1),
    CONSTRAINT volClePrim PRIMARY KEY (idVol)
);

-- création table pilote
CREATE TABLE pilote (
    idPilote       VARCHAR2(4),
    nomPilote      VARCHAR2(30)
    CONSTRAINT piloteNomNotNull NOT NULL,
    adrPilote      VARCHAR2(40),
    salairePilote  NUMBER(10,2)
    CONSTRAINT piloteSalairePositif CHECK (salairePilote > 0),
    embauchePilote DATE
    CONSTRAINT piloteEmbaucheNotNull NOT NULL,
    CONSTRAINT piloteClePrim PRIMARY KEY (idPilote)
);

-- création table appareil
CREATE TABLE appareil (
    typeAppareil  VARCHAR2(3),
    designation   VARCHAR2(40),
    capacite      NUMBER
    CONSTRAINT appareilCapaciteNotNull NOT NULL,
    CONSTRAINT capacitePositif CHECK (capacite > 0),
    CONSTRAINT appareilClePrim PRIMARY KEY (typeAppareil)
);

-- pour l'ordre de création des tables, appareil vient avant avion (car CONSTRAINT)
-- création table avion
CREATE TABLE avion (
    idAvion        VARCHAR2(4),
    typeAppareil   VARCHAR2(3),
    miseEnService  NUMBER
    CONSTRAINT avionServiceNotNull NOT NULL,
    CONSTRAINT avionClePrim PRIMARY KEY (idAvion),
    CONSTRAINT fk_typeAppareil FOREIGN KEY (typeAppareil) REFERENCES appareil (typeAppareil)
);

-- création table affectation
CREATE TABLE affectation (
    idVol       VARCHAR2(6),
    dateVol     DATE,
    idPilote    VARCHAR2(4),
    idAvion     VARCHAR2(4),
    nbPassagers  NUMBER DEFAULT 0
    CONSTRAINT nbPassagersPositif CHECK (nbPassagers > 0),
    CONSTRAINT affectationClePrim PRIMARY KEY (idVol, dateVol),
    CONSTRAINT fk_idVol FOREIGN KEY (idvol) REFERENCES vol (idVol),
    CONSTRAINT fk_idPilote FOREIGN KEY (idPilote) REFERENCES pilote (idPilote),
    CONSTRAINT fk_idAvion FOREIGN KEY (idAvion) REFERENCES avion (idAvion)
);
--------------------------------------------------------------------------------
-- Question 2
-- voir fichiers 'toutes-les-tables.sql' ou dossier 'CTL/'

--------------------------------------------------------------------------------
-- Question 3
-- trigger permettant avant l'insertion de données de vérifier que le nombre de passager n'est pas supérieur au nombre maximale de l'avion en question
DROP TRIGGER check_plane_capacity;
CREATE OR REPLACE TRIGGER check_plane_capacity
BEFORE INSERT OR UPDATE ON affectation
FOR EACH ROW
DECLARE real_capacity NUMBER;  -- capacité réelle du nombre donné par la table appareil
BEGIN
    SELECT capacite INTO real_capacity  -- enregistrement du résultat de cette commande sql dans la variable real_capacity
    FROM appareil, avion
    WHERE :NEW.idAvion = avion.idAvion AND avion.typeAppareil = appareil.typeAppareil;
    IF :NEW.nbPassagers > real_capacity THEN
        RAISE_APPLICATION_ERROR(-20001,'INSERT TABLE appareil: too much passengers');
    END IF;
END;

-- q3 suite : insérer les informations suivantes :
-- Il y a 170 passagers sur le vol AF8810 du 21 avril 2019, pour lequel est affecté l’avion 8802, piloté par le pilote 0001.

--------------------------------------------------------------------------------
-- Question 3
INSERT INTO affectation VALUES ('AF8810',to_date('21/04/19','DD/MM/RR'),'0001','8802','170');
-- provoque l'erreur du trigger, c'est normal

--------------------------------------------------------------------------------
-- Question 4

-- q4.1 : Ajout de la colonne dureeVol

-- suppression de la colonne dureeVol si elle existe déjà
-- simple précaution avant de l'ajouter (ou de la modifier)
ALTER TABLE vol DROP COLUMN dureeVol;

-- modification/ajout de la colonne dureeVol correspondant à la formule suivante
ALTER TABLE vol ADD (
  dureeVol NUMBER(10,2) AS ((24)*jour+heureArrivee-heureDepart+decalage)
);

/*
-- On pourrait aussi faire une fonction, et appeler cette fonction dans un trigger
-- pour qu'elle s'exécute automatiquement après la création de la table
-- mais ça ne répondrait pas exactement à la question  
execute immediate 'select '|| ((24)*jour+heureArrivee-heureDepart+decalage) ||' from vol' into dureeVol;
return dureeVol;
-- De plus cette action serait plus lente.
*/

-- affiche le contenu de la table vol
SELECT * FROM vol;

-- q4.2 : nombre total d'heures de vol pour chacun des avions affectés à un vol

SELECT affectation.idAvion,SUM(dureeVol) as NombreTotalHeureDeVol
FROM vol,affectation
WHERE affectation.idVol = vol.idVol GROUP BY (affectation.idAvion) ORDER BY affectation.idAvion ASC;

-- q4.3 : procédure permettant d’initialiser l’attribut nbHeuresVol à partir des informations contenues dans la base de données.
-- D'après la documentation Oracle, la méthode suivante que j'ai utilisée est plus sûre/stable que la méthode utilisant les cursor

-- supprime la colonne nbHeuresVol, si jamais elle existait déjà, avant de la recréer
ALTER TABLE avion DROP COLUMN nbHeuresVol;

-- on ajoute/initialise dans la table avion la colonne nbHeuresVol
ALTER TABLE avion ADD (
  nbHeuresVol NUMBER(10,2)
);

-- on supprime la procédure avant de la créer, au cas où
DROP PROCEDURE majNbHeuresVolAvion;
-- création de la procédure
CREATE OR REPLACE PROCEDURE majNbHeuresVolAvion
IS
BEGIN
    UPDATE avion
    SET  avion.nbHeuresVol=(SELECT SUM(dureeVol)
                            FROM vol,affectation af
                            WHERE af.idVol = vol.idVol AND af.idAvion = avion.idAvion
                            GROUP BY (af.idAvion));
END;

-- appel de la procédure majHeuresVolAvion
CALL majNbHeuresVolAvion();

--  afficher le contenu de la table avion
SELECT * FROM AVION;

-- q4.4

-- supppression du trigger si déjà existant
DROP TRIGGER update_nbHeuresVol;

-- création du 1er trigger permettant de mettre à jour automatiquement le nombre d'heures de vol de l'avion
-- après un ajout dans la table affectation
CREATE OR REPLACE TRIGGER update_nbHeuresVol
AFTER INSERT OR UPDATE ON affectation
FOR EACH ROW
DECLARE heures NUMBER(10,2);
BEGIN
SELECT dureeVol INTO heures
FROM vol
WHERE idVol = :NEW.idVol;
UPDATE avion
SET nbHeuresVol = nbHeuresVol + heures
WHERE idAvion = :new.idAvion;
END;

-- affichage du contenu de la table avion
SELECT * FROM avion;

DROP TRIGGER removeNbHeuresVol;
-- création du 2eme trigger
-- avant un retrait dans la table affectation
CREATE OR REPLACE TRIGGER removeNbHeuresVol
BEFORE DELETE ON affectation
FOR EACH ROW
DECLARE heures NUMBER(10,2);
BEGIN
SELECT dureeVol INTO heures
FROM vol
WHERE idVol = :new.idVol;

UPDATE avion
SET nbHeuresVol = nbHeuresVol - heures
WHERE idAvion = :new.idAvion;
END;

-- affichage du contenu de la table avion
SELECT * FROM avion;

DROP TRIGGER modifNbHeuresVol;
-- création du 3eme trigger
-- avant une modification d'un vol dans la table affectation
-- ça consiste à ajouter les heures du nouveau vol et supprimer celles de l'ancien vol
CREATE OR REPLACE TRIGGER modifNbVol
BEFORE UPDATE ON affectation
FOR EACH ROW
DECLARE heures NUMBER(10,2);
BEGIN
IF :old.idAvion != :new.idAvion
THEN
SELECT dureeVol INTO heures
FROM vol
WHERE idVol = :new.idVol;

UPDATE avion
SET nbHeuresVol = nbHeuresVol + heures
WHERE idAvion = :new.idAvion;

UPDATE avion
SET nbHeuresVol = nbHeuresVol - heures
WHERE idAvion = :old.idAvion;
END IF;
END;

-- affichage du contenu de la table avion
SELECT * FROM avion;

/* ajout du vol */
INSERT INTO AFFECTATION (IDVOL,DATEVOL,IDPILOTE,IDAVION,NBPASSAGERS) values ('AL4520',to_date('25/04/19','DD/MM/RR'),'0007','8807','200');

/* annulation/suppression du vol */

/***
* Monsieur, je tiens à vous signaler que je suis resté travailler après la séance de jeudi 11 
* à 8h15, et que vers 10h30 environ, Oracle ne répondait plus à certaines commmandes, comme 
* les plus basiques, INSERT, DELETE (pourtant la syntaxe est bonne).
* Cela pourra vous être confirmé par Aude qui était en salle avec moi.
* Je n'ai pas pu tester le DELETE ci-dessous, malgré mes multiples essais.
***/

-- j'ai mis toutes les données présentes sur cette ligne pour être sûr qu'on supprime le bon vol
DELETE FROM affectation
WHERE (idVol = 'AL4520' AND 
      dateVol = '25/04/19' AND
      idPilote = '0007' AND
      idAvion = '8807' AND
      NbPassagers = '200');


-- Autre méthode :
-- également, j'ai volontairement mis toutes les informations présente sur la ligne
-- pour éviter de se tromper de vol.

-- cette méthode consiste à faire une requête classique de sélection des informations que l'on veut,
SELECT * FROM AFFECTATION
WHERE (idVol = 'AL4520' AND 
      dateVol = '25/04/19' AND
      idPilote = '0007' AND
      idAvion = '8807' AND
      NbPassagers = '200');
-- puis on fais un ROLLBACK; ce qui permet d'inverser l'action précédemment faite.
-- Ici l'inverse d'un SELECT [...] est un DELETE [...]
ROLLBACK;

/* modification de l'avion qui remplace celui du vol AL4520 programmé le 25/04/2019 */
UPDATE affectation
SET idavion = '8803'
WHERE (idVol = 'AL4520' AND 
dateVol = '25/04/2019' AND
idAvion = '8807');
