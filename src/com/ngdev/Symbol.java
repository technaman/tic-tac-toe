package com.ngdev;

public class Symbol {
    private int value;
    private char sign;

    public Symbol(int value, char sign) {
        this.sign = sign;
        this.value = value;
    }

    public int getValue() {
        return value;
    }

    public char getSign() {
        return sign;
    }
}
