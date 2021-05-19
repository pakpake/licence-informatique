#include <iostream>
#include <stdlib.h>     // pour le calcul de la valeur absolue (pour les nombres réels)
#include <math.h>

using namespace std;

// retourne la puissance d'un nombre entier
int puissance(int x, int a=2) {
    // par défaut si a n'est pas saisi, on calcul le carré (a=2)
    return pow(x,a);
}

// autre fonction sans utiliser la fonction pow
double puiss(double x, int a=2){
    // par défaut, la puissance est 2
    double res = 1.;     // résultat du calcul
    // calcul de la puissance avec un boucle
    // et la valeur absolue de la puissance entrée en paramètre
    for (int i=0;i<abs(a);i++) res*=x;
    // si la puissance est négative, on retourne l'inverse du nombre à la puissance
    return a<0 ? 1./res : res;
}

int main() {
    // Affichage et appel des fonctions
    cout << "Appel de puissance(3,4) = ";
    cout << puissance(3,4) << endl;
    cout << "Appel de puissance(3) = ";
    cout << puissance(3) << endl;
    cout << "Appel de puiss(10,-2) = ";
    cout << puiss(10,-2) << endl;
    cout << "Appel de puiss(3) = ";
    cout << puiss(3) << endl;
    cout << "Appel de puiss(3,0) = ";
    cout << puiss(3,0) << endl;
    return EXIT_SUCCESS;
}
