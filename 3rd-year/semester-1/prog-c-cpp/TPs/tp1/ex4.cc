#include <iostream>
#include <string>

using namespace std;

// affiche verticalement un mot préalablement saisi.

// fonction qui parcourt via les pointeurs le mot, 
// et qui affiche ligne par ligne chaque caractère
char affiche(char *p) {
    // parcourt via une boucle chaque caractère du mot avec le pointeur
    while (*p) {
        // affichage
        cout << *p << endl;
        // on passe au caractère suivant
        p++;
    }
    return EXIT_SUCCESS;
}

// fonction de saisie et stockage du mot
char * saisie() {
    // création d'un nouveau pointeur visible pour toutes les fonctions
    char *p = new char;
    // demande du mot
    cout << "Votre mot ? " << endl;
    // stockage du mot
    cin >> p;
    return p;
}

int main() {
    // utilisation des 2 fonctions pour saisir et afficher verticalement le mot
    affiche(saisie());

    return EXIT_SUCCESS;
}
