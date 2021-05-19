# Annexe

## Description des différentes tables :

* personne :

```
idPers : entier strictement positif
nomPers : chaine de 40 caractères
villePers : chaine de 30 caractères
```

* medecin :

```
idMed : entier strictement positif
nomMed : chaine de 40 caractères
villeMed : chaine de 30 caractères
specialite : chaine de 40 caractères
```

* maladie :

```
idMal : entier strictement positif
nomMal : chaine de 30 caractères
symptomes : chaine de 30 caractères
```

* consultation :

```
idPers : entier
idMed : entien
idMal : entien
dateConsult : date
```

## Organistation des tables

* personne :

| idPers | ... |
|--------|-----|
|  ...   | ... |

* medecin : 

| idMed | ... |
|-------|-----|
| ...   | ... |

* maladie : 

| idMed | ... |
|-------|-----|
| ...   | ... |


* consultation : 

| idPers | idMed | idMal | ... |
|--------|-------|-------|-----|
| ...    | ...   | ...   | ... |


Les **clés primaires** sont les **id**{Pers,Med,Mal}.
