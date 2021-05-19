#include "race.h"

using namespace rpg;

Race::Race(IntegerItem& p, IntegerItem& m) : physicFormula(p), mentalFormula(m) {}

IntegerItem& Race::phys() {
    return physicFormula;
}

const IntegerItem& Race::phys() const{
    return physicFormula;
}

IntegerItem& Race::ment() {
    return mentalFormula;
}

const IntegerItem& Race::ment() const{
    return mentalFormula;
}

