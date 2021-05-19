
/* =============================================================== 
 comparaison.c 
                                      
 Rôle : comparer 3 entiers entre eux 
                                                    
 Entrées :  entier 1,2,3                           
 Sorties :  entier le plus grand  
 
 Auteur : pakpake
 Date :  19/09/2018
                                                                      
================================================================= */

#include <stdio.h>
#include <stdlib.h>


int main (void){

    //Declarations des variables
	int a,b,c ;
  
    //Affichage Titre
	printf("\n\t\t\t\t *****  Comparateur de nombres entiers   ****** \n\n");
    
    //Instructions
	printf("\nVos 3 nomrbes entiers (s\202par\202s par une virgule)?\n");
	scanf("%d,%d,%d",&a,&b,&c);

	if(a>b && a>c){
		printf("\nLe max est : %d\n",a);
	}else if(b>a && b>c){
		printf("\nLe max est : %d\n",b);
	}else{
		printf("\nLe max est : %d\n",c);
	}
    
    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;
    
}//Fin main

 
