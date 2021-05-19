#ifndef DICE_H
#define DICE_H

#include "integeritem.h"

namespace rpg {
    class Dice : public IntegerItem {
        // attribut
	// remarquer ici le préfixe "_" qui permet ce créer ensuite des accesseurs avec le bon nom, c'est à dire sans "get" et "set"
        //int nbFaces;
        int _nbFaces;

        public:
        // constructeur
        Dice(int);

        // constructeur de clonage
        Dice(const Dice&) = default;

        // destructeur
        virtual ~Dice() = default;

        // méthode virtuelle
        virtual int getValue() const;
	// inutile de faire du friend
        //friend int operator+(const Dice&) const;
        //friend int operator+(const int&) const;
        int operator+(const IntegerItem&) const; // implementation de operatot+ hérité de IntegerItem
        int operator+(const Dice&) const; // nouvel operateur
        int operator+(const int&) const; // implementation de operator+ hérité de IntegerItem

	// qq goodies :-)
	// pour connaître le nombre de faces, donc le min et le max
	int nbFaces() const { return _nbFaces; }
	int min() const { return 1; }
	int max() const { return _nbFaces; }
	// pour modifier dynamiquement le nombre de faces
	void nbFaces(int n) { _nbFaces = n; }
	// la même chose, à l'ancienne
	int getNbFaces() const { return _nbFaces; }
	void setNbFaces(int n) { _nbFaces = n; }
    };
    // inutile
    //int operator+(const Dice&) const;
    //int operator+(const int&) const;
}


#endif
