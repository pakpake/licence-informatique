#ifndef __WEAPON_H__ 
#define __WEAPON_H__

#include "IntegerItem.h"
#include "IntegerValue.h"
#include <iostream>

using namespace std;
namespace rpg {

class Weapon {
private:
	// Il est impossible d'instancier un objet d'une classe abstraite.
	// Je choisi donc d'instancier en IntegerValue 
	// qui est une implémentation de IntegerItem
	//IntegerItem damage;
	rpg::IntegerValue damage;
public:
	Weapon(int i) : damage(i) {}
	int getDamage() const { return damage; }
	// affichage
	friend ostream& operator<<(ostream &os, const Weapon &w) {
		os << "Weapon(damage=" << w.getDamage() << ")";
		return os;
	}
};

}

#endif
