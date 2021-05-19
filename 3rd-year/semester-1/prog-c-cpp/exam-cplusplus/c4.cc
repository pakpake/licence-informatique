#include<iostream>

using namespace std;
class A {
    public:
        virtual void myfunction() {
            cout << "A" << " " ;
        }
        A() { cout << " cons A"; }
};

class B {
    public:
        virtual void myfunction() { 
            cout << "B" << " " ;
        }
        B() { cout << " cons B"; }
};

class C : public A, public B {
    public:
        virtual void myfunction() { 
            cout << "C" << " " ;
        }
        C() { cout << " cons C"; }
};
int main(void) {
    //A *a = new C;
    //A *b = new B;

    //a-> myfunction();
    //b-> myfunction();

    C c;

    return 0;
}
