(******bubble shooter version texte v5******)

let pr (l)= hd(l);; (*renvoit le premier de la liste*)
let sp (l)= tl(l);; (*renvoit la liste sans le premer element*)
let rec lg (l) = if l=[] then 0 else 1+ lg(sp(l));; (*renvoit la longueur de la liste*)
let rec dr (l)= if  lg(l)=1 then pr(l) else dr(sp(l));; (*renvoit le dernier element de la liste*)
let rec sd (l)= if  lg(l)=1 then [] else [pr(l)]@sd (sp(l)) ;; (*renvoit la liste sans le dernier element*)

(*nth  renvoit le n-ieme element de la liste*)
let rec nth(l,i)= if i=0 then pr(l)else nth(sp(l),i-1);; 

(*tab renvoit l'element en position i,j d'un tableau (liste de listes)*)
let tab(l,i,j)= nth(nth(l,i),j);; 

(*aj renvoit une liste de n elements entre 0 et 1*)
let rec aj(n,l)= if n=0 then [] else [random__int 2] @ aj(n-1,l);; 

let tableau = [[0;0;0;0];[0;0;0;0];[1;2;3;4];[2;1;4;6];[3;1;3;4];[2;2;1;9]];;

let c = ref 0;; (*compteur*)

(*affiche le tableau a l'ecran - attention la colonne 0 est a droite*)
let rec affiche(l,i,j)= 
	if (i=0) & (j=0) then 
			begin 	
				print_int(tab(l,i,j));
				print_newline();
				();
			end
	else 
		begin
			print_int(tab(l,i,j));
			if j=0 then 
				begin 
					print_newline();
					affiche(l,i-1,3)
				end
			else 
				begin
					print_string(" ");
					affiche (l,i,j-1);
				end
		end
;;

(*valeur renvoit la i-ème ligne d'un tableau passé en paramètre*)
let rec valeur(l,i)=if i=0 then hd(l) else valeur(tl(l),i-1);;

(*val renvoi l'élément en position i,j du tableau*)
let val(l,i,j)= valeur(valeur(l,i),j);;

(*identique à valeur*)
let rec donne_0(l,i)= if i=0 then hd(l) else donne_0(tl(l),i-1);;

(*identique à val*)
let donne_1(l,i,j)= donne_0(donne_0(l,i),j);;

(*place la liste val en i-ème ligne du tableau passé en paramètre*)
let rec place(l,i,val)= if i=0 then [val]@tl(l) else [hd(l)]@place(tl(l),i-1,val);;

(*remplace dans tableau l'élément à la position i,j par val*)
let remplace(t,i,j,val) = place (t,i,place(donne_0(t,i),j,val));;

(*descente deplace couleur à partir de la positon i,j vers le haut tant que la couleur au dessus est 0*)
let rec descente(l,i,j,couleur) = if donne_1(l,i+1,j)=0 then descente(l,i+1,j,couleur) else remplace(l,i,j,couleur);;

(*appelle descente depuis la ligne 0*)
let tomber(t,j,val)= descente(t,0,j,val);;



(*fonction qui compte le nombre d'elements couleur dans les 8 cases autour de la position i,j*)
let compteautour(t,i,j,couleur)= 
	c:=0; (*initialisation du compteur*)
	if (i>0) && (i<5) then (*si on n'est pas en dehors des lignes du tableau*)
		begin
			if (j>0) && (j<3) then (*si on n'est pas en dehors des colonnes du tableau*)
				begin (*cas general*)
					if donne_1(t,i-1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i-1,j)=couleur then c:=!c+1;
					if donne_1(t,i-1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
			if (j=0) then 
				begin (*premiere colonne*)
				    if donne_1(t,i-1,j)=couleur then c:=!c+1;
					if donne_1(t,i-1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
			if (j=3) then
				begin (*derniere colonne*)
					if donne_1(t,i-1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i-1,j)=couleur then c:=!c+1;
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
		end;	
	if (i=0) then (*premiere ligne*)
		begin
			if (j>0) && (j<3) then 
				begin (*cas general pour la premiere ligne*)
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
			if (j=0) then 
				begin (*premiere colonne*)
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
			if (j=3) then
				begin (*derniere colonne*)
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
		end;
	
	if (i=5) then (*derniere ligne*)
		begin
			if (j>0) && (j<3) then 
				begin (*cas general pour la derniere ligne*)
					if donne_1(t,i-1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i-1,j)=couleur then c:=!c+1;
					if donne_1(t,i-1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
				end;
			if (j=0) then 
				begin (*premiere colonne*)
					if donne_1(t,i-1,j)=couleur then c:=!c+1;
					if donne_1(t,i-1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
				end;
			if (j=3) then
				begin (*derniere colonne*)
					if donne_1(t,i-1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i-1,j)=couleur then c:=!c+1;
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
				end;
		end;
	!c
;;

(*eclate remplace l'element en position i,j par 0 si il est de la bonne couleur et propage cette modification aux 8 elements autour*)
(*** ATTENTION CETTE FONCTION PROVOQUE UNE ERREUR DE MEMOIRE ***)
let rec eclate(t,i,j,couleur)= 
	if (i<0) || (j<0) || (i>5) || (j>3) || (donne_1(t,i,j) != couleur) then (*condition d'arret generale*)
		remplace(t,0,0,0); (*on ne fait rien*)
	else (*on propage la transformation a toutes les cases voisines*)
		begin
			eclate(t,i-1,j-1,couleur);
			eclate(t,i-1,j+1,couleur);
			eclate(t,i,j-1,couleur);
			eclate(t,i,j+1,couleur);
			eclate(t,i+1,j-1,couleur);
			eclate(t,i+1,j,couleur);
			eclate(t,i+1,j+1,couleur);
			remplace(t,i,j,0); (*on remplace la couleur par 0*)
		end
;;

affiche(tableau,5,3);;
affiche(eclate(tableau,3,0,1),5,3);; (* pas d'erreur, ce n'est pas la bonne couleur*)
affiche(eclate(tableau,3,0,2),5,3);; (*** ATTENTION CET APPEL PROVOQUE UNE ERREUR DE MEMOIRE ***)


(*descente(tableau,0,0,8);;
affiche(descente(tableau,0,0,8),5,3);;
tomber(tableau,2,9);;
affiche(tomber(tableau,2,9),5,3);;
affiche(tableau,5,3);;
compteautour(tableau,4,1,2);;
eclate(tableau,2,0,2);;
*)