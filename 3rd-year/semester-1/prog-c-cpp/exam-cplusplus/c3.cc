#include <iostream>
using namespace std;
class A {
    public:
        static int val;
};
int A::val=1;
int main() {
    A a;
    A::val=1;    // erreur édition des liens 'référence indéfinie vers « A::val »'
    //A(val)=1;      // erreur 'error: conversion from ‘int’ to non-scalar type ‘A’ requested'
    //A.val=1;        // erreur 'error: expected unqualified-id before ‘.’ token'
    //a.val=1;        // erreur édition des liens 'référence indéfinie vers « A::val »'
    //A->val=1;       // error: expected unqualified-id before ‘->’ token
    //a->val=1;       // error: base operand of ‘->’ has non-pointer type ‘A’
    return 0;
}

/*
 * A::val est la bonne réponse
 * il faut que la variable statique soit initialisée en  dehors de la classe
 * int A::val=0; par ex
 */
