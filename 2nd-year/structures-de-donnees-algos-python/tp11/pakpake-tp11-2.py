class ArbrePrefixe() :
    """ Arbre prefixe contenant des mots ayant la meme premiere lettre """
    def __init__ (self,lettre,derniere=False,fils=[]):
        """ Initialise l’arbre ; Entree : la lettre prefixe de l’arbre """
        # pour construire des arbres simples, on a rajouté les parametres
        # derniere et fils avec les valeurs par defaut False et []
        # cela permettra plus tard d'initialiser les petits arbres de test.
        self.prefixe = lettre # la lettre a la racine de l’arbre
        self.derniere = derniere # vrai si cette lettre est la derniere d’un mot
        self.fils = fils      # la liste des arbres prefixes de cet arbre

    def donner_fils(self,lettre):
        """ retourne l'arbre fils dont la lettre racine est un caractère donné """
        # on parcourt self.fils (liste) si on trouve la lettre on renvoie l'arbre
        # correspondant sinon on renvoie None
        for i in self.fils:
            if lettre==i.prefixe:
                return i
        return None
            
    def parcours_arbre(self,chaine=""):
        """ permet de parcourir un arbre et d'afficher les mots entiers """
        # on suppose que l'arbre passé en paramètre n'est pas vide
        # on construit la chaine au fur et a mesure en recuperant la chaine de l'appel recursif precedent et en y ajoutant le caractere courant
        chaine = chaine + self.prefixe
        if self.derniere:
            # si c'est un derniere lettre on affiche la chaine courante
            print(chaine)
        for i in self.fils:        # on fait un appel recursif avec tous les fils
            i.parcours_arbre(chaine)
    
    def ajouter_mot(self,mot):
        """ permet d'ajouter un mot dans un arbre """
        # si le mot n'est pas vide
        if len(mot)>0:
            char = mot[0]       # caractere a tester
            child=self.donner_fils(char)        # on recupere le fils s'il existe
            if child == None:       # child n'existe pas
                child=ArbrePrefixe(char)        # on le construit
                self.fils = self.fils + [child]     # on le rajoute comme nouveau fils
            child.ajouter_mot(mot[1:])      # on fait l'appel recursif sur le reste du mot
        else:
            # si la derniere lettre du mot a rajouter
            self.derniere=True      # on met a jour self.derniere
    
    def verifier_mot(self,mot):
        """ permet de vérifier si un mot est présent dans l'arbre """
        if len(mot)>=0:     # si le mot n'est pas vide
            char = mot[0]   # on verifie le caractere courant
            child = self.donner_fils(char)  # on teste s'il existe dans l'arbre
            if child == None:   # sinon le mot n'est pas dedans
                return False
            elif len(mot)==1:   # si c'est le dernier caractere du mot a tester
                return child.derniere   # child.dernier doit etre vrai pour que le mot soit bon
            else: 
                # sinon on lance un appel recursif sur le reste du mot
                return child.verifier_mot(mot[1:])
            
        

if __name__ == "__main__":
    print("Exercice 2")
    print("--------------------------------------------------------------------")
    print("Question 1")
    A=ArbrePrefixe('A',True)
    I=ArbrePrefixe('I',True)
    B=ArbrePrefixe('B',False,[I])
    E=ArbrePrefixe('E',True)
    C=ArbrePrefixe('C',False,[A,E])
    arbre=ArbrePrefixe('/',False,[A,B,C])
    arbre.parcours_arbre("")
    print()
    print("arbre de la lettre B : ")
    arbre.donner_fils("B").parcours_arbre("")
    print("--------------------------------------------------------------------")
    print("Question 2")
    A=ArbrePrefixe('A',True)
    A2=ArbrePrefixe('A',True)
    C=ArbrePrefixe('C',False,[A2,E])
    arbre=ArbrePrefixe('/',False,[A,B,C])
    print("Arbre avant ajout de AN :")
    arbre.parcours_arbre("")
    print()
    print("Arbre après ajout de AN :")
    arbre.ajouter_mot("AN")
    arbre.parcours_arbre("")
    print("--------------------------------------------------------------------")
    print("Question 3")
    mot="BI"
    print("Arbre avant vérification de",mot)
    arbre.parcours_arbre("")
    print("Le mot",mot,"existe-t-il ? ",arbre.verifier_mot(mot))
    print("--------------------------------------------------------------------")
    print("Question 4")

    def construire_arbre_prefixe(dico):
        """ construit un arbre de préfixe contenant les mots du dictionnaire dico """
        fichier=open(dico,'r')        # Ouverture du fichier
        dic_brut = fichier.read()     # lecture des donnees au format brut
        fichier.close()
        dic = dic_brut.split('\n')    # separation des mots dans une liste
        arbre=ArbrePrefixe('/',False,[])    # on construit un arbre un vide
        for i in sorted(dic):
            # on ajoute les mots de dico 1 par 1
            arbre.ajouter_mot(i)
        return arbre
    
    # construction de l'arbre
    data="data/en.dic"
    dic_arbre = construire_arbre_prefixe(data)
    dic_arbre.parcours_arbre()
    print("--------------------------------------------------------------------")
    print("Question 5")
    def verifier_mots_texte(dico,text):
        """ permet de vérifier si les mots d'un fichier texte sont présents dans le dictionnaire dico """
        # les mots de dico sont sur des lignes differentes
        # les mots de text sont separes par des espaces
        arbre = construire_arbre_prefixe(dico)  # on construit le dictionnaire
        file1=open(text,'r')    # ouverture du fichier texte
        text_brut=file1.read() # lecture des donnees
        file1.close() # fermeture du fichier
        text_propre=text_brut.split(' ')      # separation des mots
        for i in text_propre:   # pour chaque mot on verifie si il n'est pas dans le dictionnaire et on l'affiche
            if not arbre.verifier_mot(i):
                print(i)
    
    data="data/en.dic"    
    print("texte 1 :")
    txt="data/text1.txt"
    verifier_mots_texte(data,txt)
    txt="data/text2.txt"
    print("texte 2 :")
    verifier_mots_texte(data,txt)
    txt="data/text3.txt"
    print("texte 3 :")
    verifier_mots_texte(data,txt)