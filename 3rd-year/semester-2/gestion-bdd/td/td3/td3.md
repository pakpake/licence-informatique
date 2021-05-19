---
author: 
date:  25-03-21
title: TD3 - BDD
---

# Partie 1 - Dictionnaire
## Question 1

```sql
SELECT * FROM DICTIONARY;
```

La table DICTIONARY est composée de 2 colonnes : 

- "TABLE_NAME" et
- "COMMENTS" 

Cette table contient 994 lignes au total.

## Question 2

```sql
SELECT * FROM dba_users;
```
 
![DBA_USERS](./partie-1/q2-1.png)


```sql
SELECT * FROM user_users;
```
 
![USERS_USERS](./partie-1/q2-2.png)

## Question 3

```sql
SELECT * FROM dba_objects a, dba_tables b, dba_indexes c 
WHERE a.owner=b.owner AND b.owner=c.owner AND c.owner='KESSLER_BDD';
```
 
![DBA_OBJECTS, DBA_TABLES, DBA_INDEXES](./partie-1/q3.png)

## Question 4

```sql
SELECT * FROM V$INSTANCE;
```

![V$INSTANCE](./partie-1/q4-1.png)

```sql
SELECT * FROM V$PARAMETER;
```

![V$PARAMETER](./partie-1/q4-2.png)

# Partie 2 - Fichiers
## Question 1 - fichiers de données

```sql
SELECT * FROM V$DBFILE;
```

Les fichiers sont listés dans la colonne `NAME`.


![V$DBDFILE](./partie-2/p2-q1-1.png)

On peut voir leurs date de création via la colonne `CREATION_TIME`

```sql
SELECT * FROM V$DATAFILE;
```

![V$DATAFILE](./partie-2/p2-q1-2.png)

Nous pouvons voir la taille de ces fichiers grâces à la colonne `BYTES`
Nous pouvons aussi voir via la colonne `CHECKLPONT_CHANGE#` la "date" (compteur SCN - System Change Number) du dernier checkpoint.

![V$DATAFILE](./partie-2/p2-q1-2-1.png)

Nous pouvons voir grâce à la commande suivante à quels tablespaces ces fichiers sont associés.
 
```sql
SELECT * FROM DBA_DATA_FILES;
```

![V$DBA_DATA_FILES](./partie-2/p2-q1-3.png)

## Question 2 - fichiers de reprise

Les fichiers de reprises sont listés dans la colonne `MEMBER`.

```sql
SELECT * FROM V$LOGFILE;
```

![V$LOGFILe](./partie-2/p2-q2-1.png)

La taille de chaque se trouve dans la colonne `BYTES`.

Le fichier de reprise en cours est marqué `CURRENT` dans la colonne `STATUS`.

Les fichiers ne sont pas multiplexés.
La base n'est pas en `ARCHIVELOG`, c'est marqué `NO` dans la colonne `ARCHIVE`.

```sql
SELECT * FROM V$LOG;
```

![V$LOG](./partie-2/p2-q2-2.png)

`SELECT * FROM V$LOG_HISTORY;`

![V$LOG_HISTORY](./partie-2/p2-q2-3.png)

## Question 3 - fichiers de contrôle

Les noms des fichiers se trouvent dans la colonne `MEMBER` et leur taille dans les colonnes `BLOCK_SIZE` et `FILE_SIZE_BLKS`.

```sql
SELECT * FROM V$CONTROLFILE;
```

![V$CONTROLFILE](./partie-2/p2-q3-1.png)

On trie la requête sur la table `V$DATABASE` avec les champs que l'on veut, donc on récupère le nom, puis la date de création du fichier et enfin le numéro du `SCN`.

```sql
SELECT NAME, CREATED, CHECKPOINT_CHANGE# FROM V$DATABASE;
```

![V$DATABASE](./partie-2/p2-q3-2.png)

# Partie 3 - Mémoire

## Question 1

La taille de la "SGA" (System Global Area) se trouve dans la colonne `VALUE`, 1ère ligne de la table `V$SGAINFO`.

On trouve aussi cette valeur (ici 2927384 B) dans la colonne `BYTES` de la table `V$SGA`.

On remarque aussi la colonne `RESIZEABLE`, qui veut dire redimensionnable en français et signifie si les zones qui composent la SGA sont ou ne sont pas allouées dynamiquement.
Si elles le sont, ce sera marqué `YES`, sinon `NO`.

```sql
SELECT * FROM V$SGAINFO;
```

![V$SGAINFO](./partie-3/p3-q1-0.png)

Nous n'obtenons aucune information de la requête suivante.

```sql
SELECT * FROM V$PARAMETER;
```

<!-- ![V$DATABASE](./partie-3/p3-q1-2.png) -->

## Question 2

La zone LC de la shared pool se trouve dans la table `V$SGASTAT`, dans la colonne `POOL`.

```sql
SELECT * FROM V$SGASTAT;
```

![V$SGASTAT](./partie-3/p3-q2-1.png)


# Partie 4 - Processus

L'ensemble des processus se trouvent dans la table `V$PROCESS`, et on retrouve leur `PID` dans la colonne de ce même nom.

```sql
SELECT * FROM V$PROCESS;
```

![V$PROCESS](./partie-4/p4-1.png)

L'ensemble des processus en arrière-plan sont dans la table `V$BGPROCESS`. On retrouve leur nom dans la colonne `NAME`.

```sql
SELECT * FROM V$BGPROCESS;
```

![V$BGPROCESS](./partie-4/p4-2.png)
