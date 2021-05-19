/* =============================================================== 

   exo 6.c 

   Rôle : calcul du nombre maximum et du minimum parmis une liste saisie au clavier     

Auteur : pakpake 
Date :  

================================================================= */

#include <stdio.h>
#include <stdlib.h>


int main (void){

    //Declarations
    int compteur=0;

    int total=0,entier=0; //total = total de tous les entiers
    int max,min;

    printf("Introduisez un entier positif (-1 pour terminer) : \n");
    scanf("%d",&entier); 
    min=max=entier; //<=> max=entier ; min = max
    while (entier>=0)
    {	
        if(entier>max) max = entier;
        if(entier<min) min = entier;
        printf("Entier suivant (-1 pour terminer) : \n");
        scanf("%d",&entier);
        compteur++;
    }

    if (compteur!=0)
        printf("\nMax = %d \nMin = %d\n",max,min);
    else
        printf("Aucun entier positif saisi !\n");



    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;

}//Fin main


