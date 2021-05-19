#ifndef __DICE_H__ 
#define __DICE_H__

#include <iostream>
#include <cstdlib>
#include <time.h>
#include "IntegerValue.h"

using namespace std;
namespace rpg {

// La classe IntegerItem étant totalement abstraite, j'ai pris la décision de 
// faire hériter Dice à partir de IntegerValue ce qui me permettra d'accéder
// aux attributs sans soucis
class Dice : public IntegerValue {
private:
	// nombre de faces
	int nbFaces;
	const bool trace = false;   // un booléen pour gérer l'affichage
public:
	// constructeur qui initialise aussi le générateur de nombres aléatoires
	Dice(int n) : nbFaces(n)
		{ srand((unsigned)time(NULL)); }
	// redéfinition de getValue()
	int getValue() const { 
             return int(std::rand() / (double)RAND_MAX * (nbFaces))+1;
	}
	// accesseur
	int getNbFaces() const { return nbFaces; }
	// affichage
	friend ostream& operator<<(ostream &os, const Dice &d) {
		os << "Dice: nbFaces=" << d.getNbFaces();
		return os;
	}
};

} // fin du namespace "rpg"

#endif
