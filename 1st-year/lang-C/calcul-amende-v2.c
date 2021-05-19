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

int main (void){

    //Declarations
    float vitesse_reelle,vitesse_autorisee,ecart;
    int amende=0;
    int k;

    //Affichage Titre
    printf("\n\t\t\t\t ******   Calcul du co\226t d'une amende   ****** \n\n");

    //Instructions
    printf("Vitesse du conducteur en tort (en km/h) ? ");
    scanf("%f",&vitesse_reelle);
    printf("Vitesse autorise\202e (en km/h) ? ");
    scanf("%f",&vitesse_autorisee);
    ecart = vitesse_reelle - vitesse_autorisee;

    printf("Y avait-il de la pluie (1 pour oui ou 0 pour non) ? ");
    scanf("%d",&k);
    if (k) ecart = ecart + 10; //s'il pleut l'écart augmente de 10 (la vitesse autorisée diminue de 10)

    if (ecart < 0) ecart = 0; //si l'écart est négatif alors on se sera pas en tort

    printf("\220tiez-vous en ville (1 pour oui ou 0 pour non) ? ");
    scanf("%d",&k);
    if (k && ecart != 0) amende = amende + 100; // amende majorée en ville

    //Calculs
    if (ecart==0) amende = 0;
    else if	(ecart <= 10) amende = amende +100;
    else if (ecart<=20) amende = amende + 200;
    else amende = amende + 500;

    if (amende == 0) printf("Vous n'\x88tes pas en tort\n");
    else printf("Votre amende est de %d euros",amende);

    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;

}//Fin main


