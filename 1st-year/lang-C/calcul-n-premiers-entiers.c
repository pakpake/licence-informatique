/* =============================================================== 
 somme.c 
                                      
 Rôle :  calcul des n premiers entier
 
 Auteur : pakpake 
 Date :  
                                                                      
================================================================= */

#include <stdio.h>
#include <stdlib.h>


int main (void){

    //Declarations
    int n,i=0,som=0;
  
    //Affichage Titre
    printf("\n\t\t\t\t *****  Calcul la somme des n 1er entier   ****** \n\n");
    
    //Instructions
    printf("\nSaisir n : ");
    scanf("%d",&n);
    
    while(i<n){
    	i++;
    	som=som+i;
	}
	
	printf("\nLa somme des %d premiers entiers est : %d",n,som);
    
    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;
    
}//Fin main

 
