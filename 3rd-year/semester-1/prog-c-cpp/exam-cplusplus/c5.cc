#include <iostream>

using namespace std;

int main() {
    int n = 10;
    for ( int i = 0; i<n; i++ ) {
        n++;
        cout << n;
        continue;
    }
    return 1;
}
