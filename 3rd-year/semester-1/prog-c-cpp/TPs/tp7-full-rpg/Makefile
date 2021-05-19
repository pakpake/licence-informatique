CC=g++
CFLAGS=-Wall
EXEC=personal-tests
EXECT=catch-tests

all: personal test

personal: compile link

compile:
	$(CC) -c test/Main.cpp -o test/Main.o $(CFLAGS)

link:
	$(CC) -o $(EXEC) test/Main.o

test: compileTest linkTest

compileTest:
	$(CC) -c test/MainTest.cpp -o test/MainTest.o $(CFLAGS)

linkTest:
	$(CC) -o $(EXECT) test/MainTest.o

clean:
	rm -rf test/*.o
