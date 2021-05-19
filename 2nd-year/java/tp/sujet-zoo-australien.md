# Sujet zoo australien

***tp réalisé sous le logiciel BlueJ***


* L'**ornithorynque** et le **dingo** carnivores.

* Le **kangourou** et le **wombat** herbivores qui se nourrissent uniquement de l'herbe du zoo.

* Le **koala** herbivore qui se nourrit exclusivement de feuilles d'eucalyptus.

# Classe Animal

Créez la classe Animal avec les caractéristiques suivantes.

1. Chaque animal est caractérisé par 
    + son nom, chaine de caractère
    + son poids, un réel
2. Le classe Animal a un constructeur que prend en paramètre le nom et le poids de l'animal
3. Chaque attribut de la Classe Animal a un accesseur
4. La méthode **toString** est redéfinie pour qu’elle retourne la chaîne de caractères suivante :
`<nom> (<poids> kg)`
5. La classe Animal a une méthode **coutNourriture** qui retournera en euros le coût de la nourriture par jour de chaque animal. 
Cette méthode est spécifique à chaque animal, et ne peut pas être définie spécifiquement dans cette classe Animal.

# Classe Zoo

Complétez la classe Zoo. En particulier :


1. Le constructeur qui prend en paramètres le nom du zoo et la capacité maximale d’accueil.
2. La méthode **nbAnimaux** qui retourne le nombre d'animaux présents dans le zoo.
3. La méthode **ajoutAnimal** qui permet d’ajouter un animal au zoo.
Si la capacité maximale d’accueil du zoo est atteinte, alors la méthode ne fait rien.
4. La méthode **supprimeAnimal** qui supprime le premier animal de la liste en fonction d’un nom donné en paramètre.

>Rappel : un appel à la méthode
```java
void remove(int i)
```
>permet de supprimer le **i-ème** élément d’une **ArrayList**.
5. La méthode **coutTotal** qui retourne le coût de la nourriture pour l’ensemble des animaux du zoo pour une journée.
6. La méthode **printDetails** pour afficher, si le zoo contient **N** animaux.

Le zoo <nom> (<capaciteMax> animaux maximum) accueille :
    * <description animal1>
    * ...
    * <description animalN>

La nourriture coûte **<cout>** euros par jour, sinon aucun animal dans le zoo <nom>.

# Classes Herbivore et Carnivore

Tous les animaux carnivores mangent 20 % de leur poids en viande par jour.
Un kilo de viande coûte 10 euros.
Tous les animaux herbivores mangent l'herbe du zoo, ce qui coûte 5 euros par jour (d’entretien) pour chaque animal herbivore.
Complétez la méthode **coutNourriture** pour les deux classes **Herbivore** et **Carnivore** qui, pour rappel, doit retourner en euros le coût de la nourriture par jour de chaque animal.

# Classe Dingo

Créez la classe Dingo qui contient :

1. Un constructeur qui prend en paramètres le nom et le poids du dingo.
2. La redéfinition de la méthode **toString** qui retourne la chaîne de caractères suivante :
`<nom> (<poids> kg) un dingo`

# Classe Koala

La koala est un herbivore particulier qui mange uniquement des feuilles d’eucalyptus (et donc pas l’herbe du zoo).
Créez la classe Koala qui contient :

1. Un attribut qui définit le nombre d’heure de sommeil maximal par jour du koala. 
Comme tous les animaux, le koala est aussi caractérisé par son nom et son poids.
2. Un constructeur qui prend en paramètres le nom, le poids et le nombre d’heures de sommeil maximum du koala.
3. La redéfinition de la méthode **coutNourriture** en prenant en compte qu’un koala mange entre 1 heure et son nombre d’heure maximale où il est éveillé, **et** qu’un koala mange 1kg de feuilles d’eucalyptus pour chaque heure où il mange.
Un kilogramme de feuilles d’eucalyptus coûte 6.5 euros.

Rappel : Un nombre aléatoire peut être généré en créant un objet **rand** de type **Random** (défini dans l’API java.util), puis en faisant un appel à la méthode suivante :
`int nextInt(int i)` qui retournera un nombre entre 0 et i-1.
4. La redéfinition de la méthode **toString** qui retourne la chaîne de caractères suivante :
><nom> (<poids> kg) un koala qui dort au maximum <sommeil> par jour

# Tests (pour vérification)


Pour tester le bon fonctionnement de votre programme, vous pouvez utiliser la méthode **main** de la classe **Zoo** - laissez les accolades vides lors de son appel en cliquant sur *OK* lors de l’ouverture d'une fenêtre.
Vous devez voir apparaître dans le terminal des instructions.
Pour tester le fonctionnement de la classe **Koala**, décommenter la ligne :
```java
z.ajoutAnimal(new Koala("Buster", 6, 18));
```
