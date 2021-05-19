#include <iostream>
#include <stdlib.h>

using namespace std;

// fonction booléene qui teste si la division est possible
bool divisible(int a, int b) {
    // teste si le diviseur vaut zéro
    if (b==0) {
        cout << "Division par zéro impossible !" << endl;
        exit(EXIT_FAILURE);
    } else return (a%b == 0);
    // si le reste de la division vaut 0, alors les 2 entiers sont divisibles
}

// même fonction, mais qui prend en paramètre la référence des pointeurs sur les entiers
bool div_pointeur(int &a, int &b) {
    if (b==0) {
        cout << "Division par zéro impossible !" << endl;
        exit(EXIT_FAILURE);
    } else return (a%b == 0);
    // même raisonnememnt que pour la fonction précédente
}

int main() {
    // déclaration des variables
    int x,y;
    cout << "Nombres (entier) pour la division (a/b) ?" << endl;
    // saisie et stockage des variables
    cin >> x >> y;
    // appel de la 1ère fonction
    cout << "Est-ce divisible ? " << (divisible(x,y) ? "oui" : "non ")<< endl;

    cout << "===================" << endl;

    // appel de la 2ème fonction
    cout << "Est-ce divisible ? (pointeur) " << (div_pointeur(x,y) ? "oui" : "non") << endl;

    return EXIT_SUCCESS;
}
