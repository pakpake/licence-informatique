---
title: TD 2 Exercies 3 & 4
author: pakpake 
date: '2020-01-25'
---

## Exercice 3

#### A)  

La complexité est :

$$\mathcal{O}(2+\min(m,n)*4)$$

Ce qui donne :
 
$$\mathcal{O}(\min(m,n))$$

#### B)

La complexité est :

$$\mathcal{O}(\max(m,n))$$

#### C)

La complexité est :

$$\mathcal{O}(m+n)$$

#### D)

La complexité est :

$$\mathcal{O}(m*n+n)$$

Ce qui donne :

$$\mathcal{O}(m*n)$$

## Exercice 4

#### a)

$$\mathcal{O}(2+[3+(n+1)*3](n+1))$$

Ce qui donne : 

$$\mathcal{O}(n^2)$$

#### b)

$$\mathcal{O}(2+(n+1)(4+6n^2))$$

Ce qui donne :

$$\mathcal{O}(n^3)$$

#### c)

Dans la boucle `while (j<=i):`, l'incrément à l'intérieur de la boucle porte sur `s`, ainsi la boucle est donc infinie.

#### d)

La boucle intérieure `while k<=j` est effecutée autant de fois que la valeur de $j$ dans la boucle au dessus soit $i^2$ fois. On a donc, autant d'itérations que

$$
\sum_{k=0}^{i^2}(k+1)+5 = \mathcal{O}\left(\frac{i^2(i^2+1)}{2}\right) = \mathcal{O}(i^4)
$$

Or $i$ varie de $1$ à $n$ dans la boucle principale. On a donc une
complexité de l'ordre de :  

$$
\mathcal{O}\left(
\sum_{i=1}^n i^4
\right) = \mathcal{O}\left(\frac{n}{30}(n+1)(2n+1)(3n^2+3n+1)\right) =
\mathcal{O}(n^5)
$$
