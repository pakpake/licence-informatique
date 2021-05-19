#include <iostream>

using namespace std;

bool isEven(int x) {
    // en notation binaire si le bit le plus à droite vaut 1, le nombre est impair
    return (x & 1)!=1;
    // on aurait aussi pu faire  x%2
    // x modulo 2 = reste de la division 0 si le nombre est pair ou 1 impair
}

int main() {
    int x;  // déclaration de la variable
    // on pose la question puis on stocke la valeur saisie au clavier
    cout << "Votre nombre ?" << endl;
    cin >> x;
    cout << "Votre nombre " << x << " est " 
        << (isEven(x) ? "pair" : "impair") << endl;
    return EXIT_SUCCESS;
}
