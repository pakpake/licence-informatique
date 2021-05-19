#include <iostream>
#include "catch2/catch.hpp"
#include "../src/Damageable.h"

using namespace rpg;
using namespace std;


TEST_CASE ( "Damageable Test" ) {
    cout << endl;
    cout << "*** Damageable Test ***" << endl;

    Damageable damageable1(10, "TestedDamageable");
    REQUIRE( damageable1.getHitPoints() == 10 );
    REQUIRE( damageable1.isDead() == false );
    Damageable K(12, "Knight");
    REQUIRE( K.getHitPoints() == 12 );
    K.damage(8);
    REQUIRE( K.getHitPoints() == 4 );
    REQUIRE( K.isDead() == false );
    K.healRepair(6);
    REQUIRE( K.getHitPoints() == 10 );
    K.damage(52);
    REQUIRE( K.isDead() == true );
    K.healRepair(8);
    REQUIRE( K.isDead() == true );
    
    cout << "*** Damageable Test Completed ***" << endl;
}
