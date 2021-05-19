# Sujet 3 :

## Classes, références, paramètres constants

### Question 1 :

Créer la classe `_Point_ ` pour manipuler les points d'un plan, définir un constructeur par défaut et
une fonction membre` _affiche_` qui donne les cordonnées du point.

### Question 2 :

Ajouter dans la classe `_Point_` des fonctions membres qui permettent d'accéder aux cordonnées d'un point (écriture, lecture). 
Vous définirez ces méthodes comme `inline`. Créer dans un fichier séparé un programme qui créé 2 points aux coordonnées distinctes.
Affichez ces points.
Proposez une méthode qui calcule la norme d'un vecteur défini par deux points (les points ne doivent pas être modifiés par cette opération).

### Question 3 :

Modifier la classe `_Point_` pour ajouter un constructeur prenant en argument une référence sur
un autre point (posez vous la question de savoir ce qui doit être constant). Testez ce
constructeur. Ajouter une fonction `_testEgal_` qui teste l’égalité de 2 points.

Rq : cette méthode pourrait être une surcharge de ==. Nous verrons dans les prochains TP comment traduire cet état de fait._

### Question 4 :

Créer une nouvelle classe `_NuageDePoints_` qui représente un ensemble de points. Cette classe
disposera d’un constructeur prenant le nombre de points en paramètre. Vous générerez
aléatoirement ces points (dans l’intervalle [0..10,0..10] par exemple), en veillant à respecter
qu’il n’y ait jamais 2 fois le même point dans votre nuage. Pour le hasard, on utilisera à bon
escient cette fonction :

```cpp
// get an random float between 0 and 1
float getRand()
{
return (float) rand() / (float) RAND_MAX;
}
```
### Question 5 :

Ecrivez une méthode publique de la classe` _NuageDePoints_` calculant le barycentre de votre nuage de points.
Testez cette méthode.


