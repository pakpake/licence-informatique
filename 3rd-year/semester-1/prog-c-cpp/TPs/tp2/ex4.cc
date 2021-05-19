#include <iostream>
#include <stdlib.h>

using namespace std;

double * copie(double * d, int taille) {
    double * tab = new double[taille];  // création du tableau de double de taille taille
    for (int i = 0;i < taille;i++) tab[i] = d[i];   // copie des éléments du tableau initial
    return tab;
}

int main() {
    double tableau[]={1,2,3,4,5};   // tableau initial
    int n = 3;  // nombre d'éléments à copier
    double * cti = copie(tableau,n);    // pointeur pour la création de la copie du tableau

    cout << "Tableau initial :" << endl;

    // Affichage du tableau
    for (int i=0;i<int(sizeof(tableau)/sizeof(*tableau));i++) cout << tableau[i] << " ";

    cout << endl << endl;

    cout << "Copie de " << n << " éléments du tableau "<< endl;
    cout << "Affichage de la copie du tableau :" << endl;

    /*
    // Affichage de la copie du tableau par pointeurs
    int i = 0;  // compteur pour parcourir et afficher les tableaux
    double *ctiBis = cti;
      while (i < n) {
          cout << *ctiBis++ << " ";
          i++;
      }
      cout << endl;
    */
    
    // cout << "taille de cti " << endl;
    // cout << sizeof(cti) << endl;
    // cout << sizeof(*cti) << endl;

    // Affichage de la copie du tableau
    for (int i=0;i<n;i++) cout << cti[i] << " ";
    cout << endl;
    return EXIT_SUCCESS;
}
