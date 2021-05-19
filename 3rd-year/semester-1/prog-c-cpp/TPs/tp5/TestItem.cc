#include "item.h"
#include "creature.h"
#include "integervalue.h"
#include "dice.h"

using namespace rpg;

int main() {
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


}
