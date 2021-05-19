 
/* =============================================================== 

exercices 6 - jeunes attention !.c 
                                      
 R�le :      
                                                    
 Entr�es : vitesse en km/h, �tat de la route m pour mouill�e et s pour s�che                            
 Sorties : distance necessaire � la voiture pour s'arr�ter   
 
 Auteur : pakpake
 Date :  28/09/2018
                                                                      
================================================================= */

#include <stdio.h>
#include <stdlib.h>


int main (void){

    //Declarations
    float v;				//v pour vitesse en km/h
    char etat[1];			//'m' = mouill�e 's'=sec on veut une cha�ne de char de longueur 1 pour lire avec un scanf
    char choix;				//on transformera la cha�ne �tat en un caract�re unique pour l'utiliser dans le switch
    float da,dfm,dfs,chr;	//da : distance d'arret; dfm/s : distance freinage route mouill�e/s�che; chr pour chemin de reaction=3*vitesse/10
  
  
    //Affichage Titre
    printf("\n\t\t\t\t******  Calcul Distance d'arr\210t d'une voiture   ****** \n\n");
    
    //Instructions
    printf("Quelle \202tait votre vitesse ? ");
    scanf("%f",&v);
    
    printf("\nLa route \202tait-elle mouill\202e ou s\212che (m ou s)? ");
    scanf("%s",&etat);	//lecture d'une chaine de longueur 1
    
    choix = etat[0]; 	//On transforme la chaine de longueur 1 en un char 
    
    
    /*Si on veut lire un char unique il faut d�clarer choix comme un char et lire le char avec getchar()
    Mais probleme : dans le buffer d'�criture il y a le char suivi de "enter" c'est pour �a qu'il faut lire une 2eme fois avec getchar().
    M�thode � �viter.
	choix=getchar();
    choix=getchar();*/

    //Calculs
	  
	dfm=(v/10)*(v/10);	 		//Calcul distance freinage sur route mouill�e
	chr=(3*v)/10;				//Chemin de r�action
	
	switch(choix){
		case 'm' :{
			da=chr+dfm;
			break;
		}
		case 's' : {
			dfs=(3.0/4.0)*dfm; //division entier par entier = eniter arrondi par d�faut donc 3/4=0.75=0 il faut mettre 3./4.=.75
			da=chr+dfs;
			break;
		}
		default :{
			printf("\nChoix impossible.");
			exit(0);
			break;
		}
	}
	   
    //Affichage r�sultat
	printf("\nLa distance d'arr\210t est de %.2f",da);
    
      
    
    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;
    
}//Fin main

 
