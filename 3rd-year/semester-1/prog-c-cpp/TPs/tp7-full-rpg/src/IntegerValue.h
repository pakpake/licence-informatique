#ifndef __INTEGERVALUE_H__ 
#define __INTEGERVALUE_H__

// Rq: il n'est pas dit dans l'énoncé que la classe soit dans "rpg"
// mais, si elle dérive de IntegerItem, il semble logique de la placer dans "rpg"
  
#include "IntegerItem.h"

namespace rpg {

class IntegerValue : public IntegerItem {
private:
	// la valeur
	int value;
public:
        // constructeur
	IntegerValue(int v) : value(v) {}
	IntegerValue() : IntegerValue(0) {}
	// copie
	IntegerValue(IntegerValue &i) : IntegerValue(i.value) {}
	// On doit proposer une implémentation de getValue
	int getValue() const { return value; }
	// On doit proposer une implémentation de setValue
	void setValue(int v) { value = v; }
	// conversion IntegerValue -> int
	operator int() const { return getValue(); }
	// Il n'est pas nécessaire d'écrire d'opérateurs de comparaison
	// (IntegerValue==int) : sera remplacé par (int==int) grâce à la conversion 
        // des types faite par le compilateur
	// Idem pour tous les opérateurs de comparaison int/int
	// C'est aussi vrai pour l'affichage, il y aura conversion automatique vers un int.
	
	// operator+
	IntegerValue operator+(int i) const {
		int n = getValue() + i;
		return IntegerValue(n);
	}
	IntegerValue operator+(const IntegerValue &i) const {
		int n = getValue() + i.getValue();
		return IntegerValue(n);
	}
};

} // fin du namespace "rpg"

#endif
