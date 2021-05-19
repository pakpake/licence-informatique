#include "a.h"
#include <iostream>


// constructeur initialisant l'attribut value
A::A(const double val) : value(val), id(++uniqueId) {
    cout << "Appel du constructeur par défaut pour l'objet " << id << endl;
}

// constructeur de clonage
A::A(const A& c) : id(++uniqueId) {
    value = c.value;
    cout << "Appel de constructeur de clonage pour l'objet " << id << endl;
}

// destructeur
A::~A() {
    cout << "Appel du destructeur pour l'objet " << id << endl;
}

void f() {
    cout << "Création de af1 - ";
    A af1(1.);
    cout << "Création de af2 en dynamique - ";
    A * af2 = new A(1.);
    cout << "Destruction de af2 - ";
    delete(af2);
}

