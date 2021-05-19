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
	<script type="text/javascript" src="script.js"></script>
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
                  <li id="actif"><a href="compte-perso.php" title="Mes Préférences" >Mes Préférences</a></li>
                  <li><a href="depot.php" title="Depot" >Dépot de fichiers</a></li>
                  <li><a href="telechargement-membre.php" title="Téléchargements Des Membres" >Téléchargements</a></li>
                  <li><a href="logout.php" title="Logout" >Déconnexion</a></li>
                </ul>
              </div>
            </div>'; //fin du bloc de gauche
      echo '<div class="pdedroite">'; //paragraphe de droite
        echo '<p class="mespref">Mes Préférences</p>';
        echo 'Si vous souhaitez changer votre mot de passe,veuillez saisir l\'ancien puis le nouveau';

        echo '<form method="post" action="email.php" name="js1">';
        echo '<table>';
          echo '<tr>';
            echo '<td><label for="ancien_pwd">Ancien mot de passe : </label></td>';
            echo '<td><input type="password" id="ancien_pwd" name="old_pwd" value="" /></td>';
          echo '</tr>';
          echo '<tr>';
            echo '<td><label for="new_pwd">Nouveau mot de passe : </label></td>';
            echo '<td><input type="password" id="new_pwd" name="new_pass" value="" /></td>';
          echo '</tr>';
          echo '<tr>';
            echo '<td><label for="new_pwd1">Confirmer votre nouveau mot de passe :</label></td>';
            echo '<td><input type="password" id="new_pwd1" name="new_pass1" value="" /></td>';
          echo '</tr>';
          echo '<tr>';
            echo '<td>&nbsp;</td>';
            echo '<td>&nbsp;</td>';
          echo '</tr>';
          echo '<tr>';
            echo '<td>&nbsp;</td>';
            echo '<td>&nbsp;</td>';
          echo '</tr>';
          echo '<tr>';
            echo '<td>&nbsp;</td>';
            echo '<td>';
          echo '<input name="reset" type="reset" value="Effacer" />
                <input name="confirm" type="submit" value="Valider" onclick="old_new_pwd()"/>';
          echo '</td>';
          echo '</tr>';
        echo '</table>';
        echo '</form>';
      echo '</div>'; //fin du bloc de droite
    echo '</div>'; //fin du div parent
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
