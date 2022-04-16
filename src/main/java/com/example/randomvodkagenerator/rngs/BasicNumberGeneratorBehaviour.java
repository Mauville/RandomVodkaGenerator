package com.example.randomvodkagenerator.rngs;

import java.util.ArrayList;

public abstract class BasicNumberGeneratorBehaviour implements NumberGenerator, FloatifyBehaviour {
    int seed;

    public BasicNumberGeneratorBehaviour(int seed) {
        this.seed = seed;
    }

    public abstract ArrayList<Float> floatify(ArrayList<Integer> result);

    abstract int nextInteger(int seed);

    public ArrayList<Integer> rawGenerate(int n) {
        ArrayList<Integer> result = new ArrayList<>();
        // Add the seed to aid on generation
        result.add(seed);
        for (int i = 0; i < n; i++) {
            int next = nextInteger(result.get(i));
            result.add(next);
//            if (next == seed) {
//                break;
//            }
        }
        // Remove the seed
        result.remove(0);
        return result;
    }

    @Override
    public ArrayList<Float> generate(int n) {
        return floatify(rawGenerate(n));
    }

}
