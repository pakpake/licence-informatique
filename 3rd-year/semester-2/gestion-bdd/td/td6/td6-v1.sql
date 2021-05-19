---
title: TD6 - BDD
date: 22-04-2021
---

# Partie 1 - Plans d exécution de requêtes

## Préparation du TP

Vous devez la créer au préalable en exécutant le script `utlxplan.sql`.

```sql
--output: Table PLAN_TABLE créé(e).
```

Lancez le script `creerTablesPlanExec.sql` pour créer les tables dans votre schéma.

```sql
--output: Toutes les tables ont été créé et toutes les lignes insérées.
```

On peut aussi voir cela en affichant l ensemble des tables, ou en comptant le nombre de ligne des tables.

## Examine un plan d execution de requête

Pour examiner un plan d exécution de requête, vous allez procéder de la manière suivante :

### R1

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R1'
FOR
SELECT count(*) FROM salaries ;
COMMIT ;

--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R1'
ORDER BY id;
```
Voir fig1

![fig1](images/p1-consult-res.png)

* ::: pour connaître l’option d’optimisation

```sql
SELECT optimizer FROM plan_table
WHERE statement_id = 'R1';
```

Voir fig2

![fig2](images/p1-opt-opti.png)


### R2

```sql
CREATE INDEX idxIdSalarieSalarie ON salaries (idSalarie);
--output: Index IDXIDSALARIESALARIE créé(e).
```

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R2'
FOR
SELECT count(*) FROM salaries ;
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R2'
ORDER BY id;
```
Voir fig3

![fig3](images/p1-consult-res-r2.png)

* ::: pour connaître l’option d’optimisation

```sql
SELECT optimizer FROM plan_table
WHERE statement_id = 'R2';
```

Voir fig4

![fig4](images/p1-opt-opti-r2.png)

Supprimez l’index sur l’attribut idSalarie de la table salaries :

```sql
DROP INDEX idxIdSalarieSalarie;
--output: Index IDXIDSALARIESALARIE supprimé(e).
```

Ajoutez la contrainte de clé primaire pkSalarieIdSalarie sur l’attribut idSalarie de la table salaries (ie on indexe cet attribut avec un arbre B+) :

```sql
ALTER TABLE salaries ADD CONSTRAINT pkSalarieIdSalarie PRIMARY
KEY (idSalarie);
--output: Tables SALARIES modifié(e).
```

### R3

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R3'
FOR
SELECT count(*) FROM salaries ;
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```


* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R3'
ORDER BY id;
```
Voir fig5

![fig5](images/p1-consult-res-r3.png)

Passez l’optimiseur en mode RULE :

```sql
ALTER SESSION SET OPTIMIZER_MODE= RULE;
--output: Session modifié(e).
```

### R4

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R4'
FOR
SELECT count(*) FROM salaries ;
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R4'
ORDER BY id;
```
Voir fig5

![fig5](images/p1-consult-res-r4.png)
   
Remettez l’optimiseur en mode ALL_ROWS :

```sql
ALTER SESSION SET OPTIMIZER_MODE= ALL_ROWS;
--output: Session modifié(e).
```
### R5

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R5'
FOR
SELECT * FROM salaries ;
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R5'
ORDER BY id;
```
Voir fig6

![fig6](images/p1-consult-res-r5.png)

### R6

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R6'
FOR
SELECT * FROM salaries WHERE iDSalarie = 5000;
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R6'
ORDER BY id;
```
Voir fig7

![fig7](images/p1-consult-res-r6.png)

### R7

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R7'
FOR
SELECT * FROM salaries WHERE ename = 'SCOTT';
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R7'
ORDER BY id;
```
Voir fig8

![fig8](images/p1-consult-res-r7.png)


Créez un index idxEnameSalaries sur l’attribut ename de la table salaries :

```sql
CREATE INDEX idxEnameSalaries ON salaries (ename);
--output: Index IDXENAMESALARIES créé(e).
```

Pour chacune des options de l optimiseur : ALL_ROWS, FIRST_ROWS, RULE, donnez l algorithme correspondant au plan d exécution de la requête :

