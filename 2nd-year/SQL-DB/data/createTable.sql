DROP TABLE consultation;
DROP TABLE personne;
DROP TABLE medecin;
DROP TABLE maladie;


CREATE TABLE personne
(
idPers NUMBER
  CONSTRAINT personne_idPers_Positif CHECK (idPers > 0),
nomPers VARCHAR2(40)
  CONSTRAINT personne_nomPers_NotNull NOT NULL,
villePers VARCHAR2(30)
  CONSTRAINT personne_villePers_NotNull NOT NULL,
CONSTRAINT personne_PK PRIMARY KEY (idPers)
);


CREATE TABLE medecin
(
idMed NUMBER
  CONSTRAINT medecin_idMed_Positif CHECK (idMed > 0),
nomMed VARCHAR2(40)
  CONSTRAINT medecin_nomMed_NotNull NOT NULL,
villeMed VARCHAR2(30)
  CONSTRAINT medecin_villeMed_NotNull NOT NULL,
specialite VARCHAR2 (40),
CONSTRAINT medecin_PK PRIMARY KEY (idMed)
);

/*==========================*/
/*BDD - TD 1 - SQL - Oracle */
/*==========================*/

--Q1)
CREATE TABLE maladie
(
idMal NUMBER
    CONSTRAINT maladie_idMal_positif CHECK (idMal > 0),
nomMal VARCHAR2(30)
    CONSTRAINT maladie_nomMal_NotNull NOT NULL,
symptomes VARCHAR2(30)
    CONSTRAINT symptomes_NotNull NOT NULL,
CONSTRAINT maladie_PK PRIMARY KEY (idMal)
);

CREATE TABLE consultation
(
idPers NUMBER,
idMed NUMBER,
idMal NUMBER,
dateConsult DATE
  CONSTRAINT dateconsult_NotNull NOT NULL,
CONSTRAINT consultation_idPers_FK FOREIGN KEY(idPers) REFERENCES personne(idPers),
CONSTRAINT consultation_idMed_FK FOREIGN KEY(idMed) REFERENCES medecin(idMed),
CONSTRAINT consultation_idMal_FK FOREIGN KEY(idMal) REFERENCES maladie(idMal)
);

--Q2) ajoute de 
ALTER TABLE consultation
ADD tarifConsult NUMBER
  CONSTRAINT tarifConsult_Positif CHECK (tarifConsult > 0)
  CONSTRAINT tarifConsult_NotNull NOT NULL;

--Q3) execution des tables personnes.sql, medecin.sql, maladie.sql
--Ins�rez dans la table le contenu de la table raphalen.consultation dans le fichier creerTables.sql
INSERT INTO consultation 
SELECT *
FROM RAPHALEN.consultation;

--Q4)Ins�rez les tuples suivants :
--dans la table personne
INSERT INTO personne (villePers, nomPers, idPers) 
VALUES ('Nantes', 'Legrand', 5);
INSERT INTO personne (villePers, nomPers, idPers) 
VALUES (NULL, 'Nerveux', 15);
--erreur car constaint= not null

--dans la table consultation
INSERT INTO consultation (tarifConsult, dateConsult, idMed, idMal, idPers)
VALUES (27, '05/09/2019', 4, 6, 1);

--Supprimez le m�decin d�identifiant 5 de la table medecin
DELETE FROM medecin
WHERE idMed = 5;

--D�sactivez la contrainte d�int�grit� r�f�rentielle sur l�attribut idMed dans la table consultation.
--sous oracle cela devrait fonctionner pour disable & enable
ALTER TABLE consultation DISABLE CONSTRAINT consultation_idMed_FK;

--reactivez la contrainte d’integrite referentielle sur l'attribut idMed dans la table consultation.
ALTER TABLE consultation ENABLE CONSTRAINT consultation_idMed_FK;

INSERT INTO medecin (idMed, nomMed, villeMed, specialite)
VALUES (5,'Killeur','Vannes','ophtalmologie');

--reactivation (sous oracle ca devrait marcher)
ALTER TABLE consultation ENABLE CONSTRAINT consultation_idMed_FK;

--augmenter de 1.5% le tarif des consultations
UPDATE consultation
SET tarifConsult = tarifConsult * 1.015;

