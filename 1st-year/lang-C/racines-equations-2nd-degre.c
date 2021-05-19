/* =============================================================== 
   equationduseconddegre.c 

   Rôle : calcul des racines reelles d'une equation du second degre

   Entrées : a,b,c,x_0,x_1,x_2                            
Sorties : x_0,x_1,x_2   

Auteur : pakpake 
Date :  19/09/2018

================================================================= */

#include <stdio.h>
#include <stdlib.h>
#include <math.h>

int main (void){

    //Declarations variable
    float a,b,c,x_0,x_1,x_2,d;


    //Affichage Titre
    printf("\n ***** Equation du second degre   ****** \n\n");

    //Instructions
    printf("\n a ? ");
    scanf("%f",&a);
    printf("\n b ? ");
    scanf("%f",&b);
    printf("\n c ? ");
    scanf("%f",&c);
    printf("\n\nL'expression est %.2fx^2+%.2fx+%.2f\n",a,b,c);

    //Calcul De Delta
    d=pow(b,2)-4*a*c;

    //Solutions
    if(d<0){
        printf("\nPas de solutions reelles.");
    }else if(d==0){
        x_0=-b/2*a;
        printf("\nL'unique solution est %.2f\n",x_0);
    }else{
        x_1=(-b-sqrt(d))/2*a;
        x_2=(-b+sqrt(d))/2*a;
        printf("\nDeux solutions r\202elles : %.2f et %.2f \n",x_1,x_2);
    }


    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;

}//Fin main


