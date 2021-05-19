# Base de données

## Création et manipulation de tables en SQL

### What you'll have, and have to do

*DDL*, short name of Data Definition Language, which deals with database schemas and descriptions, of how the data should reside in the database.

*DML*, short name of Data Manipulation Language which deals with data manipulation, and includes most common SQL statements such SELECT, INSERT, UPDATE, DELETE etc, and it is used to store, modify, retrieve, delete and update data in database.


\*tp fait sous Oracle, fonctionne pour tous types de SGBD\*

* la totalié de la base de donnée est disponible, c'est le fichier **complete.db**.*


Les tables ***personne***, ***medecin***, ***maladie*** et ***consultation*** disponible dans le dossier ***data*** sont considérés comme **déjà** implémentées dans la base de donnée.

**Pour les contraintes d'intégrité, se référer au fichier *annexe.md*.**

### Question 1

Executer le script createTable.sql
Créer les tables maladie et consultation.

```
N.b : Les types de données retenus sont :
    VARCHAR2(n) pour les chaînes de n caractères,
    NUMBER pour les entiers,
    NUMBER(10,2) pour les réels,
    DATE pour les dates

Tous les noms de contraintes de colonne seront de la forme :
    <<Table>><<Attribut>><<Contrainte>>

Tous les noms de contraintes de table seront de la forme
    <<Table>><<Contrainte>>
```

### Question 2

rajouter à la  table **consultation** un attribut tarifConsult, strictement positif réel.

### Question 3

Executez le script personne.sql
Executez le script medecin.sql
Executez le script maladie.sql

Insérez dans la table *consultation* le contenu de la table *consultation.sql*

### Question 4

+ insérez les tuples suivants dans la table *personne* :

| idPers | nomPers | villePers |
|--------|---------|-----------|
| 5      | Legrand | Nantes    |
| 15     | Nerveux |           |

+ insérez les tuples suivants dans la table *consultation* :

| idPers | idMal | idMed | dateConsult | tarifConsult |
|--------|-------|-------|-------------|--------------|
| 1      | 6     | 4     | 05/09/2019  | 27           |

+ Supprimer le médecin d'identifiant **5** de la table *medecin*

+ Désactivez la contrainte d’intégrité référentielle sur l’attribut idMed dans la table *consultation*

+ Supprimez le médecin d’identifiant **5** de la table medecin

+ Réactivez la contrainte d’intégrité référentielle sur l’attribut idMed dans la table *consultation*

+ Réinsérez le médecin d’identifiant **5** dans la table *medecin* et réactivez la contrainte d’intégrité référentielle sur l’attribut **idMed** dans la table *consultation*

| idMed | nomMed  | villeMed | specialite    |
|-------|---------|----------|---------------|
| 5     | Killeur | Vannes   | ophtalmologie |

+ Augmentez de **1,5%** le tarif des consultations

+ Augmentez de **2€** le tarif de consultation des médecins de spécialité ‘chirurgie’

+ Remplissez à nouveau la table consultation avec les tuples de la table *consultation.sql*

### Question 5

1. Quels sont les contenus des tables personne, medecin, maladie, consultation ?

2. Quel est l’ensemble des identifiants des personnes qui ont consulté le médecin de nom ‘Boucher’ ? Quels sont leurs noms ?

3. Quels sont les identifiants et villes des médecins qui ont reçu des personnes de 'Vannes' ?

4. Quel est le nombre de consultations par personne ?

5. Quelles sont les personnes ayant effectué au moins 5 consultations ?

6. Quelles sont les personnes ayant consulté au moins 3 médecins différents ?

7. Quels sont le nombre total de consultations réalisées, les tarifs minimum, maximum et moyen des consultations ?

8. Quels sont, par médecin : le nombre de consultations réalisées, les tarifs minimum, maximum et moyen des consultations ?

9. Quels sont les médecins qui pratiquent le tarif minimum de consultation ?

10. Quelles sont les personnes ayant consulté pour la grippe ?

11. Quelles sont les personnes ayant consulté uniquement pour la grippe ?

12. Quelles sont les personnes n’ayant jamais consulté ? Quelles sont les personnes n’ayant jamais consulté pour la grippe ?

13. Quelles sont les personnes qui ont consulté des médecins de Vannes pour la grippe ?

14. Quelles sont les personnes qui ont consulté tous les médecins ?

15. Quelles sont les personnes qui ont consulté tous les médecins de Lorient ?

16. Quelles sont les personnes qui ont consulté des médecins de leur ville ?

17. Quelles sont les personnes qui n’ont consulté que des médecins de leur ville ?

18. Quelles sont les personnes qui n’ont consulté aucun médecin de leur ville ?

19. Quelles sont les personnes ayant consulté pour des maladies différentes présentant les mêmes symptômes ?

20. Quelles sont les personnes ayant consulté en orthopédie et en chirurgie ?

21. Quelles sont les personnes ayant consulté dans plusieurs spécialités ?
