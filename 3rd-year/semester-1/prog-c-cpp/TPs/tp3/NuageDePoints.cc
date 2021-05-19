#include "NuageDePoints.h"
#include <string>

// constructeur
NuageDePoints::NuageDePoints(int nb) {
    // création d'un vecteur de taille nb
    // NuageDePoints::nuage();

    // ajoute au vecteur nuage un nouveau point généré aléatoirement
    for(int i=0;i<nb;i++) {
        nuage.push_back(aleatoire());
    }

}

// fonction d'affichage
void NuageDePoints::print() {
    cout << "Liste des points du nuage" << endl;
    // parcourt du vecteur avec un itérateur de type NuageDePoints 
    for (vector<Point>::iterator i=nuage.begin(); i != nuage.end(); i++)
        // *i est l'objet pointé par l'itérateur, donc un point
        cout << *i << endl;
}

Point NuageDePoints::barycentre() {
    int n=0;    // compteur d'éléments
    double sx=0.,sy=0.;   // somme des coordonnees des points x et y

    // parcourt du vecteur avec un itérateur de type NuageDePoints 
    for (vector<Point>::iterator i=nuage.begin(); i != nuage.end(); i++) {
        // *i est l'objet point dont on récupère les coordonnees avec getX,Y
        sx += (*i).getX();
        sy += (*i).getY();
        n++;
    }
    return Point(sx/this->nuage.size(),sy/this->nuage.size());
    // return Point(sx/n,sy/n);
}
