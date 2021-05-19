/* =========================================================================
   miniProjet.c

   Manipulation des tableaux 1D et 2D avec utilisation des sous programmes
   (les fonctions)

   Compilation sous Linux :
   gcc -o miniProjet.out miniProjet.c

   @author:  pakpake 
   ========================================================================== */

#include <stdio.h>
#include <stdlib.h>

#define MAX1 50                 // Constante Taille maximum tableau 1D 
#define MAX2 10                 // Constante Taille maximum tableau 2D 

int t[MAX1];                  // Déclaration t tableau 1D d'entiers 
int mat[MAX2][MAX2];          // Déclaration matrice tableau 2D d'entiers 

int n;                        // Dimension tableau 1D ou Ordre matrice carre(nxn)
int choixUtil;                // choix utilisateur

int temps,theures,tminutes,tsecondes; //conversion du temps
int i,j,val;

/* -----------------------------------------------
saisirTab: saisie du tableau 1D de dimension n
----------------------------------------------- */
void saisirTab( int t[], int n){
    int i;         // indice parcours tableau

    //Saisie des valeurs du tableau 1D  de dimension n
    printf("\n\n --> Saisie du tableau 1D  \n\n");
    for (i=0;i<n;i++){
        printf("Entrer t[%d] : ",i);
        scanf("%d",&t[i]);
    } 

} //fin saisirTab 


/* --------------------------------------------------
afficherTab: affichage du tableau 1D de dimension n
-------------------------------------------------- */
void afficherTab(int t[], int n){
    int i;         // indice parcours tableau

    // Affichage du tableau 1D
    printf("\n\n --> Affichage du tableau 1D  \n\n");
    for (i=0; i<n;i++){
        printf("%5d",t[i]);
    } 

} //fin afficherTab 

/* ---------------------------------------------------
saisirMat : saisie des éléments d'une matrice carré 
d'ordre n
-----------------------------------------------------*/
void saisirMat(int mat[][MAX2], int n)
{ 
    int i,j;

    printf("\n\n --> Saisie Matrice Carre  \n\n");
    for (i=0;i<n;i++){
        for (j=0;j<n;j++){
            printf("Entrer mat[%d,%d] : ",i,j);
            scanf("%d",&mat[i][j]);
        }
    }
}//fin saisirMat

/* -----------------------------------------------------
afficherMat : affiche les éléments d'une matrice carré 
d'ordre n
------------------------------------------------------ */
void afficherMat(int mat[][MAX2], int n)
{ 
    int i,j;


    printf("\n\n --> Affichage Matrice Carre   \n\n");
    for (i=0;i<n;i++){
        for (j=0;j<n;j++){
            printf("%5d",mat[i][j]);
        }
        printf("\n");
    }
}// fin afficherMat


/* -----------------------------------------------------
   conversion (procedure) : temps en heures, minutes, secondes
   ------------------------------------------------------ */

void conversion(int temps, int *th, int *tm, int *ts){
    *th=temps/3600;
    *tm=(temps%3600)/60;
    *ts=temps%60;
}


// fin afficherMat

/* ------------------------------------------------
swap : permuter element indice i avec element 
indice j du tableau t.
--------------------------------------------------- */
void swap(int t[], int i, int j){
    int tmp;         

    tmp=t[i];
    t[i]=t[j];
    t[j]=tmp;

} // fin swap

/* ------------------------------------------------
placeMax : position max
--------------------------------------------------- */

void placeMax(int t[],int n, int i){
    int j;
    for(j=(i+1);j<=(n-1);j++){
        if (t[j]>t[i]) swap(t,i,j);
    }
}

/* ------------------------------------------------
tri : trier par ordre de grandeur
--------------------------------------------------- */

void tri(int t[],int n){
    for(i=0;i<=(n-2);i++){
        //appel placeMax
        placeMax(t,n,i);
    }
}

/* ------------------------------------------------
insertion : trier un tableau par ordre croissant 
--------------------------------------------------- */

void insert(int t[], int n){
    int i;
    int val;

    //indice i=1
    for(i=1;i<n;i++){
        //sauvegarde valeur  d'ordre i à inserer
        val=t[i];
        //recherche position d'insertion et décalage dans la partie triée
        j=i-1;
        while((t[j]>val)&&(j>=0)){
            //décalage
            t[j+1]=t[j];
            j=j-1;
        }
        //insertion valeur val à la position d'insertion
        t[j+1]=val;
    }
}

