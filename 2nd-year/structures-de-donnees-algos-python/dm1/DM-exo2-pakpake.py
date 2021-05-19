from math import sqrt
import random

class Point():
    """ Classe qui gère les points en coordonnées 2D """
    def __init__(self,x_init,y_init):
        """ Initialisation avec les valeurs saisies """
        self.x = x_init
        self.y = y_init

    def __str__(self):
        """ fonction d'affichage """
        return "["+str(self.x) + "," + str(self.y) + "]"

    def distance(self,p):
        """ Calcule de la distance euclidienne entre 2 points """
        if isinstance(p,Point):     # on verifie que p est bien de la classe Point
            return sqrt((self.x-p.x)**2 + (self.y-p.y)**2)
        else:
            return NotImplemented


class Data():
    """ Classe qui gère les data (point et classe) """
    def __init__(self,point_init,classe_init):
        """ fonction d'initialisation """
        self.point = point_init
        self.classe = classe_init
    
    def get_point(self):
        """ méthode de récupération du point """
        return self.point

    def get_classe(self):
        """ méthode de récupération de la classe """
        return self.classe
    
    def __str__(self):        
        """ fonction d'affichage """
        return str(self.point)+" "+str(self.classe)

    def distance(self,data):
        """ fait appel à la méthode de distance entre 2 points pour 2 data """
        return Point.distance(self.point,data.point)


class KPPV():
    """ Classe de l'algorithme de classification par la méthode des k plus proches voisins """
    def __init__(self):
        """ initialisation de l'ensemble d'entrainement à vide """
        self.trainingSet = []       # liste de data vide
    
    def add_data(self,data):
        """ ajoute les données dans la liste trainingSet """
        self.trainingSet.append(data)

    def __str__(self):
        """ affichage sous forme d'une liste """
        return '[' + ','.join([str(e) for e in self.trainingSet]) + ']'

    def get_voisins(self,p_test,k):
        """ prend en entrée un point p_test et un entier k et retourne la liste des k données de type Data les plus proches du point p_test """
        # On créé une liste des data et la distance à p_test
        dListe = []
        for i in self.trainingSet:
            dListe.append((i,i.point.distance(p_test)))
            # on ajoute le tuple (data, distance(p_test))
        # on trie la dListe par distance croissante
        dListe.sort(key=lambda col: col[1])
        # on ne retient que les k-premiers
        del dListe[k:]
        # on ne garde que les data dans lOut
        lOut = [dListe[i][0] for i in range(k)]
        return lOut

    def prediction(self,p_test,k):
        """ méthode qui détermine la classe d'appartenance en fonction des k-plus proches voisins """
        dListe = self.get_voisins(p_test,k) # on recupere la liste des k plus proches voisins (ppv)
        cListe = []
        for i in dListe:
            # on extrait seulement la classe des elements
            cListe.append(i.get_classe())
        # on renvoit l'element qui est le plus présent dans cette liste
        return max(cListe,key=cListe.count)
        


################################################################
print("====== Zone de test ======")
a = Point(3,4)
b = Point(1,2)
print(a,b)
print("distance entre a et b : ",a.distance(b))

toto = Data(Point(2.7810836,2.550537003),0)
tata = Data(Point(1.465489372,2.362125076),0)

print("Coordonnées de toto = ",toto.get_point())
print("Classe de toto = ",toto.get_classe())
print("distance entre toto et tata = ",toto.distance(tata))

ppv = KPPV()
ppv.add_data(Data(Point(2.7810836,2.550537003),0))
ppv.add_data(Data(Point(1.465489372,2.362125076),0))
ppv.add_data(Data(Point(3.396561688,4.400293529),0))
ppv.add_data(Data(Point(1.38807019,1.850220317),0))
ppv.add_data(Data(Point(3.06407232,3.005305973),0))
ppv.add_data(Data(Point(7.627531214,2.759262235),1))
ppv.add_data(Data(Point(5.332441248,2.088626775),1))
ppv.add_data(Data(Point(6.922596716,1.77106367),1))
ppv.add_data(Data(Point(8.675418651,-0.242068655),1))
ppv.add_data(Data(Point(7.673756466,3.508563011), 1))

#print(ppv)
k=8
a=Point(5,2)

kk = ppv.get_voisins(a,k)
for i in kk:
    print(i)

ppv.prediction(a,k)

###############################################
print()
print("Test avec des points tirés aléatoirements :")
print("==========================================")

ppv2 = KPPV()

print("On tire des points aléatoirements dans un carré de 100x100")
print("Classe 0 : coin en bas à gauche")
print("Classe 1 : coin en haut à gauche")
print("Classe 2 : coin en haut à droite")
print("Classe 3 : coin en bas à droite")
print()

for i in range(1000):
    a = Point(100*random.random(),100*random.random())
    if a.x < 50 and a.y < 50:
        classe = 0
    elif a.x < 50 and a.y >= 50:
        classe = 1
    elif a.x >= 50 and a.y >= 50:
        classe = 2
    else:
        classe = 3
    ppv2.add_data(Data(a,classe))

print("On génère 10 points aléatoirement et on prédit leur classe")

k = 10
for i in range(10):
    a = Point(100*random.random(),100*random.random())
    print("Point ",i," Coordonnées : ",a)
    print("  Prédiction de classe :",ppv2.prediction(a,k))

