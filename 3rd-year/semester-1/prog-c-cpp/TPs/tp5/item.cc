#include "item.h"

using namespace rpg;

// constructeur de Item faisant appel au constructeur de Damageable
Item::Item(int hitPoints, string name, int val) : Damageable(hitPoints, name), value(val) {}

// sortie sur flot (méthode)
ostream& Item::print(ostream& os) const {
    os << "Type      : " << typeid(this).name() << endl;
    os << "  name    : " << this->getName() << endl;
    os << "  hit pts : " << this->getPoints() << endl;
    os << "  valeur  : " << value << endl;
    return os;
}

// sortie sur flot (fonction externe)
ostream& rpg::operator<<(ostream &os, const Item &i) {
    return i.print(os);
}
