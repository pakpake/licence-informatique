(*********************************************************************************)
let pr(l)=hd(l);;
let sp(l)=tl(l);;

let rec valeur(l,i)=if i=0 
						then hd(l) 
					else valeur(tl(l),i-1);;
					
let val(T,i,j)= valeur(valeur(T,i),j);;

let rec scol(l)= if l=[] then [] else [(sp(pr(l)))]@scol(sp(l));;

let rec diagonale(l)= [pr(pr(l))]@diagonale(scol(sp(l)));;

let rec place(l,i,val)= if i=0 then [val]@sp(l) else [pr(l)]@place(sp(l),i-1,val);;
let rec donne_0(l,i)= if i=0 then pr(l) else donne_0(sp(l),i-1);;



(** f(tableau,j)=descente(tableau,7,j) **)

(**Focntions suplémentaires**)
let pr(l)=hd(l);;
let sp(l)=tl(l);;
let rec donne_0(tableau,i)= if i=0 then pr(tableau) else donne_0(sp(tableau),i-1);;
let rec descente(tableau,7,j,couleur) = if donne_0(tableau,i-1,j)=0
											then descente(tableau,i-1,j)
										else place(tableau,i-1,j,!mc);;