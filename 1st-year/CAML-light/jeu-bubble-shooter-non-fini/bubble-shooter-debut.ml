#open "graphics";;
let pr (l)= hd(l);;
let rec nth(l,i)= if i=0 then pr(l)else nth(sp(l),i-1);;
let tab(l,i,j)= nth(nth(l,j),i);;
let tableau = [[1;2;3;4;4;6;10;14;1;9];[5;6;7;8;10;15;2;15;11;10];[9;10;10;1;8;3;13;14;1;5];
let colors =
  [| 0xff0000; 0xff6000; 0x000000; 0xdeff00;
(*  Affiche une ligne de la (x,y) a (xp,yp)                   *)
(**************************************************************)
let cercle_plein ( x,y, rayon, theta, couleur) =
(**************************************************************)
let rec affiche(l,i,j)= 
let rec traceDisque = function()  ->
let texte (x,y,s,couleur)  =
let f () =
f();;