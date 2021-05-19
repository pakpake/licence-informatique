(******bubble shooter version texte******)

let pr (l)= hd(l);;
let sp (l)= tl(l);;
let rec lg (l) = if l=[] then 0 else 1+ lg(sp(l));;
let rec dr (l)= if  lg(l)=1 then pr(l) else dr(sp(l));;
let rec sd (l)= if  lg(l)=1 then [] else [pr(l)]@sd (sp(l)) ;;

let rec nth(l,i)= if i=0 then pr(l)else nth(sp(l),i-1);;

let tab(l,i,j)= nth(nth(l,i),j);;

let rec aj(n,l)= if n=0 then [] else [random__int 2] @ aj(n-1,l);;

let tableau = [[0;0;0;0];[0;0;0;0];[1;2;3;4];[2;1;4;6];[3;1;3;4];[2;2;1;9]];;

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

let rec valeur(l,i)=if i=0 then hd(l) else valeur(tl(l),i-1);;
					
let val(l,i,j)= valeur(valeur(l,i),j);;
let donne_1(l,i,j)= donne_0(donne_0(l,i),j);;
let rec place(l,i,val)= if i=0 then [val]@tl(l) else [hd(l)]@place(tl(l),i-1,val);;

let remplace(t,i,j,val) = place (t,i,place(donne_0(t,i),j,val));;

let rec donne_0(l,i)= if i=0 then hd(l) else donne_0(tl(l),i-1);;


let rec descente(l,i,j,couleur) = if donne_1(l,i-1,j)=15 then descente(l,i-1,j,couleur) else remplace(l,i-1,j,couleur);;


let tomber(t,j,val)= descente(t,5,j,val);;



affiche(tableau,5,3);;