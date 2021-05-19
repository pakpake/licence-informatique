
/* =============================================================== 
 table de multiplication.c 
                                      
 Rôle : affiche table de multiplication jusqu'a 10     
 Auteur : pakpake
                                                                      
================================================================= */

#include <stdio.h>
#include <stdlib.h>


int main (void){

    //Declarations
    int x,y;
  
  
    //Affichage Titre
    printf("\n *****  Table de Multiplication   ****** \n\n");
    
    //Instructions
    for(x=1;x<=10;x++){
    	printf("\n");
    	for(y=1;y<=10;y++){
    		printf("%4d",x*y);
		}
	}
    
    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;
    
}//Fin main

 
