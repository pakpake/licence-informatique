#ifndef __INTEGERITEM_H_ 
#define __INTEGERITEM_H_

namespace rpg {

class IntegerItem { // est une classe abstraite, on ne peut donc pas l'instancier
private:

public:
	virtual int getValue() const = 0;
	virtual void setValue(int v) = 0;
};

} // fin du namespace "rpg"

#endif
