#include <typeinfo>
#include "Chercheur.h"

using namespace std;

// constructeur avec initialisation du nom
Chercheur::Chercheur(const string &n, const string &d) :
  Personne(n), _domaine(d) {
  clog << "log: Constructeur de Chercheur,  Objet " << id() << " -> "
    << nom() << endl;
}

// destructeur
Chercheur::~Chercheur(){
  clog << "log: Destructeur de Chercheur,   Objet " << id() << " -> "
    << nom() << endl;
}

// methode publique d'affichage
ostream& Chercheur::print(ostream& os) const {
  os << "Type ----- : " << typeid(this).name() << endl; // type de l'objet 
  os << " ------ Id : " << id() << endl;
  os << " ---- Name : " << nom() << endl;
  os << " - Domaine : " << domaine() << endl;
  return os;
}

//operateur d'affichage d'une personne
ostream& operator<<(ostream &os, const Chercheur &C){
  return C.print(os);
}

