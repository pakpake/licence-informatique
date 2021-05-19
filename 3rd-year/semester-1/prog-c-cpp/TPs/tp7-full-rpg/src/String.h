#ifndef __STRING_H__ 
#define __STRING_H__

#include <string.h>
#include <iostream>

using namespace std;

class String {
private:
	char * stringPtr;
	// allocateur de mémoire
	void alloc(const char *txt) {
		stringPtr = new char[strlen(txt)+1];  // allocation dans le tas
		strcpy(stringPtr, txt); // recopie de la chaîne
	}
	// désallocateur correspondant
	void free() { delete [] stringPtr; }
public:
	// constructeur à partir d'une chaîne de caractères
	String(const char *txt) { alloc(txt); } 
	// Copie
	// il suffit d'initialiser l'objet avec le constructeur précédent
	String(const String &str) : String(str.stringPtr) {}
	// destructeur qui libère l'espace alloué dans le tas
	~String() { free(); }
	// Pour avoir accès à la chaîne de l'objet sans jamais la polluer
	// 1er const protège la valeur retournée, l'appelant ne pourra pas la modifier
	// 2eme const protège l'objet, l'écrivain du code de l'opérateur 
	// 	 ne pourra pas modifier l'objet sans avoir une erreur du compilateur
	operator const char *() const { return stringPtr; }
	// affectation
	String& operator=(const String &s) {
		if (this != &s) {
			free(); // ne pas oublier de libérer la valeur précédente
			alloc(s.stringPtr);
		}
		return *this;
	}
	// operator+
	String operator+(const char *str) const {
		const int l1 = strlen(stringPtr), l2 = strlen(str); // longueurs
		char *nstr = new char[l1+l2+1]; // allocation mémoire
		strcpy(nstr, stringPtr); strcpy(nstr+l1, str); // recopie
		String S(nstr); // création objet et donc recopie de la chaîne dans S
		delete [] nstr; // libération chaîne de recopie
		return S; // renvoyer l'objet
	}
	String operator+(const String &str) const {
		return *this + str.stringPtr; // Simple réécriture
	}
	friend String operator+(const char *str, const String &s1) {
		String s2(str);
		return s2 + s1; // Simple réécriture
	}
	// pour afficher
	friend ostream& operator<<(ostream &os, const String &s) 
		{ os << "\"" << s.stringPtr << "\""; return os; }
};

#endif
