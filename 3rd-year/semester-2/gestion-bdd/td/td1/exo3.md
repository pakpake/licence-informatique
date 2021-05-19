# Exercice 3

U = (A,B,C,D,E,F,G,H)
F = { ABC->D, A->BC, BC->E, D->A, C->FG, G->DE, H->E }

1. Montrons que <R(U), F> n'est pas en 3NF

    * déterminer les clés

en 3NF ssi en 2NF,

2NF => si tout attribut qui n'est pas dans une clé ne dépned pas d'une partie seulement d'une clé
3NF => si tout atribut n'appartenant pas à une clé ne dépend pas d'un autre attribut  n'appartenant pas à une clé

Pour connaitre les clés :
    * quels sont les attributs non définit ? -> {H}
    * fermeture de H = {HE}

    * fermetures : 

HA = {U}
HB = {HBE}
HC = {U}
HD = {U}
HE = {HE}
HF = {HF}
HG = {U}


A {ABCDEFG}
B {B}
C {ABCDEFG}
D {ABCDEFG}
E {E}
F {F}
G {ABCDEFG}
H {HE}

K = {AH,CH,DH,GH}


2. Calculons une décomposition de <R(U), F> par l'algorithme de Bernstein

    * couverture minimale :

CM = {A->BC, D->A, C->FG, G->DE, H->E}

    * dépendances fonctionnelles élémentaires

A->B
A->C
D->A
C->F
C->G
G->D
G->E
H->E

R1{A,B,C}
F1{A->B, A->C}
K1{A}

R2{A,D}
F2{D->A}
K2{D}

R3{C,F,G}
F3{C->F,C->G}
K3{C}

R4{G,D,E}
F4{G->D,G->E}
K4{G}

R5{H,E}
F5{H->E}
K5{H}

R6{H,A}
F6{ensemble vide}
K6{HA}

Vérfier si l'on peut rassembler les relations

3. Calculez une décomposition de <R (U), F> par l’algorithme de décomposition en BCNF. 
    * Les dépendances sont-elles préservées par la décomposition ?

