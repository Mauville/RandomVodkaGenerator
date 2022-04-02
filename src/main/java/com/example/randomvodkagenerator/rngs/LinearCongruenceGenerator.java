package com.example.randomvodkagenerator.rngs;

import java.util.ArrayList;
import java.util.stream.Collectors;

public class LinearCongruenceGenerator extends CongruentialNumberGenerator {
    int c;

    @Override
    public boolean validate(CheckTypes c) {
        return false;
    }

    public LinearCongruenceGenerator(int seed, int a, int c, int m) {
        super(seed, a, m);
        this.c = c;
        op = (x1 -> (a * x1 + c) % m);
    }

}
