# -*- coding: utf-8 -*-
"""
@date: Avril 2020
@author: pakpake 
@comment: Exercice 1 - DM2
"""

from pile_list import Pile # On a besoin de la classe Pile

""" Exercice 1 """

class Moteur_lists:
    def __init__(self):
        self.inv_indx = dict()
        self.docs_links = dict()
    
    def __str__(self):
        """ Affichage de la Base complète """
        # On trie la liste de mots avant affichage
        return "**********DUMPING CLASS**********\n  DOCS\n  "+"  "+'\n    '.join([str((k,v)) for k,v in self.docs_links.items()])+'\n  INDX\n  '+"  "+'\n    '.join([str((k,v)) for k,v in sorted(self.inv_indx.items())]) + '\n*********************************'

    def add_to_index(self, docname):
        """:entree : self, chemin fname
        :sortie : None
        Ajoute une liste de mots à la base
        """
        # docname est une liste de mots
        ni = len(self.docs_links)+1     # on recupere le numero du nouvel index
        self.docs_links.update({ni : docname})  # on rajoute au dictionnaire doc_links le nouveau document avec son index
        for i in docname:   # pour tous les mots du document
            if i in self.inv_indx:  # si le mot existe deja dans le dictionnaire inv_index
                self.inv_indx[i].add(ni)    # on rajoute la nouvelle reference a ce mot
            else:   # sinon
                self.inv_indx.update({i : {ni}})  # on cree cette nouvelle entree dans le dictionnaire inverse

    def search_word(self, w):
        """:entree : self, mot w
        sortie : set
        renvoie les valeurs de la clé w ou un ensemble vide si la clé est absente de la base
        """
        if w in self.inv_indx:
            return self.inv_indx[w] # renvoie les valeurs de la cle w dans le dictionnaire inv_indx
        else:
            return set() # ou renvoie un ensemble vide si la cle n'est pas dans inv_indx
        
    def search_inverse_word(self, w):
        """:entree : self, mot w
        :sortie : set
        renvoie la liste des index où le mot w n'est pas présent. On prends tous les index moins ceux trouvés par la méthode précédente.
        """
        # Tous les index possibles moins ceux de search_word(w)
        # return {e for e in self.docs_links if e not in self.search_word(w)} 
        return {e for e in self.docs_links} - self.search_word(w)

    def and_word(self, w1, w2):
        """:entree : self, mot w1, mot w2
        :sortie : set
        Opérateur AND
        """ 
        # Tous les index de w1 et w2 obtenus avec search_word
        return self.search_word(w1) & self.search_word(w2)

    def or_word(self, w1, w2):
        """:entree : self, mot w1, mot w2
        :sortie : set
        Opérateur OR
        """
        # Tous les index de w1 ou w2 obtenus avec search_word
        return self.search_word(w1) | self.search_word(w2) 
        
    def eval_exp_terms(self, exp):
        """ Permet d'évaluer une expression logique en notation polonaise inverse et sans erreurs """
        # la strategie consiste a stocker dans une pile les ensembles resultant des differents filtrages et manipulations de l'expression logique
        # on cree un pile en faisant appel a Pile_List
        p = Pile()
        # on transforme l'expression en une liste de mots
        my_exp=exp.split(' ')
        # on cree un ensemble de tous les documents (utile pour not)
        tous = {e for e in self.docs_links}
        # on peut maintenant parcourir les elements de la liste my_exp 1 par 1
        if not(my_exp):
            print("eval_exp_terms : L'expression est vide")
        else:
            i = 0
            # on parcourt tous les elements de l'expression
            while i < len(my_exp):
                if my_exp[i]=="AND":
                    x = p.depile()
                    y = p.depile()
                    total=x & y
                    p.empile(total)
                elif my_exp[i] == "OR":
                    x = p.depile()
                    y = p.depile()
                    total=x | y
                    p.empile(total)
                elif my_exp[i] == "NOT":
                    x = p.depile()
                    total=tous - x
                    p.empile(total)
                else:
                    # sinon on empile le set (l'ensemble) correspondant a la recherche du mot suivant
                    s = self.search_word(my_exp[i])
                    p.empile(s)
                i+=1    # on passe a l'element suivant dans notre expression
            return total

