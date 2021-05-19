(******bubble shooter version texte******)

let pr (l)= hd(l);;
let sp (l)= tl(l);;
let rec lg (l) = if l=[] then 0 else 1+ lg(sp(l));;
let rec dr (l)= if  lg(l)=1 then pr(l) else dr(sp(l));;
let rec sd (l)= if  lg(l)=1 then [] else [pr(l)]@sd (sp(l)) ;;

let rec nth(l,i)= if i=0 then pr(l)else nth(sp(l),i-1);;

let tab(l,i,j)= nth(nth(l,j),i);;

let rec aj(n,l)= if n=0 then [] else [random__int 2] @ aj(n-1,l);;

(* let tableau = [ [15;15;15;15;15;15;15;15;15;15] ;[15;15;15;15;15;15;15;15;15;15] ; aj(10,[]) ; aj(10,[]) ; aj(10,[]) ; aj(10,[]) ; aj(10,[])];; *)
let tableau = [[0;0;0;0];[0;0;0;0];[1;2;3;4];[2;1;4;4];[3;1;3;4];[2;2;1;9]];;

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
			if j=0 then begin 
				print_newline();
				affiche(l,i-1,5)
			end
			else begin
				print_string(" ");
				affiche (l,i,j-1);
			end
		end
;;

affiche(tableau,3,5);;
