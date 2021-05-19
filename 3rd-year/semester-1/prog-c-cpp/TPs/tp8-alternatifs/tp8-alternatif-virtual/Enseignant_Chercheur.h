#ifndef __ENSEIGNANT_CHECHEUR_H__
#define __ENSEIGNANT_CHECHEUR_H__

#include <iostream>
#include "Personne.h"
#include "Enseignant.h"
#include "Chercheur.h"

using namespace std;

typedef enum { MCF, PU } Corps;

class Enseignant_Chercheur : 
  public virtual Enseignant, public virtual Chercheur {

    private:
      const Corps _corps; // membre de la classe EC

    public:
      Corps corps() const { return _corps; }

      // constructeur avec initialisation par defaut
      Enseignant_Chercheur(const string &n= (const string) string("Nobody"), 
          const string &c=(const string) string("Noclass"), 
          const string &d = (const string) string("Nodomain"), 
          const Corps = MCF);

      // destructeur
      ~Enseignant_Chercheur();

      // methode publique d'affichage
      ostream& print(ostream&) const;

  };

//operateur d'affichage d'une personne
ostream& operator<<(ostream &os, const Enseignant_Chercheur &);

#endif
