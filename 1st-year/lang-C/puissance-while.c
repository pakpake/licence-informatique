
/* =============================================================== 
puissance while.c 
                                      
 Rôle : calcul de puissance avec la boucle while
 
 Auteur : pakpake
 Date :  
                                                                      
================================================================= */

#include <stdio.h>
#include <stdlib.h>


int main (void){

    //Declarations
  	int x,n,i=0,res=1;
  
    //Affichage Titre
    printf("\n *****  Calcul d'une puissance   ****** \n\n");
    
	printf("Saisir x : ");
    scanf("%d",&x);
    printf("Saisir n : ");
    scanf("%d",&n);
    i=0;
    res=1;
    //Instructions
    while(i<n){
    	i=i+1;
    	res=res*x;
	}
    
    //Affichage
    printf("\n resultat : %d",res);
    
    
    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;
    
}//Fin main

 
