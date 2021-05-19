#ifndef INTEGERITEM_H
#define INTEGERITEM_H

namespace rpg {
    class IntegerItem {
        int max_value;
        int min_value;

        public:
            // constructeur
            IntegerItem();

            // destructeur
            virtual ~IntegerItem() = default;

            // méthode virtuelle sans implémentation
            virtual int getValue() const = 0;
            virtual int operator+(const IntegerItem&) const = 0;
            virtual int operator+(const int&) const = 0;

            // autres méthodes
            const int getMaxValue() const;
            const int getMinValue() const;
    };
}
#endif
