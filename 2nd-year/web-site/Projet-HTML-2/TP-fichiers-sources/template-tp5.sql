-- phpMyAdmin SQL Dump
-- version 4.6.6deb5
-- https://www.phpmyadmin.net/
--
-- Client :  localhost:3306
-- Généré le :  Jeu 09 Janvier 2020 à 08:56
-- Version du serveur :  5.7.28-0ubuntu0.18.04.4
-- Version de PHP :  7.2.24-0ubuntu0.18.04.1

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de données :  `projet`
--

-- --------------------------------------------------------

--
-- Structure de la table `pages`
--

CREATE TABLE `pages` (
  `Id_page` int(11) NOT NULL,
  `Titre` varchar(255) NOT NULL,
  `Mots_cles` varchar(255) NOT NULL,
  `Description` varchar(255) NOT NULL,
  `Contenu` text NOT NULL,
  `Id_parent` int(11) NOT NULL DEFAULT '1'
) ENGINE=InnoDB DEFAULT CHARSET=latin1;

--
-- Contenu de la table `pages`
--

INSERT INTO `pages` (`Id_page`, `Titre`, `Mots_cles`, `Description`, `Contenu`, `Id_parent`) VALUES
(1, 'Accueil', 'Accueil', 'Accueil', '<p>Bienvenue sur le site perso !</p><br/><br/><br/><p>Au programme, des  photos, des t&eacute;l&eacute;chargements ainsi qu\'un formulaire si vous voulez me contacter.</p><p>Bon courage &agrave; toutes et &agrave; tous!<p><br/>', 0),
(2, 'Photos', 'Photos', 'Photos Astronomie', 'photos des planetes et du systeme solaire', 1),
(3, 'Vid&eacute;os', 'Vid&eacute;os', 'Vid&eacute;os', 'Vid&eacute;os', 1),
(4, 'Contact', 'Contact', 'Veuillez remplir le formulaire de contact ci-dessous :', '<form method=\"get\" action=\"reception.php\">\r\n<table>\r\n	<tbody>\r\n		<tr>\r\n			<td><label for=\"lastName\">Votre Nom :</label></td>\r\n			<td><input id=\"lastName\" name=\"nom\" type=\"text\" value=\"\" /></td>\r\n		</tr>\r\n		<tr>\r\n			<td><label for=\"firstName\">Votre Pr&eacute;nom :</label></td>\r\n			<td><input id=\"firstName\" name=\"prenom\" type=\"text\" value=\"\" /></td>\r\n		</tr>\r\n		<tr>\r\n			<td><label for=\"email\">Votre Email :</label></td>\r\n			<td><input id=\"email\" name=\"email\" type=\"text\" value=\"\" /></td>\r\n		</tr>\r\n		<tr>\r\n			<td colspan=\"2\"><textarea cols=\"60\" name=\"com\" rows=\"6\"></textarea></td>\r\n		</tr>\r\n		<tr>\r\n			<td><input name=\"reset\" type=\"reset\" value=\"R&eacute;tablir\" /> <input name=\"submit\" type=\"submit\" value=\"Envoyer\" /></td>\r\n		</tr>\r\n	</tbody>\r\n</table>\r\n</form>\r\n<br/>', 1),
(5, 'Plan du site', 'Plan du site', 'Plan du site', '<br/>\r\n<ul id=\"blue\">\r\n<li><a href=\"index.php\">Accueil<br/></a><br/>page d\'accueil du site</li><br/>\r\n<li><a href=\"index.php?id_page=6\">Photos des plan&egrave;tes du syst&egrave;me solaire<br/></a><br/>album photo du syteme solaire</li><br/>\r\n<li><a href=\"index.php?id_page=7\">Photos des sat&eacute;lites<br/></a><br/>photos des sat&eacuteslites de notre syst&egrave;me solaire</li><br/>\r\n<li><a href=\"index.php?id_page=8\">Videos<br/></a><br/>Videos du systeme solaire, d\'exoplanetes et de comettes</li><br/>\r\n<li><a href=\"admin.php?id_page=14\">Mon Compte<br/></a><br/>Acces a votre compte</li><br/>\r\n<li><a href=\"index.php?id_page=4\">Contact<br/></a><br/>Formulaire de contact</li><br/>\r\n</ul>', 1),
(6, 'photos de Planetes', 'photos de Planetes', 'photos de Planetes', '<head>\r\n	<!-- LIGHTBOX -->\r\n			<script type=\"text/javascript\" src=\"js/jquery-lightbox-0.5/js/jquery.js\"></script>\r\n			<script type=\"text/javascript\" src=\"js/jquery-lightbox-0.5/js/jquery.lightbox-0.5.js\"></script>\r\n			<link rel=\"stylesheet\" type=\"text/css\" href=\"js/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css\" media=\"screen\">\r\n			<script type=\"text/javascript\">\r\n			$(function() {\r\n					$(\'#gallery a\').lightBox();\r\n			});\r\n			</script>\r\n</head>\r\n<div id=\"gallery\">\r\n<table>\r\n  <tr>\r\n    <td><a href=\"images/planetes/jupiter2.jpg\"><img src=\"images/planetes/jupiter2.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/jupiter3.jpg\"><img src=\"images/planetes/jupiter3.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/jupiter.jpg\"><img src=\"images/planetes/jupiter.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/mars.jpg\"><img src=\"images/planetes/mars.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/mercure2.jpg\"><img src=\"images/planetes/mercure2.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n  </tr>\r\n  <tr>\r\n    <td><a href=\"images/planetes/mercure3.jpg\"><img src=\"images/planetes/mercure3.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/mercure.jpg\"><img src=\"images/planetes/mercure.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/neptune2.jpg\"><img src=\"images/planetes/neptune2.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/neptune.jpg\"><img src=\"images/planetes/neptune.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/pluton3.jpg\"><img src=\"images/planetes/pluton3.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n  </tr>\r\n  <tr>\r\n    <td><a href=\"images/planetes/pluton.jpg\"><img src=\"images/planetes/pluton.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/saturne2.jpg\"><img src=\"images/planetes/saturne2.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/saturne.jpg\"><img src=\"images/planetes/saturne.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/terre.jpeg\"><img src=\"images/planetes/terre.jpeg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/uranus3.jpg\"><img src=\"images/planetes/uranus3.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n  </tr>\r\n  <tr>\r\n    <td><a href=\"images/planetes/uranus.gif\"><img src=\"images/planetes/uranus.gif\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/venus2.jpg\"><img src=\"images/planetes/venus2.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/venus3.jpg\"><img src=\"images/planetes/venus3.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/planetes/venus.jpg\"><img src=\"images/planetes/venus.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n  </tr>\r\n</table>\r\n</div>', 2),
(7, 'photos de satellites', 'photos de satellites', 'photos de satellites de notre systeme solaire', '<head>\r\n<!-- LIGHTBOX -->\r\n<script type=\"text/javascript\" src=\"js/jquery-lightbox-0.5/js/jquery.js\"></script>\r\n<script type=\"text/javascript\" src=\"js/jquery-lightbox-0.5/js/jquery.lightbox-0.5.js\"></script>\r\n<link rel=\"stylesheet\" type=\"text/css\" href=\"js/jquery-lightbox-0.5/css/jquery.lightbox-0.5.css\" media=\"screen\">\r\n<script type=\"text/javascript\">\r\n$(function() {\r\n		$(\'#gallery a\').lightBox();\r\n});\r\n</script>\r\n</head>\r\n<div id=\"gallery\">\r\n<table>\r\n  <tr>\r\n    <td><a href=\"images/satellites/ariel.jpg\"><img src=\"images/satellites/ariel.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/satellites/callisto.jpg\"><img src=\"images/satellites/callisto.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/satellites/copernicus.jpg\"><img src=\"images/satellites/copernicus.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/satellites/deimos.jpg\"><img src=\"images/satellites/deimos.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/satellites/dione.jpg\"><img src=\"images/satellites/dione.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n  </tr>\r\n  <tr>\r\n    <td><a href=\"images/satellites/encelade.jpg\"><img src=\"images/satellites/encelade.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/satellites/europe.jpg\"><img src=\"images/satellites/europe.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/satellites/ganymede.jpg\"><img src=\"images/satellites/ganymede.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/satellites/io.jpg\"><img src=\"images/satellites/io.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/satellites/japet.jpg\"><img src=\"images/satellites/japet.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n  </tr>\r\n  <tr>\r\n    <td><a href=\"images/satellites/lune.jpg\"><img src=\"images/satellites/lune.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/satellites/mimas.jpg\"><img src=\"images/satellites/mimas.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/satellites/oberon.jpg\"><img src=\"images/satellites/oberon.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/satellites/phobos.jpg\"><img src=\"images/satellites/phobos.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/satellites/phoebe.jpg\"><img src=\"images/satellites/phoebe.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n  </tr>\r\n  <tr>\r\n    <td><a href=\"images/satellites/thetys.jpg\"><img src=\"images/satellites/thetys.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/satellites/titania.jpg\"><img src=\"images/satellites/titania.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n    <td><a href=\"images/satellites/titan.jpg\"><img src=\"images/satellites/titan.jpg\" width=\"72\" height=\"72\" alt=\"\" /></a></td>\r\n  </tr>\r\n</table>\r\n</div>', 2),
(8, 'Vid&eacute;o &agrave; visionner', 'Vid&eacute;o &agrave; visionner', 'What can you see in the December sky? Beautiful pairings of planets and the crescent Moon throughout the month, at sunrise and sunset.', '<head>\n  <link href=\"js/video-js-7.7.3/video-js.min.css\" rel=\"stylesheet\">\n  <script src=\"js/video-js-7.7.3/video.min.js\"></script>\n</head>\n\n<video id=\"example_video_1\" class=\"video-js\" controls preload=\"none\" width=\"640\" height=\"264\" data-setup=\"{}\">\n  <source src=\"https://solarsystem.nasa.gov/system/downloadable_items/3199_JPL-20191201-WHATSUf-0001-180cc.mp4\" type=\"video/mp4\">\n  <p class=\"vjs-no-js\">To view this video please enable JavaScript, and consider upgrading to a web browser that <a href=\"https://videojs.com/html5-video-support/\" target=\"_blank\">supports HTML5 video</a></p>\n</video>\n', 3),
(9, 'Live Vid&eacute;o', 'Live Vid&eacute;o', 'Live Vid&eacute;o de la chaine nasa', '<head>\r\n  <link href=\"js/video-js-7.7.3/video-js.min.css\" rel=\"stylesheet\">\r\n  <script src=\"js/video-js-7.7.3/video.min.js\"></script>\r\n</head>\r\n\r\n<!-- <video id=\"example_video_1\" class=\"video-js\" controls preload=\"none\" width=\"640\" height=\"264\" data-setup=\"{}\">\r\n  <source src=\"https://www.arte.tv/fr/direct/\" type=\"video/mp4\">\r\n  <p class=\"vjs-no-js\">To view this video please enable JavaScript, and consider upgrading to a web browser that <a href=\"https://videojs.com/html5-video-support/\" target=\"_blank\">supports HTML5 video</a></p>\r\n</video>\r\n-->\r\n<iframe width=\"560\" height=\"315\" src=\"https://www.youtube.com/embed/21X5lGlDOfg\" frameborder=\"0\" allow=\"accelerometer; autoplay; encrypted-media; gyroscope; picture-in-picture\" allowfullscreen></iframe>\r\n\r\n', 3),
(10, 'Exoplanetes', 'Exoplanetes', 'Exoplanetes', '', 8),
(11, 'Nébuleuses', 'Nébuleuses', 'Nébuleuses', 'Nébuleuses', 8),
(12, 'Naissance de la lune', 'Naissance de la lune', '', '', 9),
(13, 'la Lune', 'la Lune', 'la Lune', 'la Lune', 9),
(14, 'Acces Priv&eacute;', '', 'Acc&egrave;s priv&eacute;', '', 1);

--
-- Index pour les tables exportées
--

--
-- Index pour la table `pages`
--
ALTER TABLE `pages`
  ADD PRIMARY KEY (`Id_page`);

--
-- AUTO_INCREMENT pour les tables exportées
--

--
-- AUTO_INCREMENT pour la table `pages`
--
ALTER TABLE `pages`
  MODIFY `Id_page` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=15;
/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
