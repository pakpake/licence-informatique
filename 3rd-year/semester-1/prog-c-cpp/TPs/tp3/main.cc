#include "point.h"
#include "NuageDePoints.h"
#include <stdlib.h>

int main() {
    Point a;
    Point b(12,3.2);
    Point c(b);

    // Affichage des coordonnées :
    cout << "Affichage des coordonnées de a :" << endl;
    a.affiche();
    cout << "Affichage des coordonnées de b :" << endl;
    b.affiche();
    cout << "Affichage des coordonnées de c :" << endl;
    c.affiche();

    // affichage avec les getters
    cout << endl;
    cout << "Affichage avec les getters de b :" << endl;
    cout << "x = " << b.getX() << endl;
    cout << "y = " << b.getY() << endl;

    // changement des variables avec les setters
    cout << endl;
    cout << "Maintenant a=(5,4.7)" << endl;
    a.setPoint(5,4.7);
    a.affiche();
    cout << "Maintenant a=(meme valeur, 12)" << endl;
    a.setPoint(a.getX(),12);
    a.affiche();

    // Calcul de la norme d'un vecteur
    cout << endl;
    cout << "Calcul de la norme du vecteur a :" << endl;
    a.affiche();
    cout << "norme de a = " << a.norme() << endl;

    // Test d'égalité
    cout << endl;
    cout << "Test d'égalité des coordonnées entre le point a et b" << endl;
    cout << "a == b ? " << (a==b ? "True" : "False") << endl;
    cout << "Test d'égalité des coordonnées entre le point b et c" << endl;
    cout << "b == c ? " << (b==c ? "True" : "False") << endl;

    // Test des opérateurs de flux
    cout << "Point a : " << a << endl;
    cout << "----------------------------------------" << endl;

    /*========================================*/
    // Test du nuage de point

    cout << "Creation d'un nuage de point :"<< endl;
    cout << "Combien de points ? " ;
    int nbp;
    cin >> nbp;
    
    // creation d'un nuage de points N1
    NuageDePoints N1(nbp);
    // affichage de ce nuage de points
    N1.print();
    cout << endl;

    // calcul du barycentre
    cout << "Le barycentre est : " << N1.barycentre() << endl;

    return EXIT_SUCCESS;
}
