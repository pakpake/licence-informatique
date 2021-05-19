#include "ex5.h"
#include <stdlib.h>

int main() {
    // création de 4 réels
    double a = 6., b = 0.5, c = 14.3, d = 0.0;    
    int i = 0;  // compteur de boucle

    // création du tableau de pointeur vers les éléments précédents
    double * t[4] = {&a,&b,&c,&d};
    // appel de la fonction creer
    double * f = creer(t,4);
    // affichage du résultat
    while (i < 4) {
        cout << f[i] << " ";
        i++;
    }
    cout << endl;

    return EXIT_SUCCESS;
}
