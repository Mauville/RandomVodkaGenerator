package com.example.randomvodkagenerator.rngs;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

public class CongruentialNumberGenerator extends BasicNumberGeneratorBehaviour implements CheckableNumberGenerator {
    int seed;
    int a;
    int c;
    int m;
    Function<Integer, Integer> op;

    @Override
    public boolean validate(CheckTypes c) {
        // TODO
        return false;
    }

    public CongruentialNumberGenerator(int seed, int a, int c, int m, boolean HullDobell, boolean multiplicative) {
        if (HullDobell) {
            NumberGeneratorMath.HullDobelTest(a, c);
        }
        this.seed = seed;
        this.a = a;
        this.c = c;
        this.m = m;
        op = multiplicative ? (x1 -> (a * x1) % m) : (x1 -> (a * x1 + c));
    }


    public int nextInteger(int seed) {
        return op.apply(seed);
    }

    public ArrayList<Float> floatify(ArrayList<Integer> result) {
        return (ArrayList<Float>) result.stream().map(x -> (float) x / m).collect(Collectors.toList());
    }
}
