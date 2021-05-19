#include "ex5.h"

// fonction qui créé un tableau de réels à partir d'un tableau de pointeurs sur réels
double * creer(double **tab,int nb) {
    // création du tableau/pointeur résultat
    double *tabRes = new double[nb];
    // copie des éléments
    for (int i=0; i < nb; i++) {
        tabRes[i] = *tab[i];
        // tab[i] est le ième éléments de tab et
        // *tab[i] est l'éléments pointé par tab[i]
    }
    return tabRes;
}
