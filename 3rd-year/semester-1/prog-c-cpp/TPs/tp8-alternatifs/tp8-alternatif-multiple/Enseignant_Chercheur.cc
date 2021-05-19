#include "Enseignant_Chercheur.h"

using namespace std;

// constructeur avec valeurs par defaut
Enseignant_Chercheur::Enseignant_Chercheur(const string &n, 
    const string &c, const string &d, const Corps co) : 
  Enseignant(n, c), Chercheur(n, d), _corps(co) {
  clog << "log: Constructeur de EC,         ObjetE " << Enseignant::id() 
    << " -> " << Enseignant::nom() << endl;
  clog << "log: Constructeur de EC,         ObjetC " << Chercheur::id()
    << " -> " << Chercheur::nom() << endl;
}

// destructeur
Enseignant_Chercheur::~Enseignant_Chercheur(){
  clog << "log: Destructeur de EC,          ObjetE " << Enseignant::id() 
    << " -> " << Enseignant::nom() << endl;
  clog << "log: Destructeur de EC,          ObjetC " << Chercheur::id()
    << " -> " << Chercheur::nom() << endl;
}

// methode publique d'affichage
ostream& Enseignant_Chercheur::print(ostream& os) const {
  os << "Type ----- : " << typeid(this).name() << endl; 
  os << " ------ IdE : " << Enseignant::id() << endl;
  os << " ---- NameE : " << Enseignant::nom() << endl;
  os << " ---- Cours : " << cours() << endl;
  os << " ------ IdC : " << Chercheur::id() << endl;
  os << " ---- NameC : " << Chercheur::nom() << endl;
  os << " -- Domaine : " << domaine() << endl;
  os << " ---- Corps : " << (corps()==MCF ? "MCF" : "PU") << endl;
  return os;
}

//operateur d'affichage d'un EC
ostream& operator<<(ostream &os, const Enseignant_Chercheur &EC){
  return EC.print(os);
}

