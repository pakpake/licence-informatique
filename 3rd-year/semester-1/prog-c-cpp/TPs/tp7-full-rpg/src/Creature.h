#ifndef __CREATURE_H__  
#define __CREATURE_H__

#include <iostream>
#include "Damageable.h"
#include "Race.h"

using namespace std;
namespace rpg {

class Creature : public Damageable {
private:
	// capacité physique
	int physic;
	// capacité mentale
	int mental;
	// Race
	Race race;
public:
	// constructeur
	Creature(const char *n, const Race &r) 
	: Damageable(r.getPhysicFormula(), n), race(r) 
	{
		physic = r.getPhysicFormula();
		mental = r.getMentalFormula();
	}
	int getPhysic() const { return physic; } // accesseur
	void setPhysic(int p) { physic = p; } // mutateur
	int getMental() const { return mental; } // accesseur
	void setMental(int m) { mental = m; } // mutateur
	// affichage
	friend ostream& operator<<(ostream &os, Creature &c) {
		os << "Creature(" << Damageable(c) << ", " << c.race
		   << ", physic=" << c.getPhysic()
		   << ", mental=" << c.getMental() << ")";
		return os;
	}
};

} // fin du namespace "rpg"

#endif
