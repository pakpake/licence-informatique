# TP5 manipulation des sockets UDP java

## Classe DataBufferizer

À la différence des sockets TCP, les sockets UDP ne permettent pas de manipuler les flots de données.
Les données transmises via le protocole UDP sont des datagrammes UDP, dont la charge utile est un tableau d’octets de taille bornée.
Une application reposant sur l’échange de datagrammes UDP doit définir précisément le format des données embarquées dans un datagramme.

La classe *DataBufferizer* qui vous est fournie définit des méthodes statiques permettant d’insérer/extraire des données de types divers dans/depuis un tableau d’octets.
Le code de cette classe n’est pas commenté, volontairement : à vous d’examiner ses méthodes pour les comprendre et pouvoir les utiliser.

## Format des datagrammes

### format n°1:

Les datagrammes échangés par les programmes que vous écrirez aujourd’hui devront comporter les champs suivants :

* type : un octet unique qui prendra la valeur 0x01
* date : une date représentant le nombre de millisecondes écoulées depuis le 1 er janvier 1970, 00 :00 :00 GMT sous forme d’un entier long (64 bits)
* address : adresse IPv4 de la forme w.x.y.z représentée par un tableau de 4 octets w, x, y, z
* port : n° de port UDP, représenté par un entier court (16 bits)
* login : nom de login, représenté par un tableau d’octets de taille fixe (16 octets), précédé d’un octet indiquant le nombre d’octets significatifs présents dans le tableau.

| type | date | address | port | n | login |
|------|------|---------|------|---|-------|
|  1   |  8   |    4    |  2   | 1 |  16   | octets

# Echange de messages de notification de présence

Vous devez écrire deux programmes (un émetteur et un récepteur) qui s’échangent des messages de notification de présence, en utilisant des datagrammes conformes au format n°1 : 

* l’émetteur envoie un datagramme pour informer de sa présence
* le récepteur reçoit des datagrammes et affiche à l’écran les informations qu’il reçoit.

Pour pouvoir tester votre programme *SendUserInfo*, un programme *RecvUserInfo* vous est fourni.

**L'émetteur** : la syntaxe d'appel est la suivante : 

```java
java SendUserInfo <adresse1> <port1> <adresse2> <port2> <login>
```

* *adresse1*, *port1* : coordonnées du récepteur auquel adresser le datagramme
* *adresse2*, *port2* : coordonnées de l'émetteur, à intégrer dans le datagramme

**Le récepteur** la syntaxe d'appel est la suivante :

```java
java RecvUserInfo <port>
```

* *port* : port sur lequel le récepteur doit se connecter pour la réception des datagrammes
