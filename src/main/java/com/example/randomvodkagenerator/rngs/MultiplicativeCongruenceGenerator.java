package com.example.randomvodkagenerator.rngs;

public class MultiplicativeCongruenceGenerator extends CongruentialNumberGenerator {
    public MultiplicativeCongruenceGenerator(int seed, int a, int m) {
        super(seed, a, m);
        op = (x1 -> (a * x1) % m);
    }

    @Override
    public boolean validate(CheckTypes c) {
        return false;
    }
}
