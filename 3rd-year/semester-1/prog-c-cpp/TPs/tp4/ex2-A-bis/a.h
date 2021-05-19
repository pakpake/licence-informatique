#ifndef A_H
#define A_H

#include <iostream>

using namespace std;

class A {
    double value;
    // Pas demandé dans le td
    int id; // identifiant de l'objet
    static int uniqueId ; // compteur global pour donner une id à chaque objet

    public :
    // constructeur initialisant l'attribut value avec valeur par défaut
    A(const double val=0.0);

    // constructeur de clonage
    A(const A&);

    // destructeur
    ~A();
};

void f();

#endif
