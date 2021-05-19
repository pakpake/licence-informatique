CC=g++
CFLAGS=-Wall
LDFLAGS=
EXEC=TestDamageable
HEADER=$(wildcard *.h)
SRC=$(wildcard *.cc)
OBJ= $(SRC:.cc=.o)

all: $(EXEC)

$(EXEC): $(OBJ)
	$(CC) -o $@ $^ $(LDFLAGS)

$(EXEC).o: $(HEADER)

%.o: %.cc
	$(CC) -o $@ -c $< $(CFLAGS)

.PHONY: clean mrproper 

clean:
	rm -rf *.o

mrproper: clean
	rm -rf $(EXEC)
