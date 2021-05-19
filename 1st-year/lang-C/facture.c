
/* =============================================================== 
   facture.c 

   Rôle : calcul de facture     

   Entrées :                             
Sorties :    

Auteur : pakpake
Date :  19/09/2018

================================================================= */

#include <stdio.h>
#include <stdlib.h>


int main (void){

    //Declarations variables
    const float tva=0.2 ;  
    char nom[30] ;
    char produit[30] ;
    int quantite ;
    float puht,pht,mtva,pttc ;
    /*
     * puht = produit unitaire hors taxe
     * pht  = produit hors taxe
     * mtva = montant TVA
     * pttc = produit toutes taxes comprises
     */

    //Instructions
    printf("\nVotre nom de famille ?\n") ;
    scanf("%s",&nom) ;
    printf("\nNom du produit ?\n") ;
    scanf("%s",&produit) ;
    printf("\nCombien d article(s) ?\n") ;
    scanf("%d",&quantite) ;
    printf("\nPrix unitaire HT ?\n") ;
    scanf("%f",&puht) ;

    //Calculs

    pht=quantite*puht ;
    mtva=tva*pht ;
    pttc=mtva+pht ;

    //Affichage résultats

    printf("\n***Edition Facture de %s ***\n",nom) ;
    printf("\nProduit : %s\n",produit) ;
    printf("\nQuantite : %d\n",quantite) ;
    printf("\nPrix unitaire HT : %.2f\n",puht) ;
    printf("\nPrix HT : %.2f\n",pht) ;
    printf("\nTaux TVA : 20%% \n") ;
    printf("\nMontant TVA : %.2f\n",mtva) ;
    printf("\nPrix TTC : %.2f\n",pttc) ;


    //Pause et retour resultat
    printf("\n\n");
    system("pause");
    return 0;

}//Fin main


