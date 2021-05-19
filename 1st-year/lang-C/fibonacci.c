/* =============================================================== 
   Fibonacci.c 

   Rôle : calcul de la suite de fibonacci a partir d'un entier saisie

Auteur : pakpake 
Date :  

================================================================= */

#include <stdio.h>
#include <stdlib.h>


int main (void){

    //Declarations
    int i=0,n;

    //Affichage Titre
    printf("\n *****  La suite de Fibonacci   ****** \n\n");

    //Instructions
    printf("Entrer n : ");
    scanf("%d",&n);

    if((n==0)||(n==1)){
        printf("\nn = %0d",n);
    }else{
        for(i=2;i<=n;i++){
            i++;
        }
        printf("\nn = %d",i);
    }

    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;

}//Fin main


