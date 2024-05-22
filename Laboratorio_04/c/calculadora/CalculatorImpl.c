#include "Calculator.h"
int add(int a, int b) {
    return a + b;
}
int sub(int a, int b) {
    return a - b;
}
int mul(int a, int b) {
    return a * b;
}
int divide(int a, int b) { 
    if (b == 0) {
        return 0; // Manejar división por cero
    }
    return a / b;
}