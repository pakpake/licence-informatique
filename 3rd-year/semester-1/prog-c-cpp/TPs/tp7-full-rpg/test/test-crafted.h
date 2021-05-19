#include <iostream>
#include "catch2/catch.hpp"
#include "../src/Item.h"
#include "../src/Weapon.h"
#include "../src/CraftedWeapon.h"


using namespace rpg;
using namespace std;

TEST_CASE ("CraftedWeapon") {
    cout << endl;
    cout << "*** CraftedWeapon Test ***" << endl;
    rpg::Weapon weapon1(10);
    rpg::Item item1(20.0, 30, "item1");
    cout << "item1: " << item1 << endl;
    rpg::CraftedWeapon craftedweapon1(weapon1, item1);
    cout << "craftedweapon1: " << craftedweapon1 << endl;
    cout << endl;
    REQUIRE(craftedweapon1.getDamage() == 10);
    REQUIRE(craftedweapon1.getValue() == 20.0);
    cout << "*** CraftedWeapon Test Completed***" << endl;
}
