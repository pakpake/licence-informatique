
/* =============================================================== 
   calcul amende.c 

   Rôle : determiner le montant d'une amende pour exces de vitesse     

   Entrées :  vitesse reelle, vitesse autorisée                 
Sorties :  montant de l'amende

Auteur : pakpake 
Date :  20/09/2018

================================================================= */

#include <stdio.h>
#include <stdlib.h>
#include <string.h>


int main (void){

    //Declarations
    float vitesse_reelle,vitesse_autorisee;
    int amende=0;
    int k;
    char all[30];
    //char oui[30] = "oui";
    char non[30] = "non";

    //Affichage Titre
    printf("\n\t\t\t\t ******   Calcul du co\226t d'une amende   ****** \n\n");

    //Instructions
    printf("\nVitesse du conducteur en tort (en km/h) ? ");
    scanf("%f",&vitesse_reelle);
    printf("\nVitesse autorise\202e (en km/h) ? ");
    scanf("%f",&vitesse_autorisee);

    printf("\nY avait-il de la pluie (oui ou non)? ");
    scanf("%s",all);
    if(strcmp(all,"oui") == 0){
        vitesse_autorisee=vitesse_autorisee-10;
    }

    /*
    //HELP
    printf("vitesse reelle %f \t vitesse autorisee %f \t amende : %d",vitesse_reelle,vitesse_autorisee,amende);
    */

    if(vitesse_autorisee==50){
        printf("\n\220tiez-vous en ville (1 pour oui ou 0 pour non) ? ");
        scanf("%d",&k);
        switch(k){
            case 1 : 
                amende=100+amende;
                if(vitesse_reelle<=10+vitesse_autorisee){
                    amende=100+amende;
                    printf("\nVotre amende est de %d euros",amende);
                };
                break;
            case 0 :
                printf("Votre amende est de %d euros",amende);
                break;
        }
    }
    if(vitesse_reelle>10+vitesse_autorisee && vitesse_reelle<=20+vitesse_autorisee){
        amende=200;
        printf("\nVotre amende est de %d euros",amende);
    }else{
        amende=500+amende;
        printf("\nVotre amende est de %d euros",amende);
    }

    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;

}//Fin main


