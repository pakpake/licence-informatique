# script awk pour filtrer les processus avec Etat endormis (STAT S SPLEEPING)
# @author: pakpake
# appel de commande avec : ps aux | awk -f nomduscirpt.awk
BEGIN{
	FS = " " #on specifie que le separateur de champs est 1 ou plusieurs espaces
	getline ; # permet de supprimer la premiere (le nom des colonnes)
}
{
	if ($8 ~ /^S/) # expression reguliere sur le champs n°8 qui commence par un "S"
		printf("%5d\t%5.1f\t%s\n",$2,$3,$11) # on affiche le PID, le % CPU et la commande
}
