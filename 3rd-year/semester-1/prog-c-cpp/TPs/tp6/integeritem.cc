#include "integeritem.h"

using namespace rpg;

// constructeur
IntegerItem::IntegerItem() {}

const int IntegerItem::getMaxValue() const {
    return max_value;
}

const int IntegerItem::getMinValue() const {
    return min_value;
}
