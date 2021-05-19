#ifndef ITEM_H
#define ITEM_H

#include "damageable.h"

namespace rpg {
    class Item : public Damageable {
        int value;  // valeur marchande

        public:

        // constructeur
        Item(int hitPoints, string name, int val);        

        // constructeur de clonage
        Item(const Item&) = default; // méthode par défaut générée par le compilateur

        // destructeur
        virtual ~Item() = default;

        // méthode public d'affichage
        ostream& print(ostream&) const;
    };
    // opérateur d'affichage d'un Item par ostream
    ostream& operator<<(ostream &os,const Item &);
}


#endif
