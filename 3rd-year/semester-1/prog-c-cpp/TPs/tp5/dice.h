#ifndef DICE_H
#define DICE_H

#include "integeritem.h"

namespace rpg {
    class Dice : public IntegerItem {
        // attribut
        int nbFaces;

        public:
        // constructeur
        Dice(int);

        // constructeur de clonage
        Dice(const Dice&) = default;

        // destructeur
        virtual ~Dice() = default;

        // méthode virtuelle
        virtual int getValue() const;

    };
}


#endif
