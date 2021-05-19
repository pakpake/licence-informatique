#include <iostream>
#include "catch2/catch.hpp"
#include "../src/Dice.h"

using namespace rpg;
using namespace std;

TEST_CASE ("Dice Test") {
    cout << endl;
    cout << "*** Dice Test ***" << endl;
    rpg::Dice d4(4);
    REQUIRE(d4.getNbFaces() == 4);
    const int N = 20;
    for(int i = 1; i <=N; i++) cout << d4.getValue() << " ";
    cout << endl;
    rpg::Dice d10(10);
    REQUIRE(d10.getNbFaces() == 10);
    for(int i = 1; i <=N; i++) cout << d10.getValue() << " ";
    cout << endl;
    cout << "*** Fin Dice Test ***" << endl;
}
