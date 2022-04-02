package com.example.randomvodkagenerator.rngs;

public class LinearCongruenceGenerator extends CongruentialNumberGenerator {
    int c;

    public LinearCongruenceGenerator(int seed, int a, int c, int m) {
        super(seed, a, m);
        this.c = c;
        op = (x1 -> (a * x1 + c) % m);
    }

}
