---
title: Compte Rendu TD3
author: pakpake 
date: Février 2020
---

# Exercice 2

## Question 4

$$f(n) = n \quad;\quad g(n) = \log^2(n)$$

On utilise la formule des limites usuelles pour les logarithmes :

$$ \lim_{n\to+\infty}\frac{ln^\alpha(n)}{n^\beta}=0 $$

Ainsi, $\forall \alpha,\beta\geq 0$, on a $$ ln^\alpha(n)=\mathcal{O}(n^\beta) $$

Dans notre cas, on choisit $\alpha=2$ et $\beta=1$.

A la constante près du changement de base des logarithmes, on a :
$$ \log^2(n)=\mathcal{O}(n) $$
$$ g(n)=\mathcal{O}(f(n)) $$

## Question 5 

$$ f(n)=n\log(n)+n \quad;\quad g(n)=\log(n) $$ 

$$\lim_{n\to+\infty}\frac{\log(n)}{n\log(n)+n}=\lim_{n\to+\infty}\frac{\log(n)}{n\log(n)}=\lim_{n\to+\infty}\frac{1}{n}=0$$
Donc $$\log(n)=\mathcal{O}(n\log(n)+n)$$
Donc $$g(n)=\mathcal{O}f(n)$$


## Question 6

$$ f(n)=10 \quad;\quad g(n)=\log(10) $$

D'après la définition, on a :
$$\exists c, \exists n_0, \forall n \geq n_0 \qquad f(n)\leq c.g(n)$$
$$ 10 \leq 10\log(10) $$

Donc, $$ f(n)=\mathcal{O}(g(n)) $$
De même, $$ \log(10) \leq 10 $$
Donc, $$ g(n)=\mathcal{O}(f(n)) $$


