#include <iostream>
#include <cstring>
#include "String.h"

String::String(const char * chaine) {
    init(chaine);
    // création du nouveau membre s de la bonne taille
    //s = new char[strlen(chaine)+1];
    // copie de chaine
    //strcpy(s,chaine);
}

String::String(const String& ss) {
    init(ss.s);
    // création du nouveau membre s de la bonne taille
    //s = new char[strlen(ss.s)+1];
    // copie de la chaine s de ss dans s
    //strcpy(s,ss.s);
}

String::~String() {
    clean();
    //if (s != NULL) delete[] s;
    //s=NULL;
}

// si le but c'est de donner l'accés en lecture à la chaîne de caractères, sans permettre de la modifier alors il y a plus simple, plus rapide, et sans "new"
/*
String::operator char * () const {
    char * res = new char[strlen(s)+1];
    sprintf(res,s);
    return res;
}
*/
String::operator const char * () const {
    return (const char *) this->s;
}

// operateur d'affectation
String& String::operator=(const char* chaine) {
    //delete[] s;  
    //s = new char[strlen(chaine)+1];
    //strcpy(s, chaine);
    clean(); init(chaine);
    return *this;
}

String& String::operator=(const String& chaine) {
    if (this == &chaine) return *this;
    //delete[] s;
    //s = new char[strlen(chaine.s)+1];
    //strcpy(s, chaine.s);
    clean(); init(chaine.s);
    return *this;
}

// operateur de concatenation
// PB devrait être const pour garantir qu'on ne pollue pas l'objet
//String String::operator+(const char* chaine) {
String String::operator+(const char* chaine) const {
    int length = strlen(s)+strlen(chaine);
    // PB: un new sans delete, c'est toujours suspect :-), sauf dans les constructeurs/destructeurs
    char * res = new char[length+1];
    strcpy(res, this->s);
    strcat(res, chaine);
    // PB: nécessaire pour éviter la fuite mémoire du new (sans son delete associé)
    //return String(res);
    String new_str(res); // créer le nouvel objet, qui va faire la copie de la chaîne "en son sein"
    delete[] res; // supprimer le tableau temporaire pour éviter la fuite mémoire
    return new_str; // renvoyer l'objet, le compilo va gérer lui-même les copies/delete
}

// PB: même pb ici
// PB devrait être const pour garantir qu'on ne pollue pas l'objet
//String String::operator+(const String& chaine) {
String String::operator+(const String& chaine) const {
    int length = strlen(s)+strlen(chaine.s);
    char * res = new char[length+1];
    strcpy(res, s);
    strcat(res, chaine.s);
    // PB
    //return String(res);
    String new_str(res);
    delete[] res;
    return new_str;
}

// sortie sur flot (méthode)
ostream& String::print(ostream& os) const {
    return os << s;
}

// sortie sur flot (fonction externe)
ostream& operator<<(ostream &os, const String &d) {
    return d.print(os);
}

// operateur + avec deux parametres
// PB: maintenant ça marche bien !!!! et c'est effectivement très élégant comme façon de faire : il suffisait d'inverser les paramètres
// bref : quasiment des maths !
String operator+(const char * lhs, const String& rhs) {
    return String(lhs) + rhs;
}
