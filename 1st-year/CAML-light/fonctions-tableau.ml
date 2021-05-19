(***** Série 5 - manipulation de tableau *****)
let pr(l)= hd(l);;
let sp(l)= tl(l);;
let rec lg(l) = if l=[] then 0 else 1+ lg_liste(sp_liste(l));;
let rec dr(l) = if lg_liste(l)=1 then pr_liste(l) else dr_liste(sp_liste(l));;
let rec sd(l) = if lg_liste(l)=1 then [] else [pr_liste(l)]@sd_liste (sp_liste(l)) ;;

(***** Ligne 1 *****)

let l=[[1;2;3;4];[5;6;7;8];[9;10;11;12];[13;14;15;16]];;
let rec valeur(l,i)=if i=0 
						then hd(l) 
					else valeur(tl(l),i-1);;
					
let val(T,i,j)= valeur(valeur(T,i),j);;

val(T,0,1);;

(***** Ligne 1 - v2 *****)

let rec donne_0(l,i)= if i=0 then pr(l) else donne_0(sp(l),i-1);;

let donne_1(l,i,j)= donne_0(donne_0(l,i),j);;

(***** Ligne 2 *****)

let rec place(l,i,val)= if i=0 then [val]@sp(l) else [pr(l)]@place(sp(l),i-1,val);;
let remplace(l,i,j,val)= place(l,i,place(donne_0(l,i),j,val));;

(***** Ligne 3 *****)

let permute(l,i,j,k,m)= remplace(remplace(l,i,j,donne_1(l,k,m)),k,m,donne_1(l,i,j));;

(***** Ligne 4 *****)

	(* fonction donne_0 *)

(***** Ligne 5 *****)

let rec colonne(l,j)= if l=[] then [] else [donne_0(pr(l),j)]@colonne(sp(l),j);;

(***** Ligne 6 *****)
let rec scol(l)= if l=[] then [] else [(sp(pr(l)))]@scol(sp(l));;

let rec diagonale(l)= [pr(pr(l))]@diagonale(scol(sp(l)));;
















