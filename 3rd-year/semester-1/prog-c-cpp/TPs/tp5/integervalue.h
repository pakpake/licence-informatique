#ifndef INTEGERVALUE_H
#define INTEGERVALUE_H

#include "integeritem.h"

namespace rpg {
    class IntegerValue : public IntegerItem {
        // attribut
        int value;

        public:
        // constructeur
        IntegerValue(int);

        // constructeur de clonage
        IntegerValue(const IntegerValue&) = default;

        // destructeur
        virtual ~IntegerValue() = default;

        // operateur de cast
        operator int () const;

        // méthode virtuelle
        virtual int getValue() const;

    };
}


#endif
