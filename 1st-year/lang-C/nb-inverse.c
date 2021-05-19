/* =============================================================== 
 exo 2 - mystère.c 
                                      
 Rôle : Le programme va afficher le nombre entré aupravant dans le sens inverse
                 
 Auteur : pakpake  
 Date :  28/09/2018
                                                                      
================================================================= */

#include <stdio.h>
#include <stdlib.h>


int main (void){

    //Declarations
    int nb,x,mystere;
  
  
    //Affichage Titre
    printf("\n *****  Affichage Titre   ****** \n\n");
    
    //Instructions
    printf("\nSaisir un entier positif : ");
    scanf("%d",&nb);
    
    mystere = 0;
	while(nb !=0){
		x=nb %10;
		nb=nb/10;
		mystere=mystere*10+x;
	}    
    
    printf("\nR\202sultat : %d ",mystere);
    
    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;
    
}//Fin main

 
