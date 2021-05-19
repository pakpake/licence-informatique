<?php
include "constante.inc.php";
/**
 * Fonctions de connexion a la base de donnée
 * return instance de connexion
 */
function connexion_DB() {

// Déclaration des paramètres de connexion
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
 * Fonctions de déconnexion de la base de donnée
 */
function deconnexion_DB($connexion) {

	mysqli_close($connexion);
}

// --------------------------------------------------------------------------------------------------------------------------

/**
 * 
 * Fonction de requetage sql 
 * @param $strSQL -requete sql
 * @param $con -connexion sql
 * return les enregistrements correspondant au résultat de la requete
 */
function requete_SQL($con,$strSQL) {
	$result = mysqli_query($con,$strSQL);
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
 * Fonctions qui récupere les informations en bdd et l'id de la page passée en paramtre
 * Les variables sont stockées en variable d'environnement
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
 * return chemin complet -> $chemin_complet
 */
function affiche_fil_ariane($con,$idpage) {
	// on définit la variable pour éviter le warning
	$chemin_complet = "";
	// Si l'id de la page en cours est différent de 0 
	// (0 = page parente de la page racine = inexistante)
	if ($idpage != 0) {
		// on récupère les informations de la page en cours dans la DB
		$strSQL = 'SELECT `Titre`, `Id_parent` FROM `pages` WHERE `Id_page` = '.$idpage;
		$resultat = requete_SQL($con,$strSQL);
		$tabl_result = mysqli_fetch_array($resultat);
		
		$titrepage = $tabl_result['Titre'];
		$idparent = $tabl_result['Id_parent'];
		
		// création du lien vers la page en cours
		
		//$chemin_page_en_cours = ' -> <a href="index.php?id_page='.$idpage.'">'.$titrepage.'</a>';
		$chemin_page_en_cours = ' <img src="images/right.gif"><a href="index.php?id_page='.$idpage.'">'.$titrepage.'</a>';
		// Concaténation du lien de la page N-1 et
		// du lien de la page en cours
		$chemin_complet = affiche_fil_ariane($con,$idparent).$chemin_page_en_cours;
	}
	// renvoie le chemin complet
	return $chemin_complet;
}
/**
 * 
 * Fonction selectionnant toutes les pages en BDD
 * @param $con 
 * return les contenu de l'ensemble des pages en bdd
 */
function extraction_all_DB($con) {
	$strSQL = 'SELECT Id_page,titre FROM `pages` where id_page!=14';
	$resultat = requete_SQL($con,$strSQL);
	
	while($data = mysqli_fetch_assoc($resultat)) {

      echo "<a href='admin.php?id_page=".$data['Id_page']."'>".$data['titre']."</a><br/>";
}
}
?>