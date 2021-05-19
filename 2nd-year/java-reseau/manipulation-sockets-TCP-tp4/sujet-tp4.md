# TP 4 : manipulation de sockets TCP en Java

# Programme serveur ItIsTime

Le programme ItIsTimeServer vous est fourni. Le fichier ItIsTimeServer.properties , placé dans le même répertoire que le programme, contient certains paramètres que vous pouvez modifier. Il vous servira à tester le programme client que vous écrirez.
Pour utiliser ce serveur : 

```java
java -jar ItIsTimeServer.jar -v 50050 50060
```

# Programme client ItIsTime

Vous devrez écrire un programme ItIsTime qui vous servira à rendre vos prochains TPs.
ItIsTime est un programme client qui se connecte à un serveur ItIsTimeServer distant.
Le serveur et ses clients communiquent via un port réservé aux échanges de commandes et de réponses, et les fichiers sont transmis via un deuxième port ouvert par le serveur à la demande pour chaque fichier transmis.
Les échanges entre le serveur et ses clients sont définis par un protocole qui se déroule en trois étapes :

* connexion/identification (méthode connect ),
* transfert de fichier (méthode send ),
* déconnexion (méthode close ).

Votre classe ItIsTime implémentera l’interface IItIsTime fournie, et possèdera une méthode main qui prendra en paramètres le nom de machine et numéro de port du serveur, le numéro du TP, votre clé, votre groupe de TP et le fichier à transmettre, et réalisera successivement les opérations connect , send , puis close (chaque méthode sera appelée ou non en fonction du résultat de la méthode précédente).

Connexion/identification : le client envoie le n° du TP, son n° de groupe et sa clé. Le serveur vérifie que la date n’est pas dépassée et renvoie en réponse le nom de login du client. En utilisant les méthodes de la classe LineTransport , la méthode connect doit :

* envoyer le message "10" + tp + grp + key ( tp et grp sont composés chacun d’un seul caractère)
* recevoir la réponse du serveur
* si la réponse du serveur est "20" + login , la méthode renvoie le login
* si la réponse du serveur commence par "5" , afficher un message d’erreur et renvoyer une chaine vide. Les messages d’erreur sont les suivants :

— "50" : identification refused - wrong id
— "51" : identification refused - out of time
— "55" : unexpected command

* transfert de fichier : à la demande du client, le serveur envoie le n ◦ de port ouvert pour ce transfert. À la fin du transfert, le client avertit le serveur, qui lui renvoie le nombre d’octets qu’il a reçus. La méthode send doit :

* envoyer le message "12"
* recevoir la réponse du serveur
* si la réponse du serveur est "52" , afficher le message d’erreur error while opening connection et renvoyer -1 .
* si la réponse du serveur est "22" + nb
	a. ouvrir une connexion avec le port nb du serveur et transmettre le fichier (en binaire) via cette connexion, puis fermer cette connexion (utiliser la méthode send de la classe DataTransport ).
	b. envoyer le message "13" sur le port habituel(c) recevoir la réponse du serveur : 
	— "23" + size : renvoyer la taille du fichier reçu par le serveur (size)
	— "53" : afficher error while reading file et renvoyer -1
	— "55" : afficher unexpected command et renvoyer -1

* déconnexion : le client envoie au serveur une demande de fermeture de la connexion ; le serveur lui répond et la connexion TCP est fermée. La méthode close doit :
* envoyer le message "14"
* recevoir la réponse du serveur :
	— "24" : afficher connexion closed
	— "55" : afficher unexpected command
