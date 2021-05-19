# TP3 Manipulation de sockets TCP en java

Classes Transport et LineTransport

Les interfaces ITransport , ILineTransport et IDataTransport qui vous sont fournies décrivent des opérations  utilisées par bon nombre d'applications clientes : 
* openClient, open, close : pour ouvrir (resp. fermer) une connexion TCP avec un serveur, ainsi que les ressources associées qui permettront de communiquer avec ce serveur (input et output streams).
* send , receive : pour envoyer (resp. recevoir) un message de type chaine de caractères (pour l’interface ILineTransport ) ou des données binaires (pour l’interface IDataTransport).

Écrivez une classe Transport qui implémente l’interface ITransport et ses opérations.
Pour avoir des précisions sur la façon de les implémenter, lisez bien la documentation de ces méthodes dans l’interface ITransport.
Écrivez ensuite une classe LineTransport qui implémente l’interface ILineTransport et ses opérations.

Pour tester les méthodes que vous implémentez, écrivez un programme de test qui crée un objet LineTransport et appelle ses méthodes open. 
Puis send (avec une chaine quelconque), puis receive , puis close , que vous testerez avec le programme EchoServer écrit au TP précédent.

# Classe DataTransport

Vous développerez une classe DataTransport qui implémente l’interface IDataTransport et ses opéra-
tions :
* receive : reçoit le contenu d’un fichier distant envoyé depuis un serveur. Cette méthode ne doit pas faire de transformations sur le fichier, et donc utiliser les flux d’entrée/sortie "bruts" (InputStream / OutputStream et non Reader / Writer)
* send : envoie le contenu d’un fichier à un serveur.

Pour tester ces méthodes, écrivez un programme de test qui appelle ces méthodes en leur passant en paramètre un objet BufferedOutputStream ou BufferedInputStream créé à partir d’un fichier dans lequel la méthode pourra écrire ou lire(1).
Vous utiliserez le programme DataServer fourni(2).

(1) : new BufferedOutputStream (new FileOutputStream(filename)) où filename est un nom de fichier
(2) : new BufferedOutputStream (new FileOutputStream(filename)) où filename est un nom de fichier