--Augmentez de 2€ le tarif de consultation des médecins de spécialite 'chirurgie'
--sous oracle ca devrait marcher
UPDATE consultation, medecin
SET tarifConsult = tarifConsult + 2
WHERE consultation.idMed = medecin.idMed and medecin.specialite = chirurgie;

--autre methode (ne marche pas surslqite3)
UPDATE consultation INNER JOIN medecin on(consultation.idMed = medecin.idMed)
SET tarifConsult = tarifConsult + 2
WHERE medecin.specialite = chirurgie;

--Remplissez à nouveau la table consultation avec les tuples de la table RAPHALEN.consultation
--demander la table à la prof

--Q5) Requetes de consultation
--5.1) a faire sous oracle : select * from "toutes les tables separees"

--5.2)Quel est l’ensemble des identifiants des personnes qui ont consulté le médecin de nom ‘Boucher’ ? Quels sont leurs noms ?

SELECT p.idPers, p.nomPers
FROM personne p, medecin m, consultation c
WHERE p.idPers=c.idPers and m.idMed=c.idMed and m.nomMed='Boucher';

--5.3)Quels sont les identifiants et villes des médecins qui ont reçu des personnes de ‘Vannes’ ?

SELECT m.idMed, m.villeMed
FROM medecin m, personne p, consultation c
WHERE m.idMed=c.idMed and p.idPers=c.idPers and p.villePers='Vannes';

--5.4) Quel est le nombre de consultations par personne ?

SELECT p.nomPers, COUNT(*)
FROM consultation c, personne p
where p.idPers=c.idPers
GROUP by c.idPers;

--5.5) Quelles sont les personnes ayant effectuees au moins 5 consultations ?
SELECT p.nomPers, count(*)
from consultation c, personne p
WHERE p.idPers=c.idPers
GROUP BY c.idPers
HAVING count(*) >= 5;

--5.6) Quelles sont les personnes ayant consulté au moins 3 médecins différents ?

SELECT p.nomPers
from consultation c, personne p
WHERE p.idPers=c.idPers
GROUP BY c.idPers
HAVING count(DISTINCT c.idMed) >= 3;

--5.7) Quels est le nombre total de consultations réalisées, les tarifs minimum, maximum et moyen des consultations ?
select count(*), MIN(tarifConsult), MAX(tarifConsult), AVG(tarifConsult)
FROM consultation;

--5.8) Quels est, par médecin : le nombre de consultations réalisées, les tarifs minimum, maximum et moyen des consultations ?

select m.nomMed, count(*), MIN(tarifConsult), MAX(tarifConsult), AVG(tarifConsult)
FROM consultation c, medecin m
WHERE c.idMed=m.idMed
GROUP BY c.idMed;

--5.9) Quels sont les médecins qui pratiquent le tarif minimum de consultation ?

select DISTINCT m.nomMed
FROM medecin m, consultation c
WHERE c.idMed=m.idMed and c.tarifConsult=
        (select MIN(tarifConsult)
         from consultation
        );

--5.10) Quelles sont les personnes ayant consulté pour la grippe ?
SELECT DISTINCT p.nomPers
FROM personne p, maladie m, consultation c
WHERE p.idPers=c.idPers and m.idMal=c.idMal and m.nomMal='grippe';

--5.11) Quelles sont les personnes ayant consulté uniquement pour la grippe ?
 /*---------------NE MARCHE PAS-----------------------*/
 SELECT DISTINCT p.nomPers
 FROM personne p, maladie m, consultation c
 WHERE p.idPers=c.idPers and m.idMal=c.idMal and m.nomMal <>
    (SELECT DISTINCT nomMal
    FROM maladie m
    WHERE m.nomMal <> 'grippe'
    );

---------------------------------
SELECT DISTINCT p.nomPers
 FROM personne p, maladie m, consultation c
 WHERE p.idPers=c.idPers and m.idMal=c.idMal and m.nomMal != (
    SELECT DISTINCT nomMal
    FROM maladie m
    WHERE m.nomMal != 'grippe'
    );

--5.12) Quelles sont les personnes n’ayant jamais consulté ? Quelles sont les personnes n’ayant jamais consulté pour la grippe ?







--5.13) Quelles sont les personnes qui ont consulté des médecins de Vannes pour la grippe ?


