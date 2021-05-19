#include <iostream>
#include "catch2/catch.hpp"
#include "../src/Item.h"

using namespace rpg;
using namespace std;

TEST_CASE ("Item Test") {
    cout << "*** Item Test ***" << endl;
    cout << "Item" << endl;
    rpg::Item i(1.0, 12, "toto");
    rpg::Item j(i); // copie
    cout << "i=" << i << endl;
    cout << "j=" << j << endl;
    cout << endl;
    REQUIRE(i.getValue() == 1);
    cout << "*** Item Test Completed *** " << endl;
}
