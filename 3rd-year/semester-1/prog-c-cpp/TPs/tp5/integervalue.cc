#include "integervalue.h"

using namespace rpg;

// IntegerValue::IntegerValue(int val) : IntegerItem(), value(val) {}
IntegerValue::IntegerValue(int val) : value(val) {}


IntegerValue::operator int () const {
    return value;
}
/* Est-il nécessaire d’écrire un opérateur d’égalité avec int ? Pourquoi ?
 *  Non, car int est un type connu, dont les oérateurs sont dérivés automatiquement
 *
 * Est-il nécessaire d’écrire les opérateur de comparaison > < ≥ ≤ avec int ?  Pourquoi ?
 *  Même réponse
*/

int IntegerValue::getValue() const {
    return value;
}

