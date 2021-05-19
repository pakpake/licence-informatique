/* =============================================================== 
   indice Masse.c 

   R�le : Calcul Indice Masse Corporelle

   Entr�es : poids, masse                            
Sorties : imc   

Auteur : pakpake 
Date :  19/09/2018

================================================================= */

#include <stdio.h>
#include <stdlib.h>


int main (void){


    //Declarations
    float poids ; //poids en kg
    float taille ; //taille en m
    float imc ; //poids/taille^2

    //Affichage Titre
    printf("\n\t\t\t\t\t *****  Calcul de l'IMC   ****** \n\n");

    //Instructions
    printf("\nSaisissez votre poids (en kg)) : \n") ;
    scanf("%f",&poids) ;
    printf("\nSaisissez votre taille (en m) : \n") ;
    scanf("%f",&taille) ;

    //Calcul

    imc=poids/(taille*taille) ;

    //Resultats

    printf("\nVotre I.M.C est : %.3f\n",imc) ;
    if(imc<=18.5){
        printf("\nMaigreur avec risque de malnutrition\n") ;
    }else if(imc>=18.5 && imc<=25){
        printf("\nPoids sante avec risque minimum\n") ;
    }else if(imc>=25 && imc<=30){
        printf("\nSurpoids avec risque\n") ;
    }else if(imc>=30 && imc<=35){
        printf("\nObesite avec risque important\n") ;
    }else{
        printf("\nOb\202site avec risque tres important\n") ;
    }

    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;

}//Fin main


