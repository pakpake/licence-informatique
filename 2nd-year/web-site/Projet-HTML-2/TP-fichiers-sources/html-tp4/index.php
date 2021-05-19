<?php
	// Active tout les warning. Utile en phase de d�veloppement
	// En phase de production, remplacer E_ALL par 0
	error_reporting(E_ALL);

	// Inclus le fichier contenant les fonctions personnalis�es
	include_once 'fonctions.inc.php';

	// Fonction de connexion � la base de donn�es
	$con = connexion_DB();

	// D�finit l'Id de la page d'accueil (1 dans cet exemple)
	// Pensez � le modifier si ce n'est pas le cas chez vous.
	$id_page_accueil = 1;

	// R�cup�re l'id de la page courante pass�e par l'URL
	// Si non d�fini, on consid�re que la page est la page d'accueil
	if (isset($_GET['id_page'])) {
		$_ENV['id_page'] = intval($_GET['id_page']);
	} else {
		$_ENV['id_page'] = $id_page_accueil;
	}

	// Extrait les informations correspondantes � la page en cours de la DB
	extraction_infos_DB($con);
?>

<!DOCTYPE html>
<html lang="fr">
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=utf-8"/>
		<!-- Ins�re les mots-cl�s extraits de la DB dans les meta -->
		<META NAME="keywords" lang="fr" CONTENT="<?php echo $_ENV['mots_cles']; ?>">
		<!-- Ins�re la description extraite de la DB dans les meta -->
		<META NAME="Description" CONTENT="<?php echo $_ENV['description']; ?>">
		<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
		<!-- Ins�re le titre extrait de la DB dans la balise correspondante -->
		<title><?php echo $_ENV['titre']; ?></title>

		<!--  TODO style css du site -->
		<link rel="stylesheet" type="text/css" href="css/style.css"/>
		<!-- TODO style css pour le menu -->


		<!--  TODO javascript pour menu dynamique -->


		<!--TODO initialisation du menu -->



		<!-- Pour le menu du haut dynamique -->
		<link rel="stylesheet" type="text/css" href="menu/ddsmoothmenu.css"/>
		<script type="text/javascript" src="menu/jquery.min.js"></script>
		<script type="text/javascript" src="menu/ddsmoothmenu.js"></script>
		<script type="text/javascript" src="js/script.js"></script>
		<!-- Pour le menu de droite -->

	</head>

	<body>
		<header id="hd1">
			<div id="banniere">
				<!--<img src="images/banniere.jpg" alt="image Astronomie" title="L'astronomie de A à Z"/>-->
			</div>
			<!-- affichage du menu horyzontal -->
			<div id="smoothmenu1" class="ddsmoothmenu">
				<ul>
					<li><a href="index.php?id_page=1">Accueil</a></li>
					<li><a href="#">Photos</a>
						<ul>
					  	<li><a href="index.php?id_page=6">Planetes</a></li>
					  	<li><a href="index.php?id_page=7">Satellites</a></li>
						</ul>
					</li>

					<li><a href="#">Vid&eacute;os</a>
						<ul>
							<li><a href="index.php?id_page=8">Planetes</a></li>
					  	<li><a href="index.php?id_page=9">Satellites</a></li>
						</ul>
					</li>
					<li><a href="index.php?id_page=4">Contact</a></li>
					<li><a href="index.php?id_page=5">Plan de Site</a></li>
					</li>
					<li><a href="admin.php?id_page=14">Acces Priv&eacute;</a></li>
				</ul>
				<br style="clear: left" />
			</div>
		</header>

		<?php
			echo '<nav id="fil_ariane">';
					//TODO Affiche le fil d'ariane
					echo affiche_fil_ariane($con, $_ENV['id_page']);
			echo '</nav>';
 		?>
	<div id="main">

	<aside>
		<h1>Cat&eacute;gories</h1>
		<ul>
			<li><a href="http://www.futura-sciences.com/fr/sciences/actualites/univers/">Actualit&eacute;</a></li>
			<li><a href="http://www.blog-nouvelles-technologies.fr/archives/category/developpement/">Mat&eacute;riel</a></li>
		</ul>
	<!-- </aside>
	<aside> -->
		<h1>Liens</h1>
		<ul>
			<li><a href="http://www.webastro.net/">Webastro </a></li>
			<li><a href="http://www.planete-astronomie.com/">Planete-Astronomie</a></li>
		 </ul>
	</aside>



		<article id="article">
			<header>
				 <?php  echo $_ENV['description'];?>
			</header>
			<section>
				<?php
					// Affiche le contenu de la page en cours
					echo $_ENV['contenu'];
				?>
			</section>
			<!-- <footer>
			</footer> -->
		</article>
		<!-- <div id="aside"> -->

			<!-- </div> -->
		</div>

		<footer id="ft1">
			<?php //TODO inclure le pied de page
				include_once("pied_page.html");
			?>
		</footer>

			<?php //TODO deconnexion bdd
				mysqli_close($con);
			 ?>
	</body>
</html>
