'''
Exemple mini module Python avec test intégré.

L'idéal serait d'utiliser des outils de test dédiés, mais
c'est peut être un peu complexe en première approche.

La "méthode" repose sur le découpage en deux parties.

1. le code qui importé pour utilisation

   Exemple d'utilisation dans un code Python :

   from methode_test_simple.py import MaClasse
   a = MaClasse()


2. la partie test qui sera exécutée si le fichier 
est interprété de façon autonome (non importé)

   Exemple d'utilisation en ligne de commande :

   python methode_test_simple.py


Avantages :

* permet de tester rapidement son module en permanence (à chaque modif)
sans entraver l'utilisation

* tout est dans un même fichier, permet de garantir une conception conjointe
du code et de son test (recommandé)


Inconvénients :

* permet difficilement de tester des cas d'exeception ou d'erreur (ce que
permettent des outils de test)

* bien moins performant que d'utiliser des outils de test
'''



class MaClasse():
    'bla bla'

    def __init__(self, x=None):
        'Création instance avec `x` un truc.'
        self.x = x

    def __str__(self):
        'Affichage du truc.'
        return '<<' + str(self.x) + '>>'

    def __add__(self, autre):
        'Addition de deux trucs.'
        return self.x + autre.x


if __name__ == '__main__':
    '''Cette partie ne sera exécutée que si le module est interprété
    directement (depuis l'interpréteur et pas depuis un "import" 
    dans un autre module).
    '''

    print('Début des tests:')
    z = MaClasse()
    a = MaClasse(1)
    b = MaClasse(2)
    print(a)
    print(b)
    print(a + b)
    print('Fin des tests.')
