# Principe de fonctionnement

Dans un 1er terminal:

$ javac SendUserInfo.java
$ java SendUserInfo localhost 5000 127.0.0.1 6000 login
on attend avant de lancer cette derniere commande
Dans un 2nd terminal
$ javac ReceiveUserInfo.java
$ java ReceiveUserInfo 5000

On peut maintenant lancer la 2eme commande dans le 1er terminal

et on revient sur le 2eme terminal et on observe le résultat.
