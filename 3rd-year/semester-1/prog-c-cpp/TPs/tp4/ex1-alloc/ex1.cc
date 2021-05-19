#include <iostream>
#include "mem.h"

using namespace std;

// les variables déclarées normalement sont sur la pile (stack)
// les variables dynamiques sont sur le tas (heap)

int main() {
    double vm,rss;

    cout << "Initialisation du programme" << endl;

    mem_usage(vm,rss);
    cout << "  Virtual Memory : " << vm << endl;
    cout << "  Resident set size : " << rss << endl;

    // char toto[1024]={'0'};        // déclaration de 1kb sur la pile
    char tota[1024*1024]={'0'};        // déclaration de 1Mb sur la pile
    // char toti[1024*1024*1024]={'0'};        // déclaration de 1Gb sur la pile
    // char totu[10*1024*1024*1024]={'0'};        // déclaration de 10Gb sur la pile
    /* segmentation fault à partir de 1 Gb */

    cout << "Mémoire après allocation dans la pile (stack)" << endl;

    mem_usage(vm,rss);
    cout << "  Virtual Memory : " << vm << endl;
    cout << "  Resident set size : " << rss << endl;

    // long long taille=10*1024*1024*1024*1024;
    // char * c0 = new char[1024];
    // char * c1 = new char[1024*1024];
    char * c2 = new char[1024*1024*1024];
    // char * c3 = new char[taille];
    
    cout << "Mémoire après allocation dynamique (heap)" << endl;
    
    mem_usage(vm,rss);
    cout << "  Virtual Memory : " << vm << endl;
    cout << "  Resident set size : " << rss << endl;

    return 0;
}
