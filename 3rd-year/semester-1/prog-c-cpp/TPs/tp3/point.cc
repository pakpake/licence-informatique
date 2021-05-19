#include "point.h"

// définition du constructeur
Point::Point(double abs, double ord) {
    x = abs;
    y = ord;
}

// re-déclaration du constructeur
Point::Point(const Point &p2) {
    x = p2.x;
    y = p2.y;
}

// fonction d'affichage
void Point::affiche() {
    cout << "x = " << x << endl;
    cout << "y = " << y << endl;
}

// getters
double Point::getX() {
    return x;
}

double Point::getY() {
    return y;
}

// setter
void Point::setPoint(double a, double b) {
    x = a;
    y = b;
} 

// norme
double Point::norme() {
    return(sqrt(x*x+y*y));
}

// fonction testEgal
bool Point::testEgal(Point p2) {
    // 2 points sont égaux ssi leurs coordonnées sont égales
    return (x == p2.x && y == p2.y);
}

// fonction de test d'égalité de 2 points
bool Point::operator==(Point p2) {
    // 2 points sont égaux ssi leurs coordonnées sont égales
    // return (x == p2.x && y == p2.y);
    return this->testEgal(p2);
}

// fonction améliorée de l'affichage d'un point par ostream
ostream& operator<<(ostream &os, const Point &p) {
    // appel de la fonction print
    return p.print(os);
}

// fonction membre avec un ostream en parametre
ostream& Point::print(ostream& os) const {
    return os << "(" << x << "," << y << ")" ;
}

// fonction qui renvoie un point aléatoire
Point aleatoire() {
    // creation d'un point
    Point p;
    // creation des coordonnées 
    double abs,ord;

    // remplissage des coordonnées en faisant appel à une fonction de utilities
    abs = randouble(0.,10.);
    ord = randouble(0.,10.);
    // creation du point avec les coordonnées
    // on aurait pu utiliser le constructeur Point(abs,ord) 
    p.setPoint(abs,ord);
    return p;
}
