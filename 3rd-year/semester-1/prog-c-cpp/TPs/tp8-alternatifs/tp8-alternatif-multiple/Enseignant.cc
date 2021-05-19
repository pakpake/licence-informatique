#include <typeinfo>
#include "Enseignant.h"

using namespace std;

// constructeur avec initialisation du nom
Enseignant::Enseignant(const string &n, const string &c) : 
  Personne(n), _cours(c) {
    clog << "log: Constructeur de Enseignant, Objet " << id() << " -> "
    << nom() << endl;
  }

// destructeur
Enseignant::~Enseignant(){
  clog << "log: Destructeur de Enseignant,  Objet " << id() << " -> "
    << nom() << endl;
}

// methode publique d'affichage
ostream& Enseignant::print(ostream& os) const {
  os << "Type ----- : " << typeid(this).name() << endl; 
  os << " ------ Id : " << id() << endl;
  os << " ---- Name : " << nom() << endl;
  os << " --- Cours : " << cours() << endl;
  return os;
}

//operateur d'affichage d'une personne
ostream& operator<<(ostream &os, const Enseignant &E){
  return E.print(os);
}

