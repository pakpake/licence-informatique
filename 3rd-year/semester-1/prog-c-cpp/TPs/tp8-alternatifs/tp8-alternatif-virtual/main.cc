#include <iostream>
#include "Personne.h"
#include "Enseignant.h"
#include "Chercheur.h"
#include "Enseignant_Chercheur.h"

int main() {

  cout << "*********************** Personne ***************************" << endl;

  cout << endl << "** Creation de Jean (var p1) et Nicolas (var p2)" << endl;
  cout << endl << "** Creation avec valeur par defaut (var p3)" << endl;

  Personne p1("Jean");
  Personne p2("Nicolas");
  Personne p3;

  cout << endl;
  cout << "** Affichage de p1" << endl;
  cout << p1 << endl;

  cout << "** Affichage de p2" << endl;
  cout << p2 << endl;

  cout << "** Affichage de p3" << endl;
  cout << p3 << endl;

  cout << endl;
  cout << "*********************** Enseignant *************************" << endl;

  cout << "** Creation de Jacques (Maths) (var e1) " 
    << " et Luc (Info) (var e2)" << endl;
  cout << "** Creation avec valeur par defaut (var e3)" << endl;

  Enseignant e1("Jacques", "Maths");
  Enseignant e2("Luc", "Info");
  Enseignant e3;

  cout << endl;
  cout  << "** Affichage de e1" << endl;
  cout << e1 << endl;

  cout << "** Affichage de e2" << endl;
  cout << e2 << endl;

  cout << "** Affichage de e3" << endl;
  cout << e3 << endl;

  cout << endl;
  cout << "*********************** Chercheur *************************" << endl;

  cout << "** Creation de Frederic (Reseaux) (var c1) " 
    << " et Luc (Langages) (var c2)" << endl;
  cout << "** Creation avec valeur par defaut (var c3)" << endl;

  Chercheur c1("Frederic", "Reseaux");
  Chercheur c2("Luc", "Langages");
  Chercheur c3;

  cout << endl;
  cout << "** Affichage de c1" << endl;
  cout << c1 << endl;

  cout << "** Affichage de c2" << endl;
  cout << c2 << endl;

  cout << "** Affichage de c3" << endl;
  cout << c3 << endl;

  cout << endl;

  cout << "******************* Enseignant-Chercheur ******************" << endl;

  cout << "** Creation de Francois (Crypto, Cryptologie, MCF) (var ec1)" 
    << endl << "   et Beatrice (Maths, Topologie, MCF) (var ec2)" << endl;
  cout << "** Creation avec valeur par defaut (var ec3)" << endl;

  Enseignant_Chercheur ec1("Francois", "Crypto", "Cryptologie", PU);
  Enseignant_Chercheur ec2("Beatrice", "Maths", "Topologie", MCF);
  Enseignant_Chercheur ec3;

  cout << endl;
  cout << "** Affichage de ec1" << endl;
  cout << ec1 << endl;

  cout << "** Affichage de ec2" << endl;
  cout << ec2 << endl;

  cout << "** Affichage de ec3" << endl;
  cout << ec3 << endl;
  
  cout << endl;
  cout << "******************* Fin du programme **********************" << endl;

  return 0;
}
// Dans mon programme, j'ai ajouté un identifiant unique par objet créé, ce qui
// me permet de l'afficher dans les appels au constructeur/destructeur.
// Je peux donc observer précisément quel objet est créé quand et détruit quand
// Dans mon operateur << je peux aussi afficher le typeid de l'objet.

