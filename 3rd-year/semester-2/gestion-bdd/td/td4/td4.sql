--- 
title: BDD - TD4
date: 25-03-2021
---

# Partie 1 : Tablespaces

## Question 1

La commande suivante nous permet de voir quelques caractéristiques des tablespaces :

```sql
SELECT * FROM DBA_TABLESPACES;
```

Voir `fig 1`

![fig 1](pics/1.png)



## Question 2 

La commande suivante nous permet d avoir plus de caractéristiques des tablespaces :

```sql
SELECT tablespace_name "Fichiers associés", SUM (bytes/1024) "Espace Total (Ko)", sum(blocks) "Espace Total (blocs)"
FROM dba_data_files
GROUP BY tablespace_name;
```

Voir `fig 2`

![fig 2](pics/2.png)

La commande suivante nous permet de connaître également le taux d'occupation de chaque tablespace, ainsi que taille restante par rapport à la taille totale qu'il occupe

```sql
SELECT DF.tablespace_name, DF.total, DS.libre, ((DF.total-DS.libre)*100/DF.total) "Taux d occupation (%)"
FROM
(SELECT tablespace_name, SUM(bytes) total FROM dba_data_files GROUP BY tablespace_name) DF,
(SELECT tablespace_name, SUM(bytes) libre FROM dba_free_space GROUP BY tablespace_name) DS
WHERE DF.tablespace_name = DS. tablespace_name;
```

Voir `fig 3`

![fig 3](pics/3.png)

Caractéristiques précises des tablespaces :

La commande suivante nous permet de voir la taille max de chaque tablespaces, son pourcentage d'utilisation et d'autres informations intéressantes.

```sql
select
    a.TABLESPACE_NAME,
    round(((nvl(sum(b.bytes),0)-nvl(sum(c.free_bytes),0)) / nvl(sum(b.maxbytes),0))*100, 2)||' %' "% Utilisation",
    --a.EXTENT_MANAGEMENT,
    --a.ALLOCATION_TYPE,
    --a.BIGFILE,
    nvl(sum(b.bytes),0)/(1024*1024)||' Mo' "Taille",
    nvl(sum(b.maxbytes),0)/(1024*1024)||' Mo' "Taille Max",
    round((nvl(sum(b.bytes),0)-nvl(sum(c.free_bytes),0))/(1024*1024),1)||' Mo'  "Utilisés",
    nvl(sum(b.count_files),0) "Nb fichiers",
    a.CONTENTS,
    a.SEGMENT_SPACE_MANAGEMENT,
    a.STATUS "Statut"
from DBA_TABLESPACES a,
    (
    select TABLESPACE_NAME,
        sum(BYTES) bytes,
        count(*) count_files,
        sum(greatest(MAXBYTES,BYTES)) maxbytes
    from DBA_DATA_FILES
    group by TABLESPACE_NAME
    union all
    select TABLESPACE_NAME,
        sum(BYTES),
        count(*),
        sum(greatest(MAXBYTES,BYTES)) maxbytes
    from DBA_TEMP_FILES
    group by TABLESPACE_NAME
    ) b,
    (
    select TABLESPACE_NAME,
        sum(BYTES) free_bytes
    from DBA_FREE_SPACE
    group by TABLESPACE_NAME
    union all
    select TABLESPACE_NAME,
        sum(BYTES_FREE) free_bytes
    from V$TEMP_SPACE_HEADER
    group by TABLESPACE_NAME
    ) c
where a.TABLESPACE_NAME = b.TABLESPACE_NAME (+)
    and a.TABLESPACE_NAME = c.TABLESPACE_NAME (+)
group by
    a.TABLESPACE_NAME,
    a.CONTENTS,
    a.EXTENT_MANAGEMENT,
    a.ALLOCATION_TYPE,
    a.SEGMENT_SPACE_MANAGEMENT,
    a.BIGFILE,
    a.STATUS
order by a.TABLESPACE_NAME;
```

Voir `fig 4`

![fig 4](pics/4.png)

## Question 3

Le nombre de segments du tablespace ETUDIANTS : 

```sql
SELECT COUNT(*) FROM DBA_SEGMENTS WHERE tablespace_name='ETUDIANTS';
```

Voir `fig 5`

![fig 5](pics/5.png)

Le nombre total d’extensions pour ce tablespace :

```sql
SELECT SUM(EXTENTS) FROM DBA_SEGMENTS WHERE tablespace_name='ETUDIANTS';
```

Voir `fig 6`

