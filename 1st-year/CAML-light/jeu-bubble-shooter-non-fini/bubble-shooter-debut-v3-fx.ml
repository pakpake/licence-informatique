#open "graphics";;

let pr (l)= hd(l);;
let sp (l)= tl(l);;
let rec lg (l) = if l=[] then 0 else 1+ lg(sp(l));;
let rec dr (l)= if  lg(l)=1 then pr(l) else dr(sp(l));;
let rec sd (l)= if  lg(l)=1 then [] else [pr(l)]@sd (sp(l)) ;;

let rec nth(l,i)= if i=0 then pr(l)else nth(sp(l),i-1);;

let tab(l,i,j)= nth(nth(l,j),i);;

(* les chiffres correspondent aux couleurs entre 1 et 16 *)

(****let tableau = [[0;2;3;4;4;6;10;14;1;9];[5;6;7;8;10;15;2;15;11;10];[9;10;10;1;8;3;13;14;1;5];[1;3;2;4;5;13;4;7;14;13];
				[1;2;3;4;4;6;10;14;1;9];[5;6;7;8;10;15;2;15;11;10];[9;10;10;9;0;3;13;14;1;5]];;
****)

(* fonction recursive qui remplit une liste avec n elements aleatoires entre 0 et 1 (inclus)*)

let rec aj(n,l)= if n=0 then [] else [random__int 5] @ aj(n-1,l);;

let tableau = [ aj(10,[]) ; aj(10,[]) ; aj(10,[]) ; aj(10,[]) ; aj(10,[]) ; aj(10,[]) ; aj(10,[])];;

(*Déclaration de variable x,y,xp -> positon de la souris*)
let x = ref 0;;
let y = ref 0;;
let xp = ref 0;;
let mc = ref 0;; (* couleur de la bille de départ *)

let colors =

  [| 0xff0000; 0x009cff; 0x000000; 0xdeff00;
	 0x7eff00; 0x1eff00; 0x1eff00; 0x00ff42;
	 0x00ffa2; 0x00fcff; 0xff6000; 0x003cff;
	 0x2400ff; 0x8400ff; 0xe400ff; 0xffffff |];;
(**************************************************************)
(*  Affiche une ligne de la (x,y) a (xp,yp)                   *)
(**************************************************************)


