<?php
//on est pas obligé de mettre la vérification des variables sur cette page php mais c'est une sécurité en plus
session_start ();
//redirection vers la page d'authentification si les variables ne sont pas reconnues
if (!isset($_SESSION['login1']) || !isset($_SESSION['pwd_crypte1'])) {
	header ('location: compte.html');
	return false;
}
?>
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
			<li id="actif"><a href="compte.html" title="Compte" >Mon Compte</a></li>
			<li><a href="carte_ciel.html" title="Carte" >Carte du Ciel</a></li>
			<li><a href="formulaire.html" title="Pour me joindre" >Contact</a></li>
			<li><a href="plan.html" title="plan du site" >Plan du site</a></li>
		</ul>
	</div>

	<br/>
 <?php
	 //code d'upload
	 if(!empty($_FILES['mon_fichier'])) {
		 $dossier = 'upload/';
		 $fichier = basename($_FILES['mon_fichier']['name']);
		 $taille=$_FILES['mon_fichier']['size'];
		 $taille_max=$_POST['MAX_FILE_SIZE'];
		 if ($taille>=$taille_max) {
		 	echo "Fichier trop volumineux";
		} else { if(move_uploaded_file($_FILES['mon_fichier']['tmp_name'], $dossier . $fichier)) //Si la fonction renvoie TRUE, c'est que ça a fonctionné...
			 {
				 echo'Upload effectué avec succès !';
			 } else //Sinon (la fonction renvoie FALSE).
			 {
				 echo'Echec de l\'upload !';
			 }
	 	}
 	}
  ?>


  <div id="footer">
    <p class="validator">
      <a href="http://validator.w3.org/check?uri=referer">
      <img src="http://www.w3.org/Icons/valid-xhtml10-blue" alt="Valid XHTML 1.0 Transitional" height="31" width="88" /></a>
    </p>
    &copy; pakpake  - 2019 <br/>  - Tombouctou - 
  </div>
</body>
</html>
