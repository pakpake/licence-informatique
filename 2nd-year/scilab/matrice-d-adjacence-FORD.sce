//matrice d'adjacence

// on peut en rajouter d'autre pour tester par la suite
// autre matrice d'exemple
B = [ 0 6 13 %inf ; %inf 0 2 15 ; %inf %inf 0 3 ; %inf %inf %inf 0 ]


//matrice d'exemple de l'algo de Ford (poly)

A=[ 0 6 %inf 2 %inf %inf 8 %inf ; %inf 0 %inf 1 %inf %inf 2 %inf ; %inf 4 0 %inf %inf 1 %inf %inf ;
%inf %inf %inf 0 1  %inf %inf %inf ; %inf 2 8  %inf 0 9 %inf %inf ; %inf %inf %inf %inf %inf  0 %inf 2 ; %inf %inf 3  %inf %inf %inf  0 7 ; %inf %inf %inf %inf  %inf %inf %inf  0 ]


// Algo de FORD


function [ potentiel,nbiter ] = FORD(A)
    // on recupere l'ordre de la matrice
    [n,p]=size(A)

    // initialisation du vecteur potentiels
    // matrice 8*8 remplie de Inf.
    // n = size(A)(1)
    potentiel = ones(1,n) * %inf    //on créer un vecteur potentiel de taille n avec +Inf
    potentiel(1) = 0        // pi(1)=0 
    
    change = %T //True
    nbiter = 0

    while change
        change = %F
        nbiter = nbiter + 1
        //disp(nbiter,"nbiter = ")
        // pour tout i de 2 a n , calculer le min donné par FORD
        // et on met a jour 'change' dès qu'un potentiel change
        for i=1:n
            pi = %inf    //on créer un pi = +Inf
            //disp(pi,"pi =")
            for j=1:n       // on parcourt les predecesseur de i
                if pi > potentiel(j) + A(j,i) then
                    pi = potentiel(j) + A(j,i)
                    //disp(j,"j =",i,"i =",pi,"pi =")
                end
            end
            if potentiel(i) > pi then
                potentiel(i) = pi
                change = %T
                //disp(potentiel,"potentiel =")
            end
        end
    end
endfunction

[p,n]=FORD(A)
disp(A,"pour la matrice :")
disp(n,"le nombre d itérations nécessaires est :")
disp(p,"les potentiels sont :")

[p,n]=FORD(B)
disp(B,"pour la matrice :")
disp(n,"le nombre d itérations nécessaires est :")
disp(p,"les potentiels sont :")
