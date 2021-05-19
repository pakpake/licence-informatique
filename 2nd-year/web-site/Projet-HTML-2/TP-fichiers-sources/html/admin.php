<?php
// Active tout les warning. Utile en phase de d�veloppement
// En phase de production, remplacer E_ALL par 0
error_reporting(E_ALL);

// Inclus le fichier contenant les fonctions personalis�es
include_once 'fonctions.inc.php';

// Fonction de connexion � la base de donn�es
$con=connexion_DB();

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
<html>
<head>
	<!-- Ins�re les mots-cl�s extraits de la DB dans les meta -->
	<META NAME="keywords" lang="fr" CONTENT="<?php echo $_ENV['mots_cles']; ?>">
		<!-- Ins�re la description extraite de la DB dans les meta -->
		<META NAME="Description" CONTENT="<?php echo $_ENV['description']; ?>">
			<meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
			<!-- Ins�re le titre extrait de la DB dans la balise correspondante -->
			<title><?php echo $_ENV['titre']; ?></title>

			<!--  style css du site -->
			<link rel="stylesheet" type="text/css" href="css/styles.css">

			<!-- style css pour le menu -->
			<link rel="stylesheet" type="text/css" href="menu/ddsmoothmenu.css" />
			<link rel="stylesheet" type="text/css" href="menu/ddsmoothmenu-v.css" />

			<!--  javascript pour menu dynamique -->
			<script type="text/javascript" src="menu/jquery.min.js"></script>
			<script type="text/javascript" src="menu/ddsmoothmenu.js"></script>

			<!-- css et js pour la galerie d'images -->

			<!--  javascript et css easyui -->
			<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.9.0/themes/default/easyui.css">
			<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.9.0/themes/icon.css">
			<link rel="stylesheet" type="text/css" href="js/jquery-easyui-1.9.0/demo/demo.css">
			<script type="text/javascript" src="js/jquery-easyui-1.9.0/jquery.min.js"></script>
			<script type="text/javascript" src="js/jquery-easyui-1.9.0/jquery.easyui.min.js"></script>


			<!-- ccs et js pour ckeditor-->


			<script src="js/ckeditor_4.13.1_standard/ckeditor/ckeditor.js"></script>
			<script src="js/ckeditor_4.13.1_standard/ckeditor/adapters/jquery.js"></script>
			<link rel="stylesheet" type="text/css" href="js/ckeditor_4.13.1_standard/ckeditor/samples/old/sample.css">

			<!-- <script type="text/javascript">
				$(document).ready(function(){
					$("#ckeditortext").ckeditor();
				});
			</script> -->


			<!--initialisation des menus -->
			<script language="javascript" type="text/javascript">

			ddsmoothmenu.init({
				mainmenuid: "smoothmenu1", //menu DIV id
				orientation: 'h', //Horizontal or vertical menu: Set to "h" or "v"
				classname: 'ddsmoothmenu', //class added to menu's outer DIV
				//customtheme: ["#1c5a80", "#18374a"],
				contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
			})

			ddsmoothmenu.init({
				mainmenuid: "smoothmenu2", //Menu DIV id
				orientation: 'v', //Horizontal or vertical menu: Set to "h" or "v"
				classname: 'ddsmoothmenu-v', //class added to menu's outer DIV
				//customtheme: ["#804000", "#482400"],
				contentsource: "markup" //"markup" or ["container_id", "path_to_menu_file"]
			})

			</script>


		</head>
		<body>
			<header id="hd1">
				<img src="images/banniere.jpg" alt="image Astronomie" title="L'astronomie de A � Z" width="100%"/>
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
					<li><a href="index.php?id_page=4">Contact</a>

					</li>
					<li><a href="index.php?id_page=5">Plan de Site</a></li>
				</li>
				<li><a href="/admin.php?id_page=14">Acces Priv&eacute;</a></li>
			</ul>
			<br style="clear: left" />
		</div>
	</header>
	<nav id="fil_ariane">
		<?php
		// Affichage du fil d'ariane
		echo affiche_fil_ariane($con,$_ENV['id_page']);
		?>
	</nav>
	<div id="main">
		<article>
			<header>

			</header>
			<section>
				<!--Jquery jeasyui -->
				<script language="javascript" type="text/javascript">
				// POUR LE DOUBLE CLIC
				// function open_infos()
				// {
				// 	// window.open('~/Bureau/form.html','formulaire','menubar=no, scrollbars=no, top=100, left=100, width=300, height=200');
				// 	// dialog('form.html','formulaire','menubar=no, scrollbars=no, top=100, left=100, width=300, height=200
				// 	var row = $('#tt').datagrid('getSelected');
				// 	$("#dialog").dialog({title:"Consultaion de la page "+row.Page});
				// }
				//
				//
				// //pas la bonne
				// function open_modal() {
				// 	window.showModalDialog("form.html",'dialog','dialogWidth=300, dialogHeight=200')
				//
				// }
				//
				// //ni celle là
				// function getSelected() {
				// 	var row = $('#tt').datagrid('getSelected');
				// 	if (row){
				// 		alert('Page: '+row.Page+"\nDescription: "+row.Description);
				//
				// 	}
				// }
				//FIN DU DOUBLE CLIC

				$(function(){
					$('#tt').datagrid({
						onDbClickCell:function(field,row) {
							editPage();
						}
					});
				});

				function editPage(){
					var row = $('#tt').datagrid('getSelected');
					if (row) {
						$('#dir').dialog('open').dialog('setTitle','Consultaion de la page' + row.Pages);
						$('#odb').form('load',row);
					}
				}

				function closeWindow(){
					$('#dir').dialog('close'); //close the dialog box
				}
				</script>

				<!-- POUR LE DOUBLE CLIC
				<a target="_blank" href="form.html" onclick="window.open(this.href, 'Titre','target=_parent, height=150, width=350, top=200, left=200, color=black, toolbar=no, menubar=no, location=no, resizable=no, scrollbars=no, status=no'); return false">LIEN</a>
			 -->

				<div style="margin:20px 0;"></div>

				<table id="tt" class="easyui-datagrid" title="Liste des pages" style="width:700px;height:250px"
				data-options="singleSelect:true,collapsible:true,url:'datagrid_data1.json',method:'get'">
				<thead>
					<tr>
						<th data-options="field:'Page',width:160">Page</th>
						<th data-options="field:'Description',width:450	">Description</th>
						<th data-options="field:'Action',width:83,align:'right'">Action</th>
					</tr>
				</thead>
			</table>

			<!-- double clic -->
			<div id="dir"class="easyui-dialog" style="width:500px;height:490px padding:10px 20px" closed="true">
				<form id="odb" method="post">
					<table>
						<tr>
							<td class="dv-label">Page : </td>
							<td> <input type="text" name="Page" class="easyui-validatebox"> </td>
						</tr>
						<tr>
							<td class="dv-label">Description : </td>
							<td> <textarea name="Description" rows="5" cols="30" class="easyui-validatebox"></textarea> </td>
						</tr>
						<tr>
							<td>&nbsp;</td>
						</tr>
					</table>
					<table>
						<tr>
							<td> <input type="button" value="Fermer" onclick="closeWindow()"> </td>
						</tr>
					</table>
				</form>

			</div>






			<div style="margin:20px 0;"></div>

			<div id="win" class="easyui-window" title="Consultation de la page" closed="true" style="width:600px;height:400px"
			data-options="iconCls:'icon-save',modal:true">
			<p><label for="Page"> Page <input type="text" name="Page"></label></p>
			<p><label for="Description"> Description <input type="text" name="Description" id="Description" style="width:350px"></label></p>

			<button onclick="$('#win').window('close')">Fermer</button>

			</div>

			<script type="text/javascript">
			  $(function(){
			    $('datagrid').datagrid({
			      url:'datagrid_data1.json',
			      columns:[[
			        {field:'Page',title:'Page',width:200},
			        {field:'Description',title:'Description',width:500},
			        {field:'Action',title:'Action',width:200}
			      ]]
			      onDbClickRow: function(index,row)
			      {
			        $('#win').window({
			          closed:false,
			          title:"Consultation de la page " + row.Page,
			        });
			        $("#Page").val(row.Page),
			        $("#Description").val(row.Description)
			      }
			    });
			  });
			</script>



			<!-- <script src="js/ckeditor_4.13.1_standard/ckeditor/ckeditor.js"></script> -->
			<textarea name="editor1" id="editor1"><?php echo $_ENV['contenu'];    ?></textarea>
			<script>
			    CKEDITOR.replace('editor1');
			</script>

			<a href="valid.php"><input type="button" name="Valider" value="Valider"></a>





			<?php
			if($_ENV['id_page']!=14){
				extraction_infos_DB($con);

				//Formulaire textarea --ckeditor--


			}
			?>
		</div>
	</div>
</section>
<footer>
</footer>
</article>
<footer id="ft1">
	<?php include("pied_page.html"); ?>
</footer>
<?php deconnexion_DB($con); ?>
