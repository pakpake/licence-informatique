import random
import tp6-etud

class TestList():

    l = DoubleLinkedList()      # creattion d'une liste vide

    for i in range(15):     # remplissage de liste par des valeurs aleatoires
        l.insertFirst(Cellule(random.randint(0,9)))
    
    print("liste = ",l)     # affichage de la liste

    # taille de la liste
    print("taille = ",l.sizeList())

    # liste vide
    print("liste vide ou non = ",l.emptyList())

    # insertFirst
    print("inserer en 1ere position = "l.insertFirst(99))

    # insertLast
    print("insere en derniere position = ",l.insertLast(66))

    # Premier element de la liste
    print("1er element de la liste = ",l.head())

    # insertBefore
    print("inserer avant le 3eme element par exemple = ",l.insertBefore(77,2))
        # 2 pour 3eme element

    # insertAfter
    print("inserer apres le 5eme element par exemple = ",l.insertAfter(88,4))

    # insertAt en nieme position
    print("insere en 7eme position = ",l.insertAt(55,6))

    # getFirst idem que head
    print("1er element de la liste = ",l.getFirst())

    # getLast
    print("Dernier element de la liste = ",l.getLast())

    # getAt : valeur du nieme element
    print("Valeur du 7 element = ",l.getAt(6))

    # tail : liste sans le 1er element de la liste  ######### Marche pas ###########
    print("liste sans le premier element = ",l.tail())

    # remove 
    print("liste sans la premiere occurence du chiffre 3 = ",l.remove(3))

    # removeAt
    print("liste sans le 5eme element = ",l.removeAt(4))

    # removeFirst   ######################### Marche pas ###########################
    print("liste en supprimant le 1er element de la liste = ",l.removeFirst())