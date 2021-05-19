/* =============================================================== 
 exo 7 - voyelles.c 
                                      
 Rôle : calcul du nombre de voyelle parmis le texte saisit au clavier
 
 Auteur : pakpake 
 Date :  
                                                                      
================================================================= */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main (void){

    //Declarations
    char car;
    int nbVoyelle;
    char* listeVoyelles="aeiouyAEIOUY";
  
    //Affichage Titre
    printf("\n *****  nombre de voyelles   ****** \n\n");
    
    //Instructions
    printf("\n Entrez votre texte et le finir par # : \n");
    scanf("%c",&car);
    nbVoyelle=0;
    
    //boucle
    do{
		/*//traitement
	    if(((car=='a')||(car=='A'))||((car=='e')||(car=='E'))||((car=='i')||(car=='I'))||((car=='o')||(car=='O'))||((car=='u')||(car=='U'))||((car=='y')||(car=='Y'))){
	    nbVoyelle++;
	    */
	    //traitement
	    if(strchr(listeVoyelles,car)!=NULL) nbVoyelle++;
		//lire caractère suivant
		scanf("%c",&car);
	}while(car!='#');
	
	//Affichage nbVoyelles
	printf("\nLe nombre de voyelles est de %d",nbVoyelle);
    
    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;
    
}//Fin main

 
