// fichier de fonctions
//construire le graphe en étoile

function [graphe] = etoile(n)
    /*sommets = n
    orientation = %F // graphes non-orienté
    un = ones(1,n-1) // matrice composée de 1 uniquement
    colonnes = [2:1:n]
    aretes = [un; colonnes]' // transposée de la matrice
    graphe = list(sommets,orientation,aretes)
    */
    
    un = ones(n-1,1)
    colonnes = (2:1:n)'
    aretes = [un, colonnes]' // transposée de la matrice
    graphe = list(n,%F,aretes)
endfunction

// appel de la fonction avec : [graphe] = etoile(6)
// avec une boucle for

function [graphe] = etoilefor(n)
    A = ones(n-1,2) // Matrice a de 2 colonnes , n lignes
    for k = 1:n-1
        A(k,2) = k + 1
    end
    graphe = list(n, %F, A)
endfunction

function [graphe] = etoilefor2(n)
    aretes =[]
    for i=2:n
        aretes = [aretes;1,i]
    end
    graphe = list(n,%F,aretes)
endfunction


//faire un cycle où les sommets sont reliés

function [graphe] = cycle(n)
    A = ones(1,n-1)
    for i=1:n-1
        A(1,i) = i + 1
    end
    graphe = list(n,%F,A)
endfunction



function [graphe] = cycle2(n)
	aretes = []
	for i = 1:n-1
		aretes = [aretes;i,i+1]
	end
	aretes = [aretes;n,1]
	graphe = list(n,%F,aretes)
endfunction




