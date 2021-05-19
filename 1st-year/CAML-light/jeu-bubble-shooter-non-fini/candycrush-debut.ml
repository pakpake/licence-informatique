let sp (l)= tl(l);;
let rec lg (l) = if l=[] then 0 else 1+ lg(sp(l));;
let rec dr (l)= if  lg(l)=1 then pr(l) else dr(sp(l));;
let rec sd (l)= if  lg(l)=1 then [] else [pr(l)]@sd (sp(l)) ;;

let rec nth(l,i)= if i=0 then pr(l)else nth(sp(l),i-1);;

let tab(l,i,j)= nth(nth(l,j),i);;

(* les chiffres correspondent aux couleurs entre 1 et 16 *)
let tableau = [[1;2;3;4;4;6;10;14;1;9];[5;6;7;8;10;15;2;15;11;10];[9;10;10;1;8;3;13;14;1;5];[1;3;2;4;5;13;4;7;14;13];
			  [1;2;3;4;4;6;10;14;1;9];[5;6;7;8;10;15;2;15;11;10];[9;10;10;1;8;3;13;14;1;5]];;

let colors =

  [| 0xff0000; 0xff6000; 0x000000; 0xdeff00;

     0x7eff00; 0x1eff00; 0x1eff00; 0x00ff42;

     0x00ffa2; 0x00fcff; 0x009cff; 0x003cff;

     0x2400ff; 0x8400ff; 0xe400ff; 0xff00ba |];;