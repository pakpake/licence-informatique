from random import uniform
import random
import numpy as np
import matplotlib.pyplot as plt

class SimulFileAttente():
    """ Permet de modéliser les clients et de simuler la file d’attente des  clients """
    serviceMin = 30
    serviceMax = 300
    tMax = 8*60*60
    # tMax : durée maximale d’ouverture du guichet (en secondes)
    # serviceMin : temps de service minimal d’un client, exprimé en secondes
    #self.serviceMin = 30
    # serviceMax : temps de service maximal d'un client, exprimé en secondes
    #self.serviceMax = 300
    
    def __init__(self):
        # currentTime : mesure le temps courant dans la boucle de simulation (incrémenté de 1 à chaque itération)
        self.currentTime = 0


    def arrivee_client(self,p):
        """ fonction booleene qui signale l'arrivee d'un client avec la probabilité p """
        return random.random() < p
    

    def simul(self,p):
        self.file_attente = []
        self.nbClient_servis = 0
        self.nbClient_total = 0
        self.libre = 0      # prochaine date a laquelle le guichet est libre
        for t in range(self.tMax):       # on parcourt toutes les secondes de l'ouverture du guichet
            if self.arrivee_client(p):      # on verifie si un nouveau client arrive a t
                self.nbClient_total += 1    # si oui on augmente le nb de client total
                self.file_attente.append(self.Client(t))    # on le met dans la file d'attente
            if self.libre <= t:     # le guichet est libre on sert un client
                # on verifie que la file d'attente n'est pas vide et contient au moins un client qui soit reste parce qu'il n'as pas eu marre d'attendre
                if not len(self.file_attente) == 0:      # si la file n'est pas vide
                    del self.file_attente[0]
                    self.libre = t + uniform(self.serviceMin,self.serviceMax)
                    self.nbClient_servis += 1
        return self.nbClient_servis/self.nbClient_total                

        
    def simul_bis(self,p):
        self.file_attente = []
        self.nbClient_servis = 0
        self.nbClient_total = 0
        self.libre = 0      # prochaine date a laquelle le guichet est libre
        for t in range(self.tMax):       # on parcourt toutes les secondes de l'ouverture du guichet
            if self.arrivee_client(p):      # on verifie si un nouveau client arrive a t
                self.nbClient_total += 1    # si oui on augmente le nb de client total
                self.file_attente.append(self.Client(t))    # on le met dans la file d'attente
            if self.libre <= t:     # le guichet est libre on sert un client
                # on verifie que la file d'attente n'est pas vide et contient au moins un client qui soit reste parce qu'il n'as pas eu marre d'attendre
                while not len(self.file_attente) == 0:      # on regarde si la liste n'est pas vide
                    c = self.file_attente[0]                # on recupere le 1er client en attente
                    del self.file_attente[0]                # on l'enleve de la liste
                    if c.leaveTime >= t:        # le client a attendu
                        self.libre = t + uniform(self.serviceMin,self.serviceMax)   # on calcule la prochiane date a laquelle le guichet sera libre
                        self.nbClient_servis += 1       # on incremente le nombre de client servis
                        break       # on quitte la boucle si on a servi un client
        return (self.nbClient_total-self.nbClient_servis)/self.nbClient_total                

    def simul_ter(self,p):
        self.file_attente = []
        self.nbClient_servis = 0
        self.nbClient_total = 0
        self.libre = 0      # prochaine date a laquelle le guichet est libre
        for t in range(self.tMax):       # on parcourt toutes les secondes de l'ouverture du guichet
            # print("itération : ", str(t))
            # print("  file : ",str(len(self.file_attente)))
            # on supprime de la file d'attente les clients qui ont perdu patience
            for i in self.file_attente:       # on verifie pour tous les clients dans la file d'attente si ils doivent quitter parce qu'ils perdent patience
                if i.leaveTime < t:     # le client i perd patience
                    self.file_attente.remove(i)     # il quitte la file d'attente
            if self.arrivee_client(p):      # on verifie si un nouveau client arrive a t
                # print("  arrivée d'un client : ")
                self.nbClient_total += 1    # si oui on augmente le nb de client total
                self.file_attente.append(self.Client(t))    # on le met dans la file d'attente
                # print("    file :",str(len(self.file_attente)))
            if self.libre <= t:     # le guichet est libre on sert un client
                # on verifie que la file d'attente n'est pas vide et contient au moins un client qui soit reste parce qu'il n'as pas eu marre d'attendre
                # print("  le guichet est libre")
                if not len(self.file_attente) == 0:      # si la liste n'est pas vide
                    # print("  on sert le client")
                    # c = self.file_attente[0]
                    del self.file_attente[0]            # on supprime le client de la liste
                    self.nbClient_servis += 1       # on incremente le nombre de client servis
                    self.libre = t + uniform(self.serviceMin,self.serviceMax)   # on calcule la prochiane date a laquelle le guichet sera libre
        return (self.nbClient_total-self.nbClient_servis)/self.nbClient_total


    class Client():
        """ Classe qui modélise les temps liés aux clients """
        # informations de la question 5
        waitMin = 120
        waitMax = 1800
        
        def __init__(self,t):
            # membre de la classe q°2)
            self.arrivTime = t
            # le client arrive a l'instant t et repart a leaveTime
            # sans avoir ete servi (marre d'attendre)
            self.leaveTime = t + uniform(self.waitMin,self.waitMax)
    
        def __str__(self):
            return "(" + str(self.arrivTime) + "," + str(self.leaveTime) + ")"



