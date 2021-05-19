(****** pour les entiers *********************)

let dernier (i)= i mod 10;;
let sauf_dernier (i) = i/10;;
let rec premier (i)= if i<10 then i else premier(sauf_dernier(i));;
let rec sauf_premier(i)= if i<10 then 0 else sauf_premier(sauf_dernier(i))*10+dernier(i);;

(****** pour les STRING *********************)

let niem (s,n) = nth_char s n;;
let dr(x) = niem(x, string_length(x)-1);;
let pr(x) =  niem(x, 0);;
let sd(x) = sub_string x 0 (string_length(x)-1);;
let sp(x) = sub_string x 1 (string_length(x)-1);;

(****** pour les LISTE *********************)

let pr_liste (l)= hd(l);;
let sp_liste (l)= tl(l);;
let rec lg_liste (l) = if l=[] then 0 else 1+ lg_liste(sp_liste(l));;
let rec dr_liste (l)= if  lg_liste(l)=1 then pr_liste(l) else dr_liste(sp_liste(l));;
let rec sd_liste (l)= if  lg_liste(l)=1 then [] else [pr_liste(l)]@sd_liste (sp_liste(l)) ;;

(******* goog functions ********************)

string_of_char(c)
random__int x
sys__time()
sound 440 1000
