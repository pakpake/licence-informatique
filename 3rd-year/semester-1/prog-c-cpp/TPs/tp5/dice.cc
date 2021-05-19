#include "dice.h"
#include <cstdlib>

using namespace rpg;

Dice::Dice(int nf) : IntegerItem(), nbFaces(nf) {
    std::srand(0);
}

int Dice::getValue() const {
    return int(std::rand() / (double)RAND_MAX * (nbFaces))+1;
}
