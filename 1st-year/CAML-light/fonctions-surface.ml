let surface_mur(longueur_mur,hauteur_mur) = longueur_mur*.hauteur_mur ;;
let surface_r(lr,hr) = lr*.hr ;;
let nb_r(longueur_mur,hauteur_mur,lr,hr) = surface_mur(longueur_mur,hauteur_mur)/.surface_r(lr,hr) ;;
let round x =
if x >=. 0.0 then int_of_float (x +. 0.5)
else int_of_float (x -. 0.5);;
round(nb_r(3.0,2.5,0.6,15.0)) ;;
