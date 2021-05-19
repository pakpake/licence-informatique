<?php
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
  echo '<div class="parent">'; //contient les 2 blocs (menu + paragraphe de droite)
    //menu vertical
    echo '<div class="child1">
            <div id="navig">
              <ul>
                <li><a href="compte-perso.php" title="Mes Préférences" >Mes Préférences</a></li>
                <li id="actif"><a href="depot.php" title="Depot" >Dépot de fichiers</a></li>
                <li><a href="telechargement-membre.php" title="Téléchargements Des Membres" >Téléchargements</a></li>
                <li><a href="logout.php" title="Logout" >Déconnexion</a></li>
              </ul>
            </div>
          </div>'; //fin du bloc de gauche
    echo '<div class="pdedroite">'; //paragraphe de droite
      echo '<p class="mespref">Dépot de fichiers</p>';
      echo '<p>Veuillez sélectionner le fichier à uploader:</p>';
    	echo '<div style="">';//debut du code d'upload
        echo '<form action="upload.php" method="post" enctype="multipart/form-data">';
          echo '<label for="file">Fichier :</label>';
          echo '<input id="file" type="file" name="mon_fichier"/>'; //type="text" pour afficher la case du fichier sélectionner à gauche et non a droite
					//limiter la taille du fichier a 10ko
						echo '<input type="hidden" name="MAX_FILE_SIZE" value="100000"/>';
          echo '<input type="submit" name="submit" value="Envoyer le fichier" />';
        echo '</form>';
      echo '</div>';  // fin du code d'upload
    echo '</div>'; //fin du bloc de droite
  echo '</div>'; //fin de div parent

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
