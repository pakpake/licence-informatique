#include <iostream>
#include <cstring>

using namespace std;

class String {
    char * s;

    public :
    // constructeur
    String(const char * chaine);
    
    // constructeur de clonage
    String(const String&);

    // destructeur
    ~String();

    // operateur de cast
    operator char * () const;

};


String::String(const char * chaine) {
    // création du nouveau membre s de la bonne taille
    s = new char[strlen(chaine)+1];
    // copie de chaine
    strcpy(s,chaine);
}

String::String(const String& ss) {
    // création du nouveau membre s de la bonne taille
    s = new char[strlen(ss.s)+1];
    // copie de la chaine s de ss dans s
    strcpy(s,ss.s);
}

String::~String() {
    if (s != NULL) delete[] s;
    s=NULL;
}

String::operator char * () const {
    char * res = new char[strlen(s)+1];
    sprintf(res,s);
    return res;
}


int main() {
    cout << "==================" << endl;
    cout << "Début du programme" << endl;

    String s1("toto");
    String s2("titi");
    String s3(s2);

    char * chaine;
    chaine = String(s1);
    cout << chaine << endl;
    s1.~String();
    s2.~String();
    s3.~String();

    cout << "Fin du programme" << endl;
    cout << "==================" << endl;
}
