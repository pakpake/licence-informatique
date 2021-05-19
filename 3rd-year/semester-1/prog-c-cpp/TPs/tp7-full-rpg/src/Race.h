#ifndef __RACE_H__ 
#define __RACE_H__

#include <iostream>
#include "IntegerValue.h"

using namespace std;
namespace rpg {

class Race {
private:
	IntegerValue physicFormula;
	IntegerValue mentalFormula;
public:
	// constructeur
	Race(int p, int m) : physicFormula(p), mentalFormula(m) {}
	// Copie
	Race(const Race &r) : Race(r.getPhysicFormula(), r.getMentalFormula()) {}
	// accesseurs
	int getPhysicFormula() const { return physicFormula; }
	int getMentalFormula() const { return mentalFormula; }
	// affichage
	friend ostream& operator<<(ostream &os, Race &r) {
		os << "Race(physicFormula=" << r.getPhysicFormula() 
		   << ", mentalFormula=" << r.getMentalFormula() << ")"; 
		return os;
	}
};

} // fin du nameqpace "rpg"

#endif