* version ALL_ROWS

```sql
ALTER SESSION SET OPTIMIZER_MODE= ALL_ROWS
--output: Session modifié(e).
```

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R8'
FOR
SELECT * FROM salaries WHERE ename = 'SCOTT';
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R8'
ORDER BY id;
```
Voir fig9

![fig9](images/p1-consult-res-r8.png)

* version FIRST_ROWS

```sql
ALTER SESSION SET OPTIMIZER_MODE= FIRST_ROWS;
--output: Session modifié(e).
```

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R8'
FOR
SELECT * FROM salaries WHERE ename = 'SCOTT';
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R8'
ORDER BY id;
```
Voir fig10

![fig10](images/p1-consult-res-r8-v2.png)


* version RULE

```sql
ALTER SESSION SET OPTIMIZER_MODE= RULE
--output: Session modifié(e).
```

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R8'
FOR
SELECT * FROM salaries WHERE ename = 'SCOTT';
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R8'
ORDER BY id;
```
Voir fig11

![fig11](images/p1-consult-res-r8-v3.png)

### R9

Insérez un salarié de nom ‘PETIT’ dans la table salaries :

``sql
INSERT INTO Salaries values (100000, 'PETIT', 'CLERK', 1000, 3);
--output: 1 ligne inséré.
```



Pour l option de l’optimiseur ALL_ROWS : donnez l algorithme correspondant au plan d exécution de la requête :

```sql
ALTER SESSION SET OPTIMIZER_MODE= ALL_ROWS
--output: Session modifié(e).
```

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R9'
FOR
SELECT * FROM salaries WHERE ename = 'PETIT';
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R9'
ORDER BY id;
```
Voir fig12

![fig12](images/p1-consult-res-r9.png)

### R10

Pour chacune des options de l optimiseur ALL_ROWS, FIRST_ROWS, RULE : donnez l algorithme correspondant au plan d exécution de la requête :

* version ALL_ROWS

```sql
ALTER SESSION SET OPTIMIZER_MODE= ALL_ROWS
--output: Session modifié(e).
```

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R10'
FOR
SELECT * FROM salaries,services
WHERE salaries.idService=services.idService;
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R10'
ORDER BY id;
```
Voir fig13

![fig13](images/p1-consult-res-r10.png)

* version FIRST_ROWS

```sql
ALTER SESSION SET OPTIMIZER_MODE= FIRST_ROWS;
--output: Session modifié(e).
```

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R10'
FOR
SELECT * 
FROM salaries, services
WHERE salaries.idService=services.idService;
COMMIT;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R10'
ORDER BY id;
```
Voir fig14, j ai des doublons, mais le résultat est là.

![fig14](images/p1-consult-res-r10-v2.png)


* version RULE

```sql
ALTER SESSION SET OPTIMIZER_MODE= RULE
--output: Session modifié(e).
```

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R10'
FOR
SELECT *
FROM salaries, services
WHERE salaries.idService=services.idService;
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R10'
ORDER BY id;
```
Voir fig15, encore une fois j ai de plus en plus de doublons, mais le résultat est là.

![fig15](images/p1-consult-res-r10-v3.png)

### R11

Modifiez la table services de manière à ce que l’attribut idService soit clé (contrainte pkServicesIdService) :

```sql
ALTER TABLE services ADD CONSTRAINT pkServicesIdService PRIMARY KEY (idService);
--output: Table SERVICES modifié(e).
```

Pour chacune des options de l optimiseur ALL_ROWS, FIRST_ROWS, RULE : donnez  l algorithme correspondant au plan d exécution de la requête :

* version ALL_ROWS

