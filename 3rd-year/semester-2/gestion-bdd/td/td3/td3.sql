/*
 * @author: 
 * @date:  18-03-21
 * title: TD3 - BDD
*/
/*** Partie 1 - Dictionnaire ***/
-- question 1
SELECT * FROM DICTIONARY;
-- question 2
SELECT * FROM dba_users;
SELECT * FROM user_users;
-- question 3
SELECT * FROM dba_objects a, dba_tables b, dba_indexes c WHERE a.owner=b.owner AND b.owner=c.owner AND c.owner='KESSLER_BDD';
-- question 4
SELECT * FROM V$INSTANCE;
SELECT * FROM V$PARAMETER;
/*** Partie 2 - Fichiers ***/
-- question 1 - fichiers de données
SELECT * FROM V$DBFILE;
SELECT * FROM V$DATAFILE;
SELECT * FROM DBA_DATA_FILES;
-- question 2 - fichiers de reprise
SELECT * FROM V$LOGFILE;
SELECT * FROM V$LOG;
SELECT * FROM V$LOG_HISTORY;
-- question 3 - fichiers de contrôle
SELECT * FROM V$CONTROLFILE;
SELECT * FROM V$DATABASE;
SELECT NAME, CREATED, CHECKPOINT_CHANGE# FROM V$DATABASE;
/*** Partie 3 - Mémoire ***/
SELECT * FROM V$PARAMETER;
SELECT * FROM V$SGA;
SELECT * FROM V$SGAINFO;
SELECT * FROM V$SQLAREA;
/*** Partie 4 - Processus ***/
SELECT * FROM V$PROCESS;
SELECT * FROM V$BGPROCESS;
