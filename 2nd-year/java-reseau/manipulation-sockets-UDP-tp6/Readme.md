On lance ProcessUserInfo
    $ java ProcessUserInfo 5000

Le programme attend sur le port 5000 de localhost de recevoir des datagrammes

On lance dans un autre terminal plusieur SendUserInfo pour connecter de nouveaux utilisateurs

    $ java SendUserInfo localhost 5000 127.0.0.1 3000 toto
    $ java SendUserInfo localhost 5000 127.0.0.1 3001 tata
    $ java SendUserInfo localhost 5000 127.0.0.1 3002 tyty

On a donc 3 users connectés comme le montre le terminal de ProcessUserInfo.

Dans un nouveau terminal on lance la commande :
    $ java RequestUserInfo localhost 5000 127.0.0.1 6000 toto

ProcessUserInfo recoit la requete (elle est affichée) et répond en renvoyant le datagramme de l'utilisateur connecté qui est affiché par RequestUserInfo

Si on lance un requete sur un utilisateur qui n'existe pas , on recoit un datagramme vide et RequestUserInfo affiche le message "USER ?? NOT CONNECTED !"

On peut déconnecter des utilisateurs avec la commande :
    $ java DeleteUserInfo localhost 5000 toto

ProcessUserInfo affiche un message comme quoi soit l'utilisateur est déconnecté soit il n'est pas connecté.
