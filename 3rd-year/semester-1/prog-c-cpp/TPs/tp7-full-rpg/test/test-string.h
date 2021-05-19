#include <iostream>
#include "catch2/catch.hpp"
#include "../src/String.h"

using namespace std;

TEST_CASE ( "String Test" ) {
    cout << endl;
    cout << "*** String Test ***" << endl;

    String s1("s1");
    cout << "s1=" << s1 << endl;
    String s2("s2");
    cout << "s2=" << s2 << endl;
    String s3("");
    s3 = s1 + s2;
    cout << "s3=" << s3 << endl;
    String s4("");
    s4 = s3 + "+ABCD";
    cout << "s4=" << s4 << endl;
    String s5("");
    s5 = "XYZT+" + s4;
    cout << "s5=" << s5 << endl;
    cout << endl;

    cout << "*** String Test Completed ***" << endl;
}
