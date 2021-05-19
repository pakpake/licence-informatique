<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta charset="utf-8">
		<title>Contact</title>
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
	</head>
<?php

  $to ='prof@prof.mobi';
	$subject = 'Informations saisies';
  $headers ='From:contact@.fr';

	$message = "";
	$message .= "<html> \n";
	$message .= "<head> \n";
	$message .= "<title> Voici vos informations </title> \n";
	$message .= "</head> \n";
	$message .= "<body> \n";
	$message .= "Vos informations \n";
	$message .= "\t Nom = $nom \n";
	$message .= "\t Prénom = $prenom \n";
	$message .= "\t Email = $email \n";
	$message .= "\t Commentaire = $com \n";
	$message .= "</body>";
	$message .= "</html>";

// récupération des variables nom prenom email et commentaire
$nom=$_GET['nom'];
$prenom=$_GET['prenom'];
$email=$_GET['email'];
$com=$_GET['com'];

//vérification que les champs ne sont pas vides et envoie le message
if (empty($nom) || empty($prenom) || empty($email)) {
	 echo '<div class="msgerror">Les champs sont vides. Veuillez cliquer <a href="index.php?id_page=4"">ICI</a> pour remplir les champs à nouveau.</div>';
} else if (mail($to,$subject,$message,$headers)) {
	echo "Message envoyé";
} else echo "Message non-envoyé";

?>
</html>
