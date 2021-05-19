# COMPILATION

# commande pour compiler

```java
javac Main.java
javac VosClasses.java
```

# pour exécuter

`sqlite-jdbc-3.32.3.2.jar` disponible dans le répertoire `drivers/`.

`java -classpath ".:sqlite-jdbc-3.32.3.2.jar" Main`


# Comment faire vos classes/methodes et comment les appeler

1. créer votre fichier Monfichier.java et faire une classe Monfichier
2. dans la classe Monfichier, écrire votre méthode en recopiant l'exemple
    votre méthode doit avoir en paramètre (Connection c)
3. dans le Main.java, remplacer dans la méthode appelRequetes(...) la ligne 
        `Requetes.testRequete(c)` par `Monfichier.maNewMethode(c)`
