<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml" xml:lang="fr" lang="fr">

<head>
	<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
	<title>Photos et téléchargements</title>
	<link rel="stylesheet" type="text/css" href="style/style.css" />
</head>

<body>
		<!--Espace réserver à l'entête -->
	<div class="banniere">
		<!--banniere-->
	</div>
		<!-- Espace réserver au menu -->
		<div id="menu_top">
			<ul>
  			<li><a href="index.html" title="Bienvenue" >Accueil</a></li>
  			<li><a href="album.html" title="PHOTOS" >Photos</a></li>
  			<li><a href="telechargement.html" title="Telechargement" >Telechargements</a></li>
  			<li><a href="video.html" title="Video" >Videos</a></li>
  			<li><a href="compte.html" title="Compte" >Mon Compte</a></li>
  			<li><a href="carte_ciel.html" title="Carte" >Carte du Ciel</a></li>
  			<li id="actif"><a href="formulaire.html" title="Pour me joindre" >Contact</a></li>
  			<li><a href="plan.html" title="plan du site" >Plan du site</a></li>
			</ul>
		</div>

	<br/>

<?php
  $nom=$_GET['nom'];
  $prenom=$_GET['prenom'];
  $email=$_GET['email'];
  $com=$_GET['com'];

  if (empty($nom) || empty($prenom) || empty($email)) {
    echo '<div class="msgerror">Les données saisies sont incorrectes. Veuillez cliquer <a href="formulaire.html">ici</a> pour remplir les champs à nouveau.</div>';
  }
  else {
      echo '<div class="rec_decal">';
      echo "nom=$nom <br/> prenom=$prenom <br/> email=$email <br/> message=$com <br/>";
      echo '</div>';
  }

?>

<div id="footer">
  <p class="validator">
    <a href="http://validator.w3.org/check?uri=referer">
      <img src="http://www.w3.org/Icons/valid-xhtml10-blue" alt="Valid XHTML 1.0 Transitional" height="31" width="88" /></a>
  </p>
  &copy;   - 2019 <br/> Tombouctou -  - 
</div>
</body>
</html>
