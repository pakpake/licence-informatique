/* ==========================================================================
   Fichier tableaux1.c
   Manipulation des tableaux 1D.

   Compilation sous Linux :
   gcc -o tableaux1.out tableaux1.c

 ===========================================================================*/
#include <stdio.h>
#include <stdlib.h>

#define MAX1 50                 // Constante Taille maximum tableau 1D 

int main(){

    int t[MAX1];                  // Déclaration t tableau 1D d'entiers de 50 éléments max
    int n;                        // Dimension (taille) tableau 1D 
    int i;                        // indice parcours tableau 


    // Affichage titre
    printf("\n ***************** Manipulation Tableaux 1D ************* \n\n");

    // ------------- Tableaux 1D : Saisie ------------------
    printf("\n\n --> Saisie du tableau 1D \n");

    // Saisie de la taille du tableau 
    printf("\n Saisir la taille du tableau : ");
    scanf("%d",&n);

    printf("\n"); 
    // Saisie des valeurs du tableau 1D
    for (i=0;i<n;i++){
        printf("Entrer t[%d]:",i);
        scanf("%d",&t[i]);
    }


    // -------------- Tableaux 1D : Affichage ----------------
    printf("\n\n --> Affichage du tableau 1D de base \n\n ");
    for (i=0; i<n;i++){
        printf("t[%d] = %d \n ",i,t[i]);
    }


    // ----------- Traitement 1D :  Partie à completer -------
    //
    //Affichage tableau à l'envers
    printf("\n\n--> Affichage de tableau 1D a l'envers");
    for(i=(n-1);i>=0;i--){
        printf("%4d",t[i]);
    }

    //Affichage nombres pairs
    printf("\nAffichage nombres pairs");
    for(i=0;i<=(n-1);i++){
        if(t[i]%2==0){
            printf("%4d",t[i]);
        }
    }
/*
    //Permutation des valeurs du tableau d'indice i et j
    int a=0,b=0,tmp;
    printf("\nPermutation des valeurs du tableau");
    printf("\nSaisir a et b : ");
    scanf("%d %d",&a,&b);

    //permutation
    tmp=t[a];
    t[a]=t[b];
    t[b]=tmp;
    for(i=0;i<n;i++){
        printf("%4d",t[i]);
    }
*/
    //Affichage des valeurs min et max ainsi que leurs positions  posMin et posMax dans le tableau
    int valMin,valMax,posMin,posMax;

    valMin=t[0];
    posMin=0;
    valMax=t[0];
    posMax=0;


    //Parcours de recherche
    for(i=0;i<(n-1);i++){
        if(t[i]<valMin){
            valMin=t[i];
            posMin=i;
        }
    }
    for(i=0;i<n;i++){	
        if(t[i]>valMax){
            valMax=t[i];
            posMax=i;
        }
    }
    printf("\nValeur minimal = %4d\nValeur maximale = %4d\n Position minimale = %4d\nPosition maximale = %4d",valMin,valMax,posMin,posMax);

    //extraction d'un sous tableau de p elements à partir d'une position donnée
    int p=0,posDebut=0,j;
    int t2[MAX1];

    for(i=posDebut;i<(posDebut+p-1);i++){
        t2[j]=t[i];
        j++;
    }

    for(i=posDebut;i<posDebut+p-1;i++){
        t2[i-posDebut]=t[i];
    }
    printf("%d",t2[j]);

    // --- Pause systeme et retour  résultat fonction main ----
    printf("\n\n");
    system("pause");
    return 0;

}// fin main
