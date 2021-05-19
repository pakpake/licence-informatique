#include <iostream>
#include <math.h>   // pour les fonctions de mathématique (racine carré par exemple)

using namespace std;

// programme qui teste si un nombre est triangulaire

// fonction qui teste si x est triangulaire

// fonction booléenne qui retourne 0 (false) ou 1 (true) si le nombre est pair ou impair
bool triang(int n) {
    // ici on calcul (dans la variable nbis) à partir du nombre saisi 
    // le 'n' qui correspond à la formule (n(n+1))/2
    // et on peut tester (via une autre formule)
    // si avec ce 'n' trouvé cela correspond bien au nombre saisi

    // floor = partie entière d'un nombre
    int nbis = floor(sqrt((n*2)));
    if (((nbis*(nbis+1))/2 == n)) {
        cout << "Le nombre est triangulaire" << endl; 
        return false;
    }
    cout << "Le nombre n'est pas triangulaire" << endl;
    return true;
}

int main() {
    int x;      // déclaration de la variable pour la saisie
    cout << "Quel est votre nombre à tester ?" << endl;
    cin >> x;       // saisie et stockage de la valeur saisie au clavier
    // affichage du résultat
    cout <<  "résultat : "<< triang(x) << endl;

    return EXIT_SUCCESS;
}
