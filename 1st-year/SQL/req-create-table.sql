-- Script td-tp1-partie1 (BDD-Cinema)

-- I Avec deux tables

-- Q1 : Liste des films (exclusivement) fran�ais (titre, ann�e, r�alisateur).
   SELECT TITRE, ANNEE, REALISATEUR
   FROM   LESFILMS
   WHERE  PAYS = 'France';

-- Q2 : Liste des films fran�ais et italiens (titre, ann�e, r�alisateur).
   SELECT TITRE, ANNEE, REALISATEUR
   FROM   LESFILMS
   WHERE  PAYS = 'France' OR PAYS = 'Italie';
   
-- Q3 : Les titres des films fran�ais d'avant 1990.
   SELECT TITRE
   FROM   LESFILMS
   WHERE  PAYS = 'France'
      AND ANNEE < 1990;
   
-- Q4 : Liste des films fran�ais ou partiellement (titre, ann�e, r�alisateur).
   SELECT TITRE, ANNEE, REALISATEUR
   FROM   LESFILMS
   WHERE  PAYS LIKE '%France%';
   
-- Q5 : La liste des films (titre, ann�e) r�alis�s par Pasolini, par ordre chronologique (du plus ancien au plus r�cent).
   SELECT   TITRE, ANNEE
   FROM     LESFILMS
   WHERE    REALISATEUR LIKE 'Pasolini'
   ORDER BY ANNEE ASC;
   
-- Q6 : La dur�e moyenne des films italiens.
   SELECT AVG(DUREE)
   FROM   LESFILMS
   WHERE  PAYS = 'Italie';
   
-- Q7 : Le nombre de films r�alis�s par Pasolini.
   SELECT COUNT(IdF)
   FROM   LESFILMS
   WHERE  REALISATEUR = 'Pasolini';
   
-- Q8 : Le nombre de films par pays, class�s par ordre d�croissant de ce nombre.
   SELECT   COUNT(IdF), PAYS
   FROM     LESFILMS
   GROUP BY PAYS
   ORDER BY COUNT(IdF) DESC;
   
-- Q9 : La liste des r�alisateurs qui ont r�alis� au moins 2 films.
   SELECT   COUNT(IdF), REALISATEUR
   FROM     LESFILMS
   GROUP BY REALISATEUR
   HAVING COUNT(IdF) >= 2;
   
-- Q10 : Le nombre de films par pays o� il y a eu au moins deux films.
   SELECT   COUNT(IdF), PAYS
   FROM     LESFILMS
   GROUP BY PAYS
   HAVING COUNT(IdF) >= 2;
   
