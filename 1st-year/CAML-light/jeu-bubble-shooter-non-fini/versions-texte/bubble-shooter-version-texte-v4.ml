(******bubble shooter version texte v4******)

let pr (l)= hd(l);;
let sp (l)= tl(l);;
let rec lg (l) = if l=[] then 0 else 1+ lg(sp(l));;
let rec dr (l)= if  lg(l)=1 then pr(l) else dr(sp(l));;
let rec sd (l)= if  lg(l)=1 then [] else [pr(l)]@sd (sp(l)) ;;

let rec nth(l,i)= if i=0 then pr(l)else nth(sp(l),i-1);;

let tab(l,i,j)= nth(nth(l,i),j);;

let rec aj(n,l)= if n=0 then [] else [random__int 2] @ aj(n-1,l);;

let tableau = [[0;0;0;0];[0;0;0;0];[1;2;3;4];[2;1;4;6];[3;1;3;4];[2;2;1;9]];;

let c = ref 0;; (*compteur*)

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



(*fonction qui compte le nombre d'elements couleur dans les 7 cases autour de la position i,j*)
let compteautour(t,i,j,couleur)= 
c:=0; (*initialisation du compteur*)
	if (i>0) & (i<5) then 
		begin
			if (j>0) & (j<3) then 
				begin 
					if donne_1(t,i-1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i-1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
			if (j=0) then 
				begin
					if donne_1(t,i-1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
			if (j=3) then
				begin 
					if donne_1(t,i-1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
		end;
	
	if (i=0) then 
		begin
			if (j>0) & (j<3) then 
				begin 
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
			if (j=0) then 
				begin
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
			if (j=3) then
				begin 
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
		end;
	
	if (i=5) then 
		begin
			if (j>0) & (j<3) then 
				begin 
					if donne_1(t,i-1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i-1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
				end;
			if (j=0) then 
				begin
					if donne_1(t,i-1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
				end;
			if (j=3) then
				begin 
					if donne_1(t,i-1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
				end;
		end;
	!c
;;

let rec eclate(t,i,j,couleur)= 
	if (i<0) || (j<0) || (i>5) || (j>3) || (donne_1(t,i,j) != couleur) then t
	else 
		begin
		    remplace(t,i,j,0);
			eclate(t,i-1,j-1,couleur);
			eclate(t,i-1,j+1,couleur);
			eclate(t,i,j-1,couleur);
			eclate(t,i,j+1,couleur);
			eclate(t,i+1,j-1,couleur);
			eclate(t,i+1,j,couleur);
			eclate(t,i+1,j+1,couleur);
		end
;;


affiche(tableau,5,3);;


(*descente(tableau,0,0,8);;
affiche(descente(tableau,0,0,8),5,3);;
tomber(tableau,2,9);;
affiche(tomber(tableau,2,9),5,3);;
affiche(tableau,5,3);;
compteautour(tableau,4,1,2);;
eclate(tableau,2,0,2);;
*)