![fig 6](pics/6.png)

Le nombre moyen d’extensions par segment pour ce tablespace :

```sql
SELECT DBA1.nbSegments, DBA2.nbExtensions, (DBA2.nbExtensions/DBA1.nbSegments) "nbMoyenExtParSeg"
FROM
(SELECT COUNT(*) nbSegments FROM DBA_SEGMENTS WHERE tablespace_name='ETUDIANTS') DBA1,
(SELECT SUM(EXTENTS) nbExtensions FROM DBA_SEGMENTS WHERE tablespace_name='ETUDIANTS') DBA2;
```

Voir `fig 7`

![fig 7](pics/7.png)

# Partie 2

## Question 1

```sql
select o.object_name, o.object_type, s.segment_type
from 
(select object_name, object_type 
from dba_objects
where owner='RAPHALEN') o,
(select segment_name, segment_type
from dba_segments
where owner='RAPHALEN') s
where o.object_name=s.segment_name (+);
```

Voir `fig 8`

![fig 8](pics/8.png)

## Question 2

Description du segment TEST:

```sql
select segment_name, bytes, blocks, extents
from dba_segments
where tablespace_name = 'ETUDIANTS' and owner='E1800010' and segment_name='TEST';
```

Voir `fig 9`

![fig 9](pics/9.png)

Ensemble de ses extensions :

```sql
select extent_id, block_id, blocks
from dba_extents
where owner='E1800010' and segment_name='TEST'
order by extent_id;
```

Voir `fig 10`

![fig 10](pics/10.png)

## Question 3

```sql
analyze table test compute statistics ;
```

```sql
--output : Table TEST analysé(s).
```

```sql
select num_rows, blocks, empty_blocks, avg_space, chain_cnt, avg_row_len
from dba_tables
where owner='E1800010' and table_name='TEST';
```

Chez d'autres étudiants les chiffres ne sont pas les mêmes pourtant j'ai la même requête qu'eux

Voir `fig 11`

![fig 11](pics/11.png)

## Question 4

```sql
analyze table TESTBIS compute statistics ;
```

```sql
select num_rows, blocks, empty_blocks, avg_space, chain_cnt, avg_row_len
from dba_tables
where owner='E1800010' and table_name='TESTBIS';
```

Voir `fig 12`

![fig 12](pics/12.png)

```sql
UPDATE TESTBIS
SET ename = 'ADAMSADAMS'
WHERE ename = 'ADAMS';
```

```sql
-- output : 1024 lignes mis à jour
```

```sql
UPDATE TESTBIS
SET ename = 'SCOTTSCOTT'
WHERE ename = 'SCOTT';
```

```sql
-- output : 1024 lignes mis à jour
```

```sql
UPDATE TESTBIS
SET ename = 'BLAKEBLAKE'
WHERE ename = 'BLAKE';
```

```sql
-- output : 1024 lignes mis à jour
-- constatations :
```

```sql
analyze table TESTBIS compute statistics ;
```

```sql
select num_rows, blocks, empty_blocks, avg_space, chain_cnt, avg_row_len
from dba_tables
where owner='E1800010' and table_name='TESTBIS';
```

Voir `fig 13`

![fig 13](pics/13.png)

## Question 5

```sql
CREATE TABLE TEST1 AS SELECT * FROM TEST WHERE ename='ADAMS';
```

-- output : Table TEST1 créé(e).

```sql
select extents, blocks
from dba_segments
where owner='E1800010' and segment_name='TEST1';
```

```sql
analyze table TESTBIS compute statistics ;
```

```sql
select num_rows, blocks, empty_blocks, avg_space, chain_cnt, avg_row_len
from dba_tables
where owner='E1800010' and table_name='TEST1' ;
```

## Question 6

```sql
DELETE FROM TEST WHERE ename='ADAMS';
```

```sql
-- output : 1 024 lignes supprimé.
```

Stockage de la table test :

```sql
select extents, blocks
from dba_segments
where owner='E1800010' and segment_name='TEST';
```

Voir `fig 14`

![fig 14](pics/14.png)

```sql
analyze table TEST compute statistics ;
```

```sql
select num_rows, blocks, empty_blocks, avg_space, chain_cnt, avg_row_len
from dba_tables
where owner='E1800010' and table_name='TEST';
```

Voir `fig 15`

![fig 15](pics/15.png)

## Question 7

```sql
INSERT INTO TEST SELECT * FROM TEST1;
```

```sql
--output : 1 024 lignes inséré.
```