```sql
ALTER SESSION SET OPTIMIZER_MODE= ALL_ROWS
--output: Session modifié(e).
```

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R11'
FOR
SELECT * FROM salaries,services
WHERE salaries.idService=services.idService;
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R11'
ORDER BY id;
```
Voir fig16

![fig16](images/p1-consult-res-r11.png)

* version FIRST_ROWS

```sql
ALTER SESSION SET OPTIMIZER_MODE= FIRST_ROWS;
--output: Session modifié(e).
```

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R11'
FOR
SELECT * 
FROM salaries, services
WHERE salaries.idService=services.idService;
COMMIT;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R11'
ORDER BY id;
```
Voir fig17, j ai des doublons, mais le résultat est là.

![fig17](images/p1-consult-res-r11-v2.png)


* version RULE

```sql
ALTER SESSION SET OPTIMIZER_MODE= RULE;
--output: Session modifié(e).
```

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R11'
FOR
SELECT *
FROM salaries, services
WHERE salaries.idService=services.idService;
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R11'
ORDER BY id;
```
Voir fig18, encore une fois j ai de plus en plus de doublons, mais le résultat est là.

![fig18](images/p1-consult-res-r11-v3.png)

### R12

Créez un index idxIdServiceSalaries sur l attribut idService de la table salaries :

```sql
CREATE INDEX idxIdServiceSalaries ON salaries (idService);
--output: Index IDXIDSERVICESALARIES créé(e).
```

Pour chacune des options de l optimiseur ALL_ROWS, FIRST_ROWS, RULE : donnez l’algorithme correspondant au plan d exécution de la requête :

* version ALL_ROWS

```sql
ALTER SESSION SET OPTIMIZER_MODE= ALL_ROWS;
--output: Session modifié(e).
```

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R12'
FOR
SELECT * FROM salaries,services
WHERE salaries.idService=services.idService;
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R12'
ORDER BY id;
```
Voir fig19

![fig19](images/p1-consult-res-r12.png)

* version FIRST_ROWS

```sql
ALTER SESSION SET OPTIMIZER_MODE= FIRST_ROWS;
--output: Session modifié(e).
```

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R12'
FOR
SELECT * 
FROM salaries, services
WHERE salaries.idService=services.idService;
COMMIT;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R12'
ORDER BY id;
```
Voir fig20, j ai des doublons, mais le résultat est là.

![fig20](images/p1-consult-res-r12-v2.png)

* version RULE

```sql
ALTER SESSION SET OPTIMIZER_MODE= RULE;
--output: Session modifié(e).
```

* ::: réalisation du plan

```sql
EXPLAIN PLAN
SET statement_id = 'R12'
FOR
SELECT *
FROM salaries, services
WHERE salaries.idService=services.idService;
COMMIT ;
--output: Explicité. Validation (commit) terminée.
```

* ::: consultation du résultat

```sql
SELECT operation, options, id, parent_id, object_name
FROM plan_table
WHERE statement_id = 'R12'
ORDER BY id;
```
Voir fig21, encore une fois j ai de plus en plus de doublons, mais le résultat est là.

![fig21](images/p1-consult-res-r12-v3.png)

## Pseudo-code correspondant aux requêtes SQL données auparavant

* R1

```txt
résultat = 0
Pour tous les tuple tSalarié de la table salarie, faire :
    obtenir le tuple tSalarié
    résultat += 1
FinPour
```

* R2

De la même manière que R1

* R3

```txt
résultat = 0
Parcourt de l index primarykey_Salarield_Salarie, position de l index sur le premier sous-élément :
Pour chaque sous-élément de cet index faire:
    résultat += 1
FinPour
```

* R4

De la même manière que R1

* R5

```txt
résultat = 0
Pour chaque tuple nommé tSalarie de la table salarie faire :
    obtenir le tuple tSalarie
    résultat += le_tuple_tSalarie
FinPour
```

* R6

```txt
résultat = ensembe_vide (ou None)

Parcourt de l index primary_key_Salarield_Salarie et position de l index sur la feuille, tel que salarie.idSalarie = 5000

lire le tuple tSalarie
obtenir le tuple tSalarie
résultat += tuple_tSalarie
```

* R7 

```txt
resultat = none
pour chaque tuple tSalarie de la table salarie faire :
    obtenir tuple_tSalarie
    Si tSalarie.ename = 'SCOTT', alors :
        resultat += tuple_tSalarie
    FinSi
