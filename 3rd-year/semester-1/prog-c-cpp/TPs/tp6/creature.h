#ifndef CREATURE_H
#define CREATURE_H

#include "damageable.h"
#include "String.h"

namespace rpg {
    class Creature : public Damageable {
        int physic;     // capacité physique
        int mental;     // capacité mentale

        public:

        // constructeur
        Creature(int hitPoints, String name, int phy, int men);        

        // constructeur de clonage
        Creature(const Creature&) = default; // méthode par défaut générée par le compilateur

        // destructeur
        virtual ~Creature() = default;

        // méthode public d'affichage
        ostream& print(ostream&) const;
    };
    // opérateur d'affichage d'un Creature par ostream
    ostream& operator<<(ostream &os,const Creature &);
}


#endif
