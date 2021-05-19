(*******Fonction: traçage d'un rectangle lo*ha au point (x,y)*******)
let rect x y lo ha =
moveto x y;
lineto x (y+ha-1);
lineto (x+lo-1) (y+ha-1);
lineto (x+lo-1) y;
lineto x y;;


(****remplir une liste avec des nombre random ****)
(* cree une liste d'entiers vide *)
let liste = tl([1]);;

liste;;
random__int 10;;

let liste = [3] @ liste;;
let liste = 5 :: liste;;
let liste = 12 :: liste;;

(* fonction recursive qui remplit une liste avec n elements aleatoires entre 0 et 1 inclus)*)
let rec aj(n,l)= if n=0 then [] else [random__int 2] @ aj(n-1,l);;
(* appel de la fonction *)
let liste = aj(9,[]);;
let ll = [ aj(9,[]) ; aj(9,[]) ; aj(9,[]) ; aj(9,[]) ; aj(9,[]) ; aj(9,[]) ; aj(9,[])];;

(*******Fonction: extraction d'un composant d'un couple*******)
#open "graphics";;
#open "sys";;
open_graph "500x500";

mouse_pos();;

fst (mouse_pos()) ;; (*** Récupere la 1ère valeur du couple (x,y) => snd pour la seconde partie du couple ***)

(*******************************************************************************************************************************************************)

#open "graphics";;

open_graph "500x500";;

let rect (x,y,lo,ha) =
	moveto x y;
	lineto x (y+ha-1);
	lineto (x+lo-1) (y+ha-1);
	lineto (x+lo-1) y;
	lineto x y;
;;
rect(80,25,325,380);;
set_color red;
rect(80,100,325,304);;


let x = ref 0;;
let y = ref 0;;

moveto 230 50 ;;
		
let rec draw_fleche () =
	(*(*Récupération d'une touche*)
	let e = wait_next_event [Key_pressed];
	(*Si touche ESC pressée on quitte le programme*)
	if e.key= `` then close_graph();
	*)
	(*Efface la position du curseur quand x<100*)
	if !x<100 then moveto 230 404;
	if !x<100 then draw_string("   ");
	(*Efface la fleche précédente*)
	set_color white;
	moveto !x 99;
	lineto 230 50;
	(*Récupération de la position de la souris*)
	x:=fst (mouse_pos());
	y:=snd (mouse_pos());
	(*On ne s'intéresse que aux coordonnées de la flèche rouge*)
	if !x>80 && !x<405 && !y>100 && !y<404 then moveto 230 404;
		(*
		if !x<80 then x:=80;
		if !x>405 then x:=405;
		if !y<100 then y:=100;
		if !y>404 then y:=404;
		*)
	if !x>80 && !x<405 && !y>100 && !y<404 then set_color black;
	if !x>80 && !x<405 && !y>100 && !y<404 then	draw_string(string_of_int(!x));
	if !x>80 && !x<405 && !y>100 && !y<404 then	moveto 230 50 ;
	if !x>80 && !x<405 && !y>100 && !y<404 then	lineto !x 99;
	draw_fleche();
;;

draw_fleche();;
















