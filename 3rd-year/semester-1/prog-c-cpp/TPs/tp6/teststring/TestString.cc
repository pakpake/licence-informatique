#include "String.h"

using namespace std;

int main() {

    String s1("Chaine 1 ");
    String s2("Chaine 2 ");
    String s3(s1);

    cout << "s1 = " << s1 << endl;
    cout << "s2 = " << s2 << endl;
    cout << "s3 = " << s3 << endl;

    cout << endl;
    s3 = s1 + s2;
    cout << "s3 = s1 + s2" << endl;
    cout << s3 << endl << endl;

    cout << "s3 = s3 + \"une chaine apres \"" << endl;
    s3 = s3 + "une chaine apres ";
    cout << s3 << endl << endl;

    cout << "s2 = \"Une chaine avant \" + s3" << endl;
    s2 = "Une chaine avant " + s3;
    cout << s2 << endl;

}
