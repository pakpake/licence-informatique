#include <iostream>
#include <stdlib.h>
#include <string>

using namespace std;

/* fonction de saisie d'entier, réels, caractère, et chaines de caractères */

// appel du paramètre de la fonction par sa référence
void saisie_int(int &x) {
    cout << "Valeur nombre entier ? ";
    cin >> x;
}

void saisie_double(double &y) {
    cout << "Valeur nombre réel ? ";
    cin >> y;
}

void saisie_car(char &c) {
    cout << "Caractère à entrer ? ";
    cin >> c;
}

void saisie_string(string &s) {
    cout << "Chaine de caractère à entrer ?" << endl;
    cin >> s;
}

int main() {
    // déclaration des variables
    int x;
    double y;
    char c;
    string s;

    // appel des fonctions
    saisie_int(x);
    saisie_double(y);
    saisie_car(c);
    saisie_string(s);

    return EXIT_SUCCESS;
}