/*-----------tri à bulle --------------*/
void tribulle(int t[],int n){
    for(i=n-1;i>0;i--){
        for(j=0;j<i;j++){
            if (t[j+1]<t[j]) swap(t,j,j+1);
        }
    }
}

/* ------------------------------------------------
   Carré magique: vérifier si un carré est magique
   --------------------------------------------------- */
/*---------- Somme diagonale 1 ----------*/
int somDiag1(int mat[][10], int n){
    int res1=0;
    for(i=0;i<n;i++){
        res1=res1+mat[i][i];
    }
    return res1;
}
/*---------- Somme diagonale 2(=anti-diagonale) ----------
  int somDiag2(int mat[][10], int n){
  int res2=0;
//i=0;
for(i=n-1;i>=0;i--){
res2+=mat[i][j];
j++;
}
return res2;
}*/


/*--------- Somme diagonale 2 v2 ----------*/
int somDiag2(int mat[][10], int n){
    int res2=0;
    for(i=0;i<n;i++){
        res2+=mat[i][n-1-i];
    }
    return res2;
}

/*---------- Somme de chaque ligne ----------*/
int somligne(int mat[][10], int n, int i){
    int ligne=0;
    for(j=0;j<n;j++){
        ligne+=mat[i][j];
        printf("\n i=%d",i);
        printf("\n j=%d",j);
    }
    return ligne;
}

/*---------- Somme de chaque colonne ----------*/
int somcolonne(int mat[][10], int n, int j){
    int colonne=0;
    for(i=0;i<n;i++){
        colonne+=mat[i][j];
    }
    return colonne;
}

/*---------- Carre magique ----------*/
int carremagique(int mat[][10], int n){
    int nbmagik, magique=1,i;
    //Init
    nbmagik=somDiag1(mat,n);
    if(somDiag2(mat,n)!=nbmagik) return 0;
    i=0;
    while(somligne(mat,n,i)==nbmagik && somcolonne(mat,n,i)==nbmagik){
        i++;
    }
    return i==n; //i==n est vrai si i==n et faux sinon ; si i==n alors tous les tests de la boucle while sont vrais = carré magique
}

/*********** Construction d'un carré magique ***********/

/* ------------ Declarer les sous programmes avant la fonction lireChoix ------------*/


/* -----------------------------------------------------
lireChoix : fonction qui renvoie la saisie du choix 
utilisateur
------------------------------------------------------ */
int  lireChoix(){

    int choix;

    printf(" \n\n\n***** Menu Manipulation Tableaux ******** \n\n\n");

    printf("0: Quitter le programme\n");
    printf("1: Saisir Tableau 1D de  dimension n \n");
    printf("2: Afficher Tableau 1D de  dimension n \n");
    printf("3: Saisir Matrice Carre d'ordre n  \n");
    printf("4: Afficher Matrice Carre d'ordre n \n");
    printf("5: Conversion temps\n");
    printf("6: Swap\n");
    printf("7: Tri\n");
    printf("8: Tri Insertion\n");
    printf("9: Tri à bulle\n");
    printf("10: Carré Magique ?\n");
    printf("11: Construction d'un carré magique\n");

    //printf(" \n\n *******************************************\n\n\n ");


    printf("\n\n");

    printf("Tapez le numero de votre choix [0,11] -> ");
    scanf("%d",&choix);
    return choix;
}



