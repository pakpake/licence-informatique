#include "dice.h"
#include <cstdlib>

using namespace rpg;

// petite modif pour les goodies
//Dice::Dice(int nf) : IntegerItem(), nbFaces(nf) {
Dice::Dice(int nf) : IntegerItem(), _nbFaces(nf) {
    std::srand(0);
}

int Dice::getValue() const {
// petite modif pour les goodies
    //return int(std::rand() / (double)RAND_MAX * (nbFaces))+1;
    return int(std::rand() / (double)RAND_MAX * (_nbFaces))+1;
}

// Ne pas oublier l'operateur hérité de IntegerItem
//int operator+(const IntegerItem& i) const {
int Dice::operator+(const IntegerItem& i) const {
    return this->getValue() + i.getValue();
}

// C'est un nouvel opérateur de la classe Dice
// Celui là n'a JAMAIS été défini dans IntegerItem
//int operator+(const Dice& d) const {
int Dice::operator+(const Dice& d) const {
    return this->getValue() + d.getValue();
}

// C'est l'implémantation de operator+ hérité de IntegerItem
//int operator+(const int& i) {
int Dice::operator+(const int& i) const {
    return this->getValue() + i;
}
