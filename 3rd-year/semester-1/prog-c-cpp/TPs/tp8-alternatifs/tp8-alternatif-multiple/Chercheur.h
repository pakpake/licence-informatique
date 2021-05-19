#ifndef __CHERCHEUR_H__
#define __CHERCHEUR_H__

#include <iostream>
#include <cstring>
#include "Personne.h"

using namespace std; 
class Chercheur : public Personne {

  private:
    const string _domaine; // membre de la classe Enseignant

  public: 
    // accesseur
    string domaine() const { return _domaine; }

    // constructeur avec initialisation du nom de l'enseignant et du cours
    Chercheur(const string &n = (const string) string("Nobody"), 
        const string &d = (const string) string("Nodomain"));

    // destructeur
    ~Chercheur();

    // methode publique d'affichage
    ostream& print(ostream&) const;

};

//operateur d'affichage d'une personne
ostream& operator<<(ostream &os, const Chercheur &);

#endif
