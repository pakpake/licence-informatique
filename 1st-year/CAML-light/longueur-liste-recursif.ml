let sp_liste (l)= tl(l);;
let rec lg_liste (l) = if l=[] then 0 else 1+ lg_liste(sp_liste(l));;
lg_liste ([0;1;2;3;4;5]);;
