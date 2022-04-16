package com.example.randomvodkagenerator.rngs;

import java.util.ArrayList;
import java.util.function.Function;
import java.util.stream.Collectors;

public abstract class CongruentialNumberGenerator extends BasicNumberGeneratorBehaviour implements  NumberGenerator {
    int a;
    int m;
    Function<Integer, Integer> op;

    public CongruentialNumberGenerator(int seed, int a, int m) {
        super(seed);
        this.a = a;
        this.m = m;
    }

    protected int nextInteger(int seed) {
        return op.apply(seed);
    }

    @Override
    public ArrayList<Float> floatify(ArrayList<Integer> result) {
        return (ArrayList<Float>) result.stream().map(x -> (float) x / m).collect(Collectors.toList());
    }

}
