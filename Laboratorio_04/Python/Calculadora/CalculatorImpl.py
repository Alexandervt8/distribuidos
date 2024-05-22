# main.py

import Calculator

def main():
    a = 10
    b = 5

    print("Addition:", Calculator.add(a, b))
    print("Subtraction:", Calculator.sub(a, b))
    print("Multiplication:", Calculator.mul(a, b))
    print("Division:", Calculator.divide(a, b))

    b = 0
    print("Division with zero:", Calculator.divide(a, b))

if __name__ == "__main__":
    main()