FinPour
```

* R8

```txt
pour ALL_ROWS : de la même manière que R7
pour FIRST_ROWS, RULE, faire :

resultat = None

parcourt de l index idxEnameSalaries, position de celui-ci sur la feuille telle que salarie.ename = 'SCOTT'

Pour chaque tuple tSalarie tel que salarie.ename='SCOTT' faire :
    lecture tuple_tSalarie
    obtention tuple_tSalarie
    resultat +=  tuple_tSalarie
FinPour
```

* R9

```txt
resultat = none/vide

parcourt de l index idxEnameSalaries, position de celui-ci sur la feuille telle que salarie.ename = 'PETIT'

Pour chaque tuple tSalarie tel que salarie.ename='PETIT' faire :
    lecture tuple_tSalarie
    obtention tuple_tSalarie
    resultat +=  tuple_tSalarie
FinPour

Faire de la même manière pour les autres options, FIRST_ROWS et RULE.
```

* R10

```txt
Pour chaque tuple tService de la table services faire :
    obtenir tService
    numPaquet = h (tService.idService)
    insertion tService dans le paquet numPaquet
FinPour

resultat = none
Pour chaque tuple tSalarie de la table salaries faire :
    obtenir tSalarie
    numPaquet = h (tSalarie.idService)
    rechercher dans le paquet numPaquet le tuple tService/tService.idService=tSalarie.idService
    resultat += < tSalarie, tService >
FinPour

resultat = none
obtenir tService
obtenir tSalarie
TantQue existe(tService) et existe(tSalarie) répéter :
    Si tService.idService=tSalarie.idService, Alors :
  resultat += < tSalarie, tService >
  obtenir tSalarie
    Sinon
  obtenir tService
    FinSi
FintTantQue
```


* R11

```txt
resultat = none

Pour chaque tuple tSalaries de la table salaries faire :
obtenir tSalarie

    Parcourir de  lindex primary_key_Services_IdService, position de celui-ci sur la feuille telle que service.idService=tSalarie.idService

    lire tService
    obtenir tService
    resultat += < tSalarie, tService >
FinPour
```

* R12

```txt
pour FIRST_ROWS, RULE :

resultat = none

Pour chaque tuple tService de la table services faire :
    obtenir tService
    
    parcourt de l index idxIdServiceSalaries, position de celui-ci sur la feuille telle que salarie.idService=tService.idService


    Pour chaque tuple tSalarie tel que salarie.idService=tService.idService faire :
  lire tSalarie
  obtenir tSalarie
  resultat += < tSalarie, tService >
    FinPour
FinPour
```


# Partie 2 - Optimisation des ressources de mémoire : calcul de ratios

* optimisation de la mémoire - shared pool area, zone LC : PIN RATIO

```sql
SELECT SUM(RELOADS)/SUM(PINS) FROM v$librarycache;
```

Voir figP2-1

![figP2-1](images/p2-1.png)


* optimisation de la mémoire - shared pool area , zone DC : GET RATIO

```sql
SELECT SUM(GETMISSES)/SUM(GETS) FROM V$ROWCACHE;
```

Voir figP2-2
  
![figP2-2](images/p2-2.png)

* optimisation de la mémoire - buffer de données : HIT RATIO
  
```sql
SELECT 1-(A.VALUE/(B.VALUE+C.VALUE)) FROM V$SYSSTAT A, V$SYSSTAT B, V$SYSSTAT C
WHERE A.NAME='physical reads' 
AND B.NAME='db block gets' 
AND C.NAME='consistent gets';
```

Voir figP2-3
  
![figP2-3](images/p2-3.png)

* optimisation de la mémoire - buffer de reprise : KEY RATIO

```sql
SELECT A.VALUE/B.VALUE FROM V$SYSSTAT A, V$SYSSTAT B
WHERE A.NAME='redo log space requests' 
AND B.NAME='redo entries';
```

Voir figP2-4
  
![figP2-4](images/p2-4.png)


