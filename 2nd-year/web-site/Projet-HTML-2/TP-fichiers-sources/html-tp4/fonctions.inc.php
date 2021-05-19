<?php
include "constante.inc.php";
/**
 * Fonctions de connexion a la base de donn�e
 * return instance de connexion � la bdd
 */
function connexion_DB() {

//TODO D�claration des param�tres de connexion
	$host = CONST_HOST;
	$user = CONST_USER;
	$bdd = CONST_BDD;
	$passwd  = CONST_PWD;
// Connexion au serveur
	$connexion=mysqli_connect($host, $user,$passwd) or die("erreur de connexion au serveur");
	mysqli_select_db($connexion,CONST_BDD) or die("erreur de connexion a la base de donnees");

	return $connexion;
}

// --------------------------------------------------------------------------------------------------------------------------
/**
 * Fonctions de d�connexion de la base de donn�e
 */
function deconnexion_DB($connexion) {
	//TODO fermer la connexion mysql
	mysqli_close($con);
}

// --------------------------------------------------------------------------------------------------------------------------

/**
 *
 * Fonction de requetage sql
 * @param $strSQL -requete sql
 * return les enregistrements correspondant au r�sultat de la requete
 */
function requete_SQL($connexion,$strSQL) {
	$result = mysqli_query($connexion,$strSQL);
	if (!$result) {
		$message  = 'Erreur SQL : ' . mysqli_error() . "<br>\n";
		$message .= 'SQL string : ' . $strSQL . "<br>\n";
		$message .= "Merci d'envoyer ce message au webmaster";
		die($message);
	}
	return $result;
}
// --------------------------------------------------------------------------------------------------------------------------

/**
 *
 * Fonctions qui r�cupere les informations en bdd et l'id de la page pass�e en paramtre
 * Les variables sont stock�es en variable d'environnement
 */
function extraction_infos_DB($con) {
	$strSQL = 'SELECT * FROM `pages` WHERE `Id_page` = '.$_ENV['id_page'];
	$resultat = requete_SQL($con,$strSQL);
	$tabl_result = mysqli_fetch_array($resultat);
	$_ENV['mots_cles'] = $tabl_result['Mots_cles'];
	$_ENV['description'] = $tabl_result['Description'];
	$_ENV['titre'] = $tabl_result['Titre'];
	$_ENV['contenu'] = $tabl_result['Contenu'];
	$_ENV['id_parent'] = $tabl_result['Id_parent'];
}

//---------------------------------------------------------------------------------------------------------------------------

/**
 *
 * Fonction d'affichage du fil d'ariane
 * @param $idpage id de la page en cours -> $idpage
 * @param $con connexion � l'instance de base de donn�es
 * return chemin complet -> $chemin_complet
 */
function affiche_fil_ariane($con,$idpage) {
	// on d�finit la variable pour �viter le warning
	$chemin_complet = "";
	// Si l'id de la page en cours est diff�rent de 0
	// (0 = page parente de la page racine = inexistante)
	if ($idpage != 0) {
		// on r�cup�re les informations de la page en cours dans la DB
		$strSQL = 'SELECT `Titre`, `Id_parent` FROM `pages` WHERE `Id_page` = '.$idpage;
		$resultat = requete_SQL($con,$strSQL);
		$tabl_result = mysqli_fetch_array($resultat);

		$titrepage = $tabl_result['Titre'];
		$idparent = $tabl_result['Id_parent'];

		// cr�ation du lien vers la page en cours

		//$chemin_page_en_cours = ' -> <a href="index.php?id_page='.$idpage.'">'.$titrepage.'</a>';
		$chemin_page_en_cours = ' <img src="images/right.gif"><a href="index.php?id_page='.$idpage.'">'.$titrepage.'</a>';
		// Concat�nation du lien de la page N-1 et
		// du lien de la page en cours
		$chemin_complet = affiche_fil_ariane($con,$idparent).$chemin_page_en_cours;
	}
	// renvoie le chemin complet
	return $chemin_complet;
}
?>
