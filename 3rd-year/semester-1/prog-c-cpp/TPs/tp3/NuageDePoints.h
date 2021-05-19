#ifndef NUAGEDEPOINTS_H
#define NUAGEDEPOINTS_H

#include <iostream>
#include "point.h"
#include <vector>

using namespace std;

class NuageDePoints {
    // vecteur des points de la classe Point, membre de la classe NuageDePoints
    vector<Point> nuage;
            
    public:
        // constructeur prenant le nombre de points en paramètre
        NuageDePoints(int nb);

        // fonction d'affichage
        void print();

        // calcul du barycentre
        Point barycentre();
};

#endif
