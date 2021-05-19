//test.c
#include<stdio.h>
#include "carre.h"

int main(){
  unsigned x;
  printf("entre la valeur a calculer \n");
  scanf("%u",&x);

  printf("le carre de %u est %d\n",x,carre(x));

}
  
