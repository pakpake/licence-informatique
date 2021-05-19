/* =============================================================== 
   exercice 5 moyenne v2.c 

   Rôle : calcul la moyenne des nombres saisis au clavier     

   Auteur : pakpake  

================================================================= */

#include <stdio.h>
#include <stdlib.h>

int main (void){

    int compteur=0;

    int total=0,entier=0; //total = total de tous les entiers

    printf("Introduisez un entier positif (-1 pour terminer) : \n");
    scanf("%d",&entier); 

    while (entier>=0)
    {	
        total = total+entier;
        compteur = compteur+1;
        printf("Entier suivant (-1 pour terminer) : \n");
        scanf("%d",&entier); 
    }

    if (compteur!=0)
        printf("\nMoyenne = %.2f",(float)total/(float)compteur); // Si on divise 2 int = int, on doit convertir total et compteur en float avant
    else
        printf("Aucun entier positif saisi !\n");


    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;

}//Fin main