if __name__ == "__main__":
    # zone de test #
    print("Exercice 1 :")
    print("** Question 1 **")
    my_engine = Moteur_lists() # creation d'un objet de la classe
    doc1 = "winter is coming".split(' ') # on cree le doc a partir de la chaine de caractere
    print(doc1)
    doc2 = "ours is the fury".split(' ')
    doc3 = "the choice is yours".split(' ')
    my_engine.add_to_index(doc1) # on ajoute les documents
    my_engine.add_to_index(doc2)
    my_engine.add_to_index(doc3)
    # print(my_engine.docs_links)
    # print(my_engine.inv_indx)
    # print(my_engine)
    doc4 = "winter is here".split(' ')
    my_engine.add_to_index(doc4)
    print(my_engine) # on affiche la base documentaire
    print()
    print("** Question 2 **")
    print("the :",my_engine.search_word('the'))
    print()
    print("** Question 3 **")
    print("NOT the :", my_engine.search_inverse_word('the'))
    print()
    print("** Question 4 **")
    print("the :",my_engine.search_word('the'))
    print("coming :",my_engine.search_word('coming'))
    print("the ET coming :",my_engine.and_word("the","coming"),"(ensemble vide)")
    print("the OU coming :",my_engine.or_word("the","coming"))
    print()
    print("** Question 5 **")
    print("the :",my_engine.search_word('the'))
    print("coming :",my_engine.search_word('coming'))
    print("is :",my_engine.search_word('is'))
    print("-> the coming OR",my_engine.eval_exp_terms("the coming OR"))
    print("-> the coming OR is AND",my_engine.eval_exp_terms("the coming OR is AND"))
    print("-> the coming OR is AND NOT",my_engine.eval_exp_terms("the coming OR is AND NOT"))
    print()
    print("---- Avec mes propres documents ----")
    print()
    list_doc = []
    list_doc.append("consequat consequat laboris mollit sunt commodo".split(' '))
    list_doc.append("amet ea mollit pariatur dolore adipisicing veniam excepteur irure reprehenderit ad ut lorem".split(' '))
    list_doc.append("occaecat veniam deserunt dolor aliqua deserunt".split(' '))
    list_doc.append("adipisicing laborum consectetur reprehenderit ipsum sint lorem dolor consequat proident sunt esse".split(' '))
    list_doc.append("voluptate dolor eiusmod velit deserunt veniam qui aliquip qui eiusmod elit officia".split(' '))
    list_doc.append("eiusmod esse do labore esse ex amet".split(' '))
    list_doc.append("magna enim quis et lorem velit nulla".split(' '))
    list_doc.append("nostrud ipsum occaecat commodo commodo ad".split(' '))
    list_doc.append("deserunt et laboris amet officia cillum qui reprehenderit".split(' '))
    list_doc.append("veniam fugiat proident ipsum ad laborum officia mollit ea mollit nisi in eiusmod exercitation adipisicing".split(' '))
    list_doc.append("voluptate cupidatat in qui sit irure aliquip nisi laboris laborum laborum elit ullamco qui".split(' '))
    list_doc.append("mollit sint adipisicing lorem consectetur fugiat in velit laboris reprehenderit ullamco exercitation".split(' '))
    list_doc.append("cillum sit cillum voluptate lorem reprehenderit aliqua officia occaecat fugiat nulla deserunt fugiat laborum ex".split(' '))
    list_doc.append("ex in culpa reprehenderit ea irure labore non laborum ipsum id".split(' '))
    list_doc.append("quis lorem nostrud ex labore consequat voluptate eu eu fugiat".split(' '))
    list_doc.append("nostrud ipsum excepteur cupidatat pariatur magna voluptate consectetur laborum nisi cupidatat".split(' '))
    list_doc.append("eiusmod duis esse nostrud adipisicing sunt dolore".split(' '))
    list_doc.append("sit aute ullamco reprehenderit consectetur occaecat consectetur voluptate dolor proident enim lorem".split(' '))
    list_doc.append("ipsum ipsum amet minim ullamco exercitation ex sunt non enim eiusmod anim ea veniam".split(' '))
    list_doc.append("ea id minim eu commodo aliqua".split(' '))
    mon_moteur = Moteur_lists()
    for i in list_doc:
        mon_moteur.add_to_index(i)
    print(mon_moteur)
    print("Expression recherchée : ")
    print()
    exp = "ipsum lorem OR consectetur AND velit OR voluptate NOT AND"
    print("  ",exp)
    print()
    print("On détaille pas à pas :")
    print("ipsum :",mon_moteur.search_word('ipsum'))
    print("lorem ",mon_moteur.search_word('lorem'))
    exp = "ipsum lorem OR"
    print("->  ",exp,mon_moteur.eval_exp_terms(exp))
    print("consectetur :",mon_moteur.search_word('consectetur'))
    exp = "ipsum lorem OR consectetur AND"
    print("->  ",exp,mon_moteur.eval_exp_terms(exp))
    print("velit :",mon_moteur.search_word('velit'))
    exp = "ipsum lorem OR consectetur AND velit OR"
    print("->  ",exp,mon_moteur.eval_exp_terms(exp))
    print("voluptate :",mon_moteur.search_word('voluptate'))
    print("voluptate NOT :",mon_moteur.search_inverse_word('voluptate'))
    exp = "ipsum lorem OR consectetur AND velit OR voluptate NOT AND"
    print("->  ",exp,mon_moteur.eval_exp_terms(exp))
    print()
    print("toto : cle absente du dictionnaire : ",mon_moteur.search_word('toto'))
    print("toto NOT : toto cle absente du dictionnaire : ",mon_moteur.search_inverse_word('toto'))