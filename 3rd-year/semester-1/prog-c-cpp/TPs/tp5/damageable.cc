#include "damageable.h"
#include <string>
#include <typeinfo>

using namespace rpg;

// constructeur
Damageable::Damageable(int hitPoints, string name){
    m_hitPoints = hitPoints;
    m_name = name;
}

// const double& Damageable::getPoints() const{
const int& Damageable::getPoints() const{
    return m_hitPoints;
}

const string& Damageable::getName() const{
    return m_name;
}

// setter
void Damageable::setName(string name){
    m_name = name;
}

// permet d'indiquer que des points de vie ont été infligés (non-négatif)
void Damageable::damage(int damage){
    m_hitPoints = m_hitPoints-damage < 0 ? 0 : m_hitPoints-damage;
}

bool Damageable::isDead(){
    return m_hitPoints == 0;
}

void Damageable::healRepair(int heal){
    if (!this->isDead()) m_hitPoints += heal;
}

// sortie sur flot (méthode)
ostream& Damageable::print(ostream& os) const {
    os << "Type      : " << typeid(this).name() << endl;
    os << "  name    : " << m_name << endl;
    os << "  hit pts : " << m_hitPoints << endl;
    return os;
}

// sortie sur flot (fonction externe)
ostream& rpg::operator<<(ostream &os, const Damageable &d)
{
    return d.print(os);
}