(*******Fonction: traçage d'un rectangle lo*ha au point (x,y)*******)
let rect (x,y,lo,ha,couleur) =
	moveto x y;
	lineto x (y+ha-1);
	lineto (x+lo-1) (y+ha-1);
	lineto (x+lo-1) y;
	lineto x y;;

let ligne (x,y,xp,yp, couleur)  =
   set_color colors.(couleur);
   moveto x y;
   lineto xp yp ;;
   
let point(x,y,couleur)=
	ligne (x,y,x+1,y+1, couleur);;
	
let rec cercle ( x,y, rayon, theta, couleur) =
	if theta > 6.3
	then begin
		 point (int_of_float(x+.rayon*.cos(theta)),
int_of_float(y+.rayon*.sin(theta)), couleur);
		 ()
		 end
	else begin
		 point (int_of_float(x+.rayon*.cos(theta)),
int_of_float(y+.rayon*.sin(theta)), couleur);
		 cercle (x,y, rayon, theta +. 0.01, couleur)
		 end;;
		 
(*let rec cercle_plein ( x,y, rayon, theta, couleur) =
	if rayon <. 0.0
	then begin
		 ()
		 end
	else begin
		 cercle (x,y, rayon, theta, couleur);
		 cercle_plein ( x,y, rayon-.0.5, theta, couleur);
		(*texte (30,30, "BONJOUR", 5);*)
		 end;;*)
	 
let cercle_plein ( x,y, rayon, theta, couleur) =
		set_color colors.(couleur);
		fill_circle (int_of_float(x)) (int_of_float(y)) (int_of_float(rayon));;
		
let rec wait_time(next)= if next-.sys__time() <0.0 then ()
						else wait_time(next);;

(*let musique () = sound 440 1000; sound 493 1000; sound 520 1000;;*)

(**************************************************************)
(*  Affiche une texte a la position(x,y)                      *)
(**************************************************************)
let texte (x,y,s,couleur)  =
   set_color colors.(couleur);
   moveto x y;
   draw_string s 
;;
   
let rec affiche(l,i,j)= 
	if (i=0) & (j=0) then 
		begin 
			cercle_plein(float_of_int(i*20+100),float_of_int(j*30+i*20+200),12.0,0.0,tab(l,i,j));
			();
		end
	else if j mod 2=0 then 
		begin 
			cercle_plein(float_of_int(i*30+100),float_of_int(j*30+200),12.0,0.0,tab(l,i,j));
			if j=0 then affiche(l,i-1,6)
			else affiche (l,i,j-1);
		end
	else 
		begin
			cercle_plein(float_of_int(i*30+115),float_of_int(j*30+200),12.0,0.0,tab(l,i,j));
			if j=0 then affiche(l,i-1,6)
			else affiche (l,i,j-1);
		end
;;

let rec animation (xd,yd,xf,yf,color)=
		if (abs(xf-xd)<=2 && abs(yf-yd)<=2) || yd>170
		then  begin
				cercle_plein(float_of_int(xd),float_of_int(yd),12.0,0.0,color);
				();
			  end
		else begin
				cercle_plein(float_of_int(xd),float_of_int(yd),12.0,0.0,color);
				wait_time(sys__time()+.0.01);
				cercle_plein(float_of_int(xd),float_of_int(yd),12.0,0.0,15);
				if xf>xd then (*Le point d'arrivé est à droite du départ*) 
					animation (xd+1,yd+(yf-yd)/(xf-xd),xf,yf,color) (*Calcul de la new coord y avec Thalès*)
				else animation (xd-1,yd+(yf-yd)/(xd-xf),xf,yf,color);
			 end;;
(*
let rec animation (xd,yd,xf,yf,color)=
		if abs(xf-xd)<=2 || abs(yf-yd)<=2
		then  begin
				moveto 250 404;
				draw_string(string_of_int(xd));
				();
			  end
		else begin
				cercle_plein(float_of_int(xd),float_of_int(yd),10.0,0.0,color);
				wait_time(sys__time()+.0.01);
				cercle_plein(float_of_int(xd),float_of_int(yd),10.0,0.0,15);
				moveto 250 404;
				draw_string("x"^string_of_int(xd));
				if xf>xd then (*Le point d'arrivé est à droite du départ*) 
					animation (xd+1,yd+(yf-yd)/(xf-xd),xf,yf,color) (*Calcul de la new coord y avec Thalès*)
				else animation (xd-1,yd+(yf-yd)/(xd-xf),xf,yf,color);
			 end;;
*)
(* let calxp1(x,y) = 
	if x <= 230 then
		230-(50*(230-(x)))/((y)-50)
	else
		230+(50*((x)-230))/((y)-50)
;;
*)

let rec traceDisque = function()  ->
	(*Efface la position du curseur quand x<100*)
	if !x<100 then moveto 230 404;
	if !x<100 then draw_string("   ");
	(*Efface la fleche précédente*)
	set_color white;
 	if !x>80 && !x<405 && !y>100 && !y<404 then 
		if !x<=230 then xp := (230-(50*(230-(!x)))/((!y)-50)) (**Calcul des coord de xp avec Thalès**)
		else xp:=230+(50*((!x)-230))/((!y)-50);	(*Si on est + grand que x=230 le triangle est inversé*)
	moveto !xp 99; (*ne marche pas le !x-1 => pour ne pas effacer les bordures*)

(*	if !x>80 && !x<405 && !y>100 && !y<404 then 
		if !x<=230 then xp := (230-(50*(230-(!x)))/((!y)-50)) (**Calcul des coord de xp avec Thalès**)
		else xp:=230+(50*((!x)-230))/((!y)-50);	(*Si on est + grand que x=230 le triangle est inversé*)
	moveto (calxp1(!x,!y)) 99; (*ne marche pas le !x-1 => pour ne pas effacer les bordures*)
*)
	lineto 230 50;
	(*Récupération de la position de la souris*)
	x := fst (mouse_pos());
	y := snd (mouse_pos());
	if !x>80 && !x<405 && !y>100 && !y<404 then moveto 230 404;
	if !x>80 && !x<405 && !y>100 && !y<404 then set_color black;
	if !x>80 && !x<405 && !y>100 && !y<404 then	draw_string(string_of_int(!x));
	if !x>80 && !x<405 && !y>100 && !y<404 then	moveto 230 50 ;
	if !x>80 && !x<405 && !y>100 && !y<404 then 
		if !x<=230 then xp := (230-(50*(230-(!x)))/((!y)-50)) (*Calcul des coord de xp avec Thalès*)
		else xp:=230+(50*((!x)-230))/((!y)-50); (*Si on est + grand que x=230 le triangle est inversé*)
	if !x>80 && !x<405 && !y>100 && !y<404 then	lineto !xp 99;
	cercle_plein(230.0,50.0,12.0,0.0,!mc);
	let e = wait_next_event [Key_pressed; Button_down; Mouse_motion] in
		let X = e.mouse_x and Y = e.mouse_y in
			if e.button then
				begin
					animation(230,50,X,Y,!mc);
					(*fill_circle X Y 12;*)
					mc := random__int 5;
					traceDisque()
				end
			else
				begin
					let c= e.key in 
						begin
							if c=`q` || c=`` then () else traceDisque()
							(*
							else 
								begin
									fill_circle X Y 50;
									traceDisque()
							end
							*)
						end
				end
	
;;

let f () =
	open_graph "500x500";
	rect(80,25,325,380,0); (*rectangle de zone de jeu*)
	set_color red;
	rect(80,100,325,304,0); (*rectangle rouge de la zone active*)
	mc := random__int 5;
	cercle_plein(230.0,50.0,12.0,0.0,!mc);
	(*cercle_plein(float_of_int(100),float_of_int(200),10.0,0.0,1);*)
	affiche(tableau,9,6);
	moveto 230 50;
	(*animation(230,50,fst (mouse_pos()),snd (mouse_pos()),!mc);*)
	traceDisque();;
