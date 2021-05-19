#ifndef __DAMAGEABLE_H__ 
#define __DAMAGEABLE_H__
#include <iostream>
#include "String.h"

using namespace std;
namespace rpg {

class Damageable {
private:
	// points de vie
	double hitPoints;
	// le nom du personnage
	String name;
public:
	// constructeur
	Damageable(int hp, const char *n) 
		: hitPoints(hp), name(String(n)) 
		{}
	// Accesseurs
	double getHitPoints() const { return hitPoints; }
	String getName() const { return name; }
	// mutateurs
	void setName(String &n) { name = n; }
	// dommages
	void damage(int d) {
		hitPoints -= (double)d; // hitPoints est double, pas int
		if (hitPoints < 0.0) hitPoints = 0.0; // correction si négatif
	}
	// etat du personnage, ne modifie pas l'état du personnage, donc const
	bool isDead() const { return hitPoints == 0.0; }
	// soins, modifie l'état du personnage, donc pas const
	void healRepair(int heal) { 
		// seulement si pas mort, les deux syntaxes OK
		if (!isDead())
		//if (!this->isDead())
			hitPoints += (double)heal;
	}
	// affichage
	friend ostream& operator<<(ostream &os, const Damageable &d) {
		os << "Damageable(hitPoints=" << d.getHitPoints()
		   << ", name=" << d.getName() << ")";
		return os;
	}
};

} // fin du namespace "rpg"

#endif
