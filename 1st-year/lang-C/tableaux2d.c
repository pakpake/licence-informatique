/* ==========================================================================
 Fichier tableaux2.c
 Manipulation des tableaux 2D.
 
 Compilation sous Linux :
 gcc -o tableaux2.out tableaux2.c
 
===========================================================================*/
#include <stdio.h>
#include <stdlib.h>
#define MAX2 10                 // Constante Ordre maximum tableau 2D 


int main(){

  int m[MAX2][MAX2];            // Déclaration m tableau 2D d'entiers   
  
  int n;                        // Ordre tableau 2D 
  int i,j;                      // indices parcours tableaux 


  
  // Affichage titre
   printf("\n ***************** Manipulation Tableaux 2D ************* \n\n");

  // -------------- Tableaux 2D : Saisie ------------------- 
  
  // Saisie ordre du tableau 2D
  printf("\n\n --> Saisie matrice \n ");
  printf("\n Donner ordre Matrice : ");
  scanf("%d",&n);

  printf("\n");
  // Saisie des valeurs du tableau 2D 
  for (i=0;i<n;i++){
    for (j=0;j<n;j++){
        printf("Entrer m[%d][%d]:",i,j);
        scanf("%d",&m[i][j]);
    }
  }
 
 
 // -------------- Tableaux 2D : Affichage ------------------ 
 
 // Affichage du tableau 2D
 printf("\n\n --> Affichage Matrice \n\n");
 for (i=0;i<n;i++){
    for (j=0;j<n;j++){
        printf("%7d",m[i][j]);
    }
    printf("\n");
 }
 
 // ----------- Traitement Tableaux 2D :  Partie à completer ---- 
 
 /* Exercie 1 TD 4
 printf("\n --> Trace matrice");
 //init
 int trace;
 
 //Boucle 
 trace =0;
 
 for(i=0;i<n;i++){
 	trace = trace +m[i][i];
 }
 printf("\nTrace matrice = %d",trace);
 */
 
 /*exercice 2 - transposee matrice
 printf("\nTransposee matrice");
 //init
 int tmp;
 
 //boucle
 for(i=0;i<=(n-2);i++){
 	for(j=(i+1);j<=(n-1);j++){
 		tmp=m[i][j];
 		m[i][j]=m[j][i];
 		m[j][i]=tmp;
	 }
 }

 printf("\n\n --> Affichage Matrice transpos\202e \n\n");
 for (i=0;i<n;i++){
    for (j=0;j<n;j++){
        printf("%7d",m[i][j]);
    }
    printf("\n");
 }
 */
 
 
 /*exercice 3 - Somme de matrice
 
 //init
 int m2[MAX2][MAX2];
 int m3[MAX2][MAX2];
 
 //Demande de la 2eme matrice
 for (i=0;i<n;i++){
    for (j=0;j<n;j++){
        printf("Entrer m[%d][%d]:",i,j);
        scanf("%d",&m2[i][j]);
	}
}

//Boucle 
 for(i=0;i<n;i++){
 	for(j=0;j<n;j++){
 		m3[i][j]=m[i][j] + m2[i][j];
	 }
 }
 //Affichage somme matrice
 printf("\n\n --> Somme matrice \n\n");
 for (i=0;i<n;i++){
    for (j=0;j<n;j++){
        printf("%7d",m3[i][j]);
    }
    printf("\n");
}	
*/ 
 
/*exercice 4 - produit des matrices
 //init
 int m2[MAX2][MAX2];
 int m3[MAX2][MAX2];
 int cumul=0,k=0;
 //Demande de la 2eme matrice
 for (i=0;i<n;i++){
    for (j=0;j<n;j++){
        printf("Entrer m[%d][%d]:",i,j);
        scanf("%d",&m2[i][j]);
	}
}

//Boucle 
 for(i=0;i<=(n-1);i++){
 	for(j=0;j<=(n-1);j++){
 		cumul=0;
 		for(k=0;k<=(n-1);k++){
 			cumul=cumul+(m[i][k]*m2[k][j]);
 		}
	m3[i][j]=cumul;
	}
}
 //Affichage produit matrice
 
 printf("\n\n --> Produit matrice \n\n");
 for (i=0;i<n;i++){
    for (j=0;j<n;j++){
        printf("%7d",m3[i][j]);
    }
    printf("\n");
}
*/

/*Exercice TD - affectation de matrice x lignes, y colonnes par les valeurs variant de 1 a x*y

//init 
int x=0,y=0; // x: lignes, y:  colonnes
 
printf("Saisir x et y : ");
scanf("%d%d",&x,&y);

//Boucle

for(i=0;i<(x-1);i++){
	for(j=0;j<(y-1);y++){
		m[i][j]=(i*y)+j+1;
	}
}
 */



 // ---- Pause systeme et retour résultat fonction main ---------
 printf("\n\n");
 system("pause");
 return 0;
 
}//fin main
