"""
title: TP 11 - Analyse de texte
author: pakpake 
date: 14 Avril 2020
"""

def bruteforce(T,M):
    """ retourne l'indice du début d'une sous-séquence du texte T qui est égale au motif M si cette sous-séquence est trouvée dans T et qui retourne -1 si la sous-séquence n'est pas trouvée """
    n = len(T)     # texte T de taille n
    m = len(M)      # motif M de taille m
    # on parcourt le texte du début jusqu'à la longueur du texte-la taille du motif (+1 car on débute à 0)
    for i in range(0,n-m+1):
        j=0
        # tant qu'on a pas parcouru la totalité du motif et tant qu'une partie du texte n'est pas égal à chaque caractère du motif
        while j<m and T[i+j]==M[j]:
            j=j+1        # on passe au caractère suivant
        if (j==m):      # si le caractère courant est égal au c
            return i
    return -1

def rindex(lst, value):
    """ retourne le dernier index d'une valeur dans un liste, -1 si il n'existe pas """
    for i, v in enumerate(reversed(lst)):
        if v == value:
            return len(lst) - i - 1  # return the index in the original list
    return -1

def traiter_motif(M,A):
    """ permet de prétraiter le motif en construisant le dictionnaire L correspondant au prétraitement de motif M """
    L=dict()        # on construit un dictionnaire
    for i in A:
        # on parcourt l'alphabet et on calcul le dernier index de chaque lettre du motif
        L[i]=rindex(M,i)
    return L

def construit_alphabet(T):
    """ permet de construire un alphabet trié """
    return sorted(set(T))

def boyer_moore(T,M):
    """ recherche de motif dans un texte """
    A = construit_alphabet(T)
    L = traiter_motif(M,A)
    m = len(M)  # longueur du motif
    n = len(T)  # longueur du texte
    i = 0
    while i<n-m+1:  #   tant que le texte entier n'est pas parcouru
        j=i+m-1     # on calcule l'indice j de fin du motif correspondant a i+m-1
        while T[j]==M[j-i] and j>=i:    # tant que les lettres correspondent et qu'on a pas parcouru tout le motif
            j = j-1     # on decremente j
        if j < i:   # si on arrive a la fin du motif
            return i    # on renvoie l'indice du debut du motif
        c=T[j]  # on recupere le caractere qui est different du motif
        if L[c]<0:  # si il n'est pas dans le motif
            i=i+m   # on decale l'indice i de la taille du motif
        else:   # sinon
            i=i+m-L[c]-1    # on decale jsqu'a mettre en correspondance la lettre du motif dans la prochaine occruence de la lettre dans le texte
    return -1   # si le motif n'est pas trouve on renvoie -1



if __name__ == "__main__":
    """ Exercice 1 """
    print("1] Recherche de motif")
    print("---------------------")
    print("Question 1 :")
    print()
    T="hello world"
    M="wor"     # retourne 6 car "w" se trouve en position 6
    M1="foo"   # retounre "-1" cat "test" n'est pas dans T
    print("T = \"hello world\"")
    print("Est-ce que \"wor\" est dans T ? ",bruteforce(T,M))
    print("Est-ce que \"foo\" est dans T ? ",bruteforce(T,M1))
    print()
    print("Complexité : O(m.n)")
    ####################################################################
    print()
    print("----------------------------------------------------")
    print("Question 2 :")
    print()
    A = ['a','b','c','d','h']
    M = ['a','a','a','b','a']
    print("A = ",A)
    print("M = ",M)
    print("d = ",traiter_motif(M,A))
    print()
    T1="Enim ea velit quis deserunt ad. Deserunt consequat reprehenderit eu nisi enim dolore esse. Pariatur reprehenderit laborum officia id. Deserunt est culpa est velit ad nostrud nulla proident occaecat laborum amet minim. Ex mollit veniam in esse proident esse."
    print("Texte T1 = ",T1)
    print()
    print("Dans le texte T1 on a :",construit_alphabet(T1))
    print()
    T3="hello"
    M="elo"
    M1="ea"
    print("Boyer-Moore (ea est à l'indice) : ",boyer_moore(T1,M1))
    print()
    TL = "Consequat helo world eu ut duis voluptate esse veniam. Officia veniam tempor exercitation nisi officia est laborum consectetur nostrud. In mollit ipsum cupidatat nostrud voluptate est esse. Proident ut eu consectetur proident amet excepteur veniam est nostrud nisi irure in nisi. Elit elit velit consequat ad. Voluptate minim nisi pariatur minim duis ea non nulla aute.Excepteur nisi ipsum proident officia pariatur esse ex laborum dolore occaecat voluptate elit in excepteur. Mollit dolore non dolore officia ea eiusmod quis ipsum Lorem est. Nulla est ullamco incididunt minim. Minim adipisicing elit Lorem ad.Ipsum ea reprehenderit eu nulla. Cupidatat sunt et commodo et irure exercitation ut et aliquip ullamco anim est. Pariatur amet Lorem consectetur duis voluptate et consequat minim minim dolor.Aliqua cillum magna ut ad nulla nisi ut non aute cupidatat eu. Irure pariatur incididunt esse enim quis ut nulla incididunt laborum voluptate adipisicing deserunt elit. Adipisicing excepteur sunt ea do aute id in voluptate cillum amet magna esse elit. Aliquip do laboris laborum qui ea consequat. Culpa sunt  adipisicing sit et irure ipsum fugiat. Proident incididunt reprehenderit fugiat ullamco est pariatur excepteur ipsum officia labore eiusmod deserunt.Dolore sit sunt Lorem nisi deserunt elit eiusmod proident ut sint do enim incididunt irure. Ut velit cillum fugiat adipisicing commodo fugiat labore magna tempor irure cupidatat culpa labore. Irure occaecat consequat nostrud reprehenderit cillum cillum. Voluptate dolor laborum sit mollit. Elit non ut sit esse nisi anim culpa commodo. Excepteur ullamco et mollit do duis nisi aliqua excepteur. Et culpa fugiat elit dolor elit. Ea sunt esse deserunt duis elit aliquip sit dolor mollit adipisicing exercitation officia aliquip. Laborum deserunt fugiat ut proident est magna. Culpa occaecat commodo labore irure aute duis tempor ullamco nisi enim reprehenderit consequat. Et anim nisi commodo fugiat et consequat id duis sint nulla aliquip pariatur reprehenderit officia. Laborum id pariatur sit laborum cillum dolor. Reprehenderit tempor ex amet id reprehenderit anim ullamco sint veniam officia sit nisi. Exercitation veniam non incididunt sunt reprehenderit sit nulla amet sint. Labore excepteur consectetur ipsum commodo est eu voluptate sit do consequat aute duis aute aliqua. Eu amet ex sunt aliquip mollit.Reprehenderit aliqua consectetur aliquip exercitation aliqua consectetur excepteur aute minim. Culpa id aliquip culpa incididunt proident nisi minim nisi mollit excepteur Lorem veniam Lorem amet. Commodo aliquip nostrud aliquip consequat commodo magna sit. Aute culpa nostrud pariatur id ad sunt amet deserunt voluptate. Enim exercitation nulla occaecat quis mollit velit fugiat. Sunt et ullamco commodo officia deserunt hello world cupidatat duis ea"
    print("Texte TL = ",TL)
    ML = "hello world"
    print("Boyer-Moore (hello word est à l'indice) : ",boyer_moore(TL,ML))
    print("Complexité : O(m.n)")