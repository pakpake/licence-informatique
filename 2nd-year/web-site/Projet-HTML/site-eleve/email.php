<?php
session_start();

if (isset($_SESSION['login1']) && isset($_SESSION['pwd_crypte1'])) {
	//return true; // si "return true" est actif quand on se connecte ça ne marche pas.
} else {
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
  $to ='prof@prof.mobi';
  $subject ='Changement de mot de passe';
  $headers ='From:contact@.fr';

	$log=$_SESSION['login1'];
	$paswd=$_SESSION['pwd_crypte1'];

	$message = "";
	$message .= "<html> \n";
	$message .= "<head> \n";
	$message .= "<title> Votre mot de passe a été modifé ! </title> \n";
	$message .= "</head> \n";
	$message .= "<body> \n";
	$message .= "Votre mot de passe a été changé ! \n";
	$message .= "Voici vos informations : \n";
	$message .= "\t login = $log \n";
	$message .= "\t password = $new_pwd";
	$message .= "</body>";
	$message .= "</html>";

  //récupération des variables de l'ancien et du nouveau mot de passe
	//récupération du name et non du id
  $old_pwd=md5($_POST['old_pwd']);
  $new_pwd=$_POST['new_pass'];
  $new_pwd1=$_POST['new_pass1'];

	//vérification de l'andien et du nouveau mdp
	if ($old_pwd != $paswd ) {
		echo '<div class="msgerror">L\'ancien mot de passe n\'est pas le bon. Veuillez cliquer <a href="compte-perso.php">ici</a> pour remplir les champs à nouveau.</div>';
	} else {
		  if (empty($old_pwd) || empty($new_pwd) || empty($new_pwd1)) {
		    echo '<div class="msgerror">Les champs sont vides. Veuillez cliquer <a href="compte-perso.php">ici</a> pour remplir les champs à nouveau.</div>';
		  } else {
		    if (mail($to,$subject,$message,$headers)) {
		      echo "Message envoyé";
		    } else {
		      echo "Message non-envoyé.";
		    }
		  }

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
