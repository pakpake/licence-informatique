/* =============================================================== 

   exercice 5 - calculatrice.c 

   Rôle : faire un menu et faire des operations     

Auteur : pakpake 
Date :  24/09/2018

================================================================= */

#include <stdio.h>
#include <stdlib.h>


int main (void){

    //Declarations
    int choix=0 ;
    float a,b,res ;

    //Affichage Menu
    printf("\n *****  Menu Calculatrice   ****** \n\n");

    printf("0 : Quitter\n");
    printf("1 : Addition\n");
    printf("2 : Soustraction\n");
    printf("3 : Multiplication\n");
    printf("4 : Division\n\n");


    //Instructions
    printf("Votre choix ? ");
    scanf("%d",&choix);

    //On test si l'utilisateur veut sortir avant de lui demander de saisir les 2 nombres
    if(choix==0){printf("\nAu revoir");
        exit(0);}
    else{
        printf("Saisissez 2 nombres : ");
        scanf("%f%f",&a,&b);
    }

    switch(choix){
        case 1 :{res=a+b;
                    break;
                }
        case 2 :{res=a-b;
                    break;
                }
        case 3 :{res=a*b;

                    break;
                }
        case 4 :{
                    if(b==0){printf("\ndivision par 0 impossible");
                        exit(1);}
                    else{res=a/b;
                    }
                    break;
                }
        default :{printf("\nChoix inconnu");
                     exit(0);
                 }
    }
    printf("\nR\202sultat du Calcul : %.3f",res);    


    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;

}//Fin main


