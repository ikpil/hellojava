package com.ikpil.hello.java.spring.ffmspring;

public class AddCalculator implements Calculator {
    @Override
    public int calc(int a, int b) {
        return a + b;
    }
}
