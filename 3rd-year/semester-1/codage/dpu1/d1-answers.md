---
title: Exercices DPU1
date: Novembre 2020
---

# Exercice 1.2

### 1)

On a : 

$$d_H(\hat{w}_1,\tilde{w}) \leq d_H(v,\tilde{w}) \quad \forall v \in \mathcal{C\{0,1\}^m}$$
Or
$$d_H(w,\tilde{w}) \leq k$$ 

Donc
$$d_H(\hat{w}_1,\tilde{w}) \leq k$$
De même
$$d_H(\hat{w}_2,\tilde{w}) \leq k$$

En sommant : 
$$d_H(\hat{w}_1,\tilde{w}) + d_H(\hat{w}_2,\tilde{w}) \leq 2k$$

Or $d_H$ est une distance, donc : 
$$d_H(\hat{w}_1,\hat{w}_2) \leq d_H(\hat{w}_1,\tilde{w}) + d_H(\hat{w}_2,\tilde{w})$$
$$d_H(\hat{w}_1,\hat{w}_2) \leq 2k$$

### 2)

$$d_H(\hat{w}_1,\hat{w}_2) \leq 2k$$
$$d_H(\hat{w}_1,\hat{w}_2) \leq 2\left\lfloor\frac{\delta{\mathcal{(C)}-1}}{2}\right\rfloor$$
$$d_H(\hat{w}_1,\hat{w}_2) \leq \delta{\mathcal{(C)}-1}$$

Or $\delta{\mathcal{(C)}}$ est le minimum de la distance de Hamming entre 2 mots différents.

Si :
$$d_H(\hat{w}_1,\hat{w}_2) \leq \delta{\mathcal{(C)}-1}$$
Alors, la seule solution est : 
$$\hat{w}_1 = \hat{w}_2$$


### 3)

On a :
$$d_H(\hat{w}_1,\tilde{w}) \leq d_H(v,\tilde{w}) \quad \forall v \in \mathcal{C\{0,1\}^m}$$
Donc : 
$$\hat w_1 = \arg \min_{v \in \mathcal{C}(\{0,1\}^m)} d_H(\tilde w, v)$$

Non, cela ne suffit pas à démontrer le théorème.

### 4)

D'après la définition $\hat w_1$ a au plus k bits différents du mot d'origine
$$d_H(w,\hat w_1) \leq k$$
Mais
$$\hat w_1 = \arg \min_{v \in \mathcal{C}(\{0,1\}^m)} d_H(\tilde w, v)$$
Donc :
$$d_H(w,\hat w_1) = \delta(\mathcal{C})$$

### 5)

$$\left\lfloor\frac{\delta{\mathcal{(C)}-1}}{2}\right\rfloor < k < \delta(\mathcal{C})-1$$
Si
$$\left\lfloor\frac{\delta{(C)}-1}{2}\right\rfloor < k \quad \mbox{ (voir remarque 1.3)}$$
Alors $\mathcal{C}$ est un l-correcteur


# Exercice 1.3

### 1)

$\mathcal{C}$ est une application injective
$$\mathcal{C}:\{0,1\} \rightarrow \{0,1\}^2$$
L'ensemble des mots du code est l'image de $\{0,1\}$ par $\mathcal{C}$, $\{00,11\}$ noté :
$$\mathcal{\{0,1\}}=\{\mathcal{C}(u),u\in\{0,1\}\}$$

### 2)

* Redondance :
    
    D'après la définition 1.6 : 

    redondance = 2-1 = 1

* Rendement : 

    rendement = $\rho=\frac{1}{2}$
    
* Distance minimale : 

$$\delta\mathcal{(C)}=\min_{v,w\in\mathcal{C}(\{0,1\}),\atop v\neq w}d_H(v,w)$$
$$=d_H(00,11)=2$$

### 3)

La capacité de ce code à détecter les erreurs de transmission est : 
$$k=\delta\mathcal{(C)}-1=2-1=1$$
Donc, on peut détecter 1 seule erreur.

### 4)

La capacité de ce code à corriger les erreurs de transmission est :
$$k=\left\lfloor\frac{\delta\mathcal{(C)}-1}{2}\right\rfloor$$
$$=\left\lfloor\frac{2-1}{2}\right\rfloor$$
$$=0$$
Ce qui signifie que le code est donc impossible à corriger.

### 5)

$$\Omega = \{ii\}^{m\times n} \mbox{ pour } i \in \{0,1\}$$

### 6)

### 7)


# Exercice 1.5

### 1)

On note $d$ un majorant de $\delta\mathcal{(C)}$

$$d=n-m+1$$
$$d=4$$
$$\delta\mathcal{(C)}\leq 4$$
$$k \leq \left\lfloor\frac{\delta\mathcal{(C)}-1}{2}\right\rfloor$$
$$k \leq \left\lfloor\frac{4-1}{2}\right\rfloor$$
$$k \leq 1$$

### 3)

En procédant au même raisonnement que dans le 1), on en déduit que $n=5$
C'est un code parfait

### 4)

3

### 5)

2
