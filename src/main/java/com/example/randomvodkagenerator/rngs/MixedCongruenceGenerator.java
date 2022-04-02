package com.example.randomvodkagenerator.rngs;

public class MixedCongruenceGenerator extends LinearCongruenceGenerator {
    public MixedCongruenceGenerator(int seed, int a, int c, int m) {
        super(seed, a, c, m);
        NumberGeneratorMath.HullDobelTest(a, c, m);
    }
}
