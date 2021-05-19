--
-- Requêtes pour la création d'un base de données :  `Librairie`
--

-- Total = 6 tables 

-- --------------------------------------------------------

--
-- Structure de la table `Abonnes`
--

CREATE TABLE `Abonnes` (
  `numSecu` varchar(15) NOT NULL PRIMARY KEY,
  `nom` varchar(50) DEFAULT NULL,
  `prenom` varchar(50) DEFAULT NULL,
  `dateNaissance` date NOT NULL,
  `dateInscription` date NOT NULL,
  `identifiant` varchar(50) DEFAULT NULL,
  `mdp` varchar(50) DEFAULT NULL,
  `listeNoire` tinyint(1) NOT NULL
);

-- --------------------------------------------------------

--
-- Structure de la table `Bibliothecaires`
--

CREATE TABLE `Bibliothecaires` (
  `numSecu` varchar(15) NOT NULL PRIMARY KEY,
  `nom` varchar(50) NOT NULL,
  `prenom` varchar(50) NOT NULL,
  `identifiant` varchar(50) NOT NULL,
  `mdp` varchar(50) NOT NULL
);

-- --------------------------------------------------------

--
-- Structure de la table `DVD`
--

CREATE TABLE `DVD` (
  `EAN` varchar(13) NOT NULL PRIMARY KEY,
  `titre` varchar(300) NOT NULL,
  `realisateur` varchar(300) NOT NULL,
  `resume` varchar(300) NOT NULL,
  `langue` varchar(50) NOT NULL,
  `principauxActeurs` varchar(300) NOT NULL,
  `datePublication` date NOT NULL,
  `motCles` varchar(300) NOT NULL,
  `nombre` int(100) NOT NULL,
  `duree` int NOT NULL,
  `etat` tinyint(1) NOT NULL
);

-- --------------------------------------------------------

--
-- Structure de la table `Emprunts`
--

CREATE TABLE `Emprunts` (
    -- id permet de numéroter les emprunts et d'en faire une clé unique 
  `id` integer PRIMARY KEY AUTOINCREMENT,
    -- EAN Foireign key pour véirfier que EAN existe bien
  `EAN` varchar(13) NOT NULL,
    -- Pour savoir qui emprunte le livre, sinon on ne sait pas
  `numSecu` varchar(15) NOT NULL,
  `dateRetourTheorique` date NOT NULL,
  `dateRetour` date,
  `dateEmprunt` date NOT NULL,
  CONSTRAINT fk_numsecu
    FOREIGN KEY (numSecu)
    REFERENCES Abonnes(numSecu)
);

-- --------------------------------------------------------

--
-- Structure de la table `Livres`
--

CREATE TABLE `Livres` (
  `EAN` varchar(13) NOT NULL PRIMARY KEY,
  `titre` varchar(300) NOT NULL,
  `auteur` varchar(300) NOT NULL,
  `datePublication` date(100) NOT NULL,
  `resume` varchar(300) NOT NULL,
  `langue` varchar(300) NOT NULL,
  `motCles` varchar(300) NOT NULL,
  `nombre` int(100) NOT NULL,
  `editeur` varchar(300) NOT NULL,
  `etat` tinyint(1) NOT NULL
);

-- --------------------------------------------------------

--
-- Structure de la table `Reservations`
--

CREATE TABLE `Reservations` (
    -- idem que Emprunts
  `id` integer PRIMARY KEY AUTOINCREMENT,
  `numSecu` varchar(15) NOT NULL,
  `EAN` varchar(13) NOT NULL,
  `dateReserv` date NOT NULL,
  CONSTRAINT fk_numsecu
    FOREIGN KEY (numSecu)
    REFERENCES Abonnes(numSecu)
);

----------------------------------------------------------

-- insertion de valeurs tests

------------------------------------------------------------
-- table Abonnes
------------------------------------------------------------

insert into Abonnes values('099887766655544','Dupont','Agathe','15/06/2012','02/12/2019','monChat','miaou',0);


------------------------------------------------------------
-- table Bibliothecaires
------------------------------------------------------------

insert into Bibliothecaires values('122334455566677','Thecaire1','Biblio1','IDBiblio1','mdpBiblio1');

------------------------------------------------------------
-- table DVD
------------------------------------------------------------

insert into DVD values('1234567890123','Titanic','James Cameron','Je taime mais coule toujours','anglais','Leonardo DiCaprio,Kate Winslet','07/01/1998','flotte,amour',4,254,0);

------------------------------------------------------------
-- table Livres
------------------------------------------------------------

insert into Livres values('0987654321098','La bdd pour les nuls','Raphalen','02/12/2020','Comment ça marche les requetes','Pas la notre','cours',15,'La fac',0);

------------------------------------------------------------
-- table Emprunts
------------------------------------------------------------

-- on ne met pas l'id, il s'incrémente tout seul
insert into Emprunts(EAN,numSecu,dateRetourTheorique,dateRetour,dateEmprunt) values('1234567890123','099887766655544','14/01/2021','','16/11/2020');

------------------------------------------------------------
-- table Reservations
------------------------------------------------------------

-- pas de réservations pour le moment
