#ifndef POINT_H
#define POINT_H

#include <iostream>
#include <math.h>
#include "utilities.h" 

using namespace std;

class Point {
    double x,y;

    public :
    // constructeur
    /* Créer un point à partir de ses coordonnées (par défaut 0.0,0.0) */
    Point(double x=0., double y=0.);

    // copie du constructeur Point de Point
    Point(Point const &p2);

    // Fonction d'affichage des coordonnées du point
    void affiche();

    // getter permettant l'accès aux coordonnées d'un point
    double getX();
    double getY();

    // setter permettant de manipuler les variables privés
    void setPoint(double a, double b);

    // fonction de calcul de la norme d'un vecteur
    double norme();

    // fonction testEgal
    bool testEgal(Point p2);

    // fonction de test d'égalité de 2 points
    bool operator==(Point p2);

    // méthode publique d'affichage
    ostream& print(ostream&) const;

};

// fonction améliorée de l'affichage d'un point par ostream
ostream& operator<<(ostream &os,const Point &p);



// fonction qui renvoie un point aléatoire
Point aleatoire();

#endif
