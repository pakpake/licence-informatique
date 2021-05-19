#include <iostream>
#include "catch2/catch.hpp"
#include "../src/Weapon.h"


using namespace rpg;
using namespace std;

TEST_CASE ("Weapon Test") {
    cout << endl;
    cout << "*** Weapon Test ***" << endl;
    rpg::Weapon weapon1(10);
    cout << "weapon1: " << weapon1 << endl;
    cout << endl;
    REQUIRE(weapon1.getDamage() == 10);
    cout << "*** Weapon Test Completed ***" << endl;
}
