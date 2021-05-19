(*ligne 5 verifier qu'un élément est dans une liste*)

let pr_liste (l)= hd(l);;
let sp_liste (l)= tl(l);;
let rec lg_liste (l) = if l=[] then 0 else 1+ lg_liste(sp_liste(l));;

(********************************************************************************)
let vide(l) = if l=[] then true else false ;;

let rec appartient(e,l) = if vide(l)
							then false
						else if pr_liste(l)=e
								then true
							else appartient(e,sp_liste(l));;
							
(*ligne 6 : transformer une chaine de caractère en une liste de caractère*)

let rec list_of_string(l) = if l=""
								then []
							else ajout(hd(l),list_of_string(tl(l)));;

(*ligne 7*)
let niem (s,n) = nth_char s n;;
let dr(x) = niem(x, string_length(x)-1);;
let pr(x) =  niem(x, 0);;

let rec prN(x) =if i=0
					then[]
				else ajoutg(pr(x),prN(sp(x)),i-1);;

let rec drN(x,i)=if lg_liste(x)=i
					then x
				else drN(sp_liste(x),i);;
				
let rec croise = drN(l,lg_liste(x)/2)@prN(x,lg_liste(x)/2);;

(*ligne 8*)

let rec fusion (s1,s2) = if vide(s1) or vide(s2)
							then s1@s2
						else ajoutg(hd(s1),ajoutg(hd(s2)),fusion(tl(s1),tl(s2)));;
