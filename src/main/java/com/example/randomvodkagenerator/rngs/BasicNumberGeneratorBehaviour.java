package com.example.randomvodkagenerator.rngs;

import java.util.ArrayList;

public abstract class BasicNumberGeneratorBehaviour implements NumberGenerator{
    int seed;

    abstract ArrayList<Float> floatify(ArrayList<Integer> result);

    abstract int nextInteger(int seed);

    @Override
    public ArrayList<Float> generate(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        result.add(seed);
        for (int i = 0; i <= n; i++) {
            int next = nextInteger(result.get(i));
            if (next == seed) {
                return floatify(result);
            }
            result.add(seed);
        }
        return floatify(result);
    }

}
