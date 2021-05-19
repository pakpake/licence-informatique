#include "creature.h"

using namespace rpg;

// constructeur de Creature faisant appel au constructeur de Damageable
Creature::Creature(int hitPoints, String name, int phy, int men) : Damageable(hitPoints, name), physic(phy), mental(men) {}

// sortie sur flot (méthode)
ostream& Creature::print(ostream& os) const {
    os << "Type      : " << typeid(this).name() << endl;
    os << "  name    : " << this->getName() << endl;
    os << "  hit pts : " << this->getPoints() << endl;
    os << "  cap phy : " << physic << endl;
    os << "  cap men : " << mental << endl;
    return os;
}

// sortie sur flot (fonction externe)
ostream& rpg::operator<<(ostream &os, const Creature &i) {
    return i.print(os);
}
