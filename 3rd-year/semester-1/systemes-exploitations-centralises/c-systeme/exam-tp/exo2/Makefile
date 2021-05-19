# fichier makefile
all :  txt_t.o testTxt
testTxt : testTxt.o txt_t.o
	gcc -o testTxt testTxt.o txt_t.o
testTxt.o : testTxt.c txt.h
	gcc -c -o testTxt.o testTxt.c
txt_t.o : txt.c txt.h
	gcc -c -o txt_t.o txt.c
clean	:
	rm -f *.o

