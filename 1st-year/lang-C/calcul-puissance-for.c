/* =============================================================== 
   exo 8 - puissance for v3.c 

Role : calcul d une puissance avec la boucle for

Auteur : pakpake 
Date :  

================================================================= */

#include <stdio.h>
#include <stdlib.h>


int main (void){

    //Declarations
    int x,n,i,res=1;

    //Affichage Titre
    printf("\n *****  Calcul d'une puissance   ****** \n\n");

    printf("Saisir x : ");
    scanf("%d",&x);
    printf("Saisir n : ");
    scanf("%d",&n);
    res=1;
    //Instructions
    for(i=1;i<=n;i++){
        res=res*x;
    }

    //Affichage
    printf("\n resultat : %d",res);


    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;

}//Fin main


