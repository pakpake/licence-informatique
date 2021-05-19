#include <iostream>
#include "catch2/catch.hpp"
#include "../src/IntegerValue.h"


using namespace rpg;
using namespace std;

TEST_CASE ("IntegerValue Test") {
    cout << endl;
    cout << "*** IntegerValue Test ***" << endl;
    rpg::IntegerValue v1(1);
    rpg::IntegerValue v2(v1); // copie
    // test égalité
    if (v1 == 1) cout << "v1 == OK" << endl;
    else { cout << "Erreur 1" << endl; exit(1); }
    // test différence
    if (v1 != 2) cout << "v1 != OK" << endl;
    else { cout << "Erreur 2" << endl; exit(1); }
    // test inférieur
    if (v1 < 2) cout << "v1 < OK" << endl;
    else { cout << "Erreur 3" << endl; exit(1); }
    // test inférieur ou égal
    if (v1 <= 1) cout << "v1 <= OK" << endl;
    else { cout << "Erreur 4" << endl; exit(1); }
    // test supérieur
    if (v1 > 0) cout << "v1 > OK" << endl;
    else { cout << "Erreur 5" << endl; exit(1); }
    // test supérieur ou égal
    if (v1 >= 1) cout << "v1 >= OK" << endl;
    else { cout << "Erreur 6" << endl; exit(1); }

    REQUIRE(v1.getValue() == 1);
    REQUIRE(v2.getValue() == 1);
    cout << "*** IntegerValue Test Completed *** " << endl;
}
