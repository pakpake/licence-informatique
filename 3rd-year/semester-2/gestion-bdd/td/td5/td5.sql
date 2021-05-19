---
titre: TD 5 - BDD
date: 15/4/21
---

# Partie 1

Quel est le rôle qui vous est affecté ?

Cette requête nous permet de savoir dans quelle table chercher les rôles/privilèges attribués.

```sql
select * from dict where comments like '%role%';
```

On peut voir ci-dessous le role qui nous est attribué : 'UBS_AVANCE'

```sql
select granted_role from DBA_ROLE_PRIVS where grantee = upper('e1800010');
```

On peut voir ci-dessous le role de l utilisateur 'KESSLER_BDD': 'UBS_PROF'

```sql
select * from DBA_ROLE_PRIVS where grantee = upper('KESSLER_BDD');
```

L arbre des privilèges pour cet utilisateur est le suivant, car son rôle est 'UBS_PROF'.

```sql
select * from dba_sys_privs where grantee='UBS_PROF';
```

# Partie 2 

## Question 1

Création de la table 'articleTest', puis insertion des valeurs suivantes et validation.

```sql
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
```

Effectuez une série de mises à jour sur votre table articleTest (INSERT, DELETE, UPDATE) :

```sql
INSERT INTO articleTest VALUES (6, 'ciseaux', 10.5, 200) ;
DELETE FROM articleTest WHERE idArticle=1 ;
UPDATE articleTest SET prixunit=prixunit*2 WHERE idArticle=2 ;
```

On visualise ce qu on vient d inserer de modifer, puis on annule cette mise à jour avec un 'ROLLBACK' et on peut revisualer cette annulation ensuite.

```sql
select * from articleTest;
ROLLBACK;
select * from articleTest;
```

## Question 2

Je donne les droits suivant à l utilisateur e1803993: 

```sql
GRANT SELECT, INSERT, UPDATE, DELETE ON articleTest TO E1803993;
```

Résultat :

```sql
-- succès de l'élément Grant.
```

Insertion de tuple dans la table E1803993 :

```sql
select * from E1803993.articleTest;
INSERT INTO E1803993.articleTest VALUES (6, 'ciseau', 10.5, 200);
INSERT INTO E1803993.articleTest VALUES (7, 'couteau', 10.5, 200);
COMMIT;
-- sans faire de commit, l'utilisateur ne verras pas l'ajout que j'ai fais
```

Je regarde ce que l autre utilisateur à rajouté dans ma table :

```sql
select * from articleTest;
```
Le tuple 'fourchette' à été ajouté après que l utilisateur ait fait une validation (COMMMIT).

J insère un tuple dans la table articleTest de E1803993. E803993 se positionne en mode READ ONLY.

```sql
INSERT INTO E1803993.articleTest VALUES (8, 'cuillère', 10.5, 200);
-- 1 ligne inséréé
select * from E1803993.articleTest;
-- la ligne est bien inséré de mon côté
-- e1803993 se met en read only
-- et elle peut voir l'insertion que j'ai faite sans que j'ai eu besoin de faire de COMMIT.
```
Après validation de la transaction :

```sql
COMMIT;
select * from E1803993.articleTest;
-- je ne vois aucun changement, le tuple inséré juste avant est présent
-- rien ne change du côté de e&803993, car le commit à validé l'insertion faite avant.
```

Note: il se peut que Oracle bug, celà arrive assez souvent d ailleurs.

### Pose de verrou implicite

Je modifie un tuple de la 
```sql
UPDATE E1803993.articleTest SET prixunit=0 WHERE idArticle=8;
COMMIT; -- on sort du mode read only
-- impossible d'update la table de e1803993, nous n'avons pas les droits
-- oracle nous dit que seul Oracle peut modifier les les tables qui sont en read only, pourtant nous ne sommes pas en read only
```

C est une erreur normale, et si e1803993 essaie de faire la même chose, elle a la même chose, c est normal.

```sql
UPDATE E1803993.articleTest SET prixunit=0 WHERE idArticle=5;
```

Même erreur, c est normal.

### Pose de verrou explicite

Je verrouille ma table :

```sql
LOCK TABLE articleTest IN EXCLUSIVE MODE;
-- Succès de l'élément Lock.
```

Je modifie ma table :

```sql
UPDATE articleTest SET prixunit=0 WHERE idArticle=2;
COMMIT;
select * from articleTest;
-- ma modification est bien présente
```

U2 fait une modification sur ma table, je peux la voir : 

```sql
select * from articleTest;
-- tuple 9 rajouté par e1803993
```

Si E1803993 essaie de Lock ma table, ça marche, même résultat que quand je l ai fait.

Note : Normalement e1803993 ne devrait pas pouvoir faire d insertion parce que ma table est lock, mais c est un problème venant d Oracle, nous n y pouvons rien.
Il aurait peut être fallut se déconnecter et se reconnecter, mais nous n avons pas le temps de le faire.

### Interblocages

expliquez ce que c est.

