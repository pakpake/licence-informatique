#ifndef __ENSEIGNANT_H__
#define __ENSEIGNANT_H__

#include <iostream>
#include <cstring>
#include "Personne.h"

using namespace std; 

class Enseignant : public Personne {
  private:
    const string _cours; // membre de la classe Enseignant

  public: 
    // accesseur
    string cours() const { return _cours; }

    // constructeur avec initialisation du nom de l'enseignant et du cours
    Enseignant(const string &n = (const string) string("Nobody"), 
        const string &c = (const string) string("Noclass"));

    // destructeur
    ~Enseignant();

    // methode publique d'affichage
    ostream& print(ostream&) const;

};

//operateur d'affichage d'une personne
ostream& operator<<(ostream &os, const Enseignant &);

#endif
