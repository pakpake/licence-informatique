(************************************************************************************************************)
(*************************** BUBBLE SHOOTER VERSION TEXTE - pakpake - VERSION 7 ******************************)
(*                  Copyright : pakpake - - Derniere modification : 6 janvier 2019                    *)
(************************************************************************************************************)
(* Le jeu fonctionne de la maniere suivante :                                                               *)
(* - d'abord le tableau de départ s'affiche                                                                 *)
(*   (fonction affiche)                                                                                     *)
(* - le joueur choisit une case ou il veut tirer avec la couleur proposee au hasard                         *)
(*   (definition de coul et de tircol)                                                                      *)
(* - la bille de couleur tombe (monte) jusqu'a se placer au contact des billes presentes                    *)
(*   (fonctions tomber qui appelle descente et numlignetomber)                                              *)
(* - si le nombre de billes de meme couleur autour est > a 2 alors les billes de cette couleur eclatent     *)
(*   (fonction compteautour)                                                                                *)
(* - toutes les billes de cette couleur eclatent ainsi que les billes voisines de la meme couleur           *)
(* - toutes les billes voisines des voisines eclatent aussi                                                 *)
(*   (fonction recursive eclate)                                                                            *)
(* - puis on recommence                                                                                     *)
(* - au bout d'un nombre de coup de jeu, une nouvelle ligne de billes est ajoutee au tableau                *)
(*   (fonction ajlignetab)                                                                                  *)
(* - on perd si la premiere ligne contient autre chose que des zeros                                        *)
(* - on gagne si la derniere ligne du tableau ne contient que des zeros                                     *)
(*   (conditions d'arret de la fonction recursive principale joue)                                          *)
(************************************************************************************************************)

let pr (l)= hd(l);; (*renvoit le premier de la liste*)
let sp (l)= tl(l);; (*renvoit la liste sans le premier element*)
let rec lg (l) = if l=[] then 0 else 1+ lg(sp(l));; (*renvoit la longueur de la liste*)
let rec dr (l)= if  lg(l)=1 then pr(l) else dr(sp(l));; (*renvoit le dernier element de la liste*)
let rec sd (l)= if  lg(l)=1 then [] else [pr(l)]@sd (sp(l)) ;; (*renvoit la liste sans le dernier element*)

(*nth  renvoit le n-ieme element de la liste*)
let rec nth(l,i)= if i=0 then pr(l)else nth(sp(l),i-1);; 

(*tab renvoit l'element en position i,j d'un tableau (liste de listes)*)
let tab(l,i,j)= nth(nth(l,i),j);; 

(*aj ajoute a la liste l une liste de n elements entre 0 et b-1*)
let rec aj(n,l,b)= if n=0 then [] else [random__int b] @ aj(n-1,l,b);; 

(*ajligne renvoit une liste de 4 elements entre 0 et 9*)
let ajligne() = aj(4,[],10);; 

(*ajlignetab renvoit un tableau dont la ligne 0 a ete supprimee et une nouvelle ligne est ajoutee à la fin*)
let ajlignetab(t) = sp(t) @ [ajligne()];;

(***Appels pour tester ajlignetab-faire plusieurs appels pour voir les lignes bouger***)
(*
  let tableau = [[0;0;0;0];[0;0;0;0];[1;2;3;4];[2;1;4;6];[3;1;3;4];[2;2;1;9]];;
  let tableau = ajlignetab(tableau);;
  affiche(tableau,5,3);;
*)

(*tableau de depart pour nos tests*)
let tableau = [[0;0;0;0];[0;0;0;0];[1;2;3;4];[2;1;4;6];[3;1;3;4];[2;2;1;9]];;

let c = ref 0;; (*compteur pour le nombre de couleurs autour de la bille courante*)
let cj = ref 0;; (*compteur du nombre d'appels a la fonction joue*)

(*affiche le tableau a l'ecran - attention la colonne 0 est a droite*)
let rec affiche(l,i,j)= 
	if (i=0) & (j=0) then 
			begin 	
				print_int(tab(l,i,j));
				print_newline();
				();
			end
	else 
		begin
			print_int(tab(l,i,j));
			if j=0 then 
				begin 
					print_newline();
					affiche(l,i-1,3)
				end
			else 
				begin
					print_string(" ");
					affiche (l,i,j-1);
				end
		end
;;

(*valeur renvoit la i-ème ligne d'un tableau l passé en paramètre*)
let rec valeur(l,i)=if i=0 then hd(l) else valeur(tl(l),i-1);;

(*val renvoi l'élément en position i,j du tableau l*)
let val(l,i,j)= valeur(valeur(l,i),j);;

(*identique à valeur*)
let rec donne_0(l,i)= if i=0 then hd(l) else donne_0(tl(l),i-1);;

(*identique à val*)
let donne_1(l,i,j)= donne_0(donne_0(l,i),j);;

(*place la liste val en i-ème ligne du tableau passé en paramètre*)
let rec place(l,i,val)= if i=0 then [val]@tl(l) else [hd(l)]@place(tl(l),i-1,val);;

(*remplace dans tableau l'élément à la position i,j par val*)
let remplace(t,i,j,val) = place (t,i,place(donne_0(t,i),j,val));;

(*descente deplace couleur à partir de la positon i,j vers le haut tant que la couleur au dessus est 0*)
let rec descente(l,i,j,couleur) = if donne_1(l,i+1,j)=0 then descente(l,i+1,j,couleur) else remplace(l,i,j,couleur);;

(*numlignetomber donne la ligne ou se trouve bille qu'on vient de lancer*)
let rec numlignetomber(l,i,j) = if not(donne_1(l,i,j)=0) then -1 else 1+numlignetomber(l,i+1,j);;

(***Appels pour tester numlignetomber***)
(*
  numlignetomber(tableau,0,0);;
*)

(*appelle descente depuis la ligne 0*)
let tomber(t,j,val)= descente(t,0,j,val);;

(***Appels pour tester les fonctions descente et tomber***)
(*
  descente(tableau,0,0,8);;
  affiche(descente(tableau,0,0,8),5,3);;
  tomber(tableau,2,9);;
  affiche(tomber(tableau,2,9),5,3);;
  affiche(tableau,5,3);;
*)

(*fonction qui compte le nombre d'elements couleur dans les 8 cases autour de la position i,j*)
let compteautour(t,i,j,couleur)= 
	c:=0; (*initialisation du compteur*)
	if (i>0) && (i<5) then (*si on n'est pas en dehors des lignes du tableau*)
		begin
			if (j>0) && (j<3) then (*si on n'est pas en dehors des colonnes du tableau*)
				begin (*cas general*)
					if donne_1(t,i-1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i-1,j)=couleur then c:=!c+1;
					if donne_1(t,i-1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
			if (j=0) then 
				begin (*premiere colonne*)
				    if donne_1(t,i-1,j)=couleur then c:=!c+1;
					if donne_1(t,i-1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
			if (j=3) then
				begin (*derniere colonne*)
					if donne_1(t,i-1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i-1,j)=couleur then c:=!c+1;
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
		end;	
	if (i=0) then (*premiere ligne*)
		begin
			if (j>0) && (j<3) then 
				begin (*cas general pour la premiere ligne*)
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
			if (j=0) then 
				begin (*premiere colonne*)
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
			if (j=3) then
				begin (*derniere colonne*)
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i+1,j)=couleur then c:=!c+1;
				end;
		end;
	
	if (i=5) then (*derniere ligne*)
		begin
			if (j>0) && (j<3) then 
				begin (*cas general pour la derniere ligne*)
					if donne_1(t,i-1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i-1,j)=couleur then c:=!c+1;
					if donne_1(t,i-1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
				end;
			if (j=0) then 
				begin (*premiere colonne*)
					if donne_1(t,i-1,j)=couleur then c:=!c+1;
					if donne_1(t,i-1,j+1)=couleur then c:=!c+1;
					if donne_1(t,i,j+1)=couleur then c:=!c+1;
				end;
			if (j=3) then
				begin (*derniere colonne*)
					if donne_1(t,i-1,j-1)=couleur then c:=!c+1;
					if donne_1(t,i-1,j)=couleur then c:=!c+1;
					if donne_1(t,i,j-1)=couleur then c:=!c+1;
				end;
		end;
	!c
;;

(***Appels pour tester la fonction compteautour***)
(*
  compteautour(tableau,4,1,2);;
*)

(*eclate remplace l'element en position i,j par 0 si il est de la bonne couleur et propage cette modification aux 8 elements autour*)
(*** ATTENTION CETTE FONCTION PROVOQUE UNE ERREUR DE MEMOIRE ***)
let rec eclate(t,i,j,couleur)= 
	if (i<0) || (j<0) || (i>5) || (j>3) || (donne_1(t,i,j) != couleur) then (*condition d'arret generale*)
		remplace(t,0,0,0) (*on ne fait rien - je voulais mettre (), mais ca provoque une erreur...*) 
	else (*on propage la transformation a toutes les cases voisines*)
		begin
			eclate(t,i-1,j-1,couleur);
			eclate(t,i-1,j,couleur);			
			eclate(t,i-1,j+1,couleur);
			eclate(t,i,j-1,couleur);
			eclate(t,i,j+1,couleur);
			eclate(t,i+1,j-1,couleur);
			eclate(t,i+1,j,couleur);
			eclate(t,i+1,j+1,couleur);
			remplace(t,i,j,0); (*on remplace la couleur par 0 - il est possible que cette ligne doivent aller au debut du begin mais erreur*)
		end
;;
(***Appels pour tester la fonction eclate***)
(*
  affiche(tableau,5,3);;
  affiche(eclate(tableau,3,0,1),5,3);; (* pas d'erreur, ce n'est pas la bonne couleur*)
  affiche(eclate(tableau,3,0,2),5,3);; (*** ATTENTION CET APPEL PROVOQUE UNE ERREUR DE MEMOIRE ***)
*)

(***Comme eclate provoque une erreur memoire, on cree eclatevertical qui ne regarde qu'au dessus - OUT OF MEMORY avec affiche***)
let rec eclatevertical(t,i,j,couleur)= 
	if (i>5) || (donne_1(t,i,j) != couleur) then (*condition d'arret generale*)
		remplace(t,5,3,1111) (*on ne fait rien - je voulais mettre (), mais ca provoque une erreur...*) 
	else (*on propage la transformation a toutes les cases voisines*)
		begin
			print_string("ligne ");
			print_int(i);
			print_newline();
			eclatevertical(t,i+1,j,couleur);
			remplace(t,i,j,0); (*on remplace la couleur par 0 - il est possible que cette ligne doivent aller au debut du begin mais erreur*)
		end
;;
(***Appels pour tester la fonction eclatevertical***)
(*
  let tableau = [[0;0;0;0];[0;0;0;0];[1;1;3;4];[2;1;4;6];[3;1;3;4];[2;2;1;9]];;
  affiche(tableau,5,3);;
  eclatevertical(tableau,2,1,1);;
  affiche(eclate(tableau,2,1,1),5,3);; (*provoque une erreur*)
*)

(************************************************************************************************************)
(* PROGRAMME PRINCIPAL **************************************************************************************)
(************************************************************************************************************)
let rec joue()=
	(*teste si on a perdu*)
	if (not(pr(tableau)=[0;0;0;0])) then
		begin
			print_string("VOUS AVEZ PERDU");
			();
		end
	else (*teste si on a gagne*)
		if (dr(tableau)=[0;0;0;0]) then
			begin
				print_string("VOUS AVEZ GAGNE");
				();
			end
	else
		begin
			(*appel recursif a la fonction pincipale joue*)
			(*on incremente le compteur de tours*)
			cj := !cj+1;
			(*affiche le tableau en cours*)
			affiche(tableau,5,3);
			(*tire au hasard une couleur*)
			let coul = random__int 10;
			(*affiche la couleur pour le joueur*)
			print_string("Votre couleur est ");
			coul;
			(*demande a l'utilisateur sur quelle colonne il veut tirer cette couleur*)
			print_string("Sur quelle colonne voulez-vous tirer ? ");
			let tircol = read_int(); (***IL MANQUE UNE FONCTION DE TYPE SCANF POUR DEMANDER LA COLONNE AU JOUEUR***)
			
			(*declenche le tir avec la fonction tomber*)
			tomber(tableau,tircol,coul);
			(*recupere la ligne du placement de la bille*)
			let nli = numlignetomber(tableau,0,tircol);
			
			(*si le nombre de billes de la meme couleur dans les 8 places autour est superieur a 2 alors les billes de cette couleur eclatent*)
			if (compteautour(tableau,nli,tircol,coul) >= 2) then
				(*ATTENTION CETTE FONCTION PROVOQUE UNE ERREUR DE MEMOIRE*)
				eclate(tableau,i,j,coul); (*appel a la fonction recursive eclate qui propage toute seule l'eclatement de couleurs*)

			(*tous les 5 tours on ajoute une ligne de couleurs aleatoires*)	
			if (cj mod 5 = 0) then 
				(* ajouteligne(tableau) *)
				let tableau = ajlignetab(tableau);
				
			(*appel recursif a joue*)
			joue();
		end
;;
(************************************************************************************************************)
(* FIN DU PROGRAMME PRINCIPAL *******************************************************************************)
(************************************************************************************************************)
