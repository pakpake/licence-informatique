#ifndef DAMAGEABLE_H 
#define DAMAGEABLE_H

#include <iostream>
#include <string>

using namespace std;

namespace rpg {
    class Damageable {
        //double m_hitPoints;
        // incohérence dans le sujet
        int m_hitPoints;
        string m_name;

        public:

        // constructeur
        Damageable(int hitPoints, string name);

        // getter
        // const double& getPoints() const;
        const int& getPoints() const;
        const string& getName() const;
        // setter
        void setName(string name);

        void damage(int damage);
        bool isDead();
        void healRepair(int heal);

    };
}

#endif
