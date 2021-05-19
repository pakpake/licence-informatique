#include <iostream>
#include "catch2/catch.hpp"
#include "../src/Dice.h"
#include "../src/Race.h"

using namespace rpg;
using namespace std;


TEST_CASE ("Race Test") {
    cout << endl;
    cout << "*** Race Test ***" << endl;
    const int M = 2; //20;
    rpg::Dice d6(6);
    for(int i = 1; i<=M; i++) {
        rpg::IntegerValue lancer1 = d6+d6+d6;
        cout << "lancer1=" << lancer1 << endl;
        rpg::IntegerValue lancer2 = d6+d6+6;
        cout << "lancer2=" << lancer2 << endl;
        rpg::Race humanRace(lancer1, lancer2);
        cout << "humanRace: " << humanRace << endl;
        if (humanRace.getPhysicFormula() < 3) { cout << "Erreur 7"; exit(1); }
        if (humanRace.getPhysicFormula() > 18) { cout << "Erreur 8"; exit(1); }
        if (humanRace.getMentalFormula() < 3) { cout << "Erreur 9"; exit(1); }
        if (humanRace.getMentalFormula() > 18) { cout << "Erreur 10"; exit(1); }
        REQUIRE(humanRace.getPhysicFormula() >= 3);
        REQUIRE(humanRace.getMentalFormula() >= 8);
    }
    for(int i = 1; i<=M; i++) {
        rpg::IntegerValue lancer1 = d6+d6+6;
        cout << "lancer1=" << lancer1 << endl;
        rpg::IntegerValue lancer2 = d6+d6;
        cout << "lancer2=" << lancer2 << endl;
        rpg::Race orcRace(lancer1, lancer2);
        cout << "orcRace: " << orcRace << endl;
        if (orcRace.getPhysicFormula() < 8) { cout << "Erreur 11"; exit(1); }
        if (orcRace.getPhysicFormula() > 18) { cout << "Erreur 12"; exit(1); }
        if (orcRace.getMentalFormula() < 2) { cout << "Erreur 13"; exit(1); }
        if (orcRace.getMentalFormula() > 12) { cout << "Erreur 14"; exit(1); }
        REQUIRE(orcRace.getPhysicFormula() >= 8);
        REQUIRE(orcRace.getMentalFormula() >= 2);
    }
    cout << endl;
    cout << "*** Race Test Completed ***" << endl;
}
