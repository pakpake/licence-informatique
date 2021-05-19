#ifndef __CRAFTEDWEAPON_H__  
#define __CRAFTEDWEAPON_H__

#include "Weapon.h"
#include "Item.h"
#include <iostream>
using namespace std;

namespace rpg {

// CraftedWeapon est à la fois une arme et un objet,
// donc hérite de Weapon et Item
class CraftedWeapon : public Weapon, public Item {
private:
public:
	// constructeur
	CraftedWeapon(const Weapon &w, const Item &i) 
	: 
		Weapon(w.getDamage()), 
		Item(i.getValue(), 
		     i.getDamageable().getHitPoints(), 
		     i.getDamageable().getName()) 
	{}
	// copie
	CraftedWeapon(const CraftedWeapon &cw)
	: 
		Weapon(cw.Weapon::getDamage()),
		Item(cw.Item::getValue(), 
		     cw.Item::getDamageable().getHitPoints(), 
		     cw.Item::getDamageable().getName()) 
	{}
	// affichage
	friend ostream & operator<<(ostream &os, const CraftedWeapon &cw) {
		os << "CraftedWeapon(" << (const Weapon&)(cw) 
		   << ", " << (const Item&)(cw) << ")";
		return os;
	}
};

}

#endif