/* -----------------------------------------------------
execChoix : procédure d'exécution du choix de 
l'utilisateur
------------------------------------------------------ */
void execChoix(int choix){


    switch (choix) {

        case 0:{ // Quitter 
                   printf("\n\n Fin du programme Manipulation des Tableaux. \n\n");
                   exit(1);
               }     

        case 1:{ //Saisie Tableau 1D de dimension n 
                   printf("\n\n --> Saisie du tableau 1D de taille variable \n");
                   printf("\n Donner la taille du tableau : ");
                   scanf("%d",&n);
                   // Appel procédure saisirTab
                   saisirTab(t,n);
                   break;
               }

        case 2:{
                   // Affichage tableau 1D
                   printf("\n\n --> Affichage du tableau 1D de taille variable \n");

                   // Appel procedure afficherTab
                   afficherTab(t,n);
                   break;
               }

        case 3:{

                   //saisie tbl 2D d'ordre n
                   printf("\n\n --> Saisie matrice d'ordre n \n");
                   printf("\n Donner ordre de la matrice : ");
                   scanf("%d",&n);
                   //Appel procedure saisieMat
                   saisirMat(mat,n);
                   break;
               }

        case 4:{
                   //affichage tableau 2D
                   printf("\n\n --> Affichage du tableau 2D d'ordre n \n");
                   //Appel procedure afficherMat
                   afficherMat(mat,n);
                   break;
               }
        case 5:{
                   //saisir temps en secondes
                   printf("\nSaisir temps (en secondes) :");
                   scanf("%d",&temps);
                   //Appel procedure
                   conversion(temps,&theures,&tminutes,&tsecondes);
                   //affichage valeurs th,tm,ts
                   printf("\nValeurs : %d heures %d minutes %d secondes ",theures,tminutes,tsecondes);
                   break;
               }
        case 6:{
                   //Saisie Tableau 1D de dimension n 
                   printf("\n\n --> Saisie du tableau 1D de taille variable \n");
                   printf("\n Donner la taille du tableau : ");
                   scanf("%d",&n);
                   // Appel procédure saisirTab
                   saisirTab(t,n);				
                   //afficher tableau 1D
                   afficherTab(t,n);
                   //saisir t[i] et t[j]
                   printf("\nSaisir i et j :");
                   scanf("%d%d",&i,&j);
                   //appel de la procedure
                   swap(t,i,j);
                   //afficher le tableau 1D
                   afficherTab(t,n);
                   break;
               }
        case 7:{
                   //Saisie Tableau 1D de dimension n 
                   printf("\n\n --> Saisie du tableau 1D de taille variable \n");
                   printf("\n Donner la taille du tableau : ");
                   scanf("%d",&n);

                   // Appel procédure saisirTab
                   saisirTab(t,n);

                   //Appel procedure tri
                   tri(t,n);

                   //Affichage de tableau
                   afficherTab(t,n);			

                   break;
               }
        case 8:{
                   //Saisie Tableau 1D de dimension n 
                   printf("\n\n --> Saisie du tableau 1D de taille variable \n");
                   printf("\n Donner la taille du tableau : ");
                   scanf("%d",&n);

                   // Appel procédure saisirTab
                   saisirTab(t,n);

                   //Affichage de tableau
                   afficherTab(t,n);

                   //Appel de la procedure insert
                   insert(t,n);

                   //Afficher le tableau
                   afficherTab(t,n);
                   break;
               }
        case 9:{	
                   //Saisie Tableau 1D de dimension n 
                   printf("\n\n --> Saisie du tableau 1D de taille variable \n");
                   printf("\n Donner la taille du tableau : ");
                   scanf("%d",&n);

                   // Appel procédure saisirTab
                   saisirTab(t,n);

                   //Affichage de tableau
                   afficherTab(t,n);

                   //Appel de la procedure tribulle
                   tribulle(t,n);

                   //Afficher le tableau
                   afficherTab(t,n);
                   break;
               }
        case 10:{
                    //Affichage des résultats
                    printf("\n\tRésultat somme diagonale 1 : %d",somDiag1(mat,n));
                    printf("\n\tRésultat somme diagonale 2 : %d",somDiag2(mat,n));
                    for(i=0;i<n;i++) printf("\n\t Somme de la ligne %d : %d ",i,somligne(mat,n,i));
                    for(j=0;j<n;j++) printf("\n\t Somme de la colonne %d : %d ",j,somcolonne(mat,n,j));
                    //printf("\n\n résultat somme de toutes les colonnes : %d",colonnes);			

                    //Carré Magique ?
                    if(carremagique(mat,n)){
                        printf("\n\n\t\t\t\t\t\t***********************\n");
                        printf("\t\t\t\t\t\t*Le carré est magique.*\n");
                        printf("\t\t\t\t\t\t***********************");
                    }
                    else printf("\n\nLe carré n'est pas magique.");
                    case 11:{

                                break;
                            }
                            break;
                }	
        default :{
                     printf("BUG! valeur %d incorrecte dans execChoix()\n",choix);
                     exit(1);
                 }  
    } //fin switch               
}// fin execChoix


/* ----------------------------------------------
   fonction main : fonction principale
   ------------------------------------------------ */ 
int main(){

    printf("\n\n ========== Manipulation des tableaux 1D et 2D avec Menu ============ \n\n\n");  
    do{
        choixUtil= lireChoix();
        execChoix(choixUtil); 
        printf("\n");    
    } while (choixUtil != 0);

    // pause
    system("pause"); 
    return 0;
}//fin main