Stockage de la table test :

```sql
select extents, blocks
from dba_segments
where owner='E1800010' and segment_name='TEST';
```

Voir `fig 16`

![fig 16](pics/16.png)

```sql
analyze table TEST compute statistics ;
```

```sql
select num_rows, blocks, empty_blocks, avg_space, chain_cnt, avg_row_len
from dba_tables
where owner='E1800010' and table_name='TEST';
```

Voir `fig 17`

![fig 17](pics/17.png)

## Question  8

### 1)

```sql
CREATE TABLE test2
AS
SELECT * FROM test
WHERE ename IN ('ADAMS', 'ALLEN', 'BLAKE', 'CLARK', 'JAMES', 'JONES', 'KING',
'SMITH','MARTIN', 'MILLER', 'TURNER');
```

```sql
select extents, blocks
from dba_segments
where owner='E1800010' and segment_name='TEST2';
```

```sql
analyze table TEST2 compute statistics ;
```

```sql
select num_rows, blocks, empty_blocks, avg_space, chain_cnt, avg_row_len
from dba_tables
where owner='E1800010' and table_name='TEST2';
```

### 2) 

```sql
DELETE FROM TEST WHERE ename IN ('ADAMS', 'ALLEN', 'BLAKE', 'CLARK', 'JAMES', 'JONES',
'KING', 'SMITH','MARTIN', 'MILLER', 'TURNER');
```

```sql
select extents, blocks
from dba_segments
where owner='E1800010' and segment_name='TEST';
```

```sql
analyze table TEST compute statistics ;
```

```sql
select num_rows, blocks, empty_blocks, avg_space, chain_cnt, avg_row_len
from dba_tables
where owner='E1800010' and table_name='TEST';
```

### 3)

```sql
INSERT INTO test
SELECT * from test2 ;
```

```sql
select extents, blocks
from dba_segments
where owner='E1800010' and segment_name='TEST';
```

```sql
analyze table TEST compute statistics ;
select num_rows, blocks, empty_blocks, avg_space, chain_cnt, avg_row_len
from dba_tables
where owner='E1800010' and table_name='TEST';
```

On supprime les tables suivantes :

```sql
DROP TABLE test1;
DROP TABLE test2;
DROP TABLE TESTBIS;
```
```sql 
-- output : Table TEST1 supprimé(e).
--          Table TEST2 supprimé(e).
--          Table TESTBIS supprimé(e).
```

## Suppression des tuples de table (Avant la question 9)

validation des tests par un commit 

```sql
COMMIT ;
```
copie du contenu de la table test dans une table TESTBIS et décrisption du stockage de cette table

```sql
CREATE TABLE TESTBIS AS SELECT * FROM test ;
```

```sql
-- output : Validation (commit) terminée.
-- output : Table TESTBIS créé(e).
```

```sql
analyze table TESTBIS compute statistics ;
```

```sql
select num_rows, blocks, empty_blocks, avg_space, chain_cnt, avg_row_len
from dba_tables
where owner='E1800010' and table_name='TESTBIS';
```

## Question 9 

```sql
DELETE FROM TEST;
COMMIT;
```

```sql
-- output : 14 336 lignes supprimé.
--          Validation (commit) terminée.
```

```sql
analyze table TESTBIS compute statistics ;
```

```sql
select num_rows, blocks, empty_blocks, avg_space, chain_cnt, avg_row_len
from dba_tables
where owner='E1800010' and table_name='TESTBIS' ;
```

## Question 10

```sql
DELETE FROM TESTBIS ;
SELECT COUNT(*) FROM TESTBIS ;
```

Voir `fig 18`

![fig 18](pics/18.png)

```sql
ROLLBACK ;
SELECT COUNT(*) FROM TESTBIS ;
```

Voir `fig 19`

![fig 19](pics/19.png)

```sql
TRUNCATE TABLE TESTBIS;
SELECT COUNT(*) FROM TESTBIS;
```

Voir `fig 18`

```sql
ROLLBACK ;
SELECT COUNT(*) FROM TESTBIS;
```

Voir `fig 18`

-- Qu’en est-il du stockage de TESTBIS ?

```sql
analyze table TESTBIS compute statistics ;
select num_rows, blocks, empty_blocks, avg_space, chain_cnt, avg_row_len
from dba_tables
where owner='E1800010' and table_name='TESTBIS' ;
```

-- suppression des tables TEST et TESTBIS

```sql
DROP TABLE TEST;
DROP TABLE TESTBIS;
```
