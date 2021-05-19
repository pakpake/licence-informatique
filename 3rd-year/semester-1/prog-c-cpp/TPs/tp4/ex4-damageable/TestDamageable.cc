#include "damageable.h"

int main() {
    cout << "Création du chevalier Knight à 12hp" << endl;
    Damageable d1(12,"Knight");
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
}
