#include "Enseignant_Chercheur.h"

using namespace std;

// constructeur avec valeurs par defaut
Enseignant_Chercheur::Enseignant_Chercheur(const string &n, 
    const string &c, const string &d, const Corps co) : 
  Personne(n), Enseignant(n, c), Chercheur(n, d), _corps(co)
{
  //Personne::nom = n; cours = c; domaine = d; corps = co;
  clog << "log: Constructeur de EC,         Objet " << id() << " -> "
    << nom() << endl;
}

// destructeur
Enseignant_Chercheur::~Enseignant_Chercheur(){
  clog << "log: Destructeur de EC,          Objet " << id() << " -> "
    << nom() << endl;
}

// methode publique d'affichage
ostream& Enseignant_Chercheur::print(ostream& os) const {
  os << "Type ----- : " << typeid(this).name() << endl; // permet d'avoir le type de l'objet support
  os << " ------ Id  : " << id() << endl;
  os << " ---- Name  : " << nom() << endl;
  os << " --- Cours  : " << cours() << endl;
  os << " - Domaine  : " << domaine() << endl;
  os << " --- Corps  : " << (corps()==MCF ? "MCF" : "PU") << endl;
  return os;
}

//operateur d'affichage d'un EC
ostream& operator<<(ostream &os, const Enseignant_Chercheur &EC){
  return EC.print(os);
}

