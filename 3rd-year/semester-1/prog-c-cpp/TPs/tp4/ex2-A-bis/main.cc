#include <iostream>
#include "a.h"

int A::uniqueId = 0; // initialisation du compteur global et static

int main() {

    cout << "Création de a1 - " ;
    A a1(10.);
    cout << "Création de a2 par clonage - ";
    A a2(a1);
    cout << "Création de a3 en dynamique - " ;
    A * a3 = new A(10.);
    cout << "Destruction de a3 - ";
    delete(a3);

    cout << endl << "APPEL FONCTION f()" << endl << endl;

    f();
    
    cout << endl << "APRES FONCTION f()" << endl << endl;

    return 0;
}
// Dans mon programme, j'ai ajouté un identifiant unique par objet créé, ce qui
// me permet de l'afficher dans les appels au constructeur/destructeur.
// Je peux donc observer précisément quel objet est créé quand et détruit quand
//
//
// Résultat attendu :
// Tous les objets qui sont créé dans la pile (stack) sont détruits par le
// destructeur dans l'ordre inverse où ils ont été créés
// Par ailleurs, il en est de même pour la fonction f(), elle est placée sur
// la pile après les objets déjà créés. Les objets de cette fonction seront
// détruits à la sortie de la fonction
//
// On ne voit pas la destruction des objets du tas (heap) si on ne la fait 
// pas nous-même.
//
// Voila le résultat de mon programme
//
// Création de a1 - Appel du constructeur par défaut pour l'objet 1
// Création de a2 par clonage - Appel de constructeur de clonage pour l'objet 2
// Création de a3 en dynamique - Appel du constructeur par défaut pour l'objet 3
// Destruction de a3 - Appel du destructeur pour l'objet 3
// 
// APPEL FONCTION f()
// 
// Création de af1 - Appel du constructeur par défaut pour l'objet 4
// Création de af2 en dynamique - Appel du constructeur par défaut pour l'objet 5
// Destruction de af2 - Appel du destructeur pour l'objet 5
// Appel du destructeur pour l'objet 4
// 
// APRES FONCTION f()
// 
// Appel du destructeur pour l'objet 2
// Appel du destructeur pour l'objet 1
