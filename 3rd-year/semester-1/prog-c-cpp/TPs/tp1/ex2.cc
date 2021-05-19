#include <iostream>

using namespace std;

// calcul d'inverse d'un nombre

// fonction qui calcule l'inverse d'un nombre
double inverse(double x) {
    if (!x) {
        // si x vaut 0
        cout << "Division par zéro impossible !" << endl;
        // sortie de la fonction et message d'erreur
        exit(EXIT_FAILURE);
    }
    // sinon je retourne l'inverse du nombre saisi
    return 1/x;    
}

int main() {
    double x;       // déclaration de la variable double pour entier et réels
    cout << "Quel est votre nombre ?" << endl;
    // on stocke la valeur du nombre saisi dans la variable définie avant
    cin >> x;
    // affichage du résultat
    cout << "Inverse = " << inverse(x) << endl;

    return EXIT_SUCCESS;
}
