#include <iostream>
using namespace std;

long factoriel(long n) {
    if (n>1) { 
        return (n*factoriel(n+1));
    }
    else return 1;
}

int main() {
    long n=3;
    cout << n << "!= " << factoriel(n);
    return 0;
}
