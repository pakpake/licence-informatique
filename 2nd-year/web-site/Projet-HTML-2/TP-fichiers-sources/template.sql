-- phpMyAdmin SQL Dump
-- version 3.3.9
-- http://www.phpmyadmin.net
--
-- Serveur: localhost
-- Généré le : Dim 18 Mars 2012 à 22:19
-- Version du serveur: 5.5.8
-- Version de PHP: 5.3.5

SET SQL_MODE="NO_AUTO_VALUE_ON_ZERO";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8 */;

--
-- Base de données: `template`
--

-- --------------------------------------------------------

--
-- Structure de la table `pages`
--

CREATE TABLE IF NOT EXISTS `pages` (
  `Id_page` int(11) NOT NULL AUTO_INCREMENT,
  `Titre` varchar(255) NOT NULL,
  `Mots_cles` varchar(255) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `Contenu` text NOT NULL,
  `Id_parent` int(11) NOT NULL DEFAULT '1',
  PRIMARY KEY (`Id_page`)
) ENGINE=InnoDB  DEFAULT CHARSET=latin1 AUTO_INCREMENT=15 ;

--
-- Contenu de la table `pages`
--

INSERT INTO `pages` (`Id_page`, `Titre`, `Mots_cles`, `Description`, `Contenu`, `Id_parent`) VALUES
(1, 'Accueil', 'Accueil', 'Accueil', '<p>Bienvenue sur le site perso!</p><br/><br/><br/><p>Au programme, des  photos, des t&eacute;l&eacute;chargements ainsi qu''un formulaire si vous voulez me contacter.</p><p>Bon courage &agrave; toutes et &agrave; tous!<p>', 0),
(2, 'Photos', 'Photos', 'Photos Astronomie', 'photos des planetes et du systeme solaire', 1),
(3, 'Téléchargements', 'Téléchargements', 'Téléchargements', 'Téléchargements', 1),
(4, 'Contact', 'Contact', 'Contact', 'Contact', 1),
(5, 'Plan du site', 'Plan du site', 'Plan du site', 'Plan du site', 1),
(6, 'photos de Planetes', 'photos de Planetes', 'photos de Planetes', '', 2),
(7, 'photos de la lune', 'photos de la lune', 'photos de la lune', 'photos de la lune', 2),
(8, 'Photos à télécharger', 'Photos à t&eacute;l&eacute;charger', 'Photos à t&eacute;l&eacute;charger', 'Photos à t&eacute;l&eacute;charger', 3),
(9, 'Videos à télécharger', 'Videos à t&eacute;l&eacute;charger', 'Videos à t&eacute;l&eacute;charger', 'Videos à t&eacute;l&eacute;charger', 3),
(10, 'Exoplanetes', 'Exoplanetes', 'Exoplanetes', '', 8),
(11, 'Nébuleuses', 'Nébuleuses', 'Nébuleuses', 'Nébuleuses', 8),
(12, 'Naissance de la lune', 'Naissance de la lune','', '', 9),
(13, 'la Lune', 'la Lune', 'la Lune', 'la Lune', 9),
(14, 'Acces Privé', '', '', '', 1);

