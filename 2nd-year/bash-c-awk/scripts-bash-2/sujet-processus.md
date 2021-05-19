# écrire un moniteur des ressources du système utilisées
# interdiction d'utiliser la commande `awk`

# processus
## lectureProc

Écrire une FONCTION lectureProc dans un script qui lit l’activité des processus sur votre machine à partir du répertoire /proc.
Le noyau maintient un ensemble d’informations sur les processus actuels (la table des processus).
Le répertoire /proc est un accès à ces informations.

```bash
$ ls /proc/
1/ 19924/ 5332/ 6260/ 6378/ config.gz meminfo
117/ 19947/ 5366/ 6279/ 6387/ cpuinfo misc
11731/ 19948/ 5376/ 6302/ 6389/ crypto modules
11980/ 2/ 5408/ 6305/ 19403/
```

Quand le nom du répertoire est un nombre, il correspond à un processus.

```bash
$ ls /proc/
auxv cwd@ exe@ maps mounts oom_score stat status wchan
cmdline environ fd/ mem oom_adj root@ statm task/
```

Le fichier status contient les informations sur l’état du processus.

```bash
$ more /proc/19403/status
Name: bash
State: S (sleeping)
SleepAVG: 88%
Tgid: 19403
Pid: 19403
PPid: 26490
TracerPid: 0
Uid: 261 261 261 261
Gid: 200 200
```

## 2 Réseau

## lectureNet

Écrire une FONCTION dans un nouveau script qui lit le trafic réseau (carte réseau Ethernet ou Wifi) de votre machine 
Le nom de l’interface de type lo,eth,eth0,enp0s,wlan,wlp1s0, ... est passé en paramètre de la fonction
La fonction exploite le fichier/proc/net/devmis à jour par le noyau.

```bash
$ more /proc/net/dev
Inter-| Receive | Transmit
face |bytes packets errs ... |bytes packets errs ...
lo:1635241 75429 0 0 0 0 0 0 1635244 75429 0 0 0 0 0 0
eth0:38975751 2626127 0 0 0 0 0 0 46554457 260618 0 0 0 0 0 0
wlan0:1605508 2754 0 0 0 0 0 0 213391 1308 0 0 0 0 0 0
```

Le fichier contient le nombre de paquets ou bytes émis, reçus et le nombre de collisions depuis le boot de la machine pour chaque interface réseau.
La fonction retourne le nombre de bytes émis ainsi que le nombre de bytes reçus.


## chargeNet

Écrire une FONCTION chargeNet qui calcule la charge réseau de votre machine depuis son dernier appel.
L’outil exploite le fichier/proc/net/devet donc la fonction précédente lectureNet. 
La différence des valeurs entre deux lectures de ce fichier donne le nombre de paquets émis ou reçus depuis la lecture précédente.
Par exmple, si on effectue une seconde lecture du fichier, 5 secondes après le la
première lecture.

```bash
$ more /proc/net/dev
Inter-| Receive | Transmit
face |bytes packets errs ... |bytes packets errs ...
lo:1635241 75429 0 0 0 0 0 0 1635244 75429 0 0 0 0 0 0
eth0:38975751 2626127 0 0 0 0 0 0 46554457 260618 0 0 0 0 0 0
wlan0:1608609 2753 0 0 0 0 0 0 213501 1309 0 0 0 0 0 0
```

on voit, entre les deux lectures sur les lignes wlan0. il y a un nombre émis de bytes de 213501 213391 soit 110/5 bytes/second
Le nom de l’interface eth,eth0,wlan,wlan0 ... est passé en paramètre de la fonction ainsi que le temps depuis de dernier appel.
La fonction retourne le débit émis et reçus depuis l’appel précédent.
Le programme principal doit une première fois appelé la fonction lectureNet puis itère toutes le N seconds sur la fonction chargeNet.
Il reçoit en argument de la ligne de commande le nom de l’interface et le temps N entre chaque mesure.
exemple :

```bash
$ tp_net.sh wlp1s0 5
Net debit 4318332 b/s entrant 262826 b/s sortant
Net debit 4318370 b/s entrant 262875 b/s sortant
Net debit 4318563 b/s entrant 262963 b/s sortant
Net debit 4318707 b/s entrant 263041 b/s sortant
```

## 3 Monitor

Proposer un programme principal qui reprenne les deux programmes précédents et affiche de façon synthétique sur une même ligne l’ensemble des mesures Process et Net (80 caractères maximum sur une ligne).
