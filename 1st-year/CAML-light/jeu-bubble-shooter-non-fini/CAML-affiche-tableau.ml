#open "graphics";;


let pr (l)= hd(l);;
let sp (l)= tl(l);;
let rec lg (l) = if l=[] then 0 else 1+ lg(sp(l));;
let rec dr (l)= if  lg(l)=1 then pr(l) else dr(sp(l));;
let rec sd (l)= if  lg(l)=1 then [] else [pr(l)]@sd (sp(l)) ;;

 let rec nth(l,i)= if i=0 then pr(l)else nth(sp(l),i-1);;
 
 let tab(l,i,j)= nth(nth(l,j),i);;
 
 let tableau = [[1;2;3;4];[5;6;7;8];[9;10;10;1];[1;2;3;4]];;
 
 
let colors =
  [| 0xff0000; 0xff6000; 0xffc000; 0xdeff00;
     0x7eff00; 0x1eff00; 0x1eff00; 0x00ff42;
     0x00ffa2; 0x00fcff; 0x009cff; 0x003cff;
     0x2400ff; 0x8400ff; 0xe400ff; 0xffffff|];;

(**************************************************************)
(*  Affiche une ligne de la (x,y) a (xp,yp)                   *)
(**************************************************************)

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
		 
let rec cercle_plein ( x,y, rayon, theta, couleur) =
		set_color colors.(couleur);
		fill_circle (int_of_float(x)) (int_of_float(y)) (int_of_float(rayon));;
		

let musique () = sound 440 1000; sound 493 1000; sound 520 1000;;

let rec wait_time(next)= if next-.sys__time() <0.0 then ()
						else wait_time(next);;

(**************************************************************)
(*  Affiche une teste a la position(x,y)                    *)
(**************************************************************)

let rec affiche(l,i,j)= if (i=0) & (j=0) then begin 
					cercle_plein(float_of_int(i*20+100),float_of_int(j*30+i*20+200),12.0,0.0,tab(l,i,j));
					();
					end
					
			else begin 
					cercle_plein(float_of_int(i*30+100),float_of_int(j*30+200),10.0,0.0,tab(l,i,j));
					if j=0 then affiche(l,i-1,3)
					else affiche (l,i,j-1);
				end
			;;

let rec animation (xd,yd,xf,yf,color)=
		if abs(xf-xd)+abs(yf-yd)<2
		then  begin
				cercle_plein(float_of_int(xd*30+100),float_of_int(yd*30+200),10.0,0.0,color);
				();
			  end
		else begin
				cercle_plein(float_of_int(xd*30+100),float_of_int(yd*30+200),10.0,0.0,color);
				wait_time(sys__time()+.0.20);
				cercle_plein(float_of_int(xd*30+100),float_of_int(yd*30+200),10.0,0.0,15);
				animation (xd+1,yd+1,xf,yf,color);
			 end;;	

let rec traceDisque = function()  ->
  let e = wait_next_event [Key_pressed; Button_down]
  in 
  let X = e.mouse_xs
  and Y = e.mouse_y
  in
    if e.button then
      begin
		fill_circle X Y 10;
		traceDisque()
      end
	else
	  begin
		let c= e.key in 
		begin
			if c=`q` then ()
			else 
				if c=`a` then 
					begin 	fill_circle 500 505 100;
							traceDisque() 
					end 
				else 
					begin 	fill_circle X Y 50;
							traceDisque()
					end
	
		end
      end ;;

let texte (x,y,s,couleur)  =
   set_color colors.(couleur);
   moveto x y;
   draw_string s ;;

let f () =
     open_graph "1000x700";
	 texte (50,50, "BONJOUR", 0);
	 animation (1,1,10,10,0);
	 (*cercle_plein(float_of_int(100),float_of_int(200),10.0,0.0,1);*)
	 affiche(tableau,3,3);
	 traceDisque();
	 
;;

