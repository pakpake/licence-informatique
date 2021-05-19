#ifndef STRING_H
#define STRING_H

#include <iostream>
#include <cstring>

using namespace std;

class String {

    // PB: deux petits outils pour être sûr que tout se passera toujours bien (new + delete)
    private:
    void clean() { if (s != NULL) delete[] s; }
    void init(const char *txt) { s = new char[strlen(txt)+1]; strcpy(s, txt); }

    char * s;

    public :

    // PB: constructeur par défaut pour être sûr à 100% que s == NULL
    String() { s = NULL; }

    // constructeur
    String(const char * chaine);

    // constructeur de clonage
    String(const String&);

    // destructeur
    ~String();

    // operateur de cast
    // PB pour avoir l'accès en lecture seule
    //operator char * () const;
    operator const char * () const;
    // operateur d'affectation
    String& operator=(const char*);
    String& operator=(const String&);
    // operateur de concatenation
    String operator+(const char*) const;
    String operator+(const String&) const;

    // méthode public d'affichage
    ostream& print(ostream&) const;

    friend String operator+(const char * lhs, const String& rhs);

};

// opérateur d'affichage d'un Damageable par ostream
ostream& operator<<(ostream &os,const String &);

// operateur + avec 2 parametres a declarer en dehors de la classe
String operator+(const char *, const String&);

#endif
