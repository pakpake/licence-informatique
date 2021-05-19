#ifndef INTEGERITEM_H
#define INTEGERITEM_H

namespace rpg {
    class IntegerItem {
        public:
            // constructeur
            IntegerItem();

            // destructeur
            virtual ~IntegerItem() = default;


            // méthode virtuelle sans implémentation
            virtual int getValue() const = 0;
    };
}


#endif
