#ifndef DAMAGEABLE_H 
#define DAMAGEABLE_H

#include <iostream>
#include "String.h"

using namespace std;

namespace rpg {
    class Damageable {
        //double m_hitPoints;
        // incohérence dans le sujet
        int m_hitPoints;
        String m_name;

        public:

        // constructeur
        Damageable(int hitPoints, String name);

        // constructeur de clonage
        Damageable(const Damageable&) = default;

        // destructeur
        virtual ~Damageable() = default;

        // getter
        // const double& getPoints() const;
        const int& getPoints() const;
        const String& getName() const;
        // setter
        void setName(String name);

        void damage(int damage);
        bool isDead();
        void healRepair(int heal);

        // méthode public d'affichage
        ostream& print(ostream&) const;

    };

    // opérateur d'affichage d'un Damageable par ostream
    ostream& operator<<(ostream &os,const Damageable &);
}

#endif
