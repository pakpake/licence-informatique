#ifndef __PERSONNE_H__
#define __PERSONNE_H__

#include <iostream>
#include <cstring>

using namespace std; 
class Personne {
  private:
    static int _uniqueId; // compteur global pour donner une id a chq objet
    const string _nom; //membre de la classe Personne
    const int _id; // identifiant de l'objet

  public: 
    // accesseurs
    int uniqueId() const { return _uniqueId; }
    string nom () const { return _nom; }
    int id() const { return _id; }

    // constructeur avec initialisation du nom
    Personne(const string &n = (const string) string("Nobody"));

    // destructeur
    ~Personne();

    // methode publique d'affichage
    ostream& print(ostream&) const;

};

//operateur d'affichage d'une personne
ostream& operator<<(ostream &os, const Personne &);

#endif
