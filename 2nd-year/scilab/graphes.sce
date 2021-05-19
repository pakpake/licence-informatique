//Fichier de fonctions
function [A,b]=firstFct(n)
    //Construction d'une matrice A composé de 0 avec A(1,1) = 1
    A = zeros(n,n);
    A(1,1) = 1;
    //Construction d'une matrice de b d'une colonne de zeros sauf b(1,1) = 1
    b = ones(n,1);
    b(1,1) = 0;
endfunction

