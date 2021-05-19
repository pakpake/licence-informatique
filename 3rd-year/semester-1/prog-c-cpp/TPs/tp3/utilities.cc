#include "utilities.h"

// get an random float between 0 and 1
double getRand() {
    return (double) rand() / (double) RAND_MAX;
}

// returns a double between a and b
double randouble(double a, double b) {
    return a+((double)rand())*(b-a) / RAND_MAX;
}