-- Q11 : Les titres des films qui sont utilis�s plusieurs fois (i.e. tels qu'il y a au moins deux films diff�rents sous ce titre).
   SELECT   COUNT(IdF), TITRE
   FROM     LESFILMS
   GROUP BY TITRE
   HAVING COUNT(IdF) >= 2;
   
-- Q12 : La dur�e du film le plus court.
   SELECT MIN(DUREE)
   FROM   LESFILMS;
   
-- Q13 : Le titre du film le plus court.
   SELECT TITRE, DUREE
   FROM   LESFILMS
   WHERE  DUREE IN
          (SELECT MIN(DUREE)
           FROM LESFILMS);
		   
-- Q14 : La liste des acteurs, avec pour chacun d'entre eux, le nombre de films dans lesquels il a jou�.

-- Jointure :
   SELECT   COUNT(LESFILMS.IdF), ACTEUR
   FROM     LESFILMS, LESACTEURS
   WHERE    LESFILMS.IdF = LESACTEURS.IdF
   GROUP BY ACTEUR;
   
-- Avec raccourci :
   SELECT   COUNT(F.IdF), ACTEUR
   FROM     LESFILMS F, LESACTEURS A
   WHERE    F.IdF = A.IdF
   GROUP BY ACTEUR;
   
-- Q15 : La liste de tous les acteurs ayant jou� dans des films fran�ais.
   SELECT PAYS, ACTEUR
   FROM   LESFILMS F, LESACTEURS A
   WHERE  F.IdF = A.IdF
      AND F.PAYS = 'France';
   
-- Q16 : Les titres des films avec Piccoli.
   SELECT TITRE, ACTEUR
   FROM   LESFILMS F, LESACTEURS A
   WHERE  F.IdF = A.IdF
      AND A.ACTEUR = 'Piccoli';
   
-- Q17 : Les acteurs qui ont jou� dans un film tourn� depuis l'ann�e 1990.
   SELECT ANNEE, ACTEUR
   FROM   LESFILMS F, LESACTEURS A
   WHERE  F.IdF = A.IdF
      AND F.ANNEE >= 1990;
   
-- Q18 : Les acteurs qui ont jou� dans des films r�alis�s par Pasolini.
   SELECT REALISATEUR, ACTEUR
   FROM   LESFILMS F, LESACTEURS A
   WHERE  F.IdF = A.IdF
      AND F.REALISATEUR LIKE 'Pasolini'; 
      
-- TO

---Q19 : ACTEURS AYANT JOUE DANS DES FILMS FRANCAIS ET ETRANGER

SELECT  ACTEUR                                                                                                                            
FROM LESFILMS F,LESACTEURS A
Where F.IdF=A.IdF 
    AND PAYS = 'France'
INTERSECT
    SELECT ACTEUR                                                                                                                                
    FROM LESFILMS F,LESACTEURS A
    Where PAYS <> 'France'
        AND F.IdF=A.IdF;

---Q20 : La liste des acteurs qui n'ont jou� que dans des films francais 

SELECT  ACTEUR,TITRE,PAYS                                                                                                                          
FROM LESFILMS F,LESACTEURS A
Where F.IdF=A.IdF 
    AND PAYS = 'France'
MINUS
    SELECT ACTEUR,TITRE,PAYS
    FROM LESFILMS F,LESACTEURS A
    Where PAYS <> 'France'
        AND F.IdF=A.IdF;
		
		
--Q21 : Les acteurs qui sont aussi realisateurs

SELECT  ACTEUR,REALISATEUR                                                                                                                      
FROM LESFILMS F,LESACTEURS A
Where  F.REALISATEUR = A.ACTEUR;

--Q22 : Les acteurs qui n'ont jou� dans aucun film depuis 1980

SELECT  ACTEUR                                                                                                                       
FROM LESFILMS F,LESACTEURS A
Where F.IdF=A.IdF 
MINUS
SELECT  ACTEUR                                                                                                                        
FROM LESFILMS F,LESACTEURS A
Where F.IdF=A.IdF 
    AND F.ANNEE > 1980;
	
--Q23 : La liste des films avec leur titre avec pour chacun d'entre eux le nombre d'acteurs 

SELECT  TITRE,COUNT(A.IdF)                                                                                                                      
FROM LESFILMS F,LESACTEURS A
Where  F.Idf=A.IdF
Group by TITRE;


--II AVEC DEUX AUTRE TABLE
--2(a) : La liste des films (titre,realisateur,ann�e) actuellement a l'affiche
SELECT TITRE,REALISATEUR,ANNEE
FROM LESFILMS F,LESACTEURS A,FILMS_SALLES P,SALLESCINEMA S
WHERE S.IdS=P.IdS
AND P.IdF=F.IdF
GROUP BY TITRE,REALISATEUR,ANNEE;
--2(b) : La liste des salles et des villes ou on peut voir un film avec belmondo
SELECT NOM,VILLE,ACTEUR
FROM LESFILMS F,LESACTEURS A,FILMS_SALLES S,SALLESCINEMA C
WHERE C.IdS=S.IdS
AND S.IdF=F.IdF
AND F.IdF=A.IdF
AND ACTEUR='Belmondo';
--2(c) : La liste des villes avec pour chacune d'entre elle le nombre dde films differents qui sont a l'affiche, les villes etant rang�es par ordre d�croissant de ce nombre 
SELECT VILLE,COUNT(DISTINCT S.IdF)
FROM LESFILMS F,LESACTEURS A,FILMS_SALLES S,SALLESCINEMA C
WHERE C.IdS=S.IdS
AND S.IdF=F.IdF
GROUP BY VILLE
ORDER BY COUNT( S.IdF) DESC ;

--2(d) Les films qui passent dans au moins 2 villes different 

SELECT TITRE,REALISATEUR,ANNEE,COUNT(DISTINCT S.IdS)
FROM LESFILMS F,LESACTEURS A,FILMS_SALLES S,SALLESCINEMA C
WHERE C.IdS=S.IdS
AND S.IdF=F.IdF
GROUP BY TITRE,REALISATEUR,ANNEE
HAVING COUNT(DISTINCT S.IdS)>=2;

--2(e) Les salles sans film

SELECT VILLE,NOM
FROM SALLESCINEMA C
GROUP BY VILLE,NOM
MINUS
SELECT VILLE,NOM
FROM SALLESCINEMA C
INNER JOIN FILMS_SALLES ON C.IdS =  FILMS_SALLES.IdS ;