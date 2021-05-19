#ifndef RACE_H
#define RACE_H

#include "integeritem.h"

namespace rpg {
    class Race {
        IntegerItem& physicFormula;
        IntegerItem& mentalFormula;

        public:
        // constructeur
        Race(IntegerItem& p, IntegerItem& m);
        // constructeur de clonage
        Race(const Race&) = default;

        // destructeur
        virtual ~Race() = default;

        // accesseurs (getter/setter)
        IntegerItem& phys(); // setter
        const IntegerItem& phys() const; // getter
        IntegerItem& ment(); // setter
        const IntegerItem& ment() const; // getter
    };
}
#endif
