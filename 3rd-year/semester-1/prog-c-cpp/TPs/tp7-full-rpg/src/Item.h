#ifndef __ITEM_H__ 
#define __ITEM_H__

#include <iostream>
#include "Damageable.h"

using namespace std;
namespace rpg {

class Item : public Damageable {
private:
	// valeur marchande
	double value;
public:
	// constructeur,  doit aussi initialiser Damageable
	Item(double v, int hp, const char *name) : Damageable(hp, name), value(v) {}
	// copie
	Item(Item &i) : Item(i.value, i.getHitPoints(), i.getName()) {}
	// accesseurs
	double getValue() const { return value; }
	Damageable getDamageable() const { return Damageable(*this); }
	// affichage
	friend ostream & operator<<(ostream &os, const Item &i) {
		os << "Item(value=" << i.getValue();
		os << ", " << (const Damageable)(i) << ")";
		return os;
	}
};

} // fin du namespace "rpg"

#endif
