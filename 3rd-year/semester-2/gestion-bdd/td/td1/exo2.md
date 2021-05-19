# Exercice 2

## F1

* 2NF, car 
K = E, 2NF car il n y a pas d'attributs non membre de clés déterminés par une partie de la clé

1 ere étape on regarde si il y a des attributs non déterminés en l'occurence on a E
puis on fais la fermeture des autre attributs A {ABCD}
B {BD}
C {C}
D {D}
on observe que E n'est pas dans les fermetures d'aucun des autres attributs donc pas la peine de continuer

## F2

D est l'attribut non déterminé et n'oublions pas E qui n'est pas présent, donc K = {D,E} (c'est la clé)

fermeture des autres attributs != de D, donc {ABC}

A {BC}
B {C}
C {C}

(DE)⁺F2 = DEABC
la clé n'est pas dans les fermetures.
Attributs non-membres de clé : {A,B,C}
pas en 2NF (donc ni 3NF ni en BCNF)


## F3

attributs non déterminés = {}, K = {}

A { ABCDE }
B { ABCDE }
C { C }
D { D }
E { ABCDE } pour les fermetures

donc K = {ABE}

2NF car il ne peut y avoir membre de clé vu que les clés ne sont pas composés de plusieurs attributs

3NF car  toutes nos dépendances sont de la forme clé ou sur ensemble de clé détermine quelque chose

BCNF = car quelque soit la relation on a une clé ou un sur ensemble de clé qui définit un attribut
