/* ------------------------------------------------
swap : permuter element indice i avec element 
indice j du tableau t.
@author: pakpake
--------------------------------------------------- */
void swap(int t[], int i, int j){
    int tmp;         

    tmp=t[i];
    t[i]=t[j];
    t[j]=tmp;

} // fin swap

/* ------------------------------------------------
positionMin: position Minimum d'un tableau 1D
de dimension dim � partir de l'indice debut 
jusqu'a indice (n-1) 
--------------------------------------------------- */
int positionMin(int t[], int n, int iDebut){
    int i=0;                  
    int indiceMin;            // variable indice du minimum

    /* Recherche indice minimum */
    indiceMin=iDebut;         
    for (i=iDebut+1; i< n; i++) {
        if (t[i]<t[indiceMin]){
            indiceMin=i;
        }
    }

    return(indiceMin);
}// fin positionMin



/* *********************************************************************
triSelection : Tri d'un tableau de dimension dim par s�lection du minimum.

Algo :

Pour (i=1, i<n-1,i++)
- On fixe l'�l�ment fronti�re d'ordre i
- On cherche l'indice de l'�l�ment minimum, parmi les �l�ments
d'ordre (i+1) � (n-1)
- on permute �l�ment fronti�re d'ordre i avec l'�l�ment minimum
FinPour

 ********************************************************************* */

void triSelection(int t[],int n){
    int i,j;

    int indiceMin;          // indice �l�ment minimum 
    int tmp;                // variable tampon 

    // Boucle de traitement 
    for (i=0; i< (n-1); i++) {
        // on cherche position miminum � partir d'indice i+1
        indiceMin=positionMin(t,n,i);   

        // Permutation element d'ordre i avec celui d'indiceMin
        if (indiceMin!=i){
            swap(t,i,indiceMin);
        }

    } // fin for i 

} // fin triSelection

