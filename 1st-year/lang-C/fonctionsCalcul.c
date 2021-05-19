/* ================================================================
   Fichier fonctionsCalcul.c

   TD Fonctions : Déclaration et appel de fonctions.  

   Compilation sous Linux :
   gcc -o fonctionsCalcul.out fonctionsCalcul.c
   ================================================================= */

#include <stdio.h>
#include <stdlib.h>
//Declaration des variables globales
int n,p,x,i,a,b;


/* ---------------------------------------------
   Fonction fact : calcule la valeur n!

   Entrées: Entier n
Sorties: fact (valeur n!)

------------------------------------------------ */
long int  fact(int n){

    //Declarations variables locales
    long int res=1;
    int i;

    for (i=1;i<=n;i++){
        res*=i;
    }

    return res;

}// fin fact

/* ---------------------------------------------
   Fonction arrangement : Calcul la valeur Anp

   Entrées: Entier n,p
Sorties: arrangement (valeur n!)

------------------------------------------------ */

long int arrangement(int n, int p){
    return(fact(n)/fact(n-p));
}

/* ---------------------------------------------
   Fonction binomiale : Calcul la valeur Cnp

   Entrées: Entier n,p
Sorties: binomiale (valeur n!)

------------------------------------------------ */

long int binomiale(int n, int p){
    return(arrangement(n,p)/fact(p));
}

/* ---------------------------------------------
   Fonction puissance recursive : Calcul la valeur powRec

   Entrées: Entier x,n
Sorties: puissancerecursive

------------------------------------------------ */

int powRec(int x, int n){
    if(n==0) return 1;
    else return x*powRec(x,n-1);
}

/* ---------------------------------------------
   Fonction comtagechiffre : Calcul la nombre de chiffre dans un entier 

   Entrées: Entier n
Sorties: comptage chiffre

------------------------------------------------ */

int comptageChiffre(int n){

    do{
        n=n/10;
        i++;
    }while(n>0);
    return i;
}

/* ---------------------------------------------
   Fonction fact recursive : Calcul la valeur factrec

   Entrées: Entier n
Sorties: factrec
------------------------------------------------ */
long int factrec(int n){
    if(n==0) return 1;
    else return n*fact(n-1);
}

/* ---------------------------------------------
   Fonction somme chiffres recursive : Calcul la valeur somchif

   Entrées: Entier n
Sorties: factrec
------------------------------------------------ */

int somchif(int n){
    if(n<10) 
        return n;
    else
        return n%10 + somchif(n/10);
}

/* ---------------------------------------------
   Fonction pgcd recursive : Calcul la valeur pgcdrec

   Entrées: Entier a,b
Sorties: pgcdrec
------------------------------------------------ */
int pgcdrec(int a, int b){
    if(a==b)
        return a;
    else if(a>b)
        return pgcdrec(a-b,b);
    else return pgcdrec(a,b-a);
}

//Rajouter les sous-prog avant la fonction main


/* ---------------------------------------------
   Fonction principale main
   ------------------------------------------------*/
int main(void){


    /*  
    // test fonction fact
    printf("\n --> Test fonction fact ");
    printf("\n Donner n : "); scanf("%d",&n);
    printf("\n Factorielle de %d = %d\n",n,fact(n));

    // test ...A completer ...

    //test fonction arrangement
    printf("\n --> Test fonction arrangement ");
    printf("\n Donner n et p : ");
    scanf("%d%d",&n,&p);
    printf("\n Arrangement de %d et %d = %ld",n,p,arrangement(n,p));

    //test fonction binomiale
    printf("\n --> Test fonction binomiale ");
    printf("\n Donner n et p : ");
    scanf("%d%d",&n,&p);
    printf("\n Binomiale de %d et %d = %ld",n,p,binomiale(n,p));

    //test fonction puissance
    printf("\n --> Test fonction puissance ");
    printf("\n Donner x et n : ");
    scanf("%d%d",&x,&n);
    printf("\n Puissance  de %d puissance %d = %d",x,n,powRec(x,n));

    //Test de la fonction comptage
    printf("\n --> Test fonction comptage chiffre ");
    printf("\n Donner n : ");
    scanf("%d",&n);
    printf("\n Comptage chiffre de %d = %d",n,comptageChiffre(n));

    //Test de la fonction factorielle recursive
    printf("\n --> Test fonction factorielle recursif ");
    printf("\n Donner n : ");
    scanf("%d",&n);
    printf("\n Factorielle de %d = %ld",n,factrec(n));

    //Test de la fonction somme Chiffres
    printf("\n --> Test fonction somme Chiffres ");
    printf("\n Donner n : ");
    scanf("%d",&n);
    printf("\n Somme des chiffres de %d = %d",n,somchif(n));
    */ 

    //Test de la fonction pgcdrec
    printf("\n --> Test fonction pgcd recursif ");
    printf("\n Donner a et b : ");
    scanf("%d%d",&a,&b);
    printf("\n PGCD de %d et %d = %d",a,b,pgcdrec(a,b));




    //Pause systeme et retour resultat fonction main
    printf("\n\n");
    system("pause");
    return 0;

}//fin main


