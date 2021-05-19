# fichier makefile
all :  test libCarreCube.so
test : test.o  libCarreCube.so
	gcc  -o test test.o -L . -lCarreCube
libCarreCube.so : cube.o carre.o
	gcc -shared  -o libCarreCube.so cube.o carre.o
test.o : test.c carre.h
	gcc -c -o test.o test.c
carre.o : carre.c carre.h
	gcc  -fPIC -c -o carre.o carre.c
cube.o : cube.c 
	gcc  -fPIC -c -o cube.o cube.c
clean :
	rm carre.o test.o