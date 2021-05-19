<?php
//on démarre notre session
session_start ();
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
	  $login=$_POST['login'];
	  $pwd=$_POST['pwd'];

		//on crypte le mot de passe dans le fonction js test_vide(), entre compte.html et auth.php
		$pwd_crypte=md5($pwd);

/*	//le mot de passe à été crypté entre compte.html et auth.php
		$pwd_crypte=$pwd;
		echo "$pwd_crypte";
*/
		/*if (empty($login) || empty($pwd)) {
			    echo '<div class="msgerror">Les données saisies sont incorrectes. Veuillez cliquer <a href="compte.html">ici</a> pour remplir les champs à nouveau.</div>';
			}
			else {*/ //n'est pas demandé et est fait dans la fonction JavaScript

			if (($login=="pakpake") && ($pwd_crypte=="f71dbe52628a3f83a77ab494817525c6")) {
				// on enregistre les paramètres de notre visiteur comme variables de session ($login1 et $pwd_crypte1)
				$_SESSION['login1'] = $_POST['login'];
				$_SESSION['pwd_crypte1'] = $pwd_crypte;

					echo '<div class="parent">'; //contient les 2 blocs (menu + paragraphe de droite)
						//menu vertical
						echo '<div class="child1">
										<div id="navig">
											<ul>
												<li><a href="compte-perso.php" title="Mes Préférences" >Mes Préférences</a></li>
												<li><a href="depot.php" title="Depot" >Dépot de fichiers</a></li>
												<li><a href="telechargement-membre.php" title="Téléchargements Des Membres" >Téléchargements</a></li>
												<li><a href="logout.php" title="Logout" >Déconnexion</a></li>
											</ul>
										</div>
									</div>';
						echo '<div class="child2">'; //paragraphe de droite
							echo '<p class="pgdecal">'; //décaler et aligner les 2 phrases avant la liste
								echo "Bienvenue $login dans votre espace privé.";
								echo '<br/><br/>
												Vous pouvez accéder aux rubriques réservées aux membres :
											<br/>';
							echo '</p>
											<ul>
												<li><a href="compte-perso.php" title="Mes Préférences">Mes Préférences</a><br/>Cette rubrique vous permet de modifier votre login et votre mot de passe</li>
												<li><a href="depot.php" title="Depot">Dépot de fichers</a><br/>Cette rubrique vous permet de déposer des fichiers ou des videos.</li>
												<li><a href="telechargement-membre.php" title="Téléchargements">Téléchargements</a><br/>Cette rubrique vous permet de télécharger des fichiers ou des videos.</li>
												<li><a href="compte.html" title="Déconnexion">Déconnexion</a><br/>	Cette option vous permet de vous déconnecter.</li>
											</ul>';
						echo '</div>';
					echo '</div>';
					} else {
						// login et/ou mdp incorrects
						echo '<div class="msgerror"> INCORRECT, clique <a href="compte.html">ici</a> pour réessayer !</div>';
					}
			//} correspond a l'acolade de test des champs vides
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
