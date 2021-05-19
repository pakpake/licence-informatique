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
let xp = ref 0;;

moveto 230 50 ;;
	
let rec draw_fleche () =
	
	(*Récupération d'une touche*)
	let e = wait_next_event [Key_pressed; Mouse_motion] in
	(*Si touche ESC pressée on quitte le programme*)
	if e.key= `` then close_graph();
	
	
	(*Efface la position du curseur quand x<100*)
	if !x<100 then moveto 230 404;
	if !x<100 then draw_string("   ");
	(*Efface la fleche précédente*)
	set_color white;
	if !x>80 && !x<405 && !y>100 && !y<404 then 
		if !x<=230 then xp := (230-(50*(230-(!x)))/((!y)-50)) (**Calcul des coord de xp avec Thalès**)
		else xp:=230+(50*((!x)-230))/((!y)-50);	(*Si on est + grand que x=230 le triangle est inversé*)
	moveto !xp 99; (*ne marche pas le !x-1 => pour ne pas effacer les bordures*)
	lineto 230 50;
	(*Récupération de la position de la souris*)
	x := fst (mouse_pos());
	y := snd (mouse_pos());
	(*On ne s'intéresse que aux coordonnées de la flèche rouge*)
	
	(***** => NE MARCHE PAS ??? : on ne fait rien quand on n'est pas dans le carré rouge.
	if !x>80 && !x<405 && !y>100 && !y<404 then
		begin
			moveto 230 404;
			set_color black;
			draw_string(string_of_int(!x));
			moveto 230 50 ;
			if !x<=230 then xp := (230-(50*(230-(!x)))/((!y)-50)) (**Calcul des coord de xp avec Thalès**)
			else xp:=230+(50*((!x)-230))/((!y)-50); (*Si on est + grand que x=230 le triangle est inversé*)
			lineto !xp 99;
		end
	*****)
	if !x>80 && !x<405 && !y>100 && !y<404 then moveto 230 404;
	if !x>80 && !x<405 && !y>100 && !y<404 then set_color black;
	if !x>80 && !x<405 && !y>100 && !y<404 then	draw_string(string_of_int(!x));
	if !x>80 && !x<405 && !y>100 && !y<404 then	moveto 230 50 ;
	if !x>80 && !x<405 && !y>100 && !y<404 then 
		if !x<=230 then xp := (230-(50*(230-(!x)))/((!y)-50)) (**Calcul des coord de xp avec Thalès**)
		else xp:=230+(50*((!x)-230))/((!y)-50); (*Si on est + grand que x=230 le triangle est inversé*)
	if !x>80 && !x<405 && !y>100 && !y<404 then	lineto !xp 99;
	draw_fleche();
;;

draw_fleche();;


