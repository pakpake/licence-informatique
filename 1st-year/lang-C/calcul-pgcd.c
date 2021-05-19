/* =============================================================== 
   pgcd.c 

   Rôle : Calcup du pgcd   

Auteur : pakpake 
Date :  

================================================================= */

#include <stdio.h>
#include <stdlib.h>


int main (void){

    //Declarations
    int x=0,y=0;


    //Affichage Titre
    printf("\n *****  PGCD   ****** \n\n");

    //Instructions
    printf("\nSaisir a et b : ");
    scanf("%d%d",&x,&y);

    //Calculs
    do{
        if(x>y){
            x=x-y;
        }else{
            y=y-x;
        }
    }while(x!=y);

    //Affichage resultat
    printf("\nLe PGCD est : %d",x);

    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;

}//Fin main


