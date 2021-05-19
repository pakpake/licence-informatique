let rec add(x,y)=
	if y=0 
		then x 
			else add(x,y-1)+1;;
let rec mul(x,y)=
	if y=0 
		then x 
			else mul(x,y-1)+x;;
let rec puiss(x,y)=
	if y=0 
		then x 
			else puiss(x,y-1)*x;;
let rec div(x,y)=
	if x<y 
		then 0 
			else div(x-y,y)+1;;
let rec rdiv(x,y)=
	if x<y 
		then x 
			else rdiv(x-y,y);;

let pr(s)=nth_char s 0;;
let sp(s)=sub_string s 1 (string_length(s)-1);;
let dr(s)=nth_char s (string_length(s)-1);;
let sd(s)=sub_string s 0 (string_length(s)-1);;
let lg(s)=string_length s;;
(traitement de texte)

let rec app(c,s)=
	if S="" 
		then false 
			else if pr(s)=c 
				then true 
					else app(c,sp(s));;

let rec nbocc(c,s)=
	if S="" 
		then 0 
			else if c=pr(s) 
				then 1+nbocc(c,sp(s)) 
					else 0+nbocc(c,sp(s));;

let rec nbmot(s)= 
	if lg(s)=2
		then if (pr(s)=` `)&&(pr(sp(s))=` `)
			then 0
				else 1
					else if (pr(s)!=` `)&&(pr(sp(s))=` `)
						then 1+nbmot(sp(s))
							else 0+nbmot(sp(s));;


let rec palin(s)= 
	if lg(s)<=1
		then true
			else if pr(s)=dr(s)
				then palin(sp(sd(s)))
					else false;;

let rec phpalin(s)=
	if lg(s)<=1
		then true
			else if pr(s)=dr(s)
				then phpalin(sp(sd(s)))
					else if (pr(s)=` `)or(pr(s)`,`)
						then phpalin(sp(s))
							else if dr=` `
								then phpalin(sd(s))
									else false;;

let rec returnphrase(s)= 
	if string_length(s)=1
		then S 
			else returnphrase(sp(s))^(string_of_char (pr(s)));;

let rec suppocc(c,s)=
	if c=""
		then c
			else if pr(c)=s
				then suppocc(sp(c),s)
					else string_of_char(pr(c))^suppocc(sp(c),s);;
let rec prmot(s)=
	if pr(s)=` `
		then ` `
			else chaine(pr(s))^spmot(sp(s));;


let rec enleveblanc(s)=
	if pr(s)=` `
		then enleveblanc(s)
			else s;;

let rec enlevechar(s)=
	if pr(s)!=` `
		then enlevechar(sp(s))
			else s;;

let rec spmot(s)= enleveblanc(enlevechar(s));;

let rec suppmot(s1,s2)=
	if s=""
		then s
			else if prmot(s2)=s1
				then suppmot(s1,spmot(s2))
					else (prmot(s1))^" "^suppmot(s1,spmot(s2));;

let rec suprime(s1,s2)=
	if prmot(s2)=s1
		then spmot(s2)
			else prmot(s2)^" "^supprime(s1)spmot(s2);;

let rec remplace(s,s1,s2)=
	if s=""
		then s
			else if pr(s)=s1
				then s2^" "^remplace(spmot(s),s1,s2)
					else prmot(s)^""^remplace(spmot(s),s1,s2);;

let rec fusion(s1,s2)=
	if (s1="")or(s2="")
		then if s1="" then s2 else s1
			else prmot(s1)^" "^prmot(s2)^" "^fusion(spmot(s1),spmot(s2);;

let rec vide(l)= if l=[]
	then true
		else false ;;

let rec lg(l)= if l=[]
	then 0
		else lg(tl(l)) ;;

let rec ajoute(x,l)= x::l ;;


let rec ajoute_position (pos,x,l)=
if pos=0
then ajoute(x,l)
else [hd(l)]@ajoute_position(pos-1,x,tl(l));;


let rec appartient(x,l)=
if vide(l)   
then false 
else if x=hd(l) 
then true 
else appartient(x,tl(l));;


let rec list_of_string(s)=
if s=""
then []
else [pr(s)]@list_of_string(sp(s));; 



let chaine(c)=string_of_char(c);;



let rec string_of_list(l)= 
if l=[] 
then ""
else chaine(hd(l))^string_of_list(tl(l));;



let rec debut(l,n)=
if n=0
then []
else [pr(l)]@debut(sp(l),n-1);; 


let rec fin (l,n)=
if n=0
then [] 
else fin(sd(l),n-1)@[dr(l)];;


let intervertir_list(l) =
fin (l, lg[l]/2)@debut(l, lg[l]/2);;

let rec change_l(l,n)=
if n=0
then l
else change_l(sauf_nieme(l,n),n-1)@[nieme(l,n)] ;;


let rec nieme(l,n)=
if n=1
then hd(l)
else nieme(tl(l),n-1) ;;


let rec sauf_nieme(l,n)=
if n=1
then tl(l)
else [hd(l)]@sauf_nieme(tl(l),n-1) ;;


let rec fusion(l1,l2)=
if vide (l1)
then l1@l2
else [hd(l1)]@[hd(l2)]@fusion(tl(l1),tl(l2)) ;;


let rec donne(l,i)=
if i=0 
then hd(l)
else donne (tl(l),i-1);;


let rec donnetableau (t,i,j)=
donne(donne(t,i),j);;


let rec Remplace(l,i,x)=
if i=0
then [x]@tl(l)
else [hd(l)]@Remplace(tl(l),i-1,x);; 


let rec Remplacetableau (t,i,j,x)=
if i=0 
then [Remplace(hd(t),j,x)]@tl(t)
else [hd(t)]@Remplacetableau(tl(t),i-1,j,x);;


let rec swap (t,i1,j1,i2,j2)=
Remplacetableau(Remplacetableau(t,i1,j1, donnetableau(t,i2,j2)),i2,j2,donnetableau(t,i1,j1));;


let rec colonne (t,j)=
if t=[]
then []
else [donne(hd(t),j)]@colonne(tl(t),j);;


let rec diagonale(t,j)=
if t=[]
then [] 
else [donne(hd(t),j)]@diagonale(tl(t),j+1);;

