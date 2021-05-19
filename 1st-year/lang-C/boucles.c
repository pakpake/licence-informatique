/* =============================================================== 

   Rôle :  que fait le programme suivant ?    

Auteur : pakpake 
Date :  28/09/2018

================================================================= */

#include <stdio.h>
#include <stdlib.h>


int main (void){

    //Declarations
    int i,x,y;


    //Affichage Titre
    printf("\n *****  Affichage Titre   ****** \n\n");

    //Instructions
    printf("\nSaisir deux entiers positives x et y (x<y): ");
    scanf("%d%d",&x,&y);

    i = x;
    while (i<=y){
        printf("%5d",i);
        i++ ; //i++ signifie que l'on incrémente de 1 la valeur tant que i est inférieur ou égal à y donc le prgm affiche toutes les valeurs comprise entre x et y
    }
    /*Le programme va afficher toutes les valeurs comprise entre x et y tant que x<=y.
      Pour ce faire il incrémente de 1 la variable x tant qu'elle est inférieure à y.  */    

    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;

}//Fin main


