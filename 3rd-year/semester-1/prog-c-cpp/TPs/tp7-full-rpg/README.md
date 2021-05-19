# Compte-rendu du tp 7 -- RPG --

* L'intégralité des sources est dans le répertoire `src`.
* Le répertoire `test` contient le fichier `MainTest.cpp` permettant de réaliser l'intégralité des tests grâce à `Catch-master`. il faiut appel aux tests individuels (fichiers `test-*.h`). Il contient aussi le fichier `Main.cpp` avec les tests personnels sur les différentes classes. 
* Le `Makefile` permet de compiler et générer les exécutables pour les tests personnels et ceux de catch master.
* Le fichier `catch-tests` permet d'exécuter les tests grâce à `Catch-master`. Le fichier `personal-tests` exécute mes propres tests avec les sorties d'affichage.

## Compilation & Exécution

Dans un terminal : 

pour nettoyer les fichiers `.o` :
```bash
    $ make clean
```

pour la compilation et l'édition des liens de l'intégralité des fichiers (création d'un exécutable pour les tests personnels (`personal-tests`) et pour les tests de `Catch-master` (`catch-tests`)) : 
```bash
    $ make      (Ou $ make all)
```

pour la compilation et l'exécution des tests `Catch-master` : 
```bash
    $ make test
```

pour la compilation et l'exécution des tests personnels : 
```bash
    $ make personal
```

pour l'exécution des programmes :
```bash
    $ ./catch-tests -s
    $ ./personal-tests
```

## Arborescence complète du projet

```bash
├── catch-tests
├── Makefile
├── personal-tests
├── README.md
├── README.pdf
├── src
│   ├── CraftedWeapon.h
│   ├── Creature.h
│   ├── Damageable.h
│   ├── Dice.h
│   ├── IntegerItem.h
│   ├── IntegerValue.h
│   ├── Item.h
│   ├── Race.h
│   ├── String.h
│   └── Weapon.h
└── test
    ├── catch2
    ├── Main.cpp
    ├── MainTest.cpp
    ├── test-crafted.h
    ├── test-creature.h
    ├── test-damageable.h
    ├── test-dice.h
    ├── test-integerValue.h
    ├── test-item.h
    ├── test-race.h
    ├── test-string.h
    └── test-weapon.h
```
