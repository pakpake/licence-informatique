#include <typeinfo>
#include "Personne.h"

using namespace std;

// constructeur avec initialisation du nom
Personne::Personne(const string &n) : _nom(n), _id(++_uniqueId) {
  clog << "log: Constructeur de Personne,   Objet " << id() << " -> "
    << nom()<< endl;
}

// destructeur
Personne::~Personne(){
  clog << "log: Destructeur de Personne,    Objet " << id() << " -> "
    << nom()<< endl;
}

// methode publique d'affichage
ostream& Personne::print(ostream& os) const {
  os << "Type ----- : " << typeid(this).name() << endl; // type de l'objet 
  os << " ------ Id : " << id() << endl;
  os << " ---- Name : " << nom() << endl;
  return os;
}

//operateur d'affichage d'une personne
ostream& operator<<(ostream &os, const Personne &P) {
  return P.print(os);
}

int Personne::_uniqueId = 0; // compteur global pour donner une id a chq objet