def main():
    """ fonction main de test. Les premiers tests sont les miens, la dernière partie correspond à ce qui est demandé en question 6 """  
    ###########################################
    print()
    print("============== Zone de test ==============")
    print()
    print("on creer une simulation, on modelise un client et simule la file d'attente")
    s1 = SimulFileAttente()
    print("on teste un client, et on affiche la date d'arrivee dans la file et la date a laquelle il s'en va si il a trop attendu")
    c = s1.Client(3)
    print(c)

    print("on simule l'arrivée des clients avec un probabilite 0.01")
    toto = s1.simul(0.01)
    print(toto)

    print()
    print("on fait varier la proba de 0.002 à 0.6 par pas de 0.01")
    liste1 = []
    x1 = np.arange(0.002,0.6,0.01)
    for i in x1:
        liste1.append(s1.simul(i))

    plt.figure(1)
    print("Graphique de cette probabilité :")
    plt.plot(x1, liste1)
    plt.title("Evolution du taux de service")
    plt.xlabel("Probabilite d'arrivee des Clients")
    plt.ylabel("Taux de Clients servis")

    ###########################################

    print("===== SIMUL BIS ======")
    s2 = SimulFileAttente()
    print("on créer une liste qui se remplira avec le taux de clients non-servis en fonction de la probabilité des clients")
    print()
    print("on fait varier la proba de 0.002 à 0.6 par pas de 0.01")
    liste2 = []
    x2 = np.arange(0.002,0.6,0.01)
    for i in x2:
        liste2.append(s2.simul_bis(i))

    plt.figure(2)
    print("Graphique de cette probabilité :")
    plt.plot(x2, liste2)
    plt.title("Evolution du taux de non service")
    plt.xlabel("Probabilite d'arrivee des Clients")
    plt.ylabel("Taux de Clients non-servis")

    ###########################################

    print("====== SIMUL TER ======")
    s3 = SimulFileAttente()
    print("on créer une liste qui se remplira avec le taux de clients non-servis en fonction de la probabilité des clients")
    print()
    print("on fait varier la proba de 0.002 à 0.6 par pas de 0.01")
    liste3 = []
    x3 = np.arange(0.002,0.6,0.01)
    for i in x3:
        liste3.append(s3.simul_ter(i))
    
    plt.figure(3)
    print("Graphique de cette probabilité :")
    plt.plot(x3, liste3)
    plt.title("Evolution du taux de non service")
    plt.xlabel("Probabilite d'arrivee des Clients")
    plt.ylabel("Taux de Clients non-servis")
    print()
    print("Cette méthode est beaucoup plus lente car à chaque itérations, on doit parcourir toute la file d'attente, alors que dans simul_bis on ne parcourt la file d'attente que quand c'est nécessaire pour trouver le prochain client à servir.")
    print("Le résultat est identique.")

    ###########################################

    print("===== SIMUL BIS Q°6) ======")
    s4 = SimulFileAttente()
    print("on créer une liste qui se remplira avec le taux de clients non-servis en fonction de la probabilité des clients")
    print()
    print("on fait varier la porba de 0.002 à 0.6 par pas de 0.01")
    liste4 = []
    x4 = np.arange(0.00025,0.025,0.00025)
    for i in x4:
        liste4.append(s4.simul_bis(i))
    
    plt.figure(4)
    print("Graphique de cette probabilité :")
    plt.plot(x4, liste4)
    plt.title("Evolution du taux de non service")
    plt.xlabel("Probabilite d'arrivee des Clients")
    plt.ylabel("Taux de Clients non-servis")

