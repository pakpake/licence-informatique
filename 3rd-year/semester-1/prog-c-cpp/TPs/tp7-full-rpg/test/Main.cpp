#include <iostream>
#include "../src/Item.h"
#include "../src/Creature.h"
#include "../src/IntegerValue.h"
#include "../src/Dice.h"
#include "../src/Race.h"
#include "../src/Weapon.h"
#include "../src/CraftedWeapon.h"

using namespace std;

int main() {

	// String
	cout << "String" << endl;
	String s1("s1");
	cout << "s1=" << s1 << endl;
	String s2("s2");
	cout << "s2=" << s2 << endl;
	String s3("");
	s3 = s1 + s2;
	cout << "s3=" << s3 << endl;
	String s4("");
	s4 = s3 + "+ABCD";
	cout << "s4=" << s4 << endl;
	String s5("");
	s5 = "XYZT+" + s4;
	cout << "s5=" << s5 << endl;
	cout << endl;

	// Item
	cout << "Item" << endl;
	rpg::Item i(1.0, 12, "toto");
	rpg::Item j(i); // copie
	cout << "i=" << i << endl;
	cout << "j=" << j << endl;
	cout << endl;

	// IntegerItem
	// ne peut pas être instancié, c'est une classe virtuelle
	
	// Integervalue
	cout << "IntegerValue" << endl;
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

	// min et max
	//cout << "v1 max=" << v1.getMaxValue() << endl;
	//cout << "v1 min=" << v1.getMinValue() << endl;
	//cout << "v2 max=" << v2.getMaxValue() << endl;
	//cout << "v2 min=" << v2.getMinValue() << endl;
	cout << endl;

	// Dice, pour forcer le tirage sans l'operateur "+"
	cout << "Dice" << endl;
	rpg::Dice d4(4);
	const int N = 2; //20;
	for(int i = 1; i <=N; i++) cout << d4.getValue() << " ";
	cout << endl;
	rpg::Dice d10(10);
	for(int i = 1; i <=N; i++) cout << d10.getValue() << " ";
	cout << endl;
	cout << endl;

	// Race
	cout << "Race" << endl;
	const int M = 20;
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
	}
	cout << endl;

	// Creature
	cout << "Creature" << endl;
	rpg::Race humanRace(d6+d6+d6, d6+d6+6);
	rpg::Creature King("Thingol", humanRace);
	cout << "King: " << King << endl;
	rpg::Race orcRace(d6+d6+6, d6+d6);
	rpg::Creature orc1("Boldog", orcRace);
	cout << "orc1: " << orc1 << endl;
	cout << endl;

	// Weapon
	cout << "Weapon" << endl;
	rpg::Weapon weapon1(10);
	cout << "weapon1: " << weapon1 << endl;
	cout << endl;

	// CraftedWeapon
	cout << "CraftedWeapon" << endl;
	rpg::Item item1(20.0, 30, "item1");
	cout << "item1: " << item1 << endl;
	rpg::CraftedWeapon craftedweapon1(weapon1, item1);
	cout << "craftedweapon1: " << craftedweapon1 << endl;
	cout << endl;

	return 0;
}

