#include "item.h"
#include "creature.h"
#include "integervalue.h"
#include "dice.h"
#include "String.h"

using namespace rpg;

int main() {
	// test Pierre
	Dice d4(4), d6(6), d8(8);
	cout << "d4 max=" << d4.max() << " min=" << d4.min() << endl;
	cout << "d6 max=" << d6.max() << " min=" << d6.min() << endl;
	cout << "d8 max=" << d8.max() << " min=" << d8.min() << endl;
	int i = d4.getValue();
	int j = d4 + d6;
	int k = d8 + 10;
	cout << "i=" << i << " j=" << j << " k=" << k << endl;
	// qq tirages au sort
	const int N = 40;
	cout << "d4: ";
	for(i=1; i<=N; i++) {
	  cout << d4.getValue() << " ";
	}
	cout << endl;
	cout << "d6: ";
	for(i=1; i<=N; i++) {
	  cout << d6.getValue() << " ";
	}
	cout << endl;
	cout << "d8: ";
	for(i=1; i<=N; i++) {
	  cout << d8.getValue() << " ";
	}
	cout << endl;
	cout << endl;
    /*
    // creation d'un Item Chevalier
    cout << "Création du chevalier Knight à 12hp et valeur marchande 10" << endl;
    Item d1(12,"Knight",10);
    cout << endl;

    // affichage
    cout << d1 << endl;
    //d1.print(cout);
    cout << endl;

    // creation creature
    cout << "Création de la creature Kreatur à 10hp et capacité physique 18 et mental 7" << endl;
    Creature d2(10,"Kreatur",18,7);
    cout << endl;

    // affichage
    cout << d2 << endl;
    //d2.print(cout);
    cout << endl;

    cout << "Damage de 8hp à Knight" << endl;
    d1.damage(8);
    cout << "Est-ce que Knight est vivant ? " << (d1.isDead() ? "non" : "oui") << endl;
    cout << "Combien a-t-il de points de vie ? " << d1.getPoints() << endl;
    cout << endl;
    cout << "Récupération de 6hp : " << endl;
    d1.healRepair(6);
    cout << "Combien a-t-il de points de vie ? " << d1.getPoints() << endl;
    cout << endl;
    cout << "Perte de 52hp" << endl;
    d1.damage(52);
    cout << "Est-ce que Knight est vivant ? " << (d1.isDead() ? "non" : "oui") << endl;
    cout << "Combien a-t-il de points de vie ? " << d1.getPoints() << endl;
    cout << endl;
    cout << "On tente de le soigner de 8hp" << endl;
    d1.healRepair(8);
    cout << "Est-ce que Knight est vivant ? " << (d1.isDead() ? "non" : "oui") << endl;
    cout << "Combien a-t-il de points de vie ? " << d1.getPoints() << endl;
    
    cout << endl;
    cout << endl;
    cout << endl;

    cout << "==================================================" << endl;
    cout << endl;
    cout << endl;
    
    IntegerValue v1(10);
    cout << int(v1) << endl;

    cout << endl;

    Dice d(10);

    
    for(int i=0;i<100;i++) {
        cout << d.getValue() << "  ";
    }

*/
    String s1("Chaine 1 ");
    String s2("Chaine 2 ");
    String s3(s1);

    cout << "s1 = " << s1 << endl;
    cout << "s2 = " << s2 << endl;
    cout << "s3 = " << s3 << endl;

    cout << endl;
    s3 = s1 + s2;
    cout << "s3 = s1 + s2" << endl;
    cout << s3 << endl << endl;

    cout << "s3 = s3 + \"une chaine apres \"" << endl;
    s3 = s3 + "une chaine apres ";
    cout << s3 << endl << endl;

    cout << "s2 = \"Une chaine avant \" + s3" << endl;
    s2 = "Une chaine avant " + s3;
    cout << s2 << endl;
}
