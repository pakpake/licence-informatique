#include <iostream>

using namespace std;

// affiche verticalement un mot préalablement saisi.

// création de l'espace de nommage
namespace mon_espace {
    // prototype des fonctions
    char affiche(char *p);
    char * saisie();
}

// appel de mon espace de nommage
using namespace mon_espace;

// définition des fonctions
char mon_espace::affiche(char *p) {
    while (*p) {
        cout << *p << endl;
        p++;
    }
    return EXIT_SUCCESS;
}

char * mon_espace::saisie() {
    char *p = new char;
    cout << "Votre mot ? " << endl;
    cin >> p;
    return p;
}

int main() {
    affiche(saisie());
    return EXIT_SUCCESS;
}
