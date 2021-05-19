# TP 6 : manipulation de sockets UDP en java

## Format des datagrammes

### Format n°2 : 

Un datagramme conforme à ce format devra comporter les champs suivants :

* type    : un octet unique qui prendra la valeur 0x02
* address : adresse IPv4 de la forme w.x.y.z représentée par un tableau de 4 octets w, x, y, z
* port    : n° de port UDP, représenté par un entier court (16 bits)
* login   : nom de login, représenté par un tableau d’octets de taille fixe (16 octets), précédé d’un octet indiquant le nombre d’octets significatifs présents dans le tableau.

| type | address | port | n | login |
|------|---------|------|---|-------|
|  1   |    4    |  2   | 1 |  16   | octets

### Format n°3 :

Un datagramme conforme à ce format devra comporter les champs suivants :

* type  : un octet unique qui prendra la valeur 0x03
* login : nom de login, représenté par un tableau d’octets de taille fixe (16 octets), précédé d’un octet indiquant le nombre d’octets significatifs présents dans le tableau.

| type | n | login |
|------|---|-------|
|  1   | 1 |  16   | octets

## Stockage et consultation des informations relatives à la présence des utilisateurs

Vous devez écrire deux programmes : le premier, *ProcessUserInfo* , mémorise les informations relatives à la présence des utilisateurs, et répond au second, *RequestUserInfo* , qui l’interroge pour obtenir les informations concernant un utilisateur.
Les informations stockées par *ProcessUserInfo* sont envoyées par des utilisateurs sous forme d’un message conforme au format n°1 à l’aide du programme *SendUserInfo* (cf TP 4).
Pour chaque nom de login, le programme ne conserve que le dernier message reçu.
Le programme *RequestUserInfo* envoie une requête relative à un utilisateur, sous forme d’un message conforme au format n°2.
*ProcessUserInfo* y répond en renvoyant aux coordonnées indiquées dans la requête un datagramme au format n°1 contenant les informations relatives au login, si elles existent.

### Programme *ProcessUserInfo*

la syntaxe d’appel est la suivante :

```java
java ProcessUserInfo <port>
```

* *port* : port sur lequel le programme se connecte pour la récpetion des datagrammes

### Programme *RequestUserInfo*

la syntaxe d’appel est la suivante :

```java
java RequestUserInfo <addresse1> <port1> <addresse2> <port2> <login>
```

* *adresse1*, *port1* : coordonnées de programme *ProcessUserInfo* auquel adresser le datagramme
* *adresse2*, *port2* : coordonnées de l'émetteur, à intégrer dans le datagramme
* *login* : nom de login sur lequel porte la requête

## Notification de départ

Vous devez écrire un programme *DeleteUserInfo* qui envoie un datagramme au format n°3 pour informer *ProcessUserInfo* du départ d’un utilisateur.
Vous devez modifier le programme *ProcessUserInfo* pour, à la récéption d’un tel datagramme, effacer les informations concernant l’utilisateur dont le nom de login est inclus dans le datagramme reçu.

### Programme *DeleteUserInfo*

la syntaxe d'appel est la suivante :

```java
java DeleteUserInfo <adresse> <port> <login>
```

* *adresse*, *port* : coordonnées de programme *ProcessUserInfo* auquel adresser le datagramme
* *login* : nom de login à intégrer dans le datagramme

