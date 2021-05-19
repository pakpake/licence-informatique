let ajoutg (s,l) = [s] @ l;;

let rec ajoupos(s,l,i) = if i=0 then ajoutg(s,l) else [hd(l)]@ ajoupos(s,tl(l),i-1);