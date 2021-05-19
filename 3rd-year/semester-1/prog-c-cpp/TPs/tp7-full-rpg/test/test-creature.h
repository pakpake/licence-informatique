#include <iostream>
#include "catch2/catch.hpp"
#include "../src/Creature.h"
#include "../src/Dice.h"
#include "../src/Race.h"

using namespace rpg;
using namespace std;


TEST_CASE ("Creature test") {
    cout << endl;
    cout << "*** Creature Test ***" << endl;
    Dice d6(6);
    cout << "Creature" << endl;
    Race humanRace(d6+d6+d6, d6+d6+6);
    Creature King("Thingol", humanRace);
    cout << "King: " << King << endl;
    Race orcRace(d6+d6+6, d6+d6);
    Creature orc1("Boldog", orcRace);
    cout << "orc1: " << orc1 << endl;
    cout << endl;
    REQUIRE(King.getPhysic() >= 3);
    REQUIRE(King.getMental() >= 8);
    REQUIRE(orc1.getPhysic() >= 8);
    REQUIRE(orc1.getMental() >= 2);
    cout << "*** Creature Test Completed ***" << endl;
}
