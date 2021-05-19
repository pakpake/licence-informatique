---
title: Devoirs Maison de mathématiques n°2
author: pakpake
date: 7 Avril 2020
---

# Exercice 1
## Partie I
### 1) Montrons que $f$ est linéaire :

\begin{align*}
f(P) &= (X+1)P
\end{align*}

$\forall P, Q \in \mathbb{R}^2[X]$, on a :
\begin{align*}
    f(P+\lambda Q)&=(X+1)(P+\lambda Q) \\
                  &= (X+1)P + (X+1)\lambda Q \\
                  &= (X+1)P + \lambda (X+1)Q \\
                  &= f(P) + \lambda f(Q)
\end{align*}

Donc $f$ est une application linéaire.

### 2) Déterminons le noyau et l'image de $f$

On peut écrire $P(X)$ sous la forme $P(X)=aX^2+bX+c$

On a donc :
\begin{align*}
f(P)&=(X+1)P \\
f(aX^2+bX+c)&=aX^3+bX^2+cX+aX^2+bX+c \\
&=aX^3+(b+a)X^2+(c+b)X+c
\end{align*}

On peut maintenant résoudre $f(P)=0$

$$\left\{
\begin{array}{rl}
a&=0 \\
b+a&=0 \\
c+b&=0 \\
c&=0
\end{array}    
\right.
\Leftrightarrow
\left\{
\begin{array}{rl}
a&=0 \\
b&=0 \\
c&=0
\end{array}    
\right.
$$

Ce qui implique que $\mbox{Ker} f=vect(0)$

Ainsi $f$ est injective.

On a d'après le théorème du rang :
$$dim E_3=dim \mbox{Ker} f+dim \mbox{Im} f$$
Avec $E_3$ l'espace des polynomes

Or $dim \mbox{Ker} f= 0$ et $dim E_3=4$, donc $dim \mbox{Imf} f= 4$

On a ainsi $\mbox{Im} f =vect{(1,X,X^2,X^3)}$

Donc $dim \mbox{Im}f=dim\mathbb{R}^3=4$

Ainsi, $f$ est surjective.

Et comme $f$ est injective et surjective, f est donc bijective.

### 3)
\begin{align*}
f(1)&=(X+1)1=X+1 \\
f(X)&=(X+1)X=X^2+X \\
f(X^2)&=(X+1)X^2=X^3+X^2
\end{align*}

### 4)

$$
\begin{array}{ccc}
  &\begin{array}{ccc} f(1)&f(X)&f(X^2) \end{array} & \\
  A=&\left(\begin{array}{c@{\hspace*{3em}}c@{\hspace*{3em}}c}
            1&0&0\\
            1&1&0\\
            0&1&1\\
            0&0&1 \end{array} \right) & \begin{array}{c} e_1\\ e_2 \\ e_3 \\ e_4 \end{array}
\end{array}
= \mathcal{M}_{\mathbb{R}^2[X],\mathbb{R}^3[X]}(A)
$$

## Partie II

### 1) Montrons que $g$ est linéaire :

D'après la définition de dérivation, on a :

$$(P+Q)'(1)=P'(1)+Q'(1)$$
et,
$$(\lambda P)'(1)=\lambda P'(1)$$

Ainsi, on a :

$$g(P+\lambda Q)=g(P)+g(\lambda Q) = g(P) + \lambda g(Q)$$
Donc $g$ est linéaire.

### 2)
Sur $\mathbb{R}^3[X]$ :

\begin{align*}
P(X) &= aX^3 +bX^2+cX+d \\
P'(X) &= 3aX^2+2bX+c \\
P''(X) &= 6aX+2b \\
\mbox{On a donc} &  \\
P(1) &= a+b+c+d \\
P'(1)&=3a+2b+c \\
P''(1)&=6a+2b
\end{align*}

Ainsi $\mbox{Ker}g \Leftrightarrow \{(P(1);P'(1);P''(1))\} = {(0,0,0)}$

$$
\left\{
\begin{array}{rl}
a+b+c+d &= 0 \\
3a+2b &= 0 \\
6a+2b &=0
\end{array}    
\right.
%
\Leftrightarrow
%
\left\{
\begin{array}{rl}
a+b+c+d &= 0 \\
2b &= -3a-c \\
a &= \frac{-2b}{6} = \frac{-1}{3}b
\end{array}    
\right.
%
\Leftrightarrow
%
\left\{
\begin{array}{rl}
a+b+c+d &= 0 \\
2b &= b-c \\
a &= \frac{-1}{3}b
\end{array}    
\right.
$$

$$
\Leftrightarrow
%
\left\{
\begin{array}{rl}
c &= \frac{1}{3}b +c-d \\
b &= -c \\
a &= \frac{-1}{3}b
\end{array}    
\right.
%
\Leftrightarrow
%
\left\{
\begin{array}{rl}
a &= \frac{1}{3}c \\
b &= -c \\
d &= \frac{-1}{3}c
\end{array}    
\right.
$$

On a donc $g=vect{(\frac{1}{3};-1;-\frac{1}{3})} \\$
On a $\mbox{Ker}g=dim 1$ et de plus, $\mbox{Ker}g$ n'est pas égale au vecteur nul, donc
on en déduit que $g$ n'est pas injective

De plus,

$dim E \neq dim \mbox{Im}g$

avec $dim E = 4$ et $dim \mbox{Im}g = 3$

Ainsi on peut dire que $g$ n'est pas surjective. Donc, $g$ n'est pas bijective.

### 3) On peut calculer $g(1),g(X),g(X^2) \mbox{ et } g(X^3)$

On a $\mbox{Im}g=vect{(1,,X^2,X^3)}$

On a donc : 

\begin{align*}
g(1)&=(1,0,0) \\
g(X)&=(X,1,0) \\
g(X^2)&=(X^2,2X,2) \\
g(X^3)&=(X^3,3X^2,6X)
\end{align*}

On a ainsi la matrice suivante :

$$
\begin{array}{cccc}
  &\begin{array}{cccc} g(1)&g(X)&g(X^2)&g(X^3) \end{array} & \\
  B=&\left(\begin{array}{c@{\hspace*{3em}}c@{\hspace*{3em}}c@{\hspace*{3em}}c}
            1&1&2&0\\
            0&1&2&6\\
            0&0&1&3\\
            0&0&0&1 \end{array} \right)
\end{array}
= \mathcal{M}at\ g
$$

## Partie III

On a :

\begin{align*}
h&=g\circ f \\
g\circ f&=\mathcal{M}at\ g*\mathcal{M}at\ f = \mathcal{M}at\ C
\end{align*}
$$
\Leftrightarrow
%
\begin{pmatrix}
 1&1&2&0\\ 
 0&1&2&6\\ 
 0&0&1&3\\
 0&0&0&1
\end{pmatrix}
.
\begin{pmatrix}
 1&0&0\\ 
 1&1&0\\ 
 0&1&1\\
 0&0&1
\end{pmatrix}
=
\begin{pmatrix}
2&3&2\\
1&3&8\\
0&1&4\\
0&0&1
\end{pmatrix}
$$

\clearpage

# Exercice 2

### 1)

On a :

$$
\mathcal{M}_{\varepsilon}(f)=
\begin{array}{ccc}
  &\left(\begin{array}{c@{\hspace*{3em}}c@{\hspace*{3em}}c}
            2&0&-1\\
            0&3&0\\
            1&2&1 \end{array} \right) & \begin{array}{c} e_1\\ e_2 \\ e_3 \end{array}
\end{array}
$$

### 2)

Calcul du déterminant :

$det\ \mathcal{M}(f)=3(2-(1*(-1)))=3(2+1)=9\neq0$

Comme le déterminant est non-nul, on peut dire que la matrice est inversible, et que les vecteurs colonnes forment une base.$\\$
Aussi, comme $det\ \mathcal{M}(f)\neq0$, on peut dire que l'application $g$ est injective.$\\$
De plus, on sait que le rang d'une matrice $n*n$ est $n$.$\\$
D'où ici, $rg(f)=3$, ainsi, $f$ est surjective.$\\$
Et comme, $g$ est injective et surjective, $g$ est bijective.

### 3)
Soient les vecteurs : $$b_1=(1,0,1),\quad b_2=(1,1,1),\quad b_3=(-1,1,0).$$

### 4)
Montrons que la famille $\mathcal{B={b_1,b_2,b_3}}$ est une base.

On obitent la matrice suivante :


$$
\begin{array}{ccc}
  &\begin{array}{ccc} b_1&b_2&b_3 \end{array} & \\
  \mathcal{M}_{\mathcal{B}}=&\left(\begin{array}{c@{\hspace*{1em}}c@{\hspace*{1em}}c}
            1&1&-1\\
            0&1&1\\
            1&1&0 \end{array} \right) 
\end{array}
$$

On peut ainsi calculer le déterminant :

\begin{align*}
det\ \mathcal{B}&=1(1*0-1*1) \\
&=1(0-1) \\
&=(-1)\neq 0
\end{align*}

Donc, la matrice est inversible, et on peut dire que la famille $\mathcal{B}={(b_1,b_2,b_3)}$ forme une base